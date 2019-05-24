package utilities;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	XSSFWorkbook workBook;
	XSSFSheet sheet;

// Constructor to initialise Excel Workbook 
	public ExcelUtility(String excelPath, String sheetName) throws Exception {
		try {
				workBook = new XSSFWorkbook(excelPath);
				sheet = workBook.getSheet(sheetName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	
// Get row count of sheet
	public int getRowCount() {
		int rowCount=0;
		try {
				rowCount = sheet.getPhysicalNumberOfRows();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return rowCount;
	}


// Get column count of row
	public int getColCount() {
		int colCount = 0;
		try {
			colCount = sheet.getRow(0).getPhysicalNumberOfCells();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return colCount;
	}

	
// Get cell value
	public String getCellDataString(int rowNum, int colNum) {
		
		String cellDataStr = "";
		try {

			cellDataStr = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();

			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return cellDataStr;
	}
	

}
