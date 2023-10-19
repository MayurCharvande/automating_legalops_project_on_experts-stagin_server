package org.fxb.experts_staging.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.fxb.experts_staging.base.InitiatingBrowser_for_Registration;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
/*
 * ITestListener is an interface provided by the TestNG testing framework. 
 * It allows you to listen to and respond to various events and statuses during the test execution process.
 * ITestListnere gives us different methods as given below 
 * Process:-
 * 1. Make a class(ListenerTest)in the packege (Utilities)
 * 2. Implement the ITestListener Interface
 * 3. Add all the methods in it
 * 4. Now use this method to what operations you wants to perform on the test case 
 * 5. Inside the Test class (above the class) you need to write 
 * 		Listeners(package In Which Listener TestClass is made . class name of the Listnere class . class)
 * */
public class ListenerTest extends InitiatingBrowser_for_Registration implements ITestListener
{
	
	public void onFinish(ITestContext arg0) {					
       				
        	System.out.println("Test is Finish");	
    }		

    public void onStart(ITestContext arg0) {					
    	System.out.println("Test Start");			
        		
    }		
    
    public void onTestFailure(ITestResult arg0)
    {					
    	System.out.println("Test Failed so Screenshot is taken");	
    	/* For saving the name of the screenshot with the unique we need to use the TimeStamp*/
    	String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    	String ssSavingFilePath = "C:\\Users\\Fxbytes\\eclipse-workspace\\Fxbytes_Experts-Staging\\Screenshots\\";
    	String ssSavingWithName = timestamp + ".jpg";
    	
		File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshotFile, new File(ssSavingFilePath + ssSavingWithName));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		driver.close();		
    }		

    public void onTestSkipped(ITestResult arg0) {					
    	System.out.println("Test Skipped");	
        		
    }		

    public void onTestStart(ITestResult arg0) {					
    	System.out.println("Test Start");	
        		
    }		

    public void onTestSuccess(ITestResult arg0) {					
       				
        		
    }		
}
