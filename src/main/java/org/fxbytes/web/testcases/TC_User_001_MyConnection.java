package org.fxbytes.web.testcases;

import org.fxbytes.web.base.InitiateBrowser_User_Login;
import org.fxbytes.web.pages.User_001_MyConnection;
import org.fxbytes.web.utilities.BrokenLinks;
import org.testng.annotations.Test;

public class TC_User_001_MyConnection extends InitiateBrowser_User_Login
{
	@Test
	public void testingWithValidCCridentials() throws InterruptedException
	{

		
		User_001_MyConnection mc = new User_001_MyConnection();
		mc.clickOnMyConnection();
		mc.clickOnMyMessages();
		mc.clickOnTheUserName("Mayur Charvande");
		mc.sendMessage("TestingMessage");
	}
}
