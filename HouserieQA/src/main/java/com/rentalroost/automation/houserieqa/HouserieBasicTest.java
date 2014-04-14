package com.rentalroost.automation.houserieqa;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import com.rentalroost.automation.houserieqa.processor.HouserieLib.HouserieLibrary;
import com.rentalroost.automation.houserieqa.processor.HouserieUtil.HouserieUtils;
import com.rentalroost.automation.houserieqa.processor.Navigator.HouserieSiteManager;
import com.rentalroost.automation.core.qa.BasicTest;
import com.rentalroost.automation.core.qa.DriverProvider;

public class HouserieBasicTest extends BasicTest {

	HouseriePropertyResolver resolver = HouseriePropertyResolver.getInstnace();

	protected HouserieSiteManager.HouserieSite site;

	protected HouserieLibrary houserieLib;
	protected HouserieUtils houserieUtils;

	public HouserieBasicTest() {
		super();

	}

	@BeforeClass(alwaysRun = true)
	public void performStartup() {
		super.performStartup();
		
		houserieLib = HouserieLibrary.getInstnace(driver);
		houserieUtils = houserieLib.getLealtaUtils();

		// Adding navigation capability to Site class
		Log("Instantiated Navigator.");
		setSite((HouserieSiteManager.HouserieSite) HouserieSiteManager.getInstnace().getSite(driver));

	}
	
	@AfterSuite(alwaysRun=true)
	public void closeSession() {
		Log("Closing session.");
		WebDriver driver = DriverProvider.createDriver().getWebDriver();
		
		if(driver != null){
			driver.close();
			try{
				System.out.println("Quiting browser.");
				driver.quit();
			}
			catch (Exception e) {
		}
			
			Log("Closing session.");
		}
		
		
		
		
	}

	public HouserieSiteManager.HouserieSite getSite() {

		return site;
	}

	public void setSite(HouserieSiteManager.HouserieSite site) {
		this.site = site;
	}



	public HouserieLibrary getHouserieLibrary() {
		return houserieLib;
	}

	public boolean isElementVisible(String serchByXpathString) {

		try {

			driver.findElement(By.xpath(serchByXpathString));

		} catch (NoSuchElementException e) {

			return false;

		}

		return true;

	}
	

	@Override
	public void tearDown() {
		
		
	}

}
