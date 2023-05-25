package org.fxbytes.web.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class InitiateBrowser_User_Login {
	public static WebDriver driver1;
	@BeforeSuite
	public void startBrowser() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fxbytes\\eclipse-workspace\\Fxbytes\\src\\main\\resources\\chromedriver.exe");
		driver1 = new ChromeDriver();
		driver1.manage().window().maximize();
		driver1.navigate().to("https://experts-staging.legalops.com/");
		driver1.findElement(By.xpath("//input[@id='user_email']")).sendKeys("mayur.charvande+464@fxbytes.com");
		driver1.findElement(By.xpath("//input[@name='user_password']")).sendKeys("Fxbytes@123");
		Thread.sleep(10000);
		driver1.findElement(By.xpath("//button[@class='js-cookie-consent-agree cookie-consent__agree']")).click();
		driver1.findElement(By.xpath("//button[@id='submit_login']")).click();
	}
	@AfterSuite(enabled=false)
	public void closeBrowser()
	{
		driver1.quit();
	}
}
