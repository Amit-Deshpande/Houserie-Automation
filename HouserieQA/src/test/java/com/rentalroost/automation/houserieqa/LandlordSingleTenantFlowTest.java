// 1) Login using Landlord and initiate the order for single tenant.
// 2) Verify the message for the order has been initiated.
// 3) Verify the message for the payment information has been received.
// 4) Login using Tenant and verify the message that tenant has been received message for the order initiated.

package com.rentalroost.automation.houserieqa;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_LANDLORDLOGIN_USERNAME;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_LOGIN_PASSWORD;

import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_ADDPROPERTY_PROPERTYNAME;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_ADDPROPERTY_PROPERTYDESCRIPTION;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_ADDPROPERTY_STREETNUMBER;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_ADDPROPERTY_STREETNAME;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_ADDPROPERTY_STREETSUFFIX;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_ADDPROPERTY_CITY;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_ADDPROPERTY_ZIP;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_ADDPROPERTY_PHONE;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_ADDPROPERTY_LEASERENT;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_ADDPROPERTY_LEASEDEPOSITE;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_ADDPROPERTY_LEASETERM;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTLOGIN;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_CC_CREDITCARDNUMBER;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_CC_EXPIRATIONMONTH;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_CC_EXPIRATIONYEAR;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_CC_VERIFICATIONCODE;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_INITIATEDSCREENING_MESSAGE1;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_INITIATEDSCREENING_MESSAGE2;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_PAYMENTINFORMATIONRECEIVED_MESSAGE1;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_PAYMENTINFORMATIONRECEIVED_MESSAGE2;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTAUTHORIZATION_MESSAGE1;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTAUTHORIZATION_MESSAGE2;

import com.rentalroost.automation.houserieqa.processor.PageObjects.AddTenantsPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.AsLandlordPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.CouponCodePage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.HomePageBeforeLoginPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.MessagePage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.PaymentPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.SelectProductsPage;

public class LandlordSingleTenantFlowTest extends HouserieBasicTest{
	
	MessagePage messagePage;
	AsLandlordPage asLandlordPage;
	AddTenantsPage addTenantsPage;
	SelectProductsPage selectProductsPage;
	CouponCodePage couponCodePage;
	PaymentPage paymentPage;
	HomePageBeforeLoginPage homePageBeforeLoginPage;
	
	String baseURL;
	String userName, password, tenantLogin, currentTime;
	String propertyName, propertyDescription, streetNumber, streetName, streetSuffix, city, zip, phoneNo, leaseRent, leaseDeposite, leaseTerm;
	String ccNo, ccExpirationMonth, ccExpirationYear, verificationCode;
	String amount;
	String initiatedScreeningMessage, initiatedScreeningMessage1, initiatedScreeningMessage2, paymentInformationReceivedMessage, paymentInformationReceivedMessage1, paymentInformationReceivedMessage2; 
	String tenantauthorizationMessage, tenantauthorizationMessage1, tenantauthorizationMessage2;
	
	@BeforeClass(alwaysRun = true)
	public void setUp() {
		
		System.out.println("LandlordSingleTenantFlow test case started.");
		
		baseURL = site.getBaseURL();
		Assert.assertFalse(site.deleteHouserieCookies(baseURL), "Verify Deleting cookies.");
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		
		System.out.println("LandlordSingleTenantFlow test case ended.");
		
	}

