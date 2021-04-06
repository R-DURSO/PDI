package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * This class gives all queries used for pay process and statistics generation
 * 
 * @author Kevin BERNARD, Raphael D'URSO, Laura FUSTINONI, Aelien MOUBECHE
 * @version
 *
 */
public class SQLQuery {
	
	// string query of information's paie and static

	// MySQL BD
	public static String NUMBER_OF_EMPLOYEES_MYSQL = "SELECT COUNT(employee_id) AS nbrempl FROM employee";
	public static String TASKS_DONE_MYSQL = "SELECT department, achievements FROM work NATURAL JOIN company GROUP BY department";
	public static String NOTE_EMPLOYEES_MYSQL = "SELECT name, f_name, (achievements-blame) AS note FROM employee NATURAL JOIN work";
	public static String WAGES_INFORMATIONS_MYSQL = "SELECT salary, soc_benefits, bonus FROM pay";
	public static String LEAVE_USAGE_MYSQL = "SELECT SUM(vacation) AS vacationusage FROM work";
	public static String MONTH_EMPLOYEE_MYSQL = "SELECT name, f_name, MAX(achievements-blame) AS maxperf FROM employee NATURAL JOIN work";
	public static String EXPENSIVE_EMPLOYEES_MYSQL = "SELECT employee_id, fees FROM pay ORDER BY fees DESC LIMIT 5";
	public static String FORMATION_UTILITY_MYSQL = "";
	public static String RESULT_BY_SENIORITY_MYSQL = "SELECT (DATEDIFF(CURDATE(),hiring_date)/365) AS seniority, AVG(achievements) FROM contract NATURAL JOIN work GROUP BY seniority ORDER BY seniority";
	public static String TYPE_OF_CONTRACT_MYSQL = "SELECT contract, COUNT(contract) AS contractnb FROM contract GROUP BY contract";
	public static String COST_OF_EMPLOYMENT_MYSQL = "";

	// Postgre BD
	public static String NUMBER_OF_EMPLOYEES_POSTGRESQL = "SELECT COUNT(employee_id) AS nbrempl FROM Employee_PI";
	public static String TASKS_DONE_POSTGRESQL = "SELECT department, SUM(achievements) AS achievements FROM Performances NATURAL JOIN Branch GROUP BY department";
	public static String NOTE_EMPLOYEES_POSTGRESQL = "SELECT name, f_name, (achievements-blames) AS note FROM Employee_PI NATURAL JOIN Performances";
	public static String WAGES_INFORMATIONS_POSTGRESQL = "SELECT salary, soc_benefits, bonus FROM Payment";
	public static String LEAVE_USAGE_POSTGRESQL = "SELECT SUM(leave) AS leaveusage From Payment";
	public static String MONTH_EMPLOYEE_POSTGRESQL = "SELECT name, f_name, MAX(achievements-blame) AS MaxPerf From Employee_PI NATURAL JOIN Performances";
	public static String EXPENSIVE_EMPLOYEES_POSTGRESQL = "SELECT employee_id, fees, FROM Payment ORDER BY fees DESC LIMIT 5";
	public static String FORMATION_UTILITY_POSTGRESQL = "";
	public static String RESULT_BY_SENIORITY_POSTGRESQL = "SELECT ((CURRENT_DATE - hiring_date)/365) AS seniority, AVG(achievements) FROM Contract NATURAL JOIN Performances GROUP BY seniority ORDER BY seniority";
	public static String TYPE_OF_CONTRACT_POSTGRESQL = "SELECT contract, COUNT(contract) AS contractnb FROM Contract GROUP BY contract";
	public static String COST_OF_EMPLOYMENT_POSTGRESQL = "";
	
}
