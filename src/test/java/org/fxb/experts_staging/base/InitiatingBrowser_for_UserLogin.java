package org.fxb.experts_staging.base;

import org.openqa.selenium.By;

public class InitiatingBrowser_for_UserLogin extends BaseClass
{
	public InitiatingBrowser_for_UserLogin(String urlOfTheLoginPage)
	{
		driver.navigate().to(urlOfTheLoginPage);
		driver.manage().window().maximize();
	}
	public void loginPage(String email, String password) throws InterruptedException
	{
		driver.findElement(By.xpath("//input[@id='user_email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		Thread.sleep(10000);
		driver.findElement(By.xpath("//button[@class='js-cookie-consent-agree cookie-consent__agree']")).click();
		driver.findElement(By.xpath("//button[@id='submit_login']")).click();
	
	}
}
