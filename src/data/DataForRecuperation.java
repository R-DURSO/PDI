package data;

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
	public static String CSV_ALL ="";
	public static String CSV_ALL2 ="";
}