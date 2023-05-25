package org.fxbytes.web.jsexecutor;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class JsExecutor //extends Initiate_Registration
{
	
	public static WebDriver driver = new ChromeDriver();
	public static JavascriptExecutor jse = (JavascriptExecutor)driver;

	public static void jsClick(String elementXpath)
	{
		//label[text()='I have read and understood the Membership Terms and Conditions ']
		WebElement element = driver.findElement(By.xpath(elementXpath));
		jse.executeScript("arguments[0].click();", element);
	}
	public static void sendTextToTextBox(String elementXpath, String textToEnter)
	{
		WebElement element = driver.findElement(By.xpath(elementXpath));
		jse.executeScript("arguments[0].value=arguments[1];", element, textToEnter);	
	}
	public static void scrollToElement(String xpathOfElement)
	{
		WebElement element = driver.findElement(By.xpath(xpathOfElement));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();

	}
}
