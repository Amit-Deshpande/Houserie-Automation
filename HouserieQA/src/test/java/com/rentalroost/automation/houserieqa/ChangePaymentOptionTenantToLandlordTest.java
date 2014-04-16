// 1) Login using Landlord and initiate the order with Tenant pay mode.
// 2) Navigate to the My Order.
// 3) Change the Payment Option from Tenant to Landlord.

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
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_CC_CREDITCARDNUMBER;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_CC_EXPIRATIONMONTH;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_CC_EXPIRATIONYEAR;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_CC_VERIFICATIONCODE;

import com.rentalroost.automation.houserieqa.processor.PageObjects.CouponCodePage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.HomePageBeforeLoginPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.MessagePage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.MyOrderHistoryPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.PaymentPage;

public class ChangePaymentOptionTenantToLandlordTest extends HouserieBasicTest{
	
	MessagePage messagePage;
	MyOrderHistoryPage myOrderHistoryPage;
	HomePageBeforeLoginPage homePageBeforeLoginPage;
	CouponCodePage couponCodePage;
	PaymentPage paymentPage;
	
	String baseURL;
	String tenantUserName, landlordUserName, password, paymethod, currentTime;
	String actualMainPageText, expectedMainPageText;
	String ccNo, ccExpirationMonth, ccExpirationYear, verificationCode;	
	
	@BeforeClass(alwaysRun=true)
	public void setUp(){
		
		System.out.println("ChangePaymentOptionTenantToLandlord test case started.");
		
		baseURL = site.getBaseURL();
		Assert.assertFalse(site.deleteHouserieCookies(baseURL), "Verify Deleting cookies.");
		
	}
	
	@AfterClass(alwaysRun=true)
	public void tearDown(){
		
		System.out.println("ChangePaymentOptionTenantToLandlord test case end.");
		
	}
	
	@Test(groups="smoke")
	public void ChangePaymentOptionTenantToLandlord(){
		
		actualMainPageText = houserieLib.getPropertyValue(DEFAULT_MAIN_TOP_TEXT);
		ccNo = houserieLib.getPropertyValue(DEFAULT_CC_CREDITCARDNUMBER);
		ccExpirationMonth = houserieLib.getPropertyValue(DEFAULT_CC_EXPIRATIONMONTH);
		ccExpirationYear = houserieLib.getPropertyValue(DEFAULT_CC_EXPIRATIONYEAR);
		verificationCode = houserieLib.getPropertyValue(DEFAULT_CC_VERIFICATIONCODE);
		landlordUserName = houserieLib.getPropertyValue(DEFAULT_LANDLORDLOGIN_USERNAME);
		tenantUserName = houserieLib.getPropertyValue(DEFAULT_TENANTLOGIN);	
		password = houserieLib.getPropertyValue(DEFAULT_LOGIN_PASSWORD);
		paymethod = "Tenant";
		currentTime = houserieUtils.getCurrentTimeStamp();
		
		houserieLib.initaiteOrder(landlordUserName, tenantUserName, password, paymethod, currentTime);
	
		messagePage = site.goToMessagePage();
		
		messagePage.clickMyAccountDropArrow();
		messagePage.clickMyOrderLink();
		
		myOrderHistoryPage = site.goToMyOrderHistoryPage();
		
		System.out.println("User is on the My Order page.");
		
		myOrderHistoryPage.clickChangeOrderOptionsButton();		
		myOrderHistoryPage.waitABit(2000);
		
		if(myOrderHistoryPage.getChangePaymentOptionDialog().isEnabled()){
			System.out.println("Enabled: User has focus on the change payment option dialog.");
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
