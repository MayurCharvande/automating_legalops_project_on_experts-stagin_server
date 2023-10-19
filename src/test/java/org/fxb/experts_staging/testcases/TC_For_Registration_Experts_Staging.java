package org.fxb.experts_staging.testcases;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.fxb.experts_staging.base.InitiatingBrowser_for_Registration;
import org.fxb.experts_staging.js.JSExecutor;
import org.fxb.experts_staging.utilities.Waits;
import org.fxb.web.library.Excel_File_Reader;
import org.fxb.web.library.Property_File_Reader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(org.fxb.experts_staging.utilities.ListenerTest.class) //This is used whenever you are using the ITestListener
public class TC_For_Registration_Experts_Staging extends InitiatingBrowser_for_Registration {
	@Test(priority = 1, groups={"regression", "smoke"})
	public void step_1_Confirm_Eligibility() throws IOException, InterruptedException {
		Waits.explicit_waitForElementToBe_Visible(By.xpath(Property_File_Reader.projectConfigurationReader("EmployedUser")), 10);
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("EmployedUser"))).click();// For Employed User
		JSExecutor.jsClick(Property_File_Reader.projectConfigurationReader("AgreeCheckbox"));
		//driver.findElement(By.xpath("(//button[@type='button'])[1]")).click();
		JSExecutor.jsClick(Property_File_Reader.projectConfigurationReader("ConfirmEligibilityNextButton")); // on the 1440 resolution there is a footer hence you can use this code line instead of the above
	}
	@Test(priority = 2, groups={"regression"}, dataProviderClass = Excel_File_Reader.class, dataProvider = "TestData")
	public void step_2_Create_Your_Account(String fname, String lname, String password, String email, String mob, String country_code) throws InterruptedException, IOException 
	{
		Actions act = new Actions(driver);
		Waits.explicit_waitForElementToBe_Visible(By.xpath(Property_File_Reader.projectConfigurationReader("user_firstname")), 10);
		JSExecutor.sendTextToTextBox(Property_File_Reader.projectConfigurationReader("user_firstname"), fname); // 'fname' is the first name of the user
		JSExecutor.sendTextToTextBox(Property_File_Reader.projectConfigurationReader("user_lastname"), lname); // 'lname' is the last name of the user
		JSExecutor.sendTextToTextBox(Property_File_Reader.projectConfigurationReader("user_password"), password);
		JSExecutor.sendTextToTextBox(Property_File_Reader.projectConfigurationReader("confirm_password"), password);
		/* Add debug statements to check for null values*/
		//System.out.println("Checking driver: " + (driver != null)); // Checking null pointer exception
		/*Mobile number verification code*/
		Select dp = new Select(driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("mobile_country_code"))));
		WebElement we1 = driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("mobile_country_code")));
		act.click(we1).perform();
		Waits.explicit_waitForElementToBe_Visible(By.xpath(Property_File_Reader.projectConfigurationReader("country_code_search")), 10);
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("country_code_search"))).sendKeys(country_code);
		act.sendKeys(Keys.TAB).sendKeys(Keys.TAB).build().perform();
		JSExecutor.sendTextToTextBox(Property_File_Reader.projectConfigurationReader("user_mobile_text_field"), mob);
		act.sendKeys(Keys.ENTER).perform();
