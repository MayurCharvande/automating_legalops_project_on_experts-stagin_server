package org.fxbytes.web.pages;

import java.util.List;

import org.fxbytes.web.base.InitiateBrowser_User_Login;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class User_001_MyConnection extends InitiateBrowser_User_Login{
	public void clickOnMyConnection() throws InterruptedException
	{
		driver1.findElement(By.xpath("//a[@id='connections']")).click();
		Thread.sleep(3000);
	}
	public void clickOnMyMessages() throws InterruptedException
	{
		driver1.findElement(By.xpath("(//a[@class='waves-effect'])[5]")).click();
		Thread.sleep(3000);
	}
	public void clickOnTheUserName(String click_username) throws InterruptedException
	{
		List<WebElement> userTitle = driver1.findElements(By.xpath("//div[@id='message-list-user']//descendant::div[@class='message-user-detail']//descendant::h2"));
		int total_Users = userTitle.size();
		System.out.println("Total Users are : " + total_Users);
		for(WebElement a : userTitle) 
		{
			System.out.println(a.getText());
			if(a.equals(click_username))
			{
				a.click();
				break;
			}
		}
		Thread.sleep(3000);
	}
	public void sendMessage(String TestingMessage) throws InterruptedException
	{	
		Actions act = new Actions(driver1);
		for(int i = 0; i<=10; i++)
		{
			WebElement textField = driver1.findElement(By.xpath("")); //path of the text field
			textField.click();
			textField.sendKeys(i + " --> " + TestingMessage);
			act.sendKeys(Keys.ENTER).perform();
			Thread.sleep(3000);
		}
	}
}
