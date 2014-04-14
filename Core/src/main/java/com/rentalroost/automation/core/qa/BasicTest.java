package com.rentalroost.automation.core.qa;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.uncommons.reportng.HTMLReporter;

import com.rentalroost.automation.core.qa.utils.ReadFlags;


public abstract class BasicTest {

	protected WebDriver driver;
	private WebDriverBackedSelenium wbSel;
	private Wait wait;
	private JavascriptExecutor js;
	private DriverProvider drvProvider;
	private ReadFlags readFlag;
	private HTMLReporter reportNGUtil = new HTMLReporter();
	private String hsrbaseURL;

	public BasicTest() {
		//Log("Basic test constructor");
	}

	/*
	 * The following method is used to perform startup tasks like instantiating
	 * the webdriver, opening the test URL etc.
	 */
	public void performStartup() {

		Log("Performing BasicTest startup");
		drvProvider = DriverProvider.createDriver();
		driver = drvProvider.getWebDriver();
		
		Log("Instantiated the driver.");
		wait = new WebDriverWait(driver, 60000);
		
		//This line is failing couple time and we are not abel to trace it.. lets commetnt this for soem time and observer the failure.
		//((JavascriptExecutor) driver).executeScript("if (window.screen) {window.moveTo(0, 0);window.resizeTo(window.screen.availWidth, window.screen.availHeight);};");

	}

	@AfterSuite()
	public abstract void tearDown();
	
	


	public static void assertEquals(Object actual, Object expected) {
		Assert.assertEquals(actual, expected);
	}

	public void Log(String statement) {
		Reporter.log(statement);
		System.out.println(statement);
	}

	private String gethsrbaseURL() {
		return  ReadFlags.getInstnace().getFlag("hsrbaseURL");
	}		public String getBrowser() {		return  ReadFlags.getInstnace().getFlag("browser");	}

}
