package org.fxb.web.library;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class Excel_File_Reader {
	@DataProvider(name="TestData")
	public static Object[][] read() throws InvalidFormatException, IOException
	{ 
		File f = new File("D:\\Mayur\\Experts-Staging-TestData-ExcelSheet\\Experts-Staging-TestData-ExcelSheet.xlsx");
		XSSFWorkbook wk = new XSSFWorkbook(f); 
		XSSFSheet s1 = wk.getSheet("Sheet1"); 
		int r = s1.getPhysicalNumberOfRows();
		Object[][] arr = new Object[r][4]; 
		for(int i=1;i<r;i++) // if you Start i from 0 then it will also put the heading in the Login page which is present in the 0th row from the Excel Sheet 
		{ 
			XSSFRow r1 = s1.getRow(i);
			XSSFCell firstName = r1.getCell(0);
			XSSFCell lastName = r1.getCell(1);
			XSSFCell password = r1.getCell(2);
			XSSFCell email = r1.getCell(3);
			//XSSFCell mobile = r1.getCell(4);
			arr[i][0] = firstName.getStringCellValue();
			arr[i][1] = lastName.getStringCellValue(); 
			arr[i][2] = password.getStringCellValue();
			arr[i][3] = email.getStringCellValue();
			//arr[i][4] = mobile.getStringCellValue();
		}
		return arr;
	}
}
