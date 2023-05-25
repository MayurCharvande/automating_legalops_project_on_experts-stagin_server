package org.fxb.experts_staging.utilities;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBrokenLinks 
{
	public void brokenlinks()
	{
		   // Set system property for ChromeDriver
	    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fxbytes\\eclipse-workspace\\Fxbytes\\src\\main\\resources\\chromedriver.exe");

	    // Create a new instance of ChromeDriver
	    WebDriver driver = new ChromeDriver();

	    // Navigate to the page to be tested
	    driver.get("https://www.example.com");

	    // Get all the links on the page using the <a> tag
	    List<WebElement> links = driver.findElements(By.tagName("a"));

	    // Loop through each link and check if it's broken
	    for (WebElement link : links) 
	    {
	        String url = link.getAttribute("href");
	        if (url != null && !url.contains("javascript")) 
	        {
	            try {
	                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
	                connection.connect();
	                int responseCode = connection.getResponseCode();
		                if (responseCode >= 400) 
		                {
		                    System.out.println("Broken link: " + url);
		                }
	                } 
	            catch (Exception e) 
	            	{
	                e.printStackTrace();
	            	}
	        }
	    }
	}
}
