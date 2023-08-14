package org.fxb.experts_staging.testcases;

import java.io.IOException;
import java.util.List;

import org.fxb.experts_staging.base.InitiatingBrowser_for_Registration;
import org.fxb.experts_staging.js.JSExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class TC_For_Registration_Experts_Staging extends InitiatingBrowser_for_Registration{

	@Test(priority=1)
	public void step_1_Confirm_Eligibility() throws IOException
	{
/*checking broken links*/
//		  BrokenLinks bl = new BrokenLinks(); 
//		  bl.brokenLink();
		 
		driver.findElement(By.xpath("//label[@for='qualification_check']")).click();// For Employed User
		driver.findElement(By.xpath("//label[@for='agree2']")).click();
		driver.findElement(By.xpath("(//button[@type='button'])[1]")).click();
	}
	@Test(priority=2)
	public void step_2_Create_Your_Account() throws InterruptedException, IOException
	{
		Actions act = new Actions(driver);
		Thread.sleep(3000);
		JSExecutor.sendTextToTextBox("//input[@id='user_firstname']", "Mayur");
		JSExecutor.sendTextToTextBox("//input[@id='user_lastname']", "Emp 28");		
		JSExecutor.sendTextToTextBox("//input[@id='user_password']", "Fxbytes@123");		
		JSExecutor.sendTextToTextBox("//input[@id='confirm_password']", "Fxbytes@123");
		/*Scroll until element visible*/
		JSExecutor.scrollToElement("//input[@id='personal_email']");
		/*enter email and other details*/
		JSExecutor.sendTextToTextBox("//input[@id='user_email']", "mayur.charvande+1006@fxbytes.com");		
		JSExecutor.sendTextToTextBox("//input[@id='verify_user_email']", "mayur.charvande+1006@fxbytes.com");		
		JSExecutor.sendTextToTextBox("//input[@id='personal_email']", "mayur.charvande+1006@fxbytes.com");		
		JSExecutor.sendTextToTextBox("//input[@id='conf_personal_email']", "mayur.charvande+1006@fxbytes.com");
		JSExecutor.scrollToElement("//button[@id='register-step-three']");
		/*Entering Value in Auto Suggest Dropdown for Current Employer Name*/
		JSExecutor.jsClick("//div[text()='select']");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[2]")).sendKeys("F");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[2]")).sendKeys("x");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[2]")).sendKeys("b");
		Thread.sleep(1000);
		/*Get list of all the employers present in Auto suggest dropdown*/
		List<WebElement> employer_list = driver.findElements(By.xpath("//div[@id='bs-select-2']//descendant::ul[@class='dropdown-menu inner ']//descendant::li"));
		Thread.sleep(1000);
		for(WebElement a : employer_list)
		{
			Thread.sleep(1000);
			String name = a.getText();
			System.out.println("-->"+a.getText());
			if(name.equalsIgnoreCase("fxbytes")) 
			{
				a.click();
				break;
			}
		}
		Thread.sleep(2000);
		/*clicking checkbox by js executor*/
		JSExecutor.jsClick("//label[text()='I have read and understood the Membership Terms and Conditions ']");
		/*Scroll down*/
		
		/*here now write a code to show Alert that "in 10 sec you have to enter captcha"*/
		JSExecutor.jsAlert("Enter Captcha in 10 SECONDS");
		/*Action Events to double click on the captcha field*/
		act.doubleClick(driver.findElement(By.xpath("//input[@id='captcha']"))).perform();
		Thread.sleep(10000);
	}
	@Test(priority=3)
	public void mobileNumberVerification() throws InterruptedException
	{
		Actions act = new Actions(driver);
		/*Select value from the dropdown*/ 
		Select dp = new Select(driver.findElement(By.xpath("//select[@id='mobile_country_code']")));
		dp.selectByValue("+91");
		/*verify number*/
		JSExecutor.sendTextToTextBox("//input[@id='user_mobile']", "7620248349");
		act.sendKeys(Keys.TAB).sendKeys(Keys.TAB).build().perform();
		Thread.sleep(3000);
		JSExecutor.jsClick("//button[text()='Verify']");
		Thread.sleep(3000);
		JSExecutor.jsClick("//button[text()='Yes, do it!']");
		Thread.sleep(15000);
		JSExecutor.jsClick("//input[@id='otp-verify-submit']");
		Thread.sleep(3000);
		JSExecutor.jsClick("//button[text()='OK']");
		/*clicking on the Next button*/
		Thread.sleep(3000);
		JSExecutor.jsClick("//button[@id='register-step-three']");
	}
	@Test(priority=4)
	public void step_3_Basic_Data() throws InterruptedException
	{
		Actions act = new Actions(driver);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='address_line_one']")).sendKeys("Dewas Naka");
		/*Select state value from Auto Suggest dropdown*/
		act.click(driver.findElement(By.xpath("//div[text()='Select a State']"))).perform();
		Thread.sleep(1000);//until dropdown apears
		//JSExecutor.sendTextToTextBox("//input[@aria-activedescendant='bs-select-4-0']", "Washington");
		JSExecutor.jsClick("//input[@aria-activedescendant='bs-select-4-0']");
		Thread.sleep(1000);
			System.out.println("1");
		driver.findElement(By.xpath("(//input[@type='search'])[4]")).sendKeys("W");
		Thread.sleep(2000);
			System.out.println("2");
		driver.findElement(By.xpath("(//input[@type='search'])[4]")).sendKeys("a");
		Thread.sleep(1000);
			System.out.println("3");
		driver.findElement(By.xpath("(//input[@type='search'])[4]")).sendKeys("s");
		Thread.sleep(1000);
			System.out.println("4");
		driver.findElement(By.xpath("(//input[@type='search'])[4]")).sendKeys("h");
		Thread.sleep(3000);//until dropdown apears
			System.out.println("5");
		List<WebElement> state_list = driver.findElements(By.xpath("//div[@id='bs-select-4']//descendant::ul[@class='dropdown-menu inner ']//descendant::li"));
		Thread.sleep(2000);
			System.out.println("6");
		for(WebElement b : state_list)
		{
			Thread.sleep(2000);
			String name = b.getText();
			System.out.println("-->"+b.getText());
			if(name.equalsIgnoreCase("washington")) 
			{
				b.click();
				break;
			}
		}
		Thread.sleep(1000);
		JSExecutor.sendTextToTextBox("//input[@id='city']", "NewYour");
		JSExecutor.sendTextToTextBox("//input[@id='postal_code']", "gh32");
		Thread.sleep(1000);
		System.out.println("7");
		JSExecutor.jsAlert("Add Image in 15 SECONDS");
		System.out.println("8");
		/*Wait until Image is Adding*/
		Thread.sleep(15000);
		System.out.println("9");
		JSExecutor.jsClick("(//button[@id='final-submit'])[1]");
		System.out.println("10");
		Thread.sleep(3000);
	}
	@Test(priority=5)
	public void Step_4_Work_Experience() throws InterruptedException
	{
		JSExecutor.sendTextToTextBox("//input[@id='years_in_legal_ops']", "7");
		JSExecutor.sendTextToTextBox("//input[@id='year_direct_managing']", "7");
/*Step 1 : Selecting Option "Are you licensed Attorney ?"*/		
		Actions act = new Actions(driver);
		JSExecutor.jsClick("//div[text()='Select Are You a Licensed Attorney?']");
		driver.findElement(By.xpath("(//input[@type='search'])[5]")).sendKeys("Y");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[5]")).sendKeys("e");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[5]")).sendKeys("s");
		Thread.sleep(1000);
		act.sendKeys(Keys.ENTER).perform();
