package vehicleStepDefinitions;

import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import dvlaPageObjects.GetVehicleInformationPage;
import dvlaPageObjects.VehicleConfirmationPage;
import dvlaPageObjects.VehicleValidationPage;
import utilities.BrowserUtility;
import utilities.CaptureScreenshot;
import utilities.ExcelUtility;
import utilities.PropertiesUtility;

public class StepDefinitions {
	
	public static  WebDriver driver;
	public static Properties prop;
	public static int countOfExcelFiles;
	public static int countOfCsvFiles;
	public static ArrayList<String> excelFilesList;
	public static ArrayList<String> csvFilesList;

	public static Logger logger = LogManager.getLogger(StepDefinitions.class);
	

	@Given("Scan and list the configured directory for files")
	public void scan_and_list_the_configured_directory_for_files() {
		FilesMetadataProvider filesMetadata = new FilesMetadataProvider();
		filesMetadata.listFilesMetadata();
	}

	@Then("List excel and csv files only")
	public void list_excel_and_csv_files_only() {

		excelFilesList = new ArrayList<String>();
		csvFilesList = new ArrayList<String>();

		ExcelCsvFilesProvider excelCsvFilesProvider = new ExcelCsvFilesProvider(); 

		excelFilesList = excelCsvFilesProvider.getListOfExcelFiles();
		csvFilesList = excelCsvFilesProvider.getListOfCsvFiles();

		if(excelFilesList.isEmpty()==false) {
			logger.info("\n ");
			logger.info("List of Excel files: ");
			logger.info("*********************");
			for(String excelFileName : excelFilesList) {
				logger.info(excelFileName);
				
			}
		}
		

		if(csvFilesList.isEmpty()==false) {
			logger.info("\n ");
			logger.info("List of CSV files: ");
			logger.info("*********************");

			for(String csvFileName : csvFilesList) {
				logger.info(csvFileName);
				
			}
		}

	}

	@Given("Open vehicle information page from dvla")
	public void open_vehicle_information_page_from_dvla() {

		PropertiesUtility propUtility = new PropertiesUtility();
		prop = propUtility.getProperty();
		
//		Get the Web driver object from Browser Utility
		try {
			driver = BrowserUtility.getBrowser(prop.getProperty("browserName"), prop.getProperty("baseURL"));
			logger.info("Opening the browser");
		} catch (Exception e) {
			logger.error("Error while opening the browser");
			e.printStackTrace();;
		}


		try {
			new GetVehicleInformationPage(driver);
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			
//			Thread.sleep(2000);
			CaptureScreenshot.CaptureScreenshotUtility(driver, "GetVehicleInformationPage");
		} catch (Exception e) {
			e.printStackTrace();
		}

		driver.quit();;
	}

	
	@Then("Read file and validate vehicle information")
	public void read_file_and_validate_vehicle_information() {

		ExcelUtility excelUtility = null;
		GetVehicleInformationPage vehicleInformationPage = null;
		VehicleConfirmationPage vehicleConfirmationPage = null;
		VehicleValidationPage vehicleValidationPage = null;

		String excelFilePath = "";
		String vehicleRegNum = "";
		String expectedVehicleMake = "";
		String expectedVehicleColour = "";
		String actualVehicleMake = "";
		String actualVehicleColour = "";
		
		int rowCount=0;
		int colCount=0;

		PropertiesUtility propUtility = new PropertiesUtility();
		prop = propUtility.getProperty();
		
		String excelFileName = prop.getProperty("excelFile");

		try {
			excelFilePath = prop.getProperty("testDataFolder")+"/"+excelFileName;
		} catch (Exception e1) {
			e1.printStackTrace();
		}


		try {
			excelUtility = new ExcelUtility(excelFilePath,"testdata");
			logger.info(" Reading Excel file data ");
		} catch (Exception e) {
			logger.error(" Error while reading Excel file data ");
			e.printStackTrace();
		}

		rowCount = excelUtility.getRowCount();
		colCount = excelUtility.getColCount();

		for(int i =1; i<rowCount; i++) {
			for(int j=0; j<colCount; j++) {
				if(j==0) {
					vehicleRegNum = excelUtility.getCellDataString(i, j);
				}
				if(j==1) {
					expectedVehicleMake = excelUtility.getCellDataString(i, j);
				}
				if(j==2) {
					expectedVehicleColour = excelUtility.getCellDataString(i, j);
				}

			}
//			Get the Web driver object from Browser Utility
			try {
				driver = BrowserUtility.getBrowser(prop.getProperty("browserName"), prop.getProperty("baseURL"));
				logger.info("Opening the browser ");
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				CaptureScreenshot.CaptureScreenshotUtility(driver, "GetVehicleInformationPage");
			} catch (Exception e) {
				logger.error(" Error while Opening the browser ");
				e.printStackTrace();
			}


			try {
				vehicleInformationPage = new GetVehicleInformationPage(driver);
				vehicleInformationPage.NavigateToVehicleConfirmationPage();
				logger.info(" Navigating to vehicle confirmation page ");

			} catch (Exception e) {
				logger.error(" Error while navigating to vehicle confirmation page ");
				e.printStackTrace();
			}
			
			try {
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				vehicleConfirmationPage = new VehicleConfirmationPage(driver);
				vehicleConfirmationPage.enterVehicleRegistrationNumber(vehicleRegNum);
				CaptureScreenshot.CaptureScreenshotUtility(driver, "RegistrationNumberFilledPage");
				vehicleConfirmationPage.NavigateToVehicleEnquiryPage();
				logger.info(" Navigating to vehicle information validation page ");

			} catch (Exception e1) {
				logger.error(" Error while navigating to vehicle information validation page ");
				e1.printStackTrace();
			}
	
			
			try {
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				vehicleValidationPage = new VehicleValidationPage(driver);
				actualVehicleMake =  vehicleValidationPage.getVehicleMake();
				actualVehicleColour =  vehicleValidationPage.getVehicleColur();
				CaptureScreenshot.CaptureScreenshotUtility(driver, "VehicleDataValidationPage");
				logger.info(" Reading vehicle information for validation ");
			} catch (Exception e) {
				logger.error(" Error while reading vehicle information for validation ");
				e.printStackTrace();
			}
			
			if(expectedVehicleMake.equalsIgnoreCase(actualVehicleMake)) {
				logger.info("\n Actual vehicle make for registration number "+ vehicleRegNum + " is " + actualVehicleMake + " same as expected " + expectedVehicleMake);
			}else {
				logger.error("\n Actual vehicle make for registration number "+ vehicleRegNum + " is " + actualVehicleMake + " but expected is " + expectedVehicleMake);
			}
			
			if(expectedVehicleColour.equalsIgnoreCase(actualVehicleColour)) {
				logger.info("\n Actual vehicle colour for registration number "+ vehicleRegNum + " is " + actualVehicleColour + " same as expected " + expectedVehicleColour);
			}else {
				logger.error("\n Actual vehicle colour for registration number "+ vehicleRegNum + " is " + actualVehicleColour + " but expected is " + expectedVehicleColour);
			}

			driver.quit();
		}

		
	}
	
}