//		driver.findElement(By.xpath("//input[@id='user_mobile']")).sendKeys(mob); // by using .sendkeys() it add the new mobile number next to old mobile number 
		
		/* Scroll until element visible */
		JSExecutor.scrollToElement(Property_File_Reader.projectConfigurationReader("personal_email"));
		/* enter email and other details */
		JSExecutor.sendTextToTextBox(Property_File_Reader.projectConfigurationReader("user_email"), email);
		JSExecutor.sendTextToTextBox(Property_File_Reader.projectConfigurationReader("verify_user_email"), email);
		JSExecutor.sendTextToTextBox(Property_File_Reader.projectConfigurationReader("personal_email"), email);
		JSExecutor.sendTextToTextBox(Property_File_Reader.projectConfigurationReader("conf_personal_email"), email);
		JSExecutor.scrollToElement(Property_File_Reader.projectConfigurationReader("register_step_three")); 			
			/*Below if-else statement is used for checking the element is present or not and if element is not visible 
			 * then it will throw and exception
			 * */
			WebElement element = driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("CompanySelectField")));
			if(element != null && element.isDisplayed())
			{
				JSExecutor.scrollToElement(Property_File_Reader.projectConfigurationReader("CompanySelectField"));
				JSExecutor.jsClick(Property_File_Reader.projectConfigurationReader("CompanySelectField"));
				/*EnteringValue in Auto Suggest Dropdown for Current Employer Name*/
				driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("CompanySelectTextField"))).sendKeys("F");
				Thread.sleep(500);
		     	driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("CompanySelectTextField"))).sendKeys("x");
				Thread.sleep(500);
				driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("CompanySelectTextField"))).sendKeys("b");
				Thread.sleep(1000); 
				//Get list of all the employers present in Auto suggest dropdown 
				List<WebElement> employer_list = driver.findElements(By.xpath(Property_File_Reader.projectConfigurationReader("CompanySelectTextFieldAllResultList"))); 
				Thread.sleep(1000); 
				for(WebElement a : employer_list) 
				{
					//Thread.sleep(1000);
			    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1)); //instead of applying Thread.sleep() we have applied here implicitly wait
					String name = a.getText();
					System.out.println("-->"+a.getText()); 
					if(name.equalsIgnoreCase("fxbytes")) {
						a.click(); 
						break; 
					} 
				} 
			}
			else
			{
				System.out.println("Element is not visible.");
				throw new RuntimeException("Element is not visible.");
			}
		/*
		 * clicking checkbox by js executor, we are using if-else to check wether the checkbox is checked or not 
		 * and if its not checked then check it 
		 * for this purpose (checkbox/radio button) we use .isSelected() method
		 * */
		WebElement aggrement_checkbox = driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("aggrement_checkbox")));
		if(aggrement_checkbox.isSelected())
		{
			System.out.println("checkbox is already checked so we have not clicked on it and we have moved to next line of code");
		}
		else
		{
			System.out.println("The Aggrement checking checkbox was not checked so we have checked it");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			JSExecutor.jsClick(Property_File_Reader.projectConfigurationReader("aggrement_checkbox"));
		}
		// Scroll down here now write a code to show Alert that "in 10 sec you have to enter captcha"
		JSExecutor.jsAlert("Enter Captcha in 10 SECONDS");
		Thread.sleep(3000); //here you can handle error message which is visible on the frontend on runtime 
		//Action Events to double click on the captcha field
		//act.doubleClick(driver.findElement(By.xpath("//input[@id='captcha']"))).perform();
		/*
		 * here we are checking the field below captcha field is blank or not and if it dont have any value contain in it then 
		 * click on it so that you can enter the captcha in it 
		 * */
		WebElement captcha_field = driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("captchaTextField")));
		String fieldValue = captcha_field.getAttribute("value");
		if(fieldValue.isEmpty())
		{
		System.out.println("The captcha field value was empty so we have clicked on it");
		JSExecutor.jsClick(Property_File_Reader.projectConfigurationReader("captchaTextField"));
		Thread.sleep(1000); // this is for entering the captcha in 10 second 
		}
		else
		{
		System.out.println("The captcha field value was not empty so no need to click on it");
		}	
	}
	@Test(priority = 3, groups={"regression"})
	public void step_2_click_next_button() throws InterruptedException, IOException
	{
		/* clicking on the Next button */
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		JSExecutor.jsClick(Property_File_Reader.projectConfigurationReader("register_step_three"));
	}
	@Test(priority = 4, enabled=false, dataProviderClass = Excel_File_Reader.class, dataProvider = "Excel_Mobile_Data")
	public void mobileNumberVerification(String mob) throws InterruptedException, IOException {
		Actions act = new Actions(driver);
		/* Select value from the dropdown */
		Select dp = new Select(driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("mobile_country_code"))));
		dp.selectByValue("+91");
		/* verify number */
		Waits.explicit_waitForElementToBe_Visible(By.xpath(Property_File_Reader.projectConfigurationReader("user_mobile")), 10);
		JSExecutor.sendTextToTextBox(Property_File_Reader.projectConfigurationReader("user_mobile"), mob); // here we are fetching the mobile number from the Excel Sheet 
		act.sendKeys(Keys.TAB).sendKeys(Keys.TAB).build().perform();
		Waits.explicit_waitForElementToBe_Visible(By.xpath(Property_File_Reader.projectConfigurationReader("Verify_button")), 10);
		JSExecutor.jsClick(Property_File_Reader.projectConfigurationReader("Verify_button"));
		Waits.explicit_waitForElementToBe_Visible(By.xpath(Property_File_Reader.projectConfigurationReader("Yes_do_it_button")), 10);
		JSExecutor.jsClick(Property_File_Reader.projectConfigurationReader("Yes_do_it_button"));
		Thread.sleep(15000); // Here we have to enter the OTP so wait for 15 second hence used thread.sleep()
		JSExecutor.jsClick(Property_File_Reader.projectConfigurationReader("otp_verify_submit_button"));
		Thread.sleep(3000);
		JSExecutor.jsClick(Property_File_Reader.projectConfigurationReader("ok_button"));
	}
	@Test(priority = 4, dataProviderClass = Excel_File_Reader.class, dataProvider = "Excel_User_Address_Data")
	public void step_3_Basic_Data(String address) throws InterruptedException, IOException {
		Actions act = new Actions(driver);
		Waits.explicit_waitForElementToBe_Visible(By.xpath(Property_File_Reader.projectConfigurationReader("Basic_Data_address_line_one")), 120);
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Basic_Data_address_line_one"))).sendKeys(address); // fetch the Address data of the user from the excel
		/* Select state value from Auto Suggest dropdown */
		act.click(driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Basic_Data_Select_a_State")))).perform();
		Waits.explicit_waitForElementToBe_Visible(By.xpath(Property_File_Reader.projectConfigurationReader("Basic_Data_activedescendant")), 2);// until dropdown apears
		// JSExecutor.sendTextToTextBox("//input[@aria-activedescendant='bs-select-4-0']", // "Washington"
		JSExecutor.jsClick(Property_File_Reader.projectConfigurationReader("Basic_Data_activedescendant"));
		Waits.explicit_waitForElementToBe_Visible(By.xpath(Property_File_Reader.projectConfigurationReader("Basic_Data_search")), 10);
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Basic_Data_search"))).sendKeys("W");
		Thread.sleep(500);
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Basic_Data_search"))).sendKeys("a");
		Thread.sleep(500);
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Basic_Data_search"))).sendKeys("s");
		Thread.sleep(500);
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Basic_Data_search"))).sendKeys("h");
		Waits.explicit_waitForElementToBe_Visible(By.xpath(Property_File_Reader.projectConfigurationReader("Basic_Data_bs_select_4")), 10);// until dropdown apears
		List<WebElement> state_list = driver.findElements(By.xpath(Property_File_Reader.projectConfigurationReader("Basic_Data_bs_select_4")));
		for (WebElement b : state_list) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
			String name = b.getText();
			System.out.println("-->" + b.getText());
			if (name.equalsIgnoreCase("washington")) {
				b.click();
				break;
			}
		}
		Waits.explicit_waitForElementToBe_Visible(By.xpath(Property_File_Reader.projectConfigurationReader("Basic_Data_city")), 1);
		JSExecutor.sendTextToTextBox(Property_File_Reader.projectConfigurationReader("Basic_Data_city"), "NewYour");
		JSExecutor.sendTextToTextBox(Property_File_Reader.projectConfigurationReader("Basic_Data_postal_code"), "gh32");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		JSExecutor.jsAlert("Add Image in 15 SECONDS");
		/* Wait until Image is Adding */
		Thread.sleep(15000);
		JSExecutor.jsClick(Property_File_Reader.projectConfigurationReader("Basic_Data_final_submit"));
	}

	@Test(priority = 5)
	public void Step_4_Work_Experience() throws InterruptedException, IOException {
		JSExecutor.sendTextToTextBox(Property_File_Reader.projectConfigurationReader("Work_Experience_years_in_legal_ops"), "7");
		JSExecutor.sendTextToTextBox(Property_File_Reader.projectConfigurationReader("Work_Experience_year_direct_managing"), "7");
		/* Step 1 : Selecting Option "Are you licensed Attorney ?" */
		Actions act = new Actions(driver);
		Waits.explicit_waitForElementToBe_Visible(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_Licensed_Attorney")), 3);
		JSExecutor.jsClick(Property_File_Reader.projectConfigurationReader("Work_Experience_Licensed_Attorney"));
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_search_5"))).sendKeys("Y");
		Thread.sleep(500);
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_search_5"))).sendKeys("e");
		Thread.sleep(500);
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_search_5"))).sendKeys("s");
		Thread.sleep(500);
		act.sendKeys(Keys.ENTER).perform();
		/* Selecting Title */
		JSExecutor.sendTextToTextBox(Property_File_Reader.projectConfigurationReader("Work_Experience_title"), "QA");
		/* Step 1 : Selecting Current Role */
		Waits.explicit_waitForElementToBe_Visible(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_Select_Current_Level")), 3);
		JSExecutor.jsClick(Property_File_Reader.projectConfigurationReader("Work_Experience_Select_Current_Level"));
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_search_11"))).sendKeys("S");
		Thread.sleep(500);
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_search_11"))).sendKeys("r");
		Thread.sleep(500);
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_search_11"))).sendKeys(".");
		/* Step 2 : Selecting Current Role */
		List<WebElement> currentRole_list = driver.findElements(
		By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_select_11")));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		for (WebElement d : currentRole_list) {
			Thread.sleep(2000);
			String name = d.getText();
			System.out.println("-->" + d.getText());
			if (name.equalsIgnoreCase("Sr. Analyst")) {
				d.click();
				break;
			}
		}
		/* Step 1 : Selecting Start Date */
		Waits.explicit_waitForElementToBe_Visible(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_Select_Start_Date")), 3);
		JSExecutor.jsClick(Property_File_Reader.projectConfigurationReader("Work_Experience_Select_Start_Date"));
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_search_12"))).sendKeys("2");
		Thread.sleep(500);
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_search_12"))).sendKeys("0");
		Thread.sleep(500);
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_search_12"))).sendKeys("2");
		Thread.sleep(500);
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_search_12"))).sendKeys("2");
		Thread.sleep(500);
		act.sendKeys(Keys.ENTER).perform();
		/* Step 2 : Selecting Start Date 
		 		//We have commented this because we have used another method for selecting the start date 
				List<WebElement> startDate_list = driver.findElements(By.xpath("//div[@id='bs-select-12']//descendant::ul[@class='dropdown-menu inner ']//descendant::li"));
				Thread.sleep(2000);
				for(WebElement e : startDate_list)
				{
					Thread.sleep(2000);
					String name = e.getText();
					System.out.println("-->"+e.getText());
					if(name.equalsIgnoreCase("2022")) 
					{
						e.click();
						break;
					}
				}
		*/
		/* Step 1 : Selecting Option licensed Attorney */
		Waits.explicit_waitForElementToBe_Visible(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_Attorney_Role")), 3);
		JSExecutor.jsClick(Property_File_Reader.projectConfigurationReader("Work_Experience_Attorney_Role"));
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_search_14"))).sendKeys("Y");
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_search_14"))).sendKeys("e");
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_search_14"))).sendKeys("s");
		act.sendKeys(Keys.ENTER).perform();
		/* Step 2 : Selecting Option licensed Attorney 
				//We have commented this because we have used another method for selecting the licensed Attorney  
				List<WebElement> Licensed_Attorney_Role_list = driver.findElements(By.xpath("//div[@id='bs-select-14']//descendant::ul//descendant::li"));
				Thread.sleep(2000);
				for(WebElement f : Licensed_Attorney_Role_list)
				{
					Thread.sleep(2000);
					String name = f.getText();
					System.out.println("-->"+f.getText());
					if(name.equalsIgnoreCase("yes")) 
					{
						f.click();
						break;
					}
				}
		 */
		/* Step 1: Selecting Option 'Do You Report to the GC?'*/
		Waits.explicit_waitForElementToBe_Visible(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_Report_to_the_GC")), 3);
		JSExecutor.jsClick(Property_File_Reader.projectConfigurationReader("Work_Experience_Report_to_the_GC"));
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_search_17"))).sendKeys("Y");
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_search_17"))).sendKeys("e");
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_search_17"))).sendKeys("s");
		act.sendKeys(Keys.ENTER).perform();
		/* Step 2: Selecting Option 'Do You Report to the GC?' 
		 		//We have commented this because we have used another method for selecting the 'Do You Report to the GC?'   
				List<WebElement> Do_You_Report_to_the_GC = driver.findElements(By.xpath("//div[@id='bs-select-17']//descendant::ul//descendant::li"));
				Thread.sleep(2000);
				for(WebElement g : Do_You_Report_to_the_GC)
				{
					Thread.sleep(2000);
					String name = g.getText();
					System.out.println("-->"+g.getText());
					if(name.equalsIgnoreCase("yes")) 
					{
						g.click();
						break;
					}
				}
		 */
		/* clicking on he 'Years You Managed People at this Company ' */
		JSExecutor.sendTextToTextBox(Property_File_Reader.projectConfigurationReader("Work_Experience_no_of_years_managed"), "7");
		/* Step 1: Selecting Option 'US State in which You Work' */
		JSExecutor.jsClick(Property_File_Reader.projectConfigurationReader("Work_Experience_Select_US_State"));
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_search_19"))).sendKeys("c");
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_search_19"))).sendKeys("a");
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_search_19"))).sendKeys("l");
		/* Step 2: Selecting Option 'US State in which You Work' */
		List<WebElement> US_State_in_which_You_Work = driver
				.findElements(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_select_19")));
		Thread.sleep(2000);
		for (WebElement g : US_State_in_which_You_Work) {
			Thread.sleep(2000);
			String name = g.getText();
			if (name.equalsIgnoreCase("california")) {
				g.click();
				break;
			}
		}
		/* Step 1: Selecting Option 'Department' */
		Waits.explicit_waitForElementToBe_Visible(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_Select_Department")), 3);
		JSExecutor.jsClick(Property_File_Reader.projectConfigurationReader("Work_Experience_Select_Department"));
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_search_20"))).sendKeys("i");
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_search_20"))).sendKeys("t");
		act.sendKeys(Keys.ENTER).perform();
		/* Step 2: Selecting Option 'Department'
				List<WebElement> Department = driver.findElements(By.xpath("//div[@id='bs-select-20']//descendant::ul//descendant::li"));
				Thread.sleep(2000);
				for(WebElement g : Department)
				{
					Thread.sleep(2000);
					String name = g.getText();
					System.out.println("-->"+g.getText());
					if(name.equalsIgnoreCase("IT")) 
					{
						g.click();
						break;
					}
				}
		 */
		/* Step 1: Selecting Option 'Legal Department Size' */
		Waits.explicit_waitForElementToBe_Visible(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_Legal_Department_Size")), 3);
		JSExecutor.jsClick(Property_File_Reader.projectConfigurationReader("Work_Experience_Legal_Department_Size"));
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_search_21"))).sendKeys("2");
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_search_21"))).sendKeys("5");
		Thread.sleep(500);
		act.sendKeys(Keys.ENTER).perform();
		/* Step 2: Selecting Option 'Legal Department Size' 
				List<WebElement> Legal_Department_Size = driver.findElements(By.xpath("//div[@id='bs-select-21']//descendant::ul//descendant::li"));
				Thread.sleep(2000);
				for(WebElement g : Legal_Department_Size)
				{
					Thread.sleep(2000);
					String name = g.getText();
					System.out.println("-->"+g.getText());
					if(name.equalsIgnoreCase("25 or Fewer")) 
					{
						g.click();
						break;
					}
				}
		 */
		/* Step 1: Selecting Option 'Do You Lead The Legal Ops Team?' */
		Waits.explicit_waitForElementToBe_Visible(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_Do_You_Lead_The_Legal_Ops_Team")), 3);
		JSExecutor.jsClick(Property_File_Reader.projectConfigurationReader("Work_Experience_Do_You_Lead_The_Legal_Ops_Team"));
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_search_22"))).sendKeys("n");
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_search_22"))).sendKeys("o");
		act.sendKeys(Keys.ENTER).perform();
		/* Step 2: Selecting Option 'Do You Lead The Legal Ops Team?' 
				List<WebElement> Do_You_Lead_The_Legal_Ops_Team = driver.findElements(By.xpath("//div[@id='bs-select-22']//descendant::ul//descendant::li"));
				Thread.sleep(2000);
				for(WebElement g : Do_You_Lead_The_Legal_Ops_Team)
				{
					Thread.sleep(2000);
					String name = g.getText();
					System.out.println("-->"+g.getText());
					if(name.equalsIgnoreCase("no")) 
					{
						g.click();
						break;
					}
				}
		 */
		/* clicking on the Next button */
		Waits.explicit_waitForElementToBe_Visible(By.xpath(Property_File_Reader.projectConfigurationReader("Work_Experience_final_submit_12")), 3);
		JSExecutor.jsClick(Property_File_Reader.projectConfigurationReader("Work_Experience_final_submit_12"));
	}

	@Test(priority = 6)
	public void Step_5_Annual_Compensation() throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Annual_Compensation_base_comp_2020_1"))).sendKeys("10000");
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Annual_Compensation_comp_bonus_2020"))).sendKeys("10000");
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Annual_Compensation_comp_stock_2020"))).sendKeys("10000");
		/* Step 1 : Selecting Job Level */
		JSExecutor.jsClick(Property_File_Reader.projectConfigurationReader("Annual_Compensation_Select"));
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Annual_Compensation_search_43"))).sendKeys("S");
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Annual_Compensation_search_43"))).sendKeys("r");
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Annual_Compensation_search_43"))).sendKeys(".");
		Thread.sleep(1000);
		/* Step 2 : Selecting Current Role */
		List<WebElement> currentRole_list = driver.findElements(By.xpath(Property_File_Reader.projectConfigurationReader("Annual_Compensation_elect")));
		for (WebElement d : currentRole_list) {
			Thread.sleep(2000);
			String name = d.getText();
			System.out.println("-->" + d.getText());
			if (name.equalsIgnoreCase("Sr. Analyst")) {
				d.click();
				break;
			}
		}
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Annual_Compensation_base_comp_2020_2"))).sendKeys("10000");
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Annual_Compensation_comp_bonus_2020"))).sendKeys("10000");
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("Annual_Compensation_comp_stock_2020"))).sendKeys("10000");
		/* clicking on the Next button */
		Waits.explicit_waitForElementToBe_Clickable(By.xpath(Property_File_Reader.projectConfigurationReader("Annual_Compensation_final_submit_3")), 3);
		JSExecutor.jsClick(Property_File_Reader.projectConfigurationReader("Annual_Compensation_final_submit_3"));
	}

	@Test(priority = 7)
	public void Step_5_Education() throws InterruptedException {
		Actions act = new Actions(driver);
		/* Step 1: Selecting Option 'Degree/Certification' */
		Waits.explicit_waitForElementToBe_Clickable(By.xpath("(//select[@id='education_name'])[1]"), 3);
		JSExecutor.jsClick("(//select[@id='education_name'])[1]");
		driver.findElement(By.xpath("(//input[@type='search'])[44]")).sendKeys("j");
		driver.findElement(By.xpath("(//input[@type='search'])[44]")).sendKeys("d");
		act.sendKeys(Keys.ENTER).perform();
		/* Step 1 : Selecting School/University Attended */
		Waits.explicit_waitForElementToBe_Clickable(By.xpath("(//div[@class='filter-option'])[45]"), 3);
		JSExecutor.jsClick("(//div[@class='filter-option'])[45]");
		driver.findElement(By.xpath("(//input[@type='search'])[45]")).sendKeys("a");
		Thread.sleep(500);
		driver.findElement(By.xpath("(//input[@type='search'])[45]")).sendKeys("m");
		driver.findElement(By.xpath("(//input[@type='search'])[45]")).sendKeys("e");
		driver.findElement(By.xpath("(//input[@type='search'])[45]")).sendKeys("r");
		/* Step 2 : Selecting School/University Attended */
		List<WebElement> currentRole_list = driver.findElements(By.xpath("//div[@id='bs-select-24']//descendant::ul[@class='dropdown-menu inner']//descendant::li"));
		Thread.sleep(2000);
		for (WebElement d : currentRole_list) {
			Thread.sleep(2000);
			String name = d.getText();
			if (name.equalsIgnoreCase("American Academy of Art")) {
				d.click();
				break;
			}
		}
		/* Step 1: Selecting Option 'Program Level' */
		JSExecutor.jsClick("(//div[text()='Select Program Level'])[1]");
		driver.findElement(By.xpath("(//input[@type='search'])[46]")).sendKeys("c");
		driver.findElement(By.xpath("(//input[@type='search'])[46]")).sendKeys("e");
		driver.findElement(By.xpath("(//input[@type='search'])[46]")).sendKeys("r");
		act.sendKeys(Keys.ENTER).perform();
		/* Step 1 : Selecting Start Date */
		JSExecutor.jsClick("(//div[text()='Select Start Date (Year Only)'])[2]");
		driver.findElement(By.xpath("(//input[@type='search'])[47]")).sendKeys("2");
		driver.findElement(By.xpath("(//input[@type='search'])[47]")).sendKeys("0");
		driver.findElement(By.xpath("(//input[@type='search'])[47]")).sendKeys("2");
		driver.findElement(By.xpath("(//input[@type='search'])[47]")).sendKeys("2");
		act.sendKeys(Keys.ENTER).perform();
		/* Step 1 : Selecting End Date */
		JSExecutor.jsClick("//div[text()='Select End Date (Year only)']");
		driver.findElement(By.xpath("(//input[@type='search'])[48]")).sendKeys("2");
		driver.findElement(By.xpath("(//input[@type='search'])[48]")).sendKeys("0");
		driver.findElement(By.xpath("(//input[@type='search'])[48]")).sendKeys("2");
		driver.findElement(By.xpath("(//input[@type='search'])[48]")).sendKeys("3");
		act.sendKeys(Keys.ENTER).perform();
		/* Step 1 : Selecting Area of Study */
		JSExecutor.jsClick("(//div[text()='Select Area of Study'])[1]");
		driver.findElement(By.xpath("(//input[@type='search'])[49]")).sendKeys("a");
		driver.findElement(By.xpath("(//input[@type='search'])[49]")).sendKeys("r");
		driver.findElement(By.xpath("(//input[@type='search'])[49]")).sendKeys("t");
		driver.findElement(By.xpath("(//input[@type='search'])[49]")).sendKeys("s");
		act.sendKeys(Keys.ENTER).perform();
		/* clicking on the Next button */
		JSExecutor.jsClick("(//button[@id='final-submit'])[4]");
	}
}
