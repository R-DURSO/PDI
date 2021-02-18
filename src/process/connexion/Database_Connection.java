package process.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import data.DataForRecuperation;
import org.apache.log4j.Logger;

import logger.LoggerUtility;

/**
 * @author Raphaël D'URSO 
 *
 */
public class Database_Connection {
	private Connection connection;
	private static Logger logger = LoggerUtility.getLogger(Database_Connection.class, LoggerUtility.LOG_PREFERENCE);
	/**
	 * this function could be connect the java application on the database
	 * @param url  
	 * @param user
	 * @param password
	 * @param database
	 * @throws SQLException
	 */
	public Database_Connection(String url, String user, String password,String database) throws SQLException {
		if(database.contains(DataForRecuperation.DATABASE_POSRTGRESQL) ) {
		logger.info("Start connection to " + url);
		connection = DriverManager.getConnection("jdbc:postgresql://" + url, user, password);
		//if we are here, we are connected
		logger.info("Database connected !");
		}
		else {
			logger.info("Start connection to " + url);
			connection = DriverManager.getConnection("jdbc:mysql://" +url , user, password);
			//if we are here, we are connected
			logger.info("Database connected !");
		}
	}
	/**
	 * this function will be used for take some data from database 
	 * @param query
	 * @return a Resultet will be use for take information about woker or create stats with that 
	 * @throws SQLException
	 */
	public ResultSet Query(String query) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		return preparedStatement.executeQuery();
		
	}
}
