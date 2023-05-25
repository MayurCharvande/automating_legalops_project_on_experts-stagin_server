package org.fxb.experts_staging.pages;

import java.io.IOException;

import org.fxb.experts_staging.base.BaseClass;
import org.fxb.experts_staging.utilities.BrokenLinks;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class OpenWebPageToTestBrokenLinks extends BaseClass
{
//public WebDriver driver;
	public void openTheWebPage(String url) throws InterruptedException, IOException
	{
		//driver.navigate().to("https://experts-staging.legalops.com/register/b694ec07-4ab6-4ce6-b9bc-8b47e3d4c747");
		driver.navigate().to(url);
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}
}
