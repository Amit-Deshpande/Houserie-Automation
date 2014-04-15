package com.rentalroost.automation.houserieqa;

import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_CC_CREDITCARDNUMBER;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_CC_EXPIRATIONMONTH;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_CC_EXPIRATIONYEAR;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_CC_VERIFICATIONCODE;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_LANDLORDLOGIN_USERNAME;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_LOGIN_PASSWORD;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTLOGIN;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.rentalroost.automation.houserieqa.processor.PageObjects.CouponCodePage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.HomePageBeforeLoginPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.MessagePage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.MyOrderHistoryPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.PaymentPage;

public class ChangePaymentOptionBeforeMultipleTenantsAuthorizationTest extends HouserieBasicTest{
	
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
		
		ccNo = houserieLib.getPropertyValue(DEFAULT_CC_CREDITCARDNUMBER);
		ccExpirationMonth = houserieLib.getPropertyValue(DEFAULT_CC_EXPIRATIONMONTH);
		ccExpirationYear = houserieLib.getPropertyValue(DEFAULT_CC_EXPIRATIONYEAR);
		verificationCode = houserieLib.getPropertyValue(DEFAULT_CC_VERIFICATIONCODE);
		landlordUserName = houserieLib.getPropertyValue(DEFAULT_LANDLORDLOGIN_USERNAME);
		tenantUserName = houserieLib.getPropertyValue(DEFAULT_TENANTLOGIN);	
		password = houserieLib.getPropertyValue(DEFAULT_LOGIN_PASSWORD);
		paymethod = "Tenant";
		noOfTenants = 3;
		
		houserieLib.initaiteOrderWithMultipleTenants(landlordUserName, tenantUserName, password, paymethod, noOfTenants);
		
		messagePage = site.goToMessagePage();
		
		messagePage.clickMyAccountDropArrow();
		messagePage.clickMyOrderLink();
		
		myOrderHistoryPage = site.goToMyOrderHistoryPage();
		
		System.out.println("User is on the My Order page.");
		
		myOrderHistoryPage.clickChangeOrderOptionsButton();
		
		myOrderHistoryPage.waitABit(2000);
		//myOrderHistoryPage.waitForAnElement(By.xpath("html/body/div[12]"));
		
		if(myOrderHistoryPage.getChangePaymentOptionDialog().isDisplayed()){
			System.out.println("User has focus on the change payment option dialog.");
			myOrderHistoryPage.clickPaysLandlordButton();
			myOrderHistoryPage.clickChangePaymentOptionButton();			
		}
		
		myOrderHistoryPage.waitABit(1500);
		
		couponCodePage = site.goToCouponCodePage();
		
		System.out.println("User is on the Coupon code page.");
		
		couponCodePage.clickSkipButton();
	
		paymentPage = site.goToPaymentPage();
		
		System.out.println("User is on the payment page.");
	
		paymentPage.enterCCField(ccNo);
		paymentPage.enterCCExpMonth(ccExpirationMonth);
		paymentPage.enterCCExpYear(ccExpirationYear);
		paymentPage.enterCCVerification(verificationCode);
		paymentPage.clickPaymentNowButton();
		
		System.out.println("User has done the payment.");
	
		paymentPage.waitForAnElement(By.xpath("id('maincont')/div[2]/p"));
	
		Assert.assertTrue(paymentPage.getPaymentConfirmationNote().contains("Thank you"), "Thank you message does not appears screen.");
		
		paymentPage.clickMyAccountDropArrow();
		paymentPage.clickSignOutLink();
		
		homePageBeforeLoginPage = site.goToLoggedOutPage();
		
	}

}
