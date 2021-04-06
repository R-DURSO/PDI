package process;

/**
 * 
 * @author Laura FUSTINONI
 * @author Raphaël D'URSO
 * @author Aëlien MOUBECHE
 * use for transform the different data into a stats
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime; 


import org.apache.log4j.Logger;

import java.sql.Connection;
import data.CSV_Information;
import data.SQLQuery;
import logger.LoggerUtility;
import process.connexion.CsvRecuperation;
import process.connexion.Database_Connection;

public class StatBuilder {
	private static Logger logger = LoggerUtility.getLogger(StatBuilder.class, LoggerUtility.LOG_PREFERENCE);
	private Database_Connection dataBase_MySQL;
	private Database_Connection dataBase_POSTGRE;
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private LocalDateTime now;
	private String timestamp;
	
	
	public StatBuilder(Database_Connection db1, Database_Connection db2) {
		dataBase_MySQL = db1;
		dataBase_POSTGRE = db2;
		// TODO Auto-generated constructor stub
	}

	/*
	 * 
	 */
	public Integer numberOfEmployeesCSV(List<List<String>> information){
		Integer nbr = 0;
		
		for (List<String> list : information) {
			nbr ++;
			}
		
		return nbr;
	}

	public Integer numberOfEmployeesBD(String branch) throws SQLException{
		Integer nbr = 0;
		
		ResultSet count;
		
		if (branch.equals("Chn")) {
			// Get the result of Chinese query
			count = dataBase_MySQL.Query(SQLQuery.NUMBER_OF_EMPLOYEES_MYSQL);
			while (count.next()) {
				nbr = count.getInt("nbrempl");
			}
		} else {
			// Get the result of American query
			count = dataBase_POSTGRE.Query(SQLQuery.NUMBER_OF_EMPLOYEES_POSTGRESQL);
			while (count.next()) {
				nbr = count.getInt("nbrempl");
			}
		}
		
		return nbr;
	}
	
	/*

	 * Used to find the best salary from CSV data
	 */
 	public HashMap<String, Integer> monthEmployeeCSV(List<List<String>> information, List<List<String>> otherInformation, String typeCSV) {
		HashMap<String, Integer> bestNote = new HashMap<String, Integer>();
		String employeeName = "no employee";
		int noteBestEmployee = 0;
		int noteEmployee = 0;
		if (typeCSV.equals(CSV_Information.fR_CSV)) {
			for (List<String> employeeList : information) {
				try {
					noteEmployee = Integer.parseInt(employeeList.get(CSV_Information.ACHIEVEMENTS_FRANCE)) - Integer.parseInt(employeeList.get(CSV_Information.BLAME_FRANCE));
					if (noteBestEmployee < noteEmployee) {

						noteBestEmployee = noteEmployee;
						employeeName = employeeList.get(CSV_Information.FAMILY_NAME_FRANCE) + " " + employeeList.get(CSV_Information.FIRST_NAME_FRANCE);
					}
				} catch (Exception e) {
					logger.error("error during convertion of achievement and blame for best month employe FR");
				}
			}

		} else {
			for (List<String> employeListGER : otherInformation) {
				try {
					noteEmployee = Integer.parseInt(employeListGER.get(CSV_Information.ACHIEVEMENTS_GER))
							- Integer.parseInt(employeListGER.get(CSV_Information.BLAME_GER));
					if (noteBestEmployee < noteEmployee) {
						noteBestEmployee = noteEmployee;
						employeeName = employeListGER.get(CSV_Information.ID_GER);
					}
				} catch (Exception e) {
					logger.error("error during convertion of achievement and blame for best month employe GER");
				}
			}
			for (List<String> employe : information) {
				if ((employe.get(CSV_Information.ID_GER)).equals(employeeName)) {
					employeeName = employe.get(CSV_Information.FIRST_NAME_GER);
				}
			}
		}

		bestNote.put(employeeName, noteBestEmployee);
		return bestNote;
	}

	public HashMap<String, Integer> monthEmployeeBD(String branch) throws SQLException{
		HashMap<String, Integer> bestemployee = new HashMap<String, Integer>();
		ResultSet monthempl;
		
		Integer note_mthempl = 0;
		String name_mthempl = "default";
		
		
		if (branch.equals("Chn")) {
			// Get the result of Chinese query
			monthempl = dataBase_MySQL.Query(SQLQuery.MONTH_EMPLOYEE_MYSQL);
		} else {
			// Get the result of American query
			monthempl = dataBase_POSTGRE.Query(SQLQuery.MONTH_EMPLOYEE_POSTGRESQL);
		}
		while (monthempl.next()) {
			note_mthempl = monthempl.getInt("note");
			name_mthempl = monthempl.getString("name") + " " + monthempl.getString("f_name");
		}
		
		bestemployee.put(name_mthempl, note_mthempl);
		return bestemployee;
	}
	
	/**
	 * 
	 * @param information
	 * @param otherInformation
	 * @param typeCSV
	 * @return
	 */
	public HashMap<String, Integer> noteEmployeeCSV(List<List<String>> information, List<List<String>> otherInformation,
			String typeCSV) {
		HashMap<String, Integer> notes = new HashMap<String, Integer>();
		String employeeName = "no employe ";
		int noteEmployee = 0;
		if (typeCSV.equals(CSV_Information.fR_CSV)) {
			for (List<String> employeList : information) {
				try {
					noteEmployee = Integer.parseInt(employeList.get(CSV_Information.ACHIEVEMENTS_FRANCE))
							- Integer.parseInt(employeList.get(CSV_Information.BLAME_FRANCE));

					employeeName = employeList.get(CSV_Information.FAMILY_NAME_FRANCE) + " "
							+ employeList.get(CSV_Information.FIRST_NAME_FRANCE);

				} catch (Exception e) {
					logger.error("error during convertion of achievement and blame for best month employee FR ");
				}
			}
		} else {
			for (List<String> employeListGER : otherInformation) {
				try {
					noteEmployee = Integer.parseInt(employeListGER.get(CSV_Information.ACHIEVEMENTS_GER))
							- Integer.parseInt(employeListGER.get(CSV_Information.BLAME_GER));
					employeeName = employeListGER.get(CSV_Information.ID_GER);
					for (List<String> employe : information) {
						if ((employe.get(CSV_Information.ID_GER)).equals(employeeName)) {
							employeeName = employe.get(CSV_Information.FIRST_NAME_GER);
						}
					}
				} catch (Exception e) {
					logger.error("error during convert archivement and blame for best month employee GER ");
				}
			}
		}
		
		notes.put(employeeName, noteEmployee);
		return notes;
	}
	
	public HashMap<String, Integer> noteEmployeeBD(String branch) throws SQLException{
		HashMap<String, Integer> notes = new HashMap<String, Integer>();
		ResultSet resultnotes;
		
		if (branch.equals("Chn")) {
			// Get the result of Chinese query
			resultnotes = dataBase_MySQL.Query(SQLQuery.NOTE_EMPLOYEES_MYSQL);
		} else {
			// Get the result of American query
			resultnotes = dataBase_POSTGRE.Query(SQLQuery.NOTE_EMPLOYEES_POSTGRESQL);
		}
		while (resultnotes.next()) {
			int note = resultnotes.getInt("note");
			String name = resultnotes.getString("name") + " " + resultnotes.getString("f_name");
			 notes.put(name, note);
		}
		
		return notes;
	}
	
	/**
	 * 
	 * @param information
	 * @param typeCsv
	 * @return
	 */
	public Integer leaveUsageCSV(List<List<String>> information, String typeCsv) {
		int leaveUsageCount = 0;

		if (typeCsv.equals(CSV_Information.fR_CSV)) {
			for (List<String> list : information) {
				try {
					leaveUsageCount += Integer.parseInt(list.get(CSV_Information.LEAVE_FRANCE));
				} catch (Exception e) {
					logger.error("error during recuperation of French succursale leave usage");
				}

			}
		} else {
			for (List<String> list : information) {
				try {
					leaveUsageCount += Integer.parseInt(list.get(CSV_Information.LEAVE_GER));
				} catch (Exception e) {
					logger.error("error during recuperation of German succursale leave usage");
				}

			}
		}
		
		return leaveUsageCount;
	}

	public Integer leaveUsageBD(String branch) throws SQLException {
		int leaveUsageCount = 0;
		
		ResultSet leave;
		
		if (branch.equals("Chn")) {
			// Get the result of Chinese query
			leave = dataBase_MySQL.Query(SQLQuery.LEAVE_USAGE_MYSQL);
			while (leave.next()) {
				leaveUsageCount = leave.getInt("vacationusage");
			}
		} else {
			// Get the result of American query
			leave = dataBase_POSTGRE.Query(SQLQuery.LEAVE_USAGE_POSTGRESQL);
			while (leave.next()) {
				leaveUsageCount = leave.getInt("leaveusage");
			}
		}
		
		return leaveUsageCount;
	}
	
	/**
	 * returns total of achievements by branch
	 * 
	 * @param CSV  data
	 * @param type of CSV
	 * @return number of tasks done by branch
	 */
	public HashMap<String, Integer> taskDoneCSV(List<List<String>> information, String typeCsv) {
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		String department = "";
		int add;
		if (typeCsv.equals(CSV_Information.fR_CSV)) {
			result.put("totalFr", 0);
			for (List<String> list : information) {
				try {
					department = list.get(CSV_Information.DEPARTMENT_FRANCE);
					if (result.containsKey(department)) {
						add = result.get(department);
						result.put(department, add + Integer.parseInt(list.get(CSV_Information.ACHIEVEMENTS_FRANCE)));
					} else {
						result.put(department, Integer.parseInt(list.get(CSV_Information.ACHIEVEMENTS_FRANCE)));
					}

					add = result.get("totalFr");
					result.put("total" + typeCsv,
							add + Integer.parseInt(list.get(CSV_Information.ACHIEVEMENTS_FRANCE)));

				} catch (Exception e) {
					logger.error("error during recuperation of achievements's French succursale ");

				}
			}
		} else {
			result.put("totalGer", 0);
			for (List<String> list : information) {
				try {
					department = list.get(CSV_Information.DEPARTMENT_GER);
					if (result.containsKey(department)) {
						add = result.get(department);
						result.put(department, add + Integer.parseInt(list.get(CSV_Information.ACHIEVEMENTS_GER)));
					} else {
						result.put(department, Integer.parseInt(list.get(CSV_Information.ACHIEVEMENTS_GER)));
					}
					add = result.get("totalGer");
					result.put("total" + typeCsv, add + Integer.parseInt(list.get(CSV_Information.ACHIEVEMENTS_GER)));
				} catch (Exception e) {
					// logger.error("error during recuperation of achievements's German succursale
					// ");
					// System.out.println(e.toString());
				}
			}
		}
		return result;
	}

	public HashMap<String, Integer> taskDoneBD(String branch) throws SQLException {
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		ResultSet resulttasks;

		if (branch.equals("Chn")) {
			// Get the result of Chinese query
			resulttasks = dataBase_MySQL.Query(SQLQuery.TASKS_DONE_MYSQL);
		} else {
			// Get the result of American query
			resulttasks = dataBase_POSTGRE.Query(SQLQuery.TASKS_DONE_POSTGRESQL);
		}

		// Save achievements for total sum
		int sum_achievements = 0;

		// Browse the query result and get data
		while (resulttasks.next()) {
			int tmp_achv = resulttasks.getInt("achievements");
			String dep=resulttasks.getString("department");
			result.put(dep, tmp_achv);
			sum_achievements = sum_achievements + tmp_achv;
		}
		// Total
		result.put("total" + branch, sum_achievements);

		return result;
	}

	/**
	 * return fees by employee
	 * 
	 * @param CSV  data (1 or 2)
	 * @param type of CSV
	 * @return fees by employee
	 */
	public HashMap<String, Integer> feesEmployeesCSV(List<List<String>> information,
			List<List<String>> otherInformation, String typeCsv) {
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		String employeeID = "";
		String name = "";
		Integer fees = 0;

		if (typeCsv.equals(CSV_Information.fR_CSV)) {
			for (List<String> employefeesList : information) {
				try {
					name = employefeesList.get(CSV_Information.FAMILY_NAME_FRANCE) + " "
							+ employefeesList.get(CSV_Information.FIRST_NAME_FRANCE);
					result.put(name, Integer.parseInt(employefeesList.get(CSV_Information.FEES_FRANCE)));
				} catch (Exception e) {
					logger.error("Error during recuperation of employee fees for French succursale");
					// System.out.println(e.toString());
				}
			}
		} else {
			for (List<String> employeListGER : otherInformation) {
				try {
					employeeID = employeListGER.get(CSV_Information.ID_GER);
					fees = Integer.parseInt(employeListGER.get(CSV_Information.FEES_GER));
					for (List<String> employe : information) {
						if ((employe.get(CSV_Information.ID_GER)).equals(employeeID)) {
							name = employe.get(CSV_Information.FAMILY_NAME_GER) + " "
									+ employe.get(CSV_Information.FIRST_NAME_GER);
							result.put(name, fees);
						}

					}

				} catch (Exception e) {
					logger.error("error during convert archivement and blame for best Month employe ");
				}
			}

		}
		return result;
	}

	public HashMap<String, Integer> feesEmployeesBD(String branch) throws SQLException {
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		ResultSet resultfees;

		if (branch.equals("Chn")) {
			// Get the result of Chinese query
			resultfees = dataBase_MySQL.Query(SQLQuery.EXPENSIVE_EMPLOYEES_MYSQL);
		} else {
			// Get the result of American query
			resultfees = dataBase_POSTGRE.Query(SQLQuery.EXPENSIVE_EMPLOYEES_POSTGRESQL);
		}

		// Browse the query result and get data
		while (resultfees.next()) {
			result.put(resultfees.getString("employee_id"), resultfees.getInt("fees"));
		}

		return result;
	}

	/**
	 * 
	 */
	public HashMap<Integer, Integer> resultBySeniorityCSV (List<List<String>> information, List<List<String>> otherInformation, String typeCSV){
		HashMap<Integer, Integer> seniorityResults = new HashMap<Integer,Integer>();
		// HashMap key and value template variables
		Integer seniority;
		Integer results;
		
		String hiring_date;
		String employeeID;
		
		if (typeCSV.equals(CSV_Information.fR_CSV)) {
			for (List<String> list : information) {
				try {
					hiring_date = list.get(CSV_Information.HIRING_DATE_FRANCE);
					seniority = seniorityCalc(hiring_date);
					
					results = Integer.parseInt(list.get(CSV_Information.ACHIEVEMENTS_FRANCE));
					
					seniorityResults.put(seniority, seniorityResults.get(seniority) + results);
					
				} catch (Exception e) {
					logger.error("error during recuperation of French succursale's results by seniority");
				}
			}
		} else {
			for (List<String> list : information) {
				try {
					hiring_date = list.get(CSV_Information.HIRING_DATE_GER);
					seniority = seniorityCalc(hiring_date);
					
					employeeID = list.get(CSV_Information.ID_GER);
					
					
					for (List<String> employe : otherInformation) {
						if ((employe.get(CSV_Information.ID_GER)).equals(employeeID)) {
							results = Integer.parseInt(employe.get(CSV_Information.ACHIEVEMENTS_GER));
							seniorityResults.put(seniority, seniorityResults.get(seniority) + results);
						}

					}
					
					
				} catch (Exception e) {
					logger.error("error during recuperation of German succursale's results by seniority");
				}
			}
		}
		
		return seniorityResults;
	}
	
	public Integer seniorityCalc(String hiring_date) {
		Integer seniority;
		
		// current date year, month and day
		now = LocalDateTime.now();
		timestamp = dtf.format(now);
		String[] timestamparray = timestamp.split("\\s+");
		String date = timestamparray[0];
		String[] splitdate = date.split("-");
		Integer currentYear = Integer.parseInt(splitdate[0]);
		Integer currentMonth = Integer.parseInt(splitdate[1]);
		Integer currentDay = Integer.parseInt(splitdate[2]);
		
		String delim = "/";
		
		String[] split_hd;
		
		// hiring date year, month and day
		Integer hdYear;
		Integer hdMonth;
		Integer hdDay;
		
		// differences between hiring and current dates
		Integer diffYear;
		Integer diffMonth;
		Integer diffDay;
		
		Integer dayDiffCount;
		
		//------
		
		split_hd = hiring_date.split(delim);
		
		// French and German syntax : dd/MM/yyyy
		hdYear = Integer.parseInt(split_hd[2]);
		hdMonth = Integer.parseInt(split_hd[1]);
		hdDay = Integer.parseInt(split_hd[0]);
		
		diffYear = currentYear-hdYear;
		diffMonth = currentMonth-hdMonth;
		diffDay = currentDay-hdDay;
		
		dayDiffCount = diffYear*365 + diffMonth*30 + diffDay;
		
		seniority = dayDiffCount/365;
		
		return seniority;
	};
	
	public HashMap<Integer, Integer> resultBySeniorityBD(String branch) throws SQLException{
		HashMap<Integer, Integer> seniorities = new HashMap<Integer, Integer>();
		ResultSet resultseniority;
		
		if (branch.equals("Chn")) {
			// Get the result of Chinese query
			resultseniority = dataBase_MySQL.Query(SQLQuery.RESULT_BY_SENIORITY_MYSQL);
		} else {
			// Get the result of American query
			resultseniority = dataBase_POSTGRE.Query(SQLQuery.RESULT_BY_SENIORITY_POSTGRESQL);
		}

		// Browse the query result and get data
		while (resultseniority.next()) {
			seniorities.put(resultseniority.getInt("seniority"), resultseniority.getInt("results"));
		}
		
		
		return seniorities;
	}
	
	/**
	 * returns
	 */
 	public HashMap<String, Integer> contractTypesCSV(List<List<String>> information, String typeCSV) {
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		String contract = "";

		if (typeCSV.equals(CSV_Information.fR_CSV)) {
			for (List<String> list : information) {
				try {
					contract = list.get(CSV_Information.CONTRACT_FRANCE);
					result.put(contract, result.get(contract) + 1);
				} catch (Exception e) {
					logger.error("error during recuperation of French succursale's contract types");
				}
			}
		} else {
			for (List<String> list : information) {
				try {
					contract = list.get(CSV_Information.CONTRACT_GER);
					result.put(contract, result.get(contract) + 1);
				} catch (Exception e) {
					logger.error("error during recuperation of German succursale's contract types");
				}
			}
		}
		
		return result;
	}
	
	public HashMap<String, Integer> contractTypesBD(String branch) throws SQLException {
		HashMap<String, Integer> contracts = new HashMap<String, Integer>();
		
		ResultSet resultcontract;

		if (branch.equals("Chn")) {
			// Get the result of Chinese query
			resultcontract = dataBase_MySQL.Query(SQLQuery.TYPE_OF_CONTRACT_MYSQL);
		} else {
			// Get the result of American query
			resultcontract = dataBase_POSTGRE.Query(SQLQuery.TYPE_OF_CONTRACT_POSTGRESQL);
		}

		// Browse the query result and get data
		while (resultcontract.next()) {
			contracts.put(resultcontract.getString("contract"), resultcontract.getInt("contractnb"));
		}
		
		return contracts;
	}
}
