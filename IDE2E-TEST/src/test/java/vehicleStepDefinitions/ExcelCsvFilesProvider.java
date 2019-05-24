package vehicleStepDefinitions;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

import utilities.DirectoryUtility;
import utilities.PropertiesUtility;

public class ExcelCsvFilesProvider {

	public static Properties prop;
	public static DirectoryUtility listDirectory;
	public static ArrayList<String> filesList;
	public static ArrayList<String> excelFilesList;
	public static ArrayList<String> csvFilesList;


	public ArrayList<String> getListOfExcelFiles() {
		
		PropertiesUtility propUtility = new PropertiesUtility();
		prop = propUtility.getProperty();
		listDirectory = new DirectoryUtility();
		excelFilesList = new ArrayList<String>();
		

//		Get Files list from test data folder
		try {
			filesList = listDirectory.getFilesList(prop.getProperty("testDataFolder"));
		} catch (Exception e) {
			e.printStackTrace();;
		}
		
//		Iterate Files list for excel files
		for(String fileName : filesList) {

			File filePath = new File(prop.getProperty("testDataFolder")+"/"+fileName);
			boolean excelFile = false; 
			
			if(listDirectory.getFileExtension(filePath).equals(prop.getProperty("excelFormat"))){
				excelFile = true;
			}

//			Print only Excel files			
			try {
				if(excelFile) {
					excelFilesList.add(fileName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		return excelFilesList;
	}

	
	public ArrayList<String> getListOfCsvFiles() {
		
		PropertiesUtility propUtility = new PropertiesUtility();
		prop = propUtility.getProperty();
		listDirectory = new DirectoryUtility();
		csvFilesList = new ArrayList<String>();

//		Get Files list from test data folder
		try {
			filesList = listDirectory.getFilesList(prop.getProperty("testDataFolder"));
		} catch (Exception e) {
			e.printStackTrace();;
		}
		
//		Iterate Files list for CSV files
		for(String fileName : filesList) {

			File filePath = new File(prop.getProperty("testDataFolder")+"/"+fileName);
			boolean csvFile = false;
			
			if(listDirectory.getFileExtension(filePath).equals(prop.getProperty("csvFormat"))){
				csvFile = true;
			}

//			Print only CSV files			
			if(csvFile) {
				csvFilesList.add(fileName);
			}

		}
		return csvFilesList;
	}
	
}
