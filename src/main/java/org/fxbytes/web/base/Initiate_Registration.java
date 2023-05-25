package org.fxbytes.web.base;

import java.util.List;

import org.fxbytes.web.jsexecutor.JsExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeSuite;

public class Initiate_Registration {
	
	public static WebDriver driver;
	public	Actions act = new Actions(driver);
	
	@BeforeSuite
	public void restrictionPage()
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Fxbytes\\eclipse-workspace\\Fxbytes\\src\\main\\resources\\chromedriver.exe");
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(co);
		driver.navigate().to("https://experts-staging.legalops.com/register/b694ec07-4ab6-4ce6-b9bc-8b47e3d4c747");
		driver.manage().window().maximize();
		JsExecutor.sendTextToTextBox("//input[@type='text']", "staging");
		JsExecutor.sendTextToTextBox("//input[@type='password']", "9UXn7'ufSyG<Lr^2");
		JsExecutor.jsClick("//button[@type='submit']");
	}
	//@Test(priority=1)
	public void step_1_Confirm_Eligibility()
	{
		JsExecutor.jsClick("//label[@for='qualification_check']");
		JsExecutor.jsClick("//label[@for='agree2']");
		JsExecutor.jsClick("(//button[@type='button'])[1]");
	}
	//@Test(priority=2)
	public void step_2_Create_Your_Account() throws InterruptedException
	{
		Thread.sleep(3000);
		JsExecutor.sendTextToTextBox("//input[@id='user_firstname']", "Mayur");		
		JsExecutor.sendTextToTextBox("//input[@id='user_lastname']", "User");		
		JsExecutor.sendTextToTextBox("//input[@id='user_password']", "Fxbytes@123");		
		JsExecutor.sendTextToTextBox("//input[@id='confirm_password']", "Fxbytes@123");
		/*Scroll until element visible*/
		JsExecutor.scrollToElement("//input[@id='personal_email']");
		/*Select value from the dropdown*/ 
		Select dp = new Select(driver.findElement(By.xpath("//select[@id='mobile_country_code']")));
		dp.selectByValue("+91");
		/*verify number*/
		JsExecutor.sendTextToTextBox("//input[@id='user_mobile']", "7620248349");
		act.sendKeys(Keys.TAB).sendKeys(Keys.TAB).build().perform();
		Thread.sleep(3000);
		JsExecutor.jsClick("//button[text()='Verify']");
		Thread.sleep(3000);
		JsExecutor.jsClick("//button[text()='Yes, do it!']");
		Thread.sleep(15000);
		JsExecutor.jsClick("//input[@id='otp-verify-submit']");
		Thread.sleep(3000);
		JsExecutor.jsClick("//button[text()='OK']");
		/*enter email and other details*/
		JsExecutor.sendTextToTextBox("//input[@id='user_email']", "mayur.charvande+888@fxbytes.com");		
		JsExecutor.sendTextToTextBox("//input[@id='verify_user_email']", "mayur.charvande+888@fxbytes.com");		
		JsExecutor.sendTextToTextBox("//input[@id='personal_email']", "mayur.charvande+888@fxbytes.com");		
		JsExecutor.sendTextToTextBox("//input[@id='conf_personal_email']", "mayur.charvande+888@fxbytes.com");
		JsExecutor.scrollToElement("//button[@id='register-step-three']");
		/*Entering Value in Auto Suggest Dropdown for Current Employer Name*/
		JsExecutor.jsClick("//div[text()='select']");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[2]")).sendKeys("F");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[2]")).sendKeys("x");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[2]")).sendKeys("b");
		Thread.sleep(2000);
		/*Get list of all the employers present in Auto suggest dropdown*/
		List<WebElement> employer_list = driver.findElements(By.xpath("//div[@id='bs-select-2']//descendant::ul[@class='dropdown-menu inner ']//descendant::li"));
		Thread.sleep(2000);
		for(WebElement a : employer_list)
		{
			Thread.sleep(2000);
			String name = a.getText();
			System.out.println("-->"+a.getText());
			if(name.equalsIgnoreCase("fxbytes")) 
			{
				a.click();
				break;
			}
		}
		Thread.sleep(3000);
		/*clicking checkbox by js executor*/
		JsExecutor.jsClick("//label[text()='I have read and understood the Membership Terms and Conditions ']");
		/*Scroll down*/

		/*Action Events to double click on the captcha field*/
		act.doubleClick(driver.findElement(By.xpath("//input[@id='captcha']"))).perform();
		Thread.sleep(10000);
		JsExecutor.jsClick("//button[@id='register-step-three']");
	}
	//@Test(priority=3)
	public void step_3_Basic_Data() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='address_line_one']")).sendKeys("Dewas Naka");
		/*Select state value from Auto Suggest dropdown*/
		act.click(driver.findElement(By.xpath("//div[text()='Select a State']"))).perform();
		Thread.sleep(1000);//until dropdown apears
		JsExecutor.sendTextToTextBox("//input[@aria-activedescendant='bs-select-4-0']", "Washington");
		Thread.sleep(3000);//until dropdown apears
		List<WebElement> employer_list = driver.findElements(By.xpath("//div[@id='bs-select-2']//descendant::ul[@class='dropdown-menu inner ']//descendant::li"));
		Thread.sleep(2000);
		for(WebElement a : employer_list)
		{
			Thread.sleep(2000);
			String name = a.getText();
			System.out.println("-->"+a.getText());
			if(name.equalsIgnoreCase("washington")) 
			{
				a.click();
				break;
			}
		}
		Thread.sleep(3000);
		JsExecutor.sendTextToTextBox("//input[@id='city']", "NewYour");
		JsExecutor.sendTextToTextBox("//input[@id='postal_code']", "gh32");
		JsExecutor.jsClick("//input[@id='profile-img']");
		/*Wait until Image is Adding*/
		Thread.sleep(10000);
		JsExecutor.jsClick("(//button[@id='final-submit'])[1]");
		Thread.sleep(3000);
	}
	//@Test(priority=4)
	public void Step_4_Work_Experience()
	{
		JsExecutor.sendTextToTextBox("//input[@id='years_in_legal_ops']", "7");
		JsExecutor.sendTextToTextBox("//input[@id='year_direct_managing']", "7");
	}
}
