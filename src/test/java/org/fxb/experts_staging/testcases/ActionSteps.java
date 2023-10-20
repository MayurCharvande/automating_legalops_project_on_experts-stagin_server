package org.fxb.experts_staging.testcases;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.fxb.experts_staging.base.InitiatingBrowser_for_Registration;
import org.fxb.experts_staging.js.JSExecutor;
import org.fxb.experts_staging.utilities.Waits;
import org.fxb.web.library.Property_File_Reader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ActionSteps extends InitiatingBrowser_for_Registration
{
	/*Below code is for Restricted Page*/
	@Given("Open the Chrome and launch the Web application")
	public void open_the_chrome_and_launch_the_web_application() throws IOException {
		System.setProperty("webdriver.chrome.driver",Property_File_Reader.projectConfigurationReader("OfficialChromebrowserDriverPath"));
		//System.setProperty("webdriver.chrome.driver",Property_File_Reader.projectConfigurationReader("LocalChromebrowserDriverPath"));
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(co);
		driver.navigate().to(Property_File_Reader.projectConfigurationReader("ApplicationURL")); // here we are calling the value(url) from the internal file (i.e Property file) for that purpose we use className.methodName(keyValue)
		//driver.navigate().to("https://experts-staging.legalops.com/register/b694ec07-4ab6-4ce6-b9bc-8b47e3d4c747");
		driver.manage().window().maximize();
	}

	@When("Restricted Username and Password")
	public void restricted_username_and_password() throws IOException {
		/* Here we apply the wait until the element is visible */
		By element_locator = By.xpath("//input[@type='text']");
		Waits.explicit_waitForElementToBe_Visible(element_locator, 10);
		/*Restricted Page login code*/
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("RestrictionPageUser"))).sendKeys(Property_File_Reader.projectConfigurationReader("UserName"));
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("RestrictionPagePassword"))).sendKeys(Property_File_Reader.projectConfigurationReader("Password"));
	}

	@When("Click on the Submit button")
	public void click_on_the_submit_button() throws IOException {
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("RestrictionPageSubmitButton"))).click();
	}

	@Then("Redirect to the Registration page")
	public void redirect_to_the_registration_page() {
		//Here apply assertion to check where you are redirected to the correct page or not
		String expectedTitle = "LegalOps";
		System.out.println("Expected Title is --> "+ driver.getTitle());
		Assert.assertEquals(driver.getTitle(), expectedTitle);
	}
	/*Below code is for Step 1*/
	@Given("Select the CURRENTLY EMPLOYED checkbox")
	public void select_the_currently_employed_checkbox() throws IOException {   
		Waits.explicit_waitForElementToBe_Visible(By.xpath(Property_File_Reader.projectConfigurationReader("EmployedUser")), 10);
		driver.findElement(By.xpath(Property_File_Reader.projectConfigurationReader("EmployedUser"))).click();// For Employed User
	}

	@When("Select the Aggrement Checkbox")
	public void select_the_aggrement_checkbox() throws IOException {	    
		JSExecutor.jsClick(Property_File_Reader.projectConfigurationReader("AgreeCheckbox"));
	}

	@When("Click on the Confirm Eligibility Next button")
	public void click_on_the_confirm_eligibility_next_button() throws IOException {
		//driver.findElement(By.xpath("(//button[@type='button'])[1]")).click();
		JSExecutor.jsClick(Property_File_Reader.projectConfigurationReader("ConfirmEligibilityNextButton")); // on the 1440 resolution there is a footer hence you can use this code line instead of the above
	}

	@Then("Redirect to the Step2 page")
	public void redirect_to_the_rtep2_page(){
		String expectedTitle = "LegalOps";
		System.out.println("Expected Title is --> "+ driver.getTitle());
		Assert.assertEquals(driver.getTitle(), expectedTitle);

	}
	/*Below code is for Step 2*/
	@Given("the user is on the Create Your Account page")
	public void the_user_is_on_the_create_your_account_page() {
		String expectedTitle = "LegalOps";
		System.out.println("Expected Title is --> "+ driver.getTitle());
		Assert.assertEquals(driver.getTitle(), expectedTitle);		
	}

	@When("the user provides the user information")
	public void the_user_provides_the_user_information(String fname, String lname, String password, String email, String mob, String country_code) throws IOException {
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
	}

	@When("the user selects their company as {string}")
	public void the_user_selects_their_company_as(String string) throws IOException, InterruptedException {
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

	}

	@When("the user agrees to the terms and conditions")
	public void the_user_agrees_to_the_terms_and_conditions() throws IOException {
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

	}

	@When("the user enters the captcha code within {int} seconds")
	public void the_user_enters_the_captcha_code_within_seconds(Integer int1) throws InterruptedException, IOException {
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

	@Then("the registration process is completed successfully")
	public void the_registration_process_is_completed_successfully() throws IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		JSExecutor.jsClick(Property_File_Reader.projectConfigurationReader("register_step_three"));
	}
