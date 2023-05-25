package org.fxbytes.web.validations;

import org.fxbytes.web.base.InitiateBrowser_For_AdminLogin;
import org.testng.Assert;

public class PageValidation extends InitiateBrowser_For_AdminLogin
{
	public static void textValidation(String actualText, String expectedText)
	{
		System.out.println("Actual text is = "+ actualText);
		System.out.println("Expected text is = "+ expectedText);
		
		Assert.assertEquals(actualText, expectedText);
	}
}
