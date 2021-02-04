package process;

import data.DataForRecuperation;
import logger.LoggerUtility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import process.connexion.CsvRecuperation;
import process.connexion.Database_Connection;

public class Mediator {
	private static Logger logger = LoggerUtility.getLogger(Mediator.class, LoggerUtility.LOG_PREFERENCE);
	// we start the connection with the database
	private Database_Connection dataBase_MSQL;
	private Database_Connection dataBase_POSTGREY;
	private List<List<String>> csv_fr;
	private List<List<String>> csv_ALL1;
	private List<List<String>> csv_ALL2; // variable for
	private CsvRecuperation csv;

	public Mediator() {
		try {
			dataBase_MSQL = new Database_Connection(DataForRecuperation.DATABASE_URL_MYSQL,
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
		for(List<String> csList :csv_fr) {
			System.out.println(csList.toString());
		}
	}

	public ResultSet ValueMYSQL(String query) {
		try {
			return dataBase_MSQL.SelectQuery(query);
		} catch (SQLException e) {
			logger.info("the request couldn't do ");
			return null;
		}

	}
}
