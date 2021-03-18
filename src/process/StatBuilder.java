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

import org.apache.log4j.Logger;

import data.CSV_Information;
import data.SQLQuery;
import logger.LoggerUtility;
import process.connexion.CsvRecuperation;
import process.connexion.Database_Connection;

public class StatBuilder {
	private static Logger logger = LoggerUtility.getLogger(StatBuilder.class, LoggerUtility.LOG_PREFERENCE);

	public StatBuilder() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * Use for find the best salary from CSV data
	 */
	public List<String> MonthEmployeCSV(List<List<String>> information, List<List<String>> otherInformation,
			String typeCSV) {
		List<String> bestNote = new ArrayList<String>();
		String employeName = "no employe ";
		int noteBestEmploye = 0;
		int noteEmploye = 0;
		if (typeCSV.equals(CSV_Information.fR_CSV)) {
			for (List<String> employeList : information) {
				try {
					noteEmploye = Integer.parseInt(employeList.get(CSV_Information.ACHIEVEMENTS_FRANCE))
							- Integer.parseInt(employeList.get(CSV_Information.BLAME_FRANCE));
					if (noteBestEmploye < noteEmploye) {

						noteBestEmploye = noteEmploye;
						employeName = employeList.get(CSV_Information.FAMILY_NAME_FRANCE) + " "
								+ employeList.get(CSV_Information.FIRST_NAME_FRANCE);
					}
				} catch (Exception e) {
					logger.error("error during convert archivement and blame for best Month employe ");
					// System.out.println(e.toString());
				}
			}
			bestNote.add("For french  succursale the best employe of month is" + " : " + employeName + " "
					+ noteBestEmploye);

		} else {
			for (List<String> employeListGER : otherInformation) {
				try {
					noteEmploye = Integer.parseInt(employeListGER.get(CSV_Information.ACHIEVEMENTS_GER))
							- Integer.parseInt(employeListGER.get(CSV_Information.BLAME_GER));
					if (noteBestEmploye < noteEmploye) {
						noteBestEmploye = noteEmploye;
						employeName = employeListGER.get(CSV_Information.ID_GER);

					}
				} catch (Exception e) {
					logger.error("error during convert archivement and blame for best Month employe ");
				}

			}
			for (List<String> employe : information) {
				if ((employe.get(CSV_Information.ID_GER)).equals(employeName)) {
					employeName = employe.get(CSV_Information.FIRST_NAME_GER);
				}

			}
			bestNote.add("For German  succursale the best employe of month is" + " : " + employeName + " "
					+ noteBestEmploye);

		}

		return bestNote;
	}

	public List<String> NoteEmployeCSV(List<List<String>> information, List<List<String>> otherInformation,
			String typeCSV) {
		List<String> note = new ArrayList<String>();
		String employeName = "no employe ";
		int noteEmploye = 0;
		if (typeCSV.equals(CSV_Information.fR_CSV)) {
			for (List<String> employeList : information) {
				try {
					noteEmploye = Integer.parseInt(employeList.get(CSV_Information.ACHIEVEMENTS_FRANCE))
							- Integer.parseInt(employeList.get(CSV_Information.BLAME_FRANCE));

					employeName = employeList.get(CSV_Information.FAMILY_NAME_FRANCE) + " "
							+ employeList.get(CSV_Information.FIRST_NAME_FRANCE);

				} catch (Exception e) {
					logger.error("error during convert archivement and blame for best Month employe ");
					// System.out.println(e.toString());
				}
				note.add("For french  succursale the  employe " + " : " + employeName + " have as note : "
						+ noteEmploye);
			}
		} else {
			for (List<String> employeListGER : otherInformation) {
				try {
					noteEmploye = Integer.parseInt(employeListGER.get(CSV_Information.ACHIEVEMENTS_GER))
							- Integer.parseInt(employeListGER.get(CSV_Information.BLAME_GER));
					employeName = employeListGER.get(CSV_Information.ID_GER);
					for (List<String> employe : information) {
						if ((employe.get(CSV_Information.ID_GER)).equals(employeName)) {
							employeName = employe.get(CSV_Information.FIRST_NAME_GER);
						}

					}

				} catch (Exception e) {
					logger.error("error during convert archivement and blame for best Month employe ");
				}
				note.add("For German  succursale the  employe " + " : " + employeName + " have as note : "
						+ noteEmploye);
			}

		}

		return note;
	}

