package org.fxb.experts_staging.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class InitiatingBrowser_for_Registration{
	public static WebDriver driver;
	/*
	 * If you are planing to use the hardcoded/static/fixed data for these restriction page then you should not do it 
	 * because the Excel sheet data is use when you have to run the same testcase with different set of the data  
	 * e.g suppose you have to use the username and the password for the LoginPage then use the Excel Sheet (i.e @DataProvider)
	 */
	@BeforeSuite
	public void restrictionPage() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver","C:\\ChromeDriver exe file\\116\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(co);
		driver.navigate().to("https://experts-staging.legalops.com/register/b694ec07-4ab6-4ce6-b9bc-8b47e3d4c747");
		driver.manage().window().maximize();
		Thread.sleep(3000);
/*Restricted Page login code*/
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("staging");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("9UXn7'ufSyG<Lr^2");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
  }
	@AfterSuite
	public void closeBrowser()
	{
		driver.quit();
	}
}
