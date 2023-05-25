package org.fxb.experts_staging.testcases;

import java.io.IOException;

import org.fxb.experts_staging.base.BaseClass;
import org.fxb.experts_staging.base.InitiatingBrowser_for_UserLogin;
import org.fxb.experts_staging.utilities.BrokenLinks;
import org.testng.annotations.Test;

public class TC_for_UserLogin extends BaseClass{
	@Test
	public void userLogin() throws InterruptedException, IOException
	{
		InitiatingBrowser_for_UserLogin ul = new InitiatingBrowser_for_UserLogin("https://experts-staging.legalops.com");
		ul.loginPage("mayur.charvande+986@fxbytes.com", "Fxbytes@123");
		BrokenLinks bl = new BrokenLinks();
		bl.brokenLink();
	}
}
