package org.fxb.experts_staging.testcases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.fxb.experts_staging.base.BaseClass;
import org.fxb.experts_staging.pages.OpenWebPageToTestBrokenLinks;
import org.fxb.experts_staging.utilities.BrokenLinks;
import org.testng.annotations.Test;

public class TC_For_CheckingBrokenLinks extends BaseClass
{
	@Test
	public void demo() throws InterruptedException, IOException
	{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		OpenWebPageToTestBrokenLinks pbl = new OpenWebPageToTestBrokenLinks();
		pbl.openTheWebPage("https://legalops.com/"); /*Web page*/
	//	pbl.openTheWebPage("https://cli.legalops.com"); /*Web page*/
	//	pbl.openTheWebPage("https://volcanus.in/"); /*Web page*/	
		
		/*checking broken links*/
		BrokenLinks bl = new BrokenLinks(); 
		//bl.brokenLinkFindAndMoveToNextLink();
		bl.brokenLink();
	}
}