	@Test(groups="smoke")
    public void LandlordSingleTenantFlow(){
		
		currentTime = houserieUtils.getCurrentTimeStamp();
		
		userName = houserieLib.getPropertyValue(DEFAULT_LANDLORDLOGIN_USERNAME);
		password = houserieLib.getPropertyValue(DEFAULT_LOGIN_PASSWORD);		
		propertyName = houserieLib.getPropertyValue(DEFAULT_ADDPROPERTY_PROPERTYNAME);
		propertyDescription = houserieLib.getPropertyValue(DEFAULT_ADDPROPERTY_PROPERTYDESCRIPTION);
		streetNumber = houserieLib.getPropertyValue(DEFAULT_ADDPROPERTY_STREETNUMBER);
		streetName = houserieLib.getPropertyValue(DEFAULT_ADDPROPERTY_STREETNAME);
		streetSuffix = houserieLib.getPropertyValue(DEFAULT_ADDPROPERTY_STREETSUFFIX);
		city = houserieLib.getPropertyValue(DEFAULT_ADDPROPERTY_CITY);
		zip = houserieLib.getPropertyValue(DEFAULT_ADDPROPERTY_ZIP);
		phoneNo = houserieLib.getPropertyValue(DEFAULT_ADDPROPERTY_PHONE);
		leaseRent = houserieLib.getPropertyValue(DEFAULT_ADDPROPERTY_LEASERENT);
		leaseDeposite = houserieLib.getPropertyValue(DEFAULT_ADDPROPERTY_LEASEDEPOSITE);
		leaseTerm = houserieLib.getPropertyValue(DEFAULT_ADDPROPERTY_LEASETERM);
		tenantLogin = houserieLib.getPropertyValue(DEFAULT_TENANTLOGIN);		
		
		ccNo = houserieLib.getPropertyValue(DEFAULT_CC_CREDITCARDNUMBER);
		ccExpirationMonth = houserieLib.getPropertyValue(DEFAULT_CC_EXPIRATIONMONTH);
		ccExpirationYear = houserieLib.getPropertyValue(DEFAULT_CC_EXPIRATIONYEAR);
		verificationCode = houserieLib.getPropertyValue(DEFAULT_CC_VERIFICATIONCODE);
		
		initiatedScreeningMessage1 = houserieLib.getPropertyValue(DEFAULT_INITIATEDSCREENING_MESSAGE1);
		initiatedScreeningMessage2 = houserieLib.getPropertyValue(DEFAULT_INITIATEDSCREENING_MESSAGE2);
		paymentInformationReceivedMessage1 = houserieLib.getPropertyValue(DEFAULT_PAYMENTINFORMATIONRECEIVED_MESSAGE1);
		paymentInformationReceivedMessage2 = houserieLib.getPropertyValue(DEFAULT_PAYMENTINFORMATIONRECEIVED_MESSAGE2);	
		
		tenantauthorizationMessage1 = houserieLib.getPropertyValue(DEFAULT_TENANTAUTHORIZATION_MESSAGE1);
		tenantauthorizationMessage2 = houserieLib.getPropertyValue(DEFAULT_TENANTAUTHORIZATION_MESSAGE2);	
		
		initiatedScreeningMessage = initiatedScreeningMessage1 + "Fname"+currentTime+ " Lname"+currentTime+ " " +initiatedScreeningMessage2 + propertyName +currentTime;
		paymentInformationReceivedMessage = paymentInformationReceivedMessage1 + propertyName + currentTime + " " + paymentInformationReceivedMessage2 + " Tenant Hr";
		tenantauthorizationMessage = tenantauthorizationMessage1 + propertyName + currentTime + " " +tenantauthorizationMessage2;
				
		System.out.println("Login to the application using Landlord user.");
		
		Assert.assertTrue(houserieLib.loginToHR(userName, password), "Login is not successfully done.");
		
		System.out.println("Landlord user logged in and initiating order.");
		
		messagePage = site.goToMessagePage();
		messagePage.clickStartScreeningTenantsButton();
		
		System.out.println("Navigating to the Landlord page.");
		
		asLandlordPage = site.goToAsLandlordPage();
		
		asLandlordPage.enterPropertyName(propertyName + currentTime);
		asLandlordPage.enterPropertyDescription(propertyDescription);
		asLandlordPage.enterStreetNumber(streetNumber);
		asLandlordPage.enterStreetName(streetName);
		asLandlordPage.enterStreetSuffix(streetSuffix);
		asLandlordPage.enterCityName(city);
		asLandlordPage.enterZipCode(zip);
		asLandlordPage.enterPhoneNumber(phoneNo);
		asLandlordPage.enterLeaseRent(leaseRent);
		asLandlordPage.enterLeaseDeposit(leaseDeposite);
		asLandlordPage.enterLeaseTerm(leaseTerm);
		asLandlordPage.clickHighEndPropertyRadioButton();
		asLandlordPage.clickNextButton();
		
		System.out.println("Navigating to the Add Tenant page.");
		
		addTenantsPage = site.goToAddTenantsPage();
		
		addTenantsPage.enterFirstName("Fname"+currentTime);
		addTenantsPage.enterMiddleName("Mname"+currentTime);
		addTenantsPage.enterLastName("Lname"+currentTime);
		addTenantsPage.enterSuffix("Mr.");
		addTenantsPage.enterEmail(tenantLogin);
		addTenantsPage.clickNextButton();
		
		System.out.println("Navigating to the select product page.");
		
		selectProductsPage = site.goToSelectProductsPage();
		
		amount = selectProductsPage.getTenantsCostDetails("2", "4").getText().trim();
		
		Assert.assertTrue(selectProductsPage.getTenantsCostDetails("2", "1").getText().contains("Fname"+currentTime), "Tenants first name on select product page is not correct.");
		Assert.assertTrue(selectProductsPage.getTenantsCostDetails("2", "1").getText().contains("Lname"+currentTime), "Tenant''s last name on select product page is not correct.");
		selectProductsPage.clickLandloardPaysRadioButton();
		selectProductsPage.clickInitiateOrderButton();
		
		System.out.println("Ordre is initiated and navigating to the coupon code page.");
		
		couponCodePage = site.goToCouponCodePage();
		couponCodePage.clickSkipButton();
		
		paymentPage = site.goToPaymentPage();
		Assert.assertTrue(paymentPage.getTenantsCostDetails("2", "1").getText().contains("Tenant Hr"), "Tenants first name on payment page is not correct.");
		Assert.assertTrue(paymentPage.getTenantsCostDetails("2", "2").getText().contains(amount), "Tenants amount is not correct.");
		
		System.out.println("Navigating to the payment page.");
		
		paymentPage.enterCCField(ccNo);
		paymentPage.enterCCExpMonth(ccExpirationMonth);
		paymentPage.enterCCExpYear(ccExpirationYear);
		paymentPage.enterCCVerification(verificationCode);
		paymentPage.clickPaymentNowButton();
		
		System.out.println("User has done payment.");
		
		paymentPage.waitForAnElement(By.xpath("id('maincont')/div[2]/p"));
		
		Assert.assertTrue(paymentPage.getPaymentConfirmationNote().contains("Thank you"), "Thank you message does not appears screen.");
		paymentPage.clickMyAccountDropArrow();
		paymentPage.clickMessageLink();
		messagePage = site.goToMessagePage();
		
		System.out.println("User is on the message page.");
		
		Assert.assertTrue(messagePage.getLandloardMessageDetails("1").getText().contains(paymentInformationReceivedMessage), "Received your screening order message does not appears screen.");
		Assert.assertTrue(messagePage.getLandloardMessageDetails("2").getText().contains(initiatedScreeningMessage), "Initiated screening message does not appears screen.");
		
		messagePage.clickMyAccountDropArrow();
		messagePage.clickSignOutLink();
		
		System.out.println("User is logging out.");
		
		homePageBeforeLoginPage = site.goToLoggedOutPage();
		
		System.out.println("Login using Tenant account and verify the message.");
		
		Assert.assertTrue(houserieLib.loginToHR(tenantLogin, password), "Login is not successfully done.");
		messagePage = site.goToMessagePage();
		
		Assert.assertTrue(messagePage.getTenantMessageDetails("1").getText().contains(tenantauthorizationMessage), "Tenant authorization message does not appears screen.");
		messagePage.clickMyAccountDropArrow();
		messagePage.clickSignOutLink();
		
		homePageBeforeLoginPage = site.goToLoggedOutPage();		
	}

}
