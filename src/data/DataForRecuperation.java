package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataForRecuperation {

	// static variable for connection on mysql database
	public static String DATABASE_URL_MYSQL = "mysql-globalresourcehuman.alwaysdata.net:3306/globalresourcehuman_data_ch";
	public static String DATABASE_USER_MYSQL = "225252_admin";
	public static String DATABASE_PASSEWORD_MYSQL = "ResourceHuman123456*";
	public static String DATABASE_MYSQL = "MYSQL";

	// for PosgreSQL
	public static String DATABASE_URL_POSRTGRESQL = "postgresql-globalresourcehuman.alwaysdata.net:5432/globalresourcehuman_data_us";
	public static String DATABASE_USER_POSRTGRESQL = "globalresourcehuman_admin";
	public static String DATABASE_PASSEWORD_POSRTGRESQL = "ResourceHuman123456*";
	public static String DATABASE_POSRTGRESQL = "POSTGRESQL";
	
	// for csv file
	public static String CSV_FR ="src/CSV/france.csv";
	public static String CSV_ALL ="src/CSV/germany_1.csv";
	public static String CSV_ALL2 ="src/CSV/germany_2.csv";
}

/*
String ribccreq = " SELECT rib FROM CompteCourant WHERE idUtilisateur = ?" ;
PreparedStatement ribreq =  conn.prepareStatement(ribccreq,ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY,ResultSet.HOLD_CURSORS_OVER_COMMIT);
ribreq.setInt(1, iduser);
ResultSet rescc = ribreq.executeQuery();

*/