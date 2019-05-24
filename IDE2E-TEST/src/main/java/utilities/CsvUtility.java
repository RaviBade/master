package utilities;

import java.io.FileReader;
import java.util.Properties;

import com.opencsv.CSVReader;

public class CsvUtility {

	public static CSVReader reader;
	public static Properties prop;
	public static String csvFile;
	public static String [] cell;
	public static String [][] dataTable;


	public String[][] getCsvFileData() {
		PropertiesUtility propUtility = new PropertiesUtility();
		prop = propUtility.getProperty();
		csvFile = prop.getProperty("testDataFolder")+"/"+prop.getProperty("csvFile");
		try {
			reader = new CSVReader(new FileReader(csvFile));
		
			int row=0;
			while((cell=reader.readNext())!=null) {

				for(int i=0; i<cell.length; i++) {
					dataTable[row][i] = cell[i];
					row++;
				}

			}
			
		
		}catch(Exception e) {
			e.printStackTrace();
		}

		return dataTable;
	}
	

}
