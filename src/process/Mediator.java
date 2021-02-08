package process;

import data.DataForRecuperation;
import logger.LoggerUtility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

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
	private String    whoRequest="";

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
		csv_fr = csv.SepareLine();
/*		for (List<String> csList : csv_fr) {
			System.out.println(csList.toString());
		}
		*/
		csv = new CsvRecuperation(DataForRecuperation.CSV_ALL);
		csv_ALL1= csv.SepareLine();
		csv = new CsvRecuperation(DataForRecuperation.CSV_ALL2);
		csv_ALL2 = csv.SepareLine();

	}
	
	/**
	 * will be change values of 
	 */
	public void ResultOfEntrepris() {
		try {
			resultSetMYSQL=dataBase_MySQL.SelectQuery(data.SQLQuery.ORDER_DO_MYSQL);
			resultSetPOSTGRESQL= dataBase_POSTGREY.SelectQuery(data.SQLQuery.ORDER_DO_POSRTGRESQL);
		} catch (SQLException e) {
			logger.error("error during  query : "+data.SQLQuery.ORDER_DO_MYSQL);
			
		}
		// il faut récuprer les données des csv 
	}
	
	/**
	 * this return is for printf the eecution
	 */
	public String getWhoRequest() {
		return whoRequest;
	}
	
	/**
	 * we clean the resultset and he  whoRequest 
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
