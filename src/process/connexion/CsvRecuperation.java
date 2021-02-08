package process.connexion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import logger.LoggerUtility;
/**
 * 
 * @author Raphaël D'URSO
 *
 */
public class CsvRecuperation {
	private static Logger logger = LoggerUtility.getLogger(CsvRecuperation.class, LoggerUtility.LOG_PREFERENCE);
	private List<String> lines = new ArrayList<String>();

	public CsvRecuperation(String PathName) {
		try {
			/**
			 * we create a Path for 
			 */
			File csv = new File(PathName);
			
			if (csv.exists()) {
				logger.info(csv +" Find ");
				FileReader rdcsv = new FileReader(csv);
				BufferedReader brcsv = new BufferedReader(rdcsv);
				for (String line = brcsv.readLine(); line != null; line = brcsv.readLine()) {
					lines.add(line);
				}
				logger.info(csv  + "  data collected");
				brcsv.close();
				rdcsv.close();
			}else {
				logger.info(csv.getAbsolutePath()+": impossible to read file");
			}

		} catch (NullPointerException e) {
			logger.error(PathName + " File not found");
		} catch (IOException e) {
			logger.error("File not read ");
		}
	}

	/**
	 * this method take the lines and separe all line and , for have information
	 * ready to use
	 * 
	 * @return a list of information about employe on this csv
	 */
	public List<List<String>> SepareLine() {
		List<List<String>> employeList = new ArrayList<List<String>>();
		
		for (int i = 0; i < lines.size(); i++) {
			String[] info = lines.get(i).split(";");
			List<String> employe = new ArrayList<String>();
			for (int j = 0; j < info.length; j++) {
				try {
					employe.add(info[j]);
				} catch (NullPointerException e) {
					logger.error("Error during recuperation data on " + lines.toString());

				}
			}
			employeList.add(employe);
		}
			//System.out.println(employe.toString());
		return employeList;
	}

}
