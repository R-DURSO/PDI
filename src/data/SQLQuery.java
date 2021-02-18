package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQLQuery {
	// string query of information's paie and static

	// MySQL BD
	public static String TASKS_DONE_MYSQL = "SELECT achievements FROM Work";
	public static String WAGES_INFORMATIONS_MYSQL = "SELECT salary, soc_benefits, bonus FROM Pay";
	public static String LEAVE_USAGE_MYSQL = "SELECT vacation FROM Work";
	public static String MONTH_EMPLOYEE_MYSQL = "SELECT name, f_name, MAX(achievements-blame) AS MaxPerf FROM Employee NATURAL JOIN Work";
	public static String EXPENSIVE_EMPLOYEES_MYSQL = "SELECT employee_id, fees FROM Pay ORDER BY fees DESC LIMIT 5";
	public static String FORMATION_UTILITY_MYSQL = "";
	public static String RESULT_BY_SENIORITY_MYSQL = "SELECT (DATEDIFF(CURDATE(),hiring_date)/365) AS seniority, achievements FROM Contract NATURAL JOIN Work GROUP BY seniority ORDER BY seniority";
	public static String TYPE_OF_CONTRACT_MYSQL = "SELECT COUNT(DISTINCT(contract)) FROM Contract";
	public static String COST_OF_EMPLOYMENT_MYSQL = "";

	// PostGrey BD
	public static String TASKS_DONE_POSTGRESQL = "SELECT achievements,department FROM Work NATURAL JOIN Branch GROUP BY department";
	public static String WAGES_INFORMATIONS_POSTGRESQL = "SELECT salary, soc_benefits, bonus FROM Payment";
	public static String LEAVE_USAGE_POSTGRESQL = "SELECT (COUNT(leave)*AVG(leave)) From Payment";
	public static String MONTH_EMPLOYEE_POSTGRESQL = "SELECT name, f_name, MAX(achievements-blame) AS MaxPerf From Employee_PI NATURAL JOIN Performances";
	public static String EXPENSIVE_EMPLOYEES_POSTGRESQL = "SELECT employee_id, fees, FROM Payment ORDER BY fees DESC LIMIT 5";
	public static String FORMATION_UTILITY_POSTGRESQL = "";
	public static String RESULT_BY_SENIORITY_POSTGRESQL = "SELECT ((CURRENT_DATE - hiring_date)/365) AS seniority, achievements FROM Contract NATURAL JOIN Performances GROUP BY seniority ORDER BY seniority";
	public static String TYPE_OF_CONTRACT_POSTGRESQL = "SELECT COUNT(DISTINCT contract), contract FROM Contract GROUP BY contract";
	public static String COST_OF_EMPLOYMENT_POSTGRESQL = "";
}
