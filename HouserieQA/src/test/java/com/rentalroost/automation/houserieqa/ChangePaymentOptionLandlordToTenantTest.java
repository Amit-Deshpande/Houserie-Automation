// 1) Login using Landlord and initiate the order with Landlord pay mode.
// 2) Navigate to the My Order.
// 3) Change the Payment Option from Landlord to Tenant.

package com.rentalroost.automation.houserieqa;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_LANDLORDLOGIN_USERNAME;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_LOGIN_PASSWORD;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTLOGIN;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_MAIN_TOP_TEXT;

import com.rentalroost.automation.houserieqa.processor.PageObjects.HomePageBeforeLoginPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.MessagePage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.MyOrderHistoryPage;

public class ChangePaymentOptionLandlordToTenantTest extends HouserieBasicTest{
	
	MessagePage messagePage;
	MyOrderHistoryPage myOrderHistoryPage;
	HomePageBeforeLoginPage homePageBeforeLoginPage;
	
	String baseURL;
	String tenantUserName, landlordUserName, password, paymethod, currentTime;
	String actualMainPageText, expectedMainPageText;
	
	@BeforeClass(alwaysRun=true)
	public void setUp(){
		
		System.out.println("ChangePaymentOptionLandlordToTenant test case started.");
		
		baseURL = site.getBaseURL();
		Assert.assertFalse(site.deleteHouserieCookies(baseURL), "Verify Deleting cookies.");	
		
	}
	
	@AfterClass(alwaysRun=true)
	public void tearDown(){		
		System.out.println("ChangePaymentOptionLandlordToTenant test case end.");		
	}
	
	@Test(groups="smoke")
	public void ChangePaymentOptionLandlordToTenant(){	
		
		expectedMainPageText = houserieLib.getPropertyValue(DEFAULT_MAIN_TOP_TEXT);
		landlordUserName = houserieLib.getPropertyValue(DEFAULT_LANDLORDLOGIN_USERNAME);
		tenantUserName = houserieLib.getPropertyValue(DEFAULT_TENANTLOGIN);	
		password = houserieLib.getPropertyValue(DEFAULT_LOGIN_PASSWORD);
		paymethod = "Landlord";
		currentTime = houserieUtils.getCurrentTimeStamp();
		
		houserieLib.initaiteOrder(landlordUserName, tenantUserName, password, paymethod, currentTime);
		
		messagePage = site.goToMessagePage();
		
		messagePage.clickMyAccountDropArrow();
		messagePage.clickMyOrderLink();
		
		System.out.println("User is on the My Order page.");
		
		myOrderHistoryPage = site.goToMyOrderHistoryPage();
		
		myOrderHistoryPage.clickChangeOrderOptionsButton();
		myOrderHistoryPage.waitForAnElement(By.xpath("html/body/div[12]"));
		
		myOrderHistoryPage.waitABit(1500);
		
		if(myOrderHistoryPage.getChangePaymentOptionDialog().isDisplayed()){
			System.out.println("User has focus on the change payment option dialog.");
			myOrderHistoryPage.clickPaysTenantButton();
			myOrderHistoryPage.clickChangePaymentOptionButton();			
		}
		
		myOrderHistoryPage.waitABit(1500);
		
		myOrderHistoryPage.clickMyAccountDropArrow();
		myOrderHistoryPage.clickSignOutLink();
		
		homePageBeforeLoginPage = site.goToLoggedOutPage();
		
		actualMainPageText = homePageBeforeLoginPage.getMainTopText().getText().trim();
		Assert.assertTrue(expectedMainPageText.contains(actualMainPageText), "User is not successfully logged out.");
		
	}

}
