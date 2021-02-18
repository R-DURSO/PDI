package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQLQuery {
	// string query of information's paie and static

	// MySQL BD
	public static String ORDER_DO_MYSQL = "SELECT achievements FROM Work";
	public static String AVERAGE_WAGES_MYSQL = "SELECT salary, soc_benefits, blame FROM Pay NATURAL JOIN Work";
	public static String FRIDAY_USING_MYSQL = "SELECT vacation FROM Work";
	public static String MONTH_EMPLOYE_MYSQL = "SELECT employee_id, achievements, blame FROM Work";
	public static String OVERPAID_EMPLOYE_MYSQL = "SELECT employee_id, salary, bonus, fees, tax FROM Pay";
	public static String FORMATION_UTILITY_MYSQL = "";
	public static String RESULT_BY_ANCIENTY_MYSQL = "SELECT achievements, hiring_date FROM Work NATURAL JOIN Contract";
	public static String TYPE_OF_CONTRACT_MYSQL = "SELECT contract FROM Contract";
	public static String COST_OF_EMPLOY_MYSQL = "";

	// PostGrey BD
	public static String ORDER_DO_POSRTGRESQL = "";
	public static String AVERAGE_WAGES_POSRTGRESQL = "";
	public static String FRIDAY_USING_POSRTGRESQL = "";
	public static String MONTH_EMPLOYE_POSRTGRESQL = "";
	public static String OVERPAID_EMPLOYE_POSRTGRESQL = "";
	public static String FORMATION_UTILITY_POSRTGRESQL = "";
	public static String RESULT_BY_ANCIENTY_POSRTGRESQL = "";
	public static String TYPE_OF_CONTRACT_POSRTGRESQL = "";
	public static String COST_OF_EMPLOY_POSRTGRESQL = "";
}