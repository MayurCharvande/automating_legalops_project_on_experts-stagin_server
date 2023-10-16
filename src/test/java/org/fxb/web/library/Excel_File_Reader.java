package org.fxb.web.library;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class Excel_File_Reader {
	/* Below Data Provider is used for fetching the String data from the excel sheet
	 * Name, password, emails
	 */
	@DataProvider(name="TestData")
	public static Object[][] read_name_email_password() throws InvalidFormatException, IOException
	{ 
		File f = new File("C:\\Mayur Automation Practice\\Experts-Staging-TestData-ExcelSheet3.xlsx");
		//(personal system path) C:\\Mayur Automation Practice\\Experts-Staging-TestData-ExcelSheet.xlsx
		//(office system path) C:\Users\Fxbytes\eclipse-workspace\Fxbytes_Experts-Staging\Configuration\Experts-Staging-TestData-ExcelSheet.xlsx
				
		XSSFWorkbook wk = new XSSFWorkbook(f); 
		XSSFSheet s1 = wk.getSheet("Sheet1"); 
		int r = s1.getPhysicalNumberOfRows();
		Object[][] arr = new Object[r-1][5]; 
		DataFormatter dataFormatter = new DataFormatter(); // Only to convert mobile number to String value we have used this class
		for(int i=1;i<r;i++) // if you Start i from 0 then it will also put the heading in the Login page which is present in the 0th row from the Excel Sheet 
		{ 
			XSSFRow r1 = s1.getRow(i);
			XSSFCell firstName = r1.getCell(0);
			XSSFCell lastName = r1.getCell(1);
			XSSFCell password = r1.getCell(2);
			XSSFCell email = r1.getCell(3);
			XSSFCell mobile = r1.getCell(4);
			arr[i-1][0] = firstName.getStringCellValue();
			arr[i-1][1] = lastName.getStringCellValue(); 
			arr[i-1][2] = password.getStringCellValue();
			arr[i-1][3] = email.getStringCellValue();
			//arr[i][4] = mobile.getStringCellValue();
			arr[i-1][4] = dataFormatter.formatCellValue(mobile);
		}
		return arr;
	}
	@DataProvider(name="Excel_Mobile_Data") 
	public static Object[][] read_mobile() throws InvalidFormatException, IOException
	{
		File f2 = new File("C:\\Mayur Automation Practice\\GitHub Expert_Staging Server Code\\automating_legalops_project_on_experts-stagin_server\\Configuration\\Experts-Staging-TestData-ExcelSheet2.xlsx");
		//(personal system path) C:\\Mayur Automation Practice\\Experts-Staging-TestData-ExcelSheet.xlsx
		// (office system path) C:\Users\Fxbytes\eclipse-workspace\Fxbytes_Experts-Staging\Configuration\Experts-Staging-TestData-ExcelSheet.xlsx
		XSSFWorkbook wk2 = new XSSFWorkbook(f2);
		XSSFSheet s2 = wk2.getSheet("Sheet1");
		int r2 = s2.getPhysicalNumberOfRows();
		/* Even though if your excel sheet have multiple columns and if you are wishing to fetch the data 
		 * from the any perticular column then use the below code
		 */
		Object[][] arr2 = new Object[r2][1]; // [r2]: for size of the rows & [1]: for size of the column you wants
		DataFormatter dataFormatter = new DataFormatter(); 
		/* DataFormatter Class: If you wants to convert the Numeric cell value to the String value 
		 * then you need to use this DataFormater class and its methods
		 */
		for(int i=1; i<r2; i++)
		{
			XSSFRow r3 = s2.getRow(i);
			XSSFCell mobile = r3.getCell(4);// this will get the value of the 4th column from your Excel sheet
			arr2[i][0] = dataFormatter.formatCellValue(mobile); // this method will convert the Numeric value to the String value
			System.out.println("--> " + dataFormatter.formatCellValue(mobile));
			/* Convert the numeric cell to a String*/
			// arr2[i][0] = mobile.getStringCellValue(); //this will get the value from the column and then store it into the place [i][0] and then this will be used for returning the value at this place
		}
		return arr2;
	}
}
