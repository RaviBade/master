package vehicleStepDefinitions;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utilities.DirectoryUtility;
import utilities.PropertiesUtility;

public class FilesMetadataProvider {

	public static Properties prop;
	
	public static ArrayList<String> FilesList;
	public static DirectoryUtility listDirectory;
	
	public static File filePath;
	public static int fileCount;
	public static String fileName;
	public static String fileType;
	public static String fileMIMEType;
	public static long fileSize;

	public static Logger logger = LogManager.getLogger(FilesMetadataProvider.class);

	public void listFilesMetadata() {


		PropertiesUtility propUtility = new PropertiesUtility();
		prop = propUtility.getProperty();
		listDirectory = new DirectoryUtility();

//		Get the number of files from test data folder
		try {
			fileCount = listDirectory.getFilesCount(prop.getProperty("testDataFolder"));
			logger.info(" Scan test data folder for files ");
		} catch (Exception e) {
			e.printStackTrace();;
			logger.error(" Error while Scannig test data folder for files  ");
		}

		if(fileCount > 0) {
			logger.info(" Files Metadata: ");
			logger.info("*****************");
		}else {
			logger.error(" No files found in the test data folder. ");
		}
//		Get the number of metadata attributes from properties file
		int metadataCount=0;
		try {
			metadataCount = Integer.parseInt(prop.getProperty("metadataCount"));
		} catch (Exception e) {
			e.printStackTrace();;
		}

		String [][] strFileDetails =  new String[fileCount][metadataCount];
	
//		Get the list of files from test data folder
		try {
			FilesList = listDirectory.getFilesList(prop.getProperty("testDataFolder"));
		} catch (Exception e) {
			e.printStackTrace();;
			logger.error(" Error while getting the list of files. ");
		}
		
		int rowCounter=0;
		
		for(String fileName : FilesList) {

			strFileDetails[rowCounter][0] = fileName;
			rowCounter++;

		}
		
//		Populate files metadata 
		for(int i=0; i<strFileDetails.length; i++) {
			
			filePath = new File(prop.getProperty("testDataFolder")+"/"+strFileDetails[i][0].toString());
			
			fileType = listDirectory.getFileExtension(filePath);
			strFileDetails[i][1]= fileType;
			
			fileSize = listDirectory.getFileSize(filePath);
			strFileDetails[i][2]= Double.toString(fileSize)+ " bytes";
			
			fileMIMEType = listDirectory.getFileMIMEType(filePath);
			strFileDetails[i][3]= fileMIMEType;
				
		}

//		Print files metadata
		for(int i=0; i< fileCount; i++) {
			logger.info(" File Name - "+strFileDetails[i][0]+", File Type - "+strFileDetails[i][1]+", File Size - "+strFileDetails[i][2] + ", and File MIME Type - "+strFileDetails[i][3]);

		}
		
	}


}
