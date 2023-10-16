package org.fxb.experts_staging.js;

import org.fxb.experts_staging.base.BaseClass;
import org.fxb.experts_staging.testcases.TC_For_Registration_Experts_Staging;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class JSExecutor extends TC_For_Registration_Experts_Staging
{	
	public static void sendTextToTextBox(String elementXpath, String textToEnter)
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		WebElement element = driver.findElement(By.xpath(elementXpath));
		jse.executeScript("arguments[0].value=arguments[1];", element, textToEnter);
	}
	public static void sendNumberToNumberField(String elementXpath, double integerToEnter)
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		WebElement element = driver.findElement(By.xpath(elementXpath));
		jse.executeScript("arguments[0].value=arguments[1];", element, integerToEnter);
	}
	public static void scrollToElement(String xpathOfElement)
	{
		WebElement element = driver.findElement(By.xpath(xpathOfElement));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();

	}
	public static void jsClick(String elementXpath)
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		//label[text()='I have read and understood the Membership Terms and Conditions ']
		WebElement element = driver.findElement(By.xpath(elementXpath));
		jse.executeScript("arguments[0].click();", element);
	}
	public static void jsAlert(String message) throws InterruptedException
	{
		/*Generating Alert popup*/
		String script="alert('"+message+"');"; 
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript(script);
		/*Closing the Alert popup*/
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
	}
	public static void highlightElement(String elementXpath) 
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		 jse.executeScript("arguments[0].style.border='3px solid red'", elementXpath);
	}	
}
