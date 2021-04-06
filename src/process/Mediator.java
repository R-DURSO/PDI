package process;

import data.CSV_Information;
import data.DataForRecuperation;
import data.DataforCircularGraphic;
import data.MediatorResult;
import data.Pedagogy;
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
 * This class contains the heterogeneous data mediator
 * 
 * @author Kevin BERNARD, Raphael D'URSO, Laura FUSTINONI, Aelien MOUBECHE
 * @version
 *
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
	private MediatorResult result = new MediatorResult("");
	private String whoRequest = "";
	private StatBuilder stat;

	/**
	 * Constructor.
	 * This method allows to connect to the different sources of data
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
	 * This method is used for telling how many leaves are taken branch by branch
	 * 
	 * @return the result in a MediatorResult
	 */
	public MediatorResult leaveUsage() {
		
		Integer leaveUsageGer = stat.leaveUsageCSV(csv_ALL2, CSV_Information.GER_CSV);
		Integer leaveUsageFr = stat.leaveUsageCSV(csv_fr, CSV_Information.fR_CSV);
		Integer leaveUsageUsa = 0;
		Integer leaveUsageChn = 0;
		
		Integer nbrEmplGer = stat.numberOfEmployeesCSV(csv_ALL1);
		Integer nbrEmplFr = stat.numberOfEmployeesCSV(csv_fr);
		Integer nbrEmplUsa = 0;
		Integer nbrEmplChn = 0;
		
		try {
			leaveUsageUsa = stat.numberOfEmployeesBD("Usa");
			leaveUsageChn = stat.numberOfEmployeesBD("Chn");
		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error("Could not get the leave usage of Chinese or Usa succursale");
		}
		
		try {
			nbrEmplUsa = stat.leaveUsageBD("Usa");
			nbrEmplChn = stat.leaveUsageBD("Chn");
		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error("Could not get the total number of employees for Chinese or Usa succursale");
		}
		
		Integer sumLeaveUsage = leaveUsageGer + leaveUsageFr + leaveUsageUsa + leaveUsageChn;
		Integer sumEmployees = nbrEmplGer + nbrEmplFr + nbrEmplUsa + nbrEmplChn;
		
		float averageLeaveUsage = sumLeaveUsage / sumEmployees;
		
		float averageLUGer = leaveUsageGer / nbrEmplGer;
		float averageLUFr = leaveUsageFr / nbrEmplFr;
		float averageLUUsa = leaveUsageUsa / nbrEmplUsa;
		float averageLUChn = leaveUsageChn / nbrEmplChn;
		
		
		float highest_avg = averageLUFr;
		String laziest_succursale = "France";
		
		if (averageLUGer > highest_avg) {
			highest_avg =averageLUGer;
			laziest_succursale = "Germany";
					
		} else if (averageLUChn > highest_avg) {
			highest_avg =averageLUChn;
			laziest_succursale = "China";
			
		} else if (averageLUUsa > highest_avg) {
			highest_avg =averageLUUsa;
			laziest_succursale = "USA";
		}
		
		result.setInformation(laziest_succursale+" succursale is the succursale with the most leave usage out of the 4 with an average of "+ highest_avg +" on a total of "+ averageLeaveUsage);
		result.setPedagogie(Pedagogy.statLeaveUsage);
		
		
		return result;
		
	}
	
	/**
	 * This method is used to get a List with the name and note of all salaries from the different branches
	 * 
	 * @return the result in a MediatorResult
	 */
	public MediatorResult SalaryNote() {
		
		List<String> noteList = new ArrayList<String>();
		HashMap<String, Integer> notesFr = stat.noteEmployeeCSV(csv_fr, null, CSV_Information.fR_CSV);
		HashMap<String, Integer> notesGer = stat.noteEmployeeCSV(csv_ALL1, csv_ALL2, CSV_Information.GER_CSV);
		HashMap<String, Integer> notesUsa = null;
		HashMap<String, Integer> notesChn = null;
		
		HashMap<String, Integer> notes = new HashMap<String, Integer>();
		
		try {
			notesChn = stat.noteEmployeeBD("Chn");
			notesUsa = stat.noteEmployeeBD("Usa");
			
			for (String key: notesChn.keySet()) {
				notes.put(key, notesChn.get(key));
				noteList.add(key+": "+notesChn.get(key)+"\n");
			}
			
			for (String key: notesUsa.keySet()) {
				notes.put(key, notesUsa.get(key));
				noteList.add(key+": "+notesUsa.get(key)+"\n");
			}
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error("Could not get the notes for Chinese of USA succursale");
		}
		
		for (String key: notesFr.keySet()) {
			notes.put(key, notesFr.get(key));
			noteList.add(key+": "+notesFr.get(key)+"\n");
		}
		
		for (String key: notesGer.keySet()) {
			noteList.add(key+": "+notesGer.get(key)+"\n");
		}
		result.setResult(noteList);
		result.setPedagogie(Pedagogy.statSalaryNote); 
		return result;
		
	}

	/**
	 * This method is used for telling the employee of the month for each branch and global
	 */
	public void MonthSalary() {
		
		HashMap<String, Integer> mthemplFr = stat.monthEmployeeCSV(csv_fr, null, CSV_Information.fR_CSV);
		HashMap<String, Integer> mthemplGer = stat.monthEmployeeCSV(csv_ALL1, csv_ALL2, CSV_Information.GER_CSV);
		HashMap<String, Integer> mthemplUsa = null;
		HashMap<String, Integer> mthemplChn = null;
		
		try {
			mthemplChn = stat.monthEmployeeBD("Chn");
			mthemplUsa = stat.monthEmployeeBD("Usa");
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error("Could not get the notes for Chinese of USA succursale");
		}
		
		Integer noteBest = 0 ;

		HashMap<String, Integer> best = mthemplFr;
		for (String key: mthemplFr.keySet()) {
			noteBest = mthemplFr.get(key);
		}
		String best_branch = "France";
		
		for (String key: mthemplGer.keySet()) {
			if (noteBest < mthemplGer.get(key)) {
				
			}
		}
		
	}
	
	/**
	 * This method is used for giving information about total tasks done
	 * 
	 * @return the information in a MediatorResult
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
		
		result.setPedagogie(Pedagogy.statTasksDones);
		result.setInformation("FR"+ " succursale is the succursale with the most achievements : "+String.valueOf(max_achv));
		List<DataforCircularGraphic> graphics = new ArrayList<DataforCircularGraphic>();
		graphics.add(new DataforCircularGraphic(tasksDoneFr.get("totalFr"),"FR"));
		graphics.add(new DataforCircularGraphic(tasksDoneGer.get("totalGer"),"GER"));
		graphics.add(new DataforCircularGraphic(tasksDoneChn.get("totalChn"),"CHN"));
		graphics.add(new DataforCircularGraphic(tasksDoneUsa.get("totalUsa"),"USA"));
		result.setCicularGraphic(graphics);
		result.setGraphicTitle("Tasks done per Succursale");
		
		if (achv_Ger > max_achv) {
			max_achv = achv_Ger;
			best_succursale = "Germany";
			result.setInformation(best_succursale+ " succursale is the succursale with the most achievements : "+String.valueOf(max_achv));
		} else if (achv_Chn > max_achv) {
			max_achv = achv_Chn;
			best_succursale = "China";
			result.setInformation(best_succursale+ " succursale is the succursale with the most achievements : "+String.valueOf(max_achv));
		} else if (achv_Usa > max_achv) {
			max_achv = achv_Usa;
			best_succursale = "USA";
			result.setInformation(best_succursale+ " succursale is the succursale with the most achievements : "+String.valueOf(max_achv));
		}
		return result;
		
	}
	
	/**
	 * This method is used to print a message on the std out
	 * 
	 * @param message : the message to print in a String
	 */
	private void printf(String message) {
		
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method is used for cleaning the resultSet of MySQL and PostgreSQL databases
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
