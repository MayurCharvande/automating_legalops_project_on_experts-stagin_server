package org.fxb.experts_staging.extent_report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reports 
{
	public ExtentReports addReports()
	{
		ExtentSparkReporter obj1 = new ExtentSparkReporter("Ex-Report.html"); 
		ExtentReports obj2 = new ExtentReports();
		obj2.attachReporter(obj1);
		obj2.setSystemInfo("Testing1", "Testing2");
		return obj2;
	}
}
