package process;

import data.CSV_Information;
import data.DataForRecuperation;
import data.MediatorResult;
import data.SQLQuery;
import logger.LoggerUtility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;

import process.connexion.CsvRecuperation;
import process.connexion.Database_Connection;

/**
 * @author Raphaël D'URSO
 * @author Aëlien MOUBECHE
 */
public class Mediator {
	private static Logger logger = LoggerUtility.getLogger(Mediator.class, LoggerUtility.LOG_PREFERENCE);
	// we start the connection with the database
	private Database_Connection dataBase_MySQL;
	private Database_Connection dataBase_POSTGRE;
	private List<List<String>> csv_fr;
	private List<List<String>> csv_ALL1;
	private List<List<String>> csv_ALL2; // variable for
	private CsvRecuperation csv;
	private ResultSet resultSetMYSQL;
	private ResultSet resultSetPOSTGRESQL;
	private MediatorResult result = new MediatorResult(null,"");
	private String whoRequest = "";
	private StatBuilder stat;

	/**
	 * Connect to the different sources of data
	 */
	public Mediator() {
		try {
			dataBase_MySQL = new Database_Connection(DataForRecuperation.DATABASE_URL_MYSQL,
					DataForRecuperation.DATABASE_USER_MYSQL, DataForRecuperation.DATABASE_PASSWORD_MYSQL,
					DataForRecuperation.DATABASE_MYSQL);
			
		} catch (SQLException e) {
			logger.error("Database connection on MYSQL is failed");
			System.out.println(e.toString());
		}
		System.out.println(dataBase_MySQL.getName());
		try {
			dataBase_POSTGRE = new Database_Connection(DataForRecuperation.DATABASE_URL_POSTGRESQL,
					DataForRecuperation.DATABASE_USER_POSTGRESQL, DataForRecuperation.DATABASE_PASSWORD_POSTGRESQL,
					DataForRecuperation.DATABASE_POSTGRESQL);

		} catch (SQLException e) {
			logger.error("Database connection on POSTGRESQL is failed");

		}
		System.out.println(dataBase_POSTGRE.getName());
		System.out.println(dataBase_MySQL.getName());
		
		stat = new StatBuilder(dataBase_MySQL, dataBase_POSTGRE);
		csv = new CsvRecuperation(DataForRecuperation.CSV_FR);
		csv_fr = csv.SepareLineFR();

		csv = new CsvRecuperation(DataForRecuperation.CSV_ALL);
		csv_ALL1 = csv.SepareLineGER();
		csv = new CsvRecuperation(DataForRecuperation.CSV_ALL2);
		csv_ALL2 = csv.SepareLineGER();
		// we supprime the first line is not a usable data
		csv_ALL1.remove(0);
		csv_ALL2.remove(0);
		csv_fr.remove(0);
	}

	/**
	 * Values will be changed
	 */
	public void ResultOfEnterprise() {

	}

	/**
	 * Used for telling how many leaves are taken branch by branch
	 */
	public void LeaveDay() {
		List<String> freeDayList = new ArrayList<String>();
		freeDayList.add("For the leaveday we addition the number of leaveday by all employe on all succursale :");
		freeDayList.addAll(stat.freeDayCSV(csv_ALL2, CSV_Information.GER_CSV));
		freeDayList.addAll(stat.freeDayCSV(csv_fr, CSV_Information.fR_CSV));
		// use for test will ne to clean after this

		System.out.println("\n \n");
		for (String test : freeDayList) {
			System.out.println(test);
		}

	}

	/**
	 * Used to get a List with the name and note of all salaries from the different branches
	 */
	public void SalaryNote() {
		List<String> noteList = new ArrayList<String>();
		noteList.add("For information the notes of all employees are calculated by substracting their blame score from their archievement score");
		noteList.addAll(stat.NoteEmployeCSV(csv_fr, null, CSV_Information.fR_CSV));
		noteList.addAll(stat.NoteEmployeCSV(csv_ALL1, csv_ALL2, CSV_Information.GER_CSV));
		// test need clean this after
		System.out.println("\n \n");
		for (String test : noteList) {
			System.out.println(test);
		}
	}

	/**
	 * Used for telling the salary of the month for each branch
	 */
	public void MonthSalary() {
		System.out.println("\n\n\n");
		List<String> noteList = new ArrayList<String>();
		noteList.add(
				"He have a best salary for all succursale  we calculate this with the subbition between archivement and blame ");
		noteList.addAll(stat.MonthEmployeCSV(csv_fr, null, CSV_Information.fR_CSV));
		noteList.addAll(stat.MonthEmployeCSV(csv_ALL1, csv_ALL2, CSV_Information.GER_CSV));
		// manque les méthode de cacule SQL
		for (String test : noteList) {
			System.out.println(test);
		}
	}
	
	/**
	 * Used for giving informations about total tasks done
	 * @return 
	 * @throws SQLException 
	 */
	public MediatorResult TasksDone() {
		HashMap<String, Integer> tasksDoneFr = stat.taskDoneCSV(csv_fr, CSV_Information.fR_CSV);
		HashMap<String, Integer> tasksDoneGer = stat.taskDoneCSV(csv_ALL2, CSV_Information.GER_CSV);
		HashMap<String, Integer> tasksDoneChn = null;
		HashMap<String, Integer> tasksDoneUsa = null;
		
		try {
			tasksDoneChn = stat.taskDoneBD("Chn");
			tasksDoneUsa = stat.taskDoneBD("Usa");
		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error("Could not get the achievements of Chinese or Usa succursale");
		}
		
		int max_achv = tasksDoneFr.get("total"+CSV_Information.fR_CSV);
		String best_succursale = "France";
		
		int achv_Ger = tasksDoneGer.get("total"+CSV_Information.GER_CSV);

		int achv_Chn = tasksDoneChn.get("totalChn");
		int achv_Usa = tasksDoneUsa.get("totalUsa");
		
		System.out.println(achv_Chn);
		System.out.println(achv_Usa);
		
		result.setPedagogie(best_succursale);
		result.getResult().put("FR",tasksDoneFr.get("totalFr"));
		result.getResult().put("GER",tasksDoneGer.get("totalGer"));
		result.getResult().put("CHN",tasksDoneChn.get("totalChn"));
		result.getResult().put("USA",tasksDoneUsa.get("totalUsa"));
		
		if (achv_Ger > max_achv) {
			max_achv = achv_Ger;
			best_succursale = "Germany";
			result.setPedagogie(best_succursale);
			result.setResult(tasksDoneGer);
		} else if (achv_Chn > max_achv) {
			max_achv = achv_Chn;
			best_succursale = "China";
			result.setPedagogie(best_succursale);
			result.setResult(tasksDoneGer);
		} else if (achv_Usa > max_achv) {
			max_achv = achv_Usa;
			best_succursale = "USA";
			result.setPedagogie(best_succursale);
			result.setResult(tasksDoneGer);
		}
		return result;
	}
	private void printf(String message) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Cleaning
	 */
	public void clean() {
		try {
			resultSetMYSQL.deleteRow();
			resultSetPOSTGRESQL.deleteRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
