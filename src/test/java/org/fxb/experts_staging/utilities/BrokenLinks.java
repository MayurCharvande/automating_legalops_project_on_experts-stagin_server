package org.fxb.experts_staging.utilities;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.fxb.experts_staging.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrokenLinks extends BaseClass
{
	
	public WebDriver brokenLink() throws IOException
	{	
		
		  //  driver = new ChromeDriver();   
/*Get lists of all links & images*/
			List<WebElement> linkslist = driver.findElements(By.tagName("a"));
			linkslist.addAll(driver.findElements(By.tagName("img")));
			System.out.println("size of full links & images--->"+linkslist.size());//show total number of links and images
			
			List<WebElement> activelinks = new ArrayList<WebElement>();
/*exclude all the links/images that doesn't have any href attribute*/
			for(int i=0;i<linkslist.size();i++)
			{
				System.out.println(linkslist.get(i).getAttribute("href"));
				
				if(linkslist.get(i).getAttribute("href")!=null && (! linkslist.get(i).getAttribute("href").contains("javascript")))
				{
					activelinks.add(linkslist.get(i));
				}
			}
//get the size of active links list:
			System.out.println("size of active links and images--->"+activelinks.size());//show total number of active links
			
/*3. Check the href url with httpconnection api
					 200--ok
					 404--not found
					 500--internal error
					 400--bad request*/
					
			for(int j=0;j<activelinks.size();j++) 
			{
				HttpURLConnection connection = (HttpURLConnection) new URL(activelinks.get(j).getAttribute("href")).openConnection();
				connection.connect();
				String response = connection.getResponseMessage();//ok
				connection.disconnect();
				System.out.println(activelinks.get(j).getAttribute("href")+"--->"+response);
			}
			return driver;
		}	
	
	/*Code to Check the Broken link and if broken link found in between then print it and move to the next link*/
	
		public void brokenLinkFindAndMoveToNextLink()
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			List<WebElement> links = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("a")));
			System.out.println("1");
			for (WebElement link : links) {
				System.out.println("2");
			    String href = link.getAttribute("href");
			    System.out.println("3");
			    if (href != null && !href.isEmpty()) {
			    	System.out.println("4");
			        try {
			        	System.out.println("5");
			            HttpURLConnection connection = (HttpURLConnection) new URL(href).openConnection();
			            connection.setRequestMethod("HEAD");
			            int responseCode = connection.getResponseCode();
			            System.out.println("6");
			            if (responseCode < 400) { // Link is valid
			            	System.out.println("7");
			                link.click();
			                System.out.println("8");
			                // Perform necessary actions on the new page
			                driver.navigate().back(); // Navigate back to the original page
			                System.out.println("9");
			            } else {
			            	System.out.println("10");
			                System.out.println("Broken link: " + href);
			            }
			        } catch (IOException e) {
			        	System.out.println("11");
			            System.out.println("Failed to check link: " + href);
			            System.out.println("12");
			        }
			    }

			    try {
			        link.click();
			    } catch (StaleElementReferenceException e) {
			        link = driver.findElement(By.linkText("Example Link"));
			        link.click();
			    
				}
			}
		}
}
