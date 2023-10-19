package org.fxb.experts_staging.testcases;

import java.io.IOException;

import org.fxb.experts_staging.base.InitiatingBrowser_for_Registration;
import org.fxb.experts_staging.utilities.Waits;
import org.fxb.web.library.Property_File_Reader;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ActionSteps extends InitiatingBrowser_for_Registration
{
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
	}

}