/*Below are the In Progress steps*/
	
	@When("the user selects the country code for mobile number")
	public void the_user_selects_the_country_code_for_mobile_number() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user enters the mobile number [mob]")
	public void the_user_enters_the_mobile_number_mob() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user clicks the Verify button")
	public void the_user_clicks_the_verify_button() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user confirms the action")
	public void the_user_confirms_the_action() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the OTP verification process is initiated")
	public void the_otp_verification_process_is_initiated() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user enters the address as [address]")
	public void the_user_enters_the_address_as_address() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user selects a state")
	public void the_user_selects_a_state() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user enters the city as {string}")
	public void the_user_enters_the_city_as(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user enters the postal code as {string}")
	public void the_user_enters_the_postal_code_as(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user adds an image within {int} seconds")
	public void the_user_adds_an_image_within_seconds(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user submits the basic data")
	public void the_user_submits_the_basic_data() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the basic data entry is completed successfully")
	public void the_basic_data_entry_is_completed_successfully() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user enters {string} years in legal operations")
	public void the_user_enters_years_in_legal_operations(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user enters {string} years in direct managing")
	public void the_user_enters_years_in_direct_managing(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user selects the role of {string}")
	public void the_user_selects_the_role_of(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user selects {string} for being a licensed attorney")
	public void the_user_selects_for_being_a_licensed_attorney(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user enters the job title as {string}")
	public void the_user_enters_the_job_title_as(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user selects the current level as {string}")
	public void the_user_selects_the_current_level_as(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user selects the start date")
	public void the_user_selects_the_start_date() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user selects {string} for attorney role")
	public void the_user_selects_for_attorney_role(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user selects {string} for reporting to the General Counsel")
	public void the_user_selects_for_reporting_to_the_general_counsel(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user enters {string} for the number of years managed")
	public void the_user_enters_for_the_number_of_years_managed(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user selects the US state of California")
	public void the_user_selects_the_us_state_of_california() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user selects the IT department")
	public void the_user_selects_the_it_department() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user selects the legal department size as {string}")
	public void the_user_selects_the_legal_department_size_as(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user selects {string} for leading the legal operations team")
	public void the_user_selects_for_leading_the_legal_operations_team(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user submits the work experience")
	public void the_user_submits_the_work_experience() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the work experience entry is completed successfully")
	public void the_work_experience_entry_is_completed_successfully() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user enters {string} for the base compensation in {int}")
	public void the_user_enters_for_the_base_compensation_in(String string, Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user enters {string} for the bonus compensation in {int}")
	public void the_user_enters_for_the_bonus_compensation_in(String string, Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user enters {string} for the stock compensation in {int}")
	public void the_user_enters_for_the_stock_compensation_in(String string, Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user selects the job level as {string}")
	public void the_user_selects_the_job_level_as(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user enters {string} for the base compensation in {int} \\(2nd time)")
	public void the_user_enters_for_the_base_compensation_in_2nd_time(String string, Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user enters {string} for the bonus compensation in {int} \\(2nd time)")
	public void the_user_enters_for_the_bonus_compensation_in_2nd_time(String string, Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user enters {string} for the stock compensation in {int} \\(2nd time)")
	public void the_user_enters_for_the_stock_compensation_in_2nd_time(String string, Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user clicks the Next button")
	public void the_user_clicks_the_next_button() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the annual compensation details are entered successfully")
	public void the_annual_compensation_details_are_entered_successfully() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user selects the degree\\/certification option")
	public void the_user_selects_the_degree_certification_option() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user selects the school\\/university attended as {string}")
	public void the_user_selects_the_school_university_attended_as(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user selects the program level as {string}")
	public void the_user_selects_the_program_level_as(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user selects the start date as {string}")
	public void the_user_selects_the_start_date_as(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user selects the end date as {string}")
	public void the_user_selects_the_end_date_as(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user selects the area of study as {string}")
	public void the_user_selects_the_area_of_study_as(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the education details are entered successfully")
	public void the_education_details_are_entered_successfully() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

}
