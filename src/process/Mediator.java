package process;

import data.BDData;
import logger.LoggerUtility;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import process.connexion.Database_Connection;

public class Mediator {
	private static Logger logger = LoggerUtility.getLogger(Mediator.class, LoggerUtility.LOG_PREFERENCE);
	// we start the connection with the database
	private Database_Connection dataBase_MSQL;
	private Database_Connection dataBase_POSTGREY;

	public Mediator() {
		try {
			dataBase_MSQL = new Database_Connection(BDData.DATABASE_URL_MYSQL, BDData.DATABASE_USER_MYSQL,
					BDData.DATABASE_PASSEWORD_MYSQL, BDData.DATABASE_MYSQL);
		} catch (SQLException e) {
			logger.error("Database connection on MYSQL is failed");
			
		}
		try {		
			dataBase_POSTGREY = new Database_Connection(BDData.DATABASE_URL_POSRTGRESQL, BDData.DATABASE_USER_POSRTGRESQL,
				BDData.DATABASE_PASSEWORD_POSRTGRESQL, BDData.DATABASE_POSRTGRESQL);
			
		} catch (SQLException e) {
			logger.error("Database connection on POSTGRESQL is failed");
			
		}
		;
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
