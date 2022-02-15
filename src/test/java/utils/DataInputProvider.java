package utils;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataInputProvider {
	
	
	public String[][] readExcel(String fileName, String sheet) throws IOException {
		// Create Object for WorkBook
		XSSFWorkbook wb = new XSSFWorkbook("./data/"+fileName+".xlsx");
		// Enter the Sheet
		XSSFSheet sh = wb.getSheet(sheet);
		// Row Count
		int rowCount = sh.getLastRowNum();				
		// Column Count
		int colCount = sh.getRow(0).getLastCellNum();		
		
		String[][] data = new String[rowCount][colCount];
		
		// for Row
		for(int i = 1; i<=rowCount;i++) {
			
			XSSFRow row = sh.getRow(i);		// Enter the Row
			// for Column
			for(int j=0; j<colCount ;j++) {
				XSSFCell cell = row.getCell(j);		// Enter the Column
				
				String value = cell.getStringCellValue(); //Read the Data
				
				data[i-1][j] = value;
			}
		}
		wb.close();
		
		return data;
	}
	
	
}
