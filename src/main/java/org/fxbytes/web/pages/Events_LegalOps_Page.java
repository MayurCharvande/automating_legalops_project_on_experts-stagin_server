package org.fxbytes.web.pages;

import org.fxbytes.web.base.BaseClass;
import org.openqa.selenium.By;

public class Events_LegalOps_Page extends BaseClass{
	public void event_page_login() 
	{
		driver.navigate().to("https://events.legalops.com/admin");
		driver.findElement(By.xpath("//input[@name='user_email']")).sendKeys("admin@fxbytes.com");//username
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys("Fxbytes@123");//password
		driver.findElement(By.xpath("//button[text()='Log In']")).click();//click login
	}
}
