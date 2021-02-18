package process;

import data.CSV_Information;
import data.DataForRecuperation;
import data.SQLQuery;
import logger.LoggerUtility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;

import process.connexion.CsvRecuperation;
import process.connexion.Database_Connection;

/**
 * @author Raphaël D'URSO
 *
 */
public class Mediator {
	private static Logger logger = LoggerUtility.getLogger(Mediator.class, LoggerUtility.LOG_PREFERENCE);
	// we start the connection with the database
	private Database_Connection dataBase_MySQL;
	private Database_Connection dataBase_POSTGREY;
	private List<List<String>> csv_fr;
	private List<List<String>> csv_ALL1;
	private List<List<String>> csv_ALL2; // variable for
	private CsvRecuperation csv;
	private ResultSet resultSetMYSQL;
	private ResultSet resultSetPOSTGRESQL;
	private String whoRequest = "";
	private StatBuilder stat = new StatBuilder();

	/**
	 * Connect to the different loctalisation of data
	 */
	public Mediator() {
		try {
			dataBase_MySQL = new Database_Connection(DataForRecuperation.DATABASE_URL_MYSQL,
					DataForRecuperation.DATABASE_USER_MYSQL, DataForRecuperation.DATABASE_PASSEWORD_MYSQL,
					DataForRecuperation.DATABASE_MYSQL);
		} catch (SQLException e) {
			logger.error("Database connection on MYSQL is failed");
			System.out.println(e.toString());
		}
		try {
			dataBase_POSTGREY = new Database_Connection(DataForRecuperation.DATABASE_URL_POSRTGRESQL,
					DataForRecuperation.DATABASE_USER_POSRTGRESQL, DataForRecuperation.DATABASE_PASSEWORD_POSRTGRESQL,
					DataForRecuperation.DATABASE_POSRTGRESQL);

		} catch (SQLException e) {
			logger.error("Database connection on POSTGRESQL is failed");

		}
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
	 * will be change values of
	 */
	public void ResultOfEntrepris() {

	}

	/**
	 * Use for saying how many freeday is taken succursale per succursale
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
	 * Use for have a List with name and note of all salary for different succursale
	 */
	public void SalaryNote() {
		List<String> noteList = new ArrayList<String>();
		noteList.add("For saying the note of all employe we  sub the archivmement with the blame");
		noteList.addAll(stat.NoteEmployeCSV(csv_fr, null, CSV_Information.fR_CSV));
		noteList.addAll(stat.NoteEmployeCSV(csv_ALL1, csv_ALL2, CSV_Information.GER_CSV));
		// test need clean this after
		System.out.println("\n \n");
		for (String test : noteList) {
			System.out.println(test);
		}
	}

	/**
	 * Use for said the best month salary from all succurale
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
	 * we clean the resultset and he whoRequest
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