/* Selecting Title */

		JSExecutor.sendTextToTextBox("//input[@id='title']", "QA");
		
/*Step 1 : Selecting Current Role*/			
		JSExecutor.jsClick("//div[text()='Select Current Level']");
		driver.findElement(By.xpath("(//input[@type='search'])[11]")).sendKeys("S");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[11]")).sendKeys("r");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[11]")).sendKeys(".");
		Thread.sleep(1000);
/*Step 2 : Selecting Current Role*/		
		List<WebElement> currentRole_list = driver.findElements(By.xpath("//div[@id='bs-select-11']//descendant::ul[@class='dropdown-menu inner ']//descendant::li"));
		Thread.sleep(2000);
		for(WebElement d : currentRole_list)
		{
			Thread.sleep(2000);
			String name = d.getText();
			System.out.println("-->"+d.getText());
			if(name.equalsIgnoreCase("Sr. Analyst")) 
			{
				d.click();
				break;
			}
		}
/*Step 1 : Selecting Start Date*/			
		JSExecutor.jsClick("(//div[text()='Select Start Date (Year Only)'])[1]");
		driver.findElement(By.xpath("(//input[@type='search'])[12]")).sendKeys("2");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[12]")).sendKeys("0");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[12]")).sendKeys("2");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[12]")).sendKeys("2");
		Thread.sleep(1000);
		act.sendKeys(Keys.ENTER).perform();
