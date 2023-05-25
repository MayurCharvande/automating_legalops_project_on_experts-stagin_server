package org.fxbytes.web.pages;

import org.fxbytes.web.base.BaseClass;
import org.fxbytes.web.base.InitiateBrowser_For_AdminLogin;
import org.openqa.selenium.By;

public class Statistics extends BaseClass{
	public void clickOnStatistic()
	{
		driver.findElement(By.xpath("//a[@id='admin-dashboard']")).click();
	}
}
