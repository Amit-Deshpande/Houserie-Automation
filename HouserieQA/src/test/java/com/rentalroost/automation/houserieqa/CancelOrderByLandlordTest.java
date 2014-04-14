// 1) Login using Landlord and initiate the order for single tenant.
// 2) Navigate to the My Order.
// 3) Cancel the order.

package com.rentalroost.automation.houserieqa;

import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_LANDLORDLOGIN_USERNAME;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_LOGIN_PASSWORD;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTLOGIN;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.rentalroost.automation.houserieqa.processor.PageObjects.HomePageBeforeLoginPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.MessagePage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.MyOrderHistoryPage;

public class CancelOrderByLandlordTest extends HouserieBasicTest{
	
	MessagePage messagePage;
	MyOrderHistoryPage myOrderHistoryPage;
	HomePageBeforeLoginPage homePageBeforeLoginPage;
	
	String baseURL;
	String landlordUserName, password, tenantUserName, currentTime, paymethod;
	String orderBeforeCancelStatus, orderAfterCancelStatus;
	
	@BeforeClass(alwaysRun = true)
	public void setUp() {
		
		System.out.println("CancelOrderByLandlord test case started.");
		
		baseURL = site.getBaseURL();
		Assert.assertFalse(site.deleteHouserieCookies(baseURL), "Verify Deleting cookies.");
				
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		
		System.out.println("CancelOrderByLandlord test case ended.");
		
	}

	@Test(groups="smoke")
    public void CancelOrderByLandlord(){
		
		orderBeforeCancelStatus = "Pending";
		orderAfterCancelStatus = "Cancelled";
		landlordUserName = houserieLib.getPropertyValue(DEFAULT_LANDLORDLOGIN_USERNAME);
		tenantUserName = houserieLib.getPropertyValue(DEFAULT_TENANTLOGIN);	
		password = houserieLib.getPropertyValue(DEFAULT_LOGIN_PASSWORD);
		paymethod = "Tenant";
		currentTime = houserieUtils.getCurrentTimeStamp();
		
		System.out.println("Landlord initiate the order.");
		
		houserieLib.initaiteOrder(landlordUserName, tenantUserName, password, paymethod, currentTime);
	
		messagePage = site.goToMessagePage();
		
		System.out.println("Navigate to the My order page.");
		
		messagePage.clickMyAccountDropArrow();
		messagePage.clickMyOrderLink();
		
		myOrderHistoryPage = site.goToMyOrderHistoryPage();
		Assert.assertTrue(myOrderHistoryPage.getOrderStatusReport().contains(orderBeforeCancelStatus), "Verify the order status before order gets cancelled.");
		Assert.assertTrue(myOrderHistoryPage.getOrderHistoryTableDetails("1", "2").getText().trim().contains(orderBeforeCancelStatus), "Verify the order status in order history table before order cancelled.");
		
		System.out.println("Click on Cancel order button");
		
		myOrderHistoryPage.clickCancelOrderButton();
		driver.switchTo().alert().accept();
		
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		myOrderHistoryPage = site.goToMyOrderHistoryPage();
		
		Assert.assertTrue(myOrderHistoryPage.getOrderStatusReport().contains(orderAfterCancelStatus), "Verify the order status after order gets cancelled.");
		
		System.out.println("User is logging out.");
		
		messagePage.clickMyAccountDropArrow();
		messagePage.clickSignOutLink();
		
		System.out.println("User is logged out.");
		
		homePageBeforeLoginPage = site.goToLoggedOutPage();
		
	}

}
