package org.fxb.experts_staging.utilities;

import java.time.Duration;

import org.fxb.experts_staging.base.InitiatingBrowser_for_Registration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits extends InitiatingBrowser_for_Registration
{
	/*
	 * Explicit wait: for element [Clickable]
	 * if the webelement is not clickable then jvm will still wait upto 10 second 
	 * and suppose if it is not clickable within 10 second then it will thorw an exception 
	 */
    public static WebElement explicit_waitForElementToBe_Clickable(By locator, int waitForSecond) 
    {
        WebDriverWait explicit_wait = new WebDriverWait(driver, Duration.ofSeconds(waitForSecond));
        WebElement element = explicit_wait.until(ExpectedConditions.elementToBeClickable(locator));
        return element;
    }
	/*
	 * Explicit wait: for element [Visibility] 
	 * if the webelement is not Visible then jvm will still wait upto 10 second 
	 * and suppose if it is not Visible within 10 second then it will thorw an exception 
	 */
    public static WebElement explicit_waitForElementToBe_Visible(By locator, int waitForSecond)
    {
        WebDriverWait explicit_wait = new WebDriverWait(driver, Duration.ofSeconds(waitForSecond));
        WebElement element = explicit_wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element;
    }
	/*
	 * Explicit wait: for element [Presence] 
	 * if the webelement is not Present then jvm will still wait upto 10 second 
	 * and suppose if it is not Present within 10 second then it will thorw an exception 
	 */
    public static WebElement explicit_waitForElementToBe_Present(By locator, int waitForSecond)
    {
        WebDriverWait explicit_wait = new WebDriverWait(driver, Duration.ofSeconds(waitForSecond));
        WebElement element = explicit_wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return element;
    }
    /*
     * Implicit Wait: 
     * If you use implicit wait then it will be applicable to all the element,
     * Suppose you have to wait for 10 second for element to be present/visible/clickable 
     * then this will be applicable to all the elements means for that elemets also which are already present/visible/clickable
     * So its better to use Explicit wait only
     * Using implicit wait can wait for a specific time until element appear before throwing 'NoSuchElementException'
     * */
    public static void implicit_waitForElement(int waitForSecond)
    {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitForSecond));
    }
}