/*Step 2 : Selecting Start Date*/		
//		List<WebElement> startDate_list = driver.findElements(By.xpath("//div[@id='bs-select-12']//descendant::ul[@class='dropdown-menu inner ']//descendant::li"));
//		Thread.sleep(2000);
//		for(WebElement e : startDate_list)
//		{
//			Thread.sleep(2000);
//			String name = e.getText();
//			System.out.println("-->"+e.getText());
//			if(name.equalsIgnoreCase("2022")) 
//			{
//				e.click();
//				break;
//			}
//		}
/*Step 1 : Selecting Option licensed Attorney */			
		JSExecutor.jsClick("(//div[text()='Select Licensed Attorney Role'])[1]");
		driver.findElement(By.xpath("(//input[@type='search'])[14]")).sendKeys("Y");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[14]")).sendKeys("e");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[14]")).sendKeys("s");
		Thread.sleep(1000);
		act.sendKeys(Keys.ENTER).perform();
/*Step 2 : Selecting Option licensed Attorney */		
//		List<WebElement> Licensed_Attorney_Role_list = driver.findElements(By.xpath("//div[@id='bs-select-14']//descendant::ul//descendant::li"));
//		Thread.sleep(2000);
//		for(WebElement f : Licensed_Attorney_Role_list)
//		{
//			Thread.sleep(2000);
//			String name = f.getText();
//			System.out.println("-->"+f.getText());
//			if(name.equalsIgnoreCase("yes")) 
//			{
//				f.click();
//				break;
//			}
//		}
/*Step 1: Selecting Option 'Do You Report to the GC?'*/
		JSExecutor.jsClick("//div[text()='Do You Report to the GC']");
		driver.findElement(By.xpath("(//input[@type='search'])[17]")).sendKeys("Y");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[17]")).sendKeys("e");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[17]")).sendKeys("s");
		Thread.sleep(1000);
		act.sendKeys(Keys.ENTER).perform();
/*Step 2: Selecting Option 'Do You Report to the GC?' */
//		List<WebElement> Do_You_Report_to_the_GC = driver.findElements(By.xpath("//div[@id='bs-select-17']//descendant::ul//descendant::li"));
//		Thread.sleep(2000);
//		for(WebElement g : Do_You_Report_to_the_GC)
//		{
//			Thread.sleep(2000);
//			String name = g.getText();
//			System.out.println("-->"+g.getText());
//			if(name.equalsIgnoreCase("yes")) 
//			{
//				g.click();
//				break;
//			}
//		}
/*clicking on he 'Years You Managed People at this Company '*/
		JSExecutor.sendTextToTextBox("(//input[@id='no_of_years_managed'])[1]", "7");
/*Step 1: Selecting Option 'US State in which You Work'*/
		JSExecutor.jsClick("(//div[text()='Select US State in which You Work'])[1]");
		driver.findElement(By.xpath("(//input[@type='search'])[19]")).sendKeys("c");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[19]")).sendKeys("a");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[19]")).sendKeys("l");
		Thread.sleep(1000);
/*Step 2: Selecting Option 'US State in which You Work' */
		List<WebElement> US_State_in_which_You_Work = driver.findElements(By.xpath("//div[@id='bs-select-19']//descendant::ul//descendant::li"));
		Thread.sleep(2000);
		for(WebElement g : US_State_in_which_You_Work)
		{
			Thread.sleep(2000);
			String name = g.getText();
			if(name.equalsIgnoreCase("california")) 
			{
				g.click();
				break;
			}
		}
