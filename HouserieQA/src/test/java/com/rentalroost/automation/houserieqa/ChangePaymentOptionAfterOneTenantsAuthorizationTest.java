/**
 * @author amit
 *
 */
package com.rentalroost.automation.houserieqa;

import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_LANDLORDLOGIN_USERNAME;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_LOGIN_PASSWORD;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTLOGIN;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.rentalroost.automation.houserieqa.processor.PageObjects.CouponCodePage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.HomePageBeforeLoginPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.MessagePage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.MyOrderHistoryPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.PaymentPage;


public class ChangePaymentOptionAfterOneTenantsAuthorizationTest extends HouserieBasicTest{

		MessagePage messagePage;
		MyOrderHistoryPage myOrderHistoryPage;
		HomePageBeforeLoginPage homePageBeforeLoginPage;
		CouponCodePage couponCodePage;
		PaymentPage paymentPage;
		
		String landlordUserName, tenantUserName, password, paymethod;
		int noOfTenants;
		String baseURL;
		String ccNo, ccExpirationMonth, ccExpirationYear, verificationCode;	
		
		@BeforeClass(alwaysRun=true)
		public void setUp(){
			
			System.out.println("ChangePaymentBeforeMultipleTenantsAuthorization test case started.");

			baseURL = site.getBaseURL();
			Assert.assertFalse(site.deleteHouserieCookies(baseURL), "Verify Deleting cookies.");
			
		}
		
		@AfterClass(alwaysRun=true)
		public void tearDown(){
			
			System.out.println("ChangePaymentBeforeMultipleTenantsAuthorization test case end.");
			
		}
		
		@Test(groups="smoke")
		public void ChangePaymentOptionBeforeMultipleTenantsAuthorization(){
			
			landlordUserName = houserieLib.getPropertyValue(DEFAULT_LANDLORDLOGIN_USERNAME);
			tenantUserName = houserieLib.getPropertyValue(DEFAULT_TENANTLOGIN);	
			password = houserieLib.getPropertyValue(DEFAULT_LOGIN_PASSWORD);
			paymethod = "Tenant";
			noOfTenants = 3;
			
			houserieLib.initaiteOrderWithMultipleTenants(landlordUserName, tenantUserName, password, paymethod, noOfTenants);
			messagePage = site.goToMessagePage();
			messagePage.clickMyAccountDropArrow();
			messagePage.clickSignOutLink();
			
			homePageBeforeLoginPage = site.goToLoggedOutPage();
			
			houserieLib.tenantAuthrization(tenantUserName, password, landlordUserName);
			
			Assert.assertTrue(houserieLib.cancelOrderByLandlord(landlordUserName, password), "Verify the cancel order.");
			
		}

}
