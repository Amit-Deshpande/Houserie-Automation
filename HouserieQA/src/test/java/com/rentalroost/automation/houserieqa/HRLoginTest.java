package com.rentalroost.automation.houserieqa;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_LANDLORDLOGIN_USERNAME;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_LOGIN_PASSWORD;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_LOGIN_SUCCESS_MESSAGE;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_LOGOUT_SUCCESS_MESSAGE;


import com.rentalroost.automation.houserieqa.processor.PageObjects.HomePageBeforeLoginPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.MessagePage;



public class HRLoginTest extends HouserieBasicTest{
	
	HomePageBeforeLoginPage homePageBeforeLoginPage;
	MessagePage messagePage;
	
	String userName, password, baseURL;
	String actualLoginSuccessMsg, actualLogoutSuccessMsg;
	String expectedLoginSuccessMsg, expectedLogoutSuccessMsg;
	
	@BeforeClass(alwaysRun = true)
	public void setUp() {
		
		System.out.println("Login test case started.");
		
		baseURL = site.getBaseURL();
		Assert.assertFalse(site.deleteHouserieCookies(baseURL), "Verify Deleting cookies.");
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		
		System.out.println("Login test case ended.");
		
	}

	@Test(groups="smoke")
    public void HRLogin() throws InterruptedException {
		
		userName = houserieLib.getPropertyValue(DEFAULT_LANDLORDLOGIN_USERNAME);
		password = houserieLib.getPropertyValue(DEFAULT_LOGIN_PASSWORD);
		expectedLoginSuccessMsg = houserieLib.getPropertyValue(DEFAULT_LOGIN_SUCCESS_MESSAGE);
		expectedLogoutSuccessMsg = houserieLib.getPropertyValue(DEFAULT_LOGOUT_SUCCESS_MESSAGE);
		
		System.out.println("Launch the application.");
		
		homePageBeforeLoginPage = site.goToHomePageBeforeLoginPage();
		homePageBeforeLoginPage.clickLoginLink();
		
		Thread.sleep(1500);
		
		System.out.println("Enter Username, password and click on login button.");

		homePageBeforeLoginPage.enterUserEmail(userName);
		homePageBeforeLoginPage.enterUserPassword(password);
		homePageBeforeLoginPage.clickLoginButton();
		
		messagePage = site.goToMessagePage();
		Thread.sleep(1500);
		actualLoginSuccessMsg = messagePage.getNoticeMessage();		
		Assert.assertTrue(actualLoginSuccessMsg.contains(expectedLoginSuccessMsg), "User is not successfully logged in. actualLoginSuccessMsg is : " +actualLoginSuccessMsg);
		
		System.out.println("Click on logout button.");
		
		messagePage.clickMyAccountDropArrow();
		messagePage.clickSignOutLink();
		Thread.sleep(2500);
		actualLogoutSuccessMsg = messagePage.getNoticeMessage();
		
		System.out.println("User is logged out.");
		
		Assert.assertTrue(actualLogoutSuccessMsg.contains(expectedLogoutSuccessMsg), "User is not successfully logged out. actualLogoutSuccessMsg is : " +actualLogoutSuccessMsg);
		
	}

}