/*Step 1: Selecting Option 'Department'*/
		JSExecutor.jsClick("(//div[text()='Select Department'])[1]");
		driver.findElement(By.xpath("(//input[@type='search'])[20]")).sendKeys("i");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[20]")).sendKeys("t");
		Thread.sleep(1000);
		act.sendKeys(Keys.ENTER).perform();
/*Step 2: Selecting Option 'Department' */
//		List<WebElement> Department = driver.findElements(By.xpath("//div[@id='bs-select-20']//descendant::ul//descendant::li"));
//		Thread.sleep(2000);
//		for(WebElement g : Department)
//		{
//			Thread.sleep(2000);
//			String name = g.getText();
//			System.out.println("-->"+g.getText());
//			if(name.equalsIgnoreCase("IT")) 
//			{
//				g.click();
//				break;
//			}
//		}
/*Step 1: Selecting Option 'Legal Department Size'*/
		JSExecutor.jsClick("(//div[text()='Select Legal Department Size'])[1]");
		driver.findElement(By.xpath("(//input[@type='search'])[21]")).sendKeys("2");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[21]")).sendKeys("5");
		Thread.sleep(1000);
		act.sendKeys(Keys.ENTER).perform();
/*Step 2: Selecting Option 'Legal Department Size' */
//		List<WebElement> Legal_Department_Size = driver.findElements(By.xpath("//div[@id='bs-select-21']//descendant::ul//descendant::li"));
//		Thread.sleep(2000);
//		for(WebElement g : Legal_Department_Size)
//		{
//			Thread.sleep(2000);
//			String name = g.getText();
//			System.out.println("-->"+g.getText());
//			if(name.equalsIgnoreCase("25 or Fewer")) 
//			{
//				g.click();
//				break;
//			}
//		}
		/*Step 1: Selecting Option 'Do You Lead The Legal Ops Team?'*/
		JSExecutor.jsClick("//div[text()='Select Do You Lead The Legal Ops Team?']");
		driver.findElement(By.xpath("(//input[@type='search'])[22]")).sendKeys("n");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[22]")).sendKeys("o");
		Thread.sleep(1000);
		act.sendKeys(Keys.ENTER).perform();
		/*Step 2: Selecting Option 'Do You Lead The Legal Ops Team?' */
//		List<WebElement> Do_You_Lead_The_Legal_Ops_Team = driver.findElements(By.xpath("//div[@id='bs-select-22']//descendant::ul//descendant::li"));
//		Thread.sleep(2000);
//		for(WebElement g : Do_You_Lead_The_Legal_Ops_Team)
//		{
//			Thread.sleep(2000);
//			String name = g.getText();
//			System.out.println("-->"+g.getText());
//			if(name.equalsIgnoreCase("no")) 
//			{
//				g.click();
//				break;
//			}
//		}

/*clicking on the Next button*/
		Thread.sleep(3000);
		JSExecutor.jsClick("(//button[@id='final-submit'])[2]");
	}
	@Test(priority=6)
	public void Step_5_Annual_Compensation() throws InterruptedException
	{
		Thread.sleep(2000);
	driver.findElement(By.xpath("(//input[@id='base_comp_2020'])[1]")).sendKeys("10000");	
	driver.findElement(By.xpath("(//input[@id='comp_bonus_2020'])[1]")).sendKeys("10000");
	driver.findElement(By.xpath("(//input[@id='comp_stock_2020'])[1]")).sendKeys("10000");
/*Step 1 : Selecting Job Level*/			
	JSExecutor.jsClick("//div[text()='Select']");
	driver.findElement(By.xpath("(//input[@type='search'])[43]")).sendKeys("S");
	Thread.sleep(1000);
	driver.findElement(By.xpath("(//input[@type='search'])[43]")).sendKeys("r");
	Thread.sleep(1000);
	driver.findElement(By.xpath("(//input[@type='search'])[43]")).sendKeys(".");
	Thread.sleep(1000);
/*Step 2 : Selecting Current Role*/		
	List<WebElement> currentRole_list = driver.findElements(By.xpath("//div[@id='bs-select-50']//descendant::ul[@class='dropdown-menu inner ']//descendant::li"));
	Thread.sleep(2000);
	for(WebElement d : currentRole_list)
	{
		Thread.sleep(2000);
		String name = d.getText();
		System.out.println("-->"+d.getText());
		if(name.equalsIgnoreCase("Sr. Analyst")) 
		{
			d.click();
			break;
		}
	}
	driver.findElement(By.xpath("(//input[@id='base_comp_2020'])[2]")).sendKeys("10000");	
	driver.findElement(By.xpath("(//input[@id='comp_bonus_2020'])[2]")).sendKeys("10000");
	driver.findElement(By.xpath("(//input[@id='comp_stock_2020'])[2]")).sendKeys("10000");
	/*clicking on the Next button*/
	Thread.sleep(1000);
	JSExecutor.jsClick("(//button[@id='final-submit'])[3]");
	}
