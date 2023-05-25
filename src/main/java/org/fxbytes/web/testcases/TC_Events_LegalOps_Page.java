package org.fxbytes.web.testcases;

import java.io.IOException;

import org.fxbytes.web.base.BaseClass;
import org.fxbytes.web.pages.Events_LegalOps_Page;
import org.fxbytes.web.utilities.BrokenLinks;
import org.testng.annotations.Test;

public class TC_Events_LegalOps_Page extends BaseClass{
	@Test
	public void eventPageTesting() throws IOException 
	{
		Events_LegalOps_Page ep = new Events_LegalOps_Page();
		ep.event_page_login();
		
		BrokenLinks bl = new BrokenLinks();
		bl.brokenLink();
	}
}