	public List<String> freeDayCSV(List<List<String>> information, String typeCsv) {
		List<String> freeday = new ArrayList<String>();
		int numberOfFreeday = 0;

		if (typeCsv.equals(CSV_Information.fR_CSV)) {
			for (List<String> list : information) {
				try {
					numberOfFreeday += Integer.parseInt(list.get(CSV_Information.LEAVE_FRANCE));
				} catch (Exception e) {
					logger.error("error during recuperation of freeday's French succursale ");
					// System.out.println(e.toString());
				}

			}
			freeday.add("For French succursale  the number of freeday used is : " + numberOfFreeday);
		} else {
			for (List<String> list : information) {
				try {

					numberOfFreeday += Integer.parseInt(list.get(CSV_Information.LEAVE_GER));
				} catch (Exception e) {
					logger.error("error during recuperation of freeday's German succursale ");
					// System.out.println(e.toString());
				}

			}
			freeday.add("For German succursale  the number of freeday used is : " + numberOfFreeday);
		}
		return freeday;

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
			result.put("total" + typeCsv, 0);
			for (List<String> list : information) {
				try {
					department = list.get(CSV_Information.DEPARTMENT_FRANCE);
					if (result.containsKey(department)) {
						add = result.get(department);
						result.put(department, add + Integer.parseInt(list.get(CSV_Information.ACHIEVEMENTS_FRANCE)));
					} else {
						result.put(department, Integer.parseInt(list.get(CSV_Information.ACHIEVEMENTS_FRANCE)));
					}

					add = result.get("total" + typeCsv);
					result.put("total" + typeCsv,
							add + Integer.parseInt(list.get(CSV_Information.ACHIEVEMENTS_FRANCE)));

				} catch (Exception e) {
					logger.error("error during recuperation of achievements's French succursale ");

				}
			}
		} else {
			result.put("total" + typeCsv, 0);
			for (List<String> list : information) {
				try {
					department = list.get(CSV_Information.DEPARTMENT_GER);
					if (result.containsKey(department)) {
						add = result.get(department);
						result.put(department, add + Integer.parseInt(list.get(CSV_Information.ACHIEVEMENTS_GER)));
					} else {
						result.put(department, Integer.parseInt(list.get(CSV_Information.ACHIEVEMENTS_GER)));
					}
					add = result.get("total" + typeCsv);
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
			resulttasks = Database_Connection.Query(SQLQuery.TASKS_DONE_MYSQL);
		} else {
			// Get the result of American query
			resulttasks = Database_Connection.Query(SQLQuery.TASKS_DONE_POSTGRESQL);
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
		ResultSet resulttasks;

		if (branch.equals("Chn")) {
			// Get the result of Chinese query
			resulttasks = Database_Connection.Query(SQLQuery.EXPENSIVE_EMPLOYEES_MYSQL);
		} else {
			// Get the result of American query
			resulttasks = Database_Connection.Query(SQLQuery.EXPENSIVE_EMPLOYEES_POSTGRESQL);
		}

		// Browse the query result and get data
		while (resulttasks.next()) {
			result.put(resulttasks.getString("employee_id"), Integer.parseInt(resulttasks.getString("fees")));
		}

		return result;
	}

	/**
	 * returns
	 */
	public HashMap<String, Integer> contractTypeCSV(List<List<String>> information, String typeCsv) {
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		String contract = "";

		if (typeCsv.equals(CSV_Information.fR_CSV)) {
			for (List<String> list : information) {
				try {
					contract = list.get(CSV_Information.CONTRACT_FRANCE);
					result.put(contract, result.get(contract) + 1);
				} catch (Exception e) {
					logger.error("error during recuperation of achievements's French succursale ");
					// System.out.println(e.toString());
				}
			}
		} else {
			for (List<String> list : information) {
				try {
					contract = list.get(CSV_Information.CONTRACT_GER);
					result.put(contract, result.get(contract) + 1);
				} catch (Exception e) {
					logger.error("error during recuperation of achievements's German succursale ");
					// System.out.println(e.toString());
				}
			}
		}
		return result;
	}
}