@Test(priority=7)
	public void Step_5_Education() throws InterruptedException
	{
		Actions act = new Actions(driver);
/*Step 1: Selecting Option 'Degree/Certification'*/
		JSExecutor.jsClick("(//select[@id='education_name'])[1]");
		driver.findElement(By.xpath("(//input[@type='search'])[44]")).sendKeys("j");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[44]")).sendKeys("d");
		Thread.sleep(1000);
		act.sendKeys(Keys.ENTER).perform();
/*Step 1 : Selecting School/University Attended */			
		JSExecutor.jsClick("(//div[@class='filter-option'])[45]");
		driver.findElement(By.xpath("(//input[@type='search'])[45]")).sendKeys("a");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[45]")).sendKeys("m");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[45]")).sendKeys("e");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[45]")).sendKeys("r");
		Thread.sleep(1000);
/*Step 2 : Selecting School/University Attended*/		
		List<WebElement> currentRole_list = driver.findElements(By.xpath("//div[@id='bs-select-24']//descendant::ul[@class='dropdown-menu inner']//descendant::li"));
		Thread.sleep(2000);
		for(WebElement d : currentRole_list)
		{
			Thread.sleep(2000);
			String name = d.getText();
			if(name.equalsIgnoreCase("American Academy of Art")) 
			{
				d.click();
				break;
			}
		}
/*Step 1: Selecting Option 'Program Level'*/
		JSExecutor.jsClick("(//div[text()='Select Program Level'])[1]");
		driver.findElement(By.xpath("(//input[@type='search'])[46]")).sendKeys("c");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[46]")).sendKeys("e");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[46]")).sendKeys("r");
		Thread.sleep(1000);
		act.sendKeys(Keys.ENTER).perform();
/*Step 1 : Selecting Start Date*/			
		JSExecutor.jsClick("(//div[text()='Select Start Date (Year Only)'])[2]");
		driver.findElement(By.xpath("(//input[@type='search'])[47]")).sendKeys("2");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[47]")).sendKeys("0");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[47]")).sendKeys("2");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[47]")).sendKeys("2");
		Thread.sleep(1000);
		act.sendKeys(Keys.ENTER).perform();
/*Step 1 : Selecting End Date*/			
		JSExecutor.jsClick("//div[text()='Select End Date (Year only)']");
		driver.findElement(By.xpath("(//input[@type='search'])[48]")).sendKeys("2");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[48]")).sendKeys("0");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[48]")).sendKeys("2");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[48]")).sendKeys("3");
		Thread.sleep(1000);
		act.sendKeys(Keys.ENTER).perform();
/*Step 1 : Selecting Area of Study*/			
		JSExecutor.jsClick("(//div[text()='Select Area of Study'])[1]");
		driver.findElement(By.xpath("(//input[@type='search'])[49]")).sendKeys("a");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[49]")).sendKeys("r");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[49]")).sendKeys("t");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[49]")).sendKeys("s");
		Thread.sleep(1000);
		act.sendKeys(Keys.ENTER).perform();
/*clicking on the Next button*/
		Thread.sleep(1000);
		JSExecutor.jsClick("(//button[@id='final-submit'])[4]");
	}
}
