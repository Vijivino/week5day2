package week5.day2;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public  String[][] runreadexcel(String filename) throws IOException {
		
		XSSFWorkbook book=new XSSFWorkbook("./data/"+filename+".xlsx");
		XSSFSheet sheet = book.getSheet("Sheet1");
		int rowNum = sheet.getLastRowNum();
		short cellNum = sheet.getRow(0).getLastCellNum();
		String[][] data=new String[rowNum][cellNum];
		
		for(int i=1;i<+rowNum;i++) {
			XSSFRow row = sheet.getRow(i);
			  for(int j=0;j<cellNum;j++) {
				  XSSFCell cell = row.getCell(j);
				  String stringCellValue = cell.getStringCellValue();
				  data[i-1][j]=stringCellValue;
			  }
		}
		book.close();
		return data;
		
	}

}
