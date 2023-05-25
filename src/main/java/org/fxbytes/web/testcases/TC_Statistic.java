package org.fxbytes.web.testcases;

import java.time.Duration;

import org.fxbytes.web.base.InitiateBrowser_For_AdminLogin;
import org.fxbytes.web.pages.Statistics;
import org.fxbytes.web.utilities.BrokenLinks;
import org.fxbytes.web.validations.PageValidation;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class TC_Statistic extends InitiateBrowser_For_AdminLogin
{
	@Test
	public void testWithValidCridentials()
	{
		BrokenLinks bl = new BrokenLinks();
		bl.baseTest();
		Statistics obj_stat = new Statistics();
		obj_stat.clickOnStatistic();
		System.out.println("a");
		driver1.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		System.out.println("b");
		PageValidation.textValidation(driver1.findElement(By.xpath("(//h5[@class='dashboard-card-text'])[1]")).getText(),"19");
	}
}
