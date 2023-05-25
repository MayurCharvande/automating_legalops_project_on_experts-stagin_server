package org.fxb.experts_staging.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class InitiatingBrowser_for_Registration{
	public static WebDriver driver;
	
	@BeforeSuite
	public void restrictionPage() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Fxbytes\\eclipse-workspace\\Fxbytes\\src\\main\\resources\\chromedriver.exe");
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(co);
		driver.navigate().to("https://experts-staging.legalops.com/register/b694ec07-4ab6-4ce6-b9bc-8b47e3d4c747");
		//driver.navigate().to("https://staging.legalops.com/");
		driver.manage().window().maximize();
		Thread.sleep(3000);
/*Restricted Page login code*/
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("staging");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("9UXn7'ufSyG<Lr^2");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
/*code for staging*/
//		driver.navigate().to("https://staging.legalops.com/");
//		driver.findElement(By.xpath("//a[@class='text-light-blue']")).click();
		
	}
	@AfterSuite(enabled=false)
	public void closeBrowser()
	{
		driver.quit();
	}
}
