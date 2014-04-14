// 1) Login using Landlord and initiate the order for single tenant.
// 2) Login using Tenant and fill the authorization and do payment against the initiated order.

package com.rentalroost.automation.houserieqa;

import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_ADDPROPERTY_PROPERTYNAME;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_CC_CREDITCARDNUMBER;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_CC_EXPIRATIONMONTH;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_CC_EXPIRATIONYEAR;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_CC_VERIFICATIONCODE;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_EMAIL_SUFFIX;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_LANDLORDLOGIN_USERNAME;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_LOGIN_PASSWORD;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTEMAIL_PREFIX;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_ANNUALSALARY;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_CITY;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_DATEOFBIRTH;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_FIRSTNAME;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_LASTNAME;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_PHONENO;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_SSN;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_SSNLASTFOURDIGIT;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_STREETADDRESS;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_STREETNO;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_ZIPCODE;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTLOGIN;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTAUTHORIZATIONCOMPLETED_MESSAGE;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.rentalroost.automation.houserieqa.processor.PageObjects.HomePageBeforeLoginPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.MessagePage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.PaymentPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.TenantAuthorizeReportPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.TenantEnterInfoAndAcceptTermsPage;

public class TenantScreeningAuthorizationTenantPayTest extends HouserieBasicTest{
	
	MessagePage messagePage;
	TenantEnterInfoAndAcceptTermsPage tenantEnterInfoAndAcceptTermsPage;
	HomePageBeforeLoginPage homePageBeforeLoginPage;
	PaymentPage paymentPage;
	TenantAuthorizeReportPage tenantAuthorizeReportPage;
	
	String baseURL;
	String tenantUserName, landlordUserName, password, paymethod, currentTime;
	String fName, lName, dob, ssn, streetNo, streetAdd, city, zip, email, phone, annualSalary, emailsuffix, emailprefix;
	String ccNo, ccExpirationMonth, ccExpirationYear, verificationCode;
	String initiatedScreeningMessage, tenantauthorizationCompletedMessage, propertyName;
	String sssLastFourDigit;
	
	@BeforeClass(alwaysRun = true)
	public void setUp() {
		
		System.out.println("TenantScreeningAuthorizationTenantPay test case started.");
		
		baseURL = site.getBaseURL();
		Assert.assertFalse(site.deleteHouserieCookies(baseURL), "Verify Deleting cookies.");		
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		
		System.out.println("TenantScreeningAuthorizationTenantPay test case ended.");
		
	}

	@Test(groups="smoke")
    public void TenantScreeningAuthorizationLandlordPay(){
		
		fName = houserieLib.getPropertyValue(DEFAULT_TENANTINFO_FIRSTNAME);
		lName = houserieLib.getPropertyValue(DEFAULT_TENANTINFO_LASTNAME);
		dob = houserieLib.getPropertyValue(DEFAULT_TENANTINFO_DATEOFBIRTH);
		ssn = houserieLib.getPropertyValue(DEFAULT_TENANTINFO_SSN);
		streetNo = houserieLib.getPropertyValue(DEFAULT_TENANTINFO_STREETNO);
		streetAdd = houserieLib.getPropertyValue(DEFAULT_TENANTINFO_STREETADDRESS);
		city = houserieLib.getPropertyValue(DEFAULT_TENANTINFO_CITY);
		zip = houserieLib.getPropertyValue(DEFAULT_TENANTINFO_ZIPCODE);
		phone = houserieLib.getPropertyValue(DEFAULT_TENANTINFO_PHONENO);
		annualSalary = houserieLib.getPropertyValue(DEFAULT_TENANTINFO_ANNUALSALARY);		
		emailsuffix = houserieLib.getPropertyValue(DEFAULT_EMAIL_SUFFIX);
		emailprefix = houserieLib.getPropertyValue(DEFAULT_TENANTEMAIL_PREFIX);
		ccNo = houserieLib.getPropertyValue(DEFAULT_CC_CREDITCARDNUMBER);
		ccExpirationMonth = houserieLib.getPropertyValue(DEFAULT_CC_EXPIRATIONMONTH);
		ccExpirationYear = houserieLib.getPropertyValue(DEFAULT_CC_EXPIRATIONYEAR);
		verificationCode = houserieLib.getPropertyValue(DEFAULT_CC_VERIFICATIONCODE);
		propertyName = houserieLib.getPropertyValue(DEFAULT_ADDPROPERTY_PROPERTYNAME);		
		tenantauthorizationCompletedMessage = houserieLib.getPropertyValue(DEFAULT_TENANTAUTHORIZATIONCOMPLETED_MESSAGE);
		sssLastFourDigit = houserieLib.getPropertyValue(DEFAULT_TENANTINFO_SSNLASTFOURDIGIT);
		
		initiatedScreeningMessage = tenantauthorizationCompletedMessage + propertyName + currentTime;
		System.out.println(initiatedScreeningMessage);
		
		email = houserieUtils.generateUniqueEmailID(emailprefix, emailsuffix);
		
		landlordUserName = houserieLib.getPropertyValue(DEFAULT_LANDLORDLOGIN_USERNAME);
		tenantUserName = houserieLib.getPropertyValue(DEFAULT_TENANTLOGIN);	
		password = houserieLib.getPropertyValue(DEFAULT_LOGIN_PASSWORD);
		paymethod = "Tenant";
		currentTime = houserieUtils.getCurrentTimeStamp();
		
		houserieLib.initaiteOrder(landlordUserName, tenantUserName, password, paymethod, currentTime);		
		
		messagePage = site.goToMessagePage();		
		messagePage.clickMyAccountDropArrow();
		messagePage.clickSignOutLink();
		
		homePageBeforeLoginPage = PageFactory.initElements(driver, HomePageBeforeLoginPage.class);
		
		Assert.assertTrue(houserieLib.loginToHR(tenantUserName, password), "Login is not successfully done.");		
		messagePage = site.goToMessagePage();
		
		System.out.println(messagePage.getTenantMessageDetails("1").getText());
		
		messagePage.getTenantMessageDetailsForClick("1").click();
		tenantEnterInfoAndAcceptTermsPage = site.goToTenantEnterInfoAndAcceptTermsPage();
		
		tenantEnterInfoAndAcceptTermsPage.setTenantFirstName(fName);
		tenantEnterInfoAndAcceptTermsPage.setTenantLastName(lName);
		tenantEnterInfoAndAcceptTermsPage.setTenantDateOfBirth(dob);
		//tenantEnterInfoAndAcceptTermsPage.setTenantSSNNumber(ssn);
		tenantEnterInfoAndAcceptTermsPage.setTenantStreetNumber(streetNo);
		tenantEnterInfoAndAcceptTermsPage.setTenantStreetAddress(streetAdd);
		tenantEnterInfoAndAcceptTermsPage.setTenantCityAddress(city);
		tenantEnterInfoAndAcceptTermsPage.setTenantZipCode(zip);
		tenantEnterInfoAndAcceptTermsPage.setTenantEmailID(email);
		tenantEnterInfoAndAcceptTermsPage.setTenantPhoneNumber(phone);
		tenantEnterInfoAndAcceptTermsPage.setTenantAnnualSalary(annualSalary);
		tenantEnterInfoAndAcceptTermsPage.clickNextButton();
		
		Assert.assertTrue(tenantEnterInfoAndAcceptTermsPage.getPopupFirstName().contains(fName), "First Name does not same as entered by tenant.");
		Assert.assertTrue(tenantEnterInfoAndAcceptTermsPage.getPopupLastName().contains(lName), "Last Name does not same as entered by tenant.");
		Assert.assertTrue(tenantEnterInfoAndAcceptTermsPage.getPopupdob().contains(dob), "DOB does not same as entered by tenant.");
		Assert.assertTrue(tenantEnterInfoAndAcceptTermsPage.getPopupStreetno().contains(streetNo), "Street No does not same as entered by tenant.");
		Assert.assertTrue(tenantEnterInfoAndAcceptTermsPage.getPopupStreetAddress().contains(streetAdd), "Street Address does not same as entered by tenant.");
		Assert.assertTrue(tenantEnterInfoAndAcceptTermsPage.getPopupCity().contains(city), "City does not same as entered by tenant.");
		Assert.assertTrue(tenantEnterInfoAndAcceptTermsPage.getPopupZipcode().contains(zip), "Zip does not same as entered by tenant.");
		Assert.assertTrue(tenantEnterInfoAndAcceptTermsPage.getPopupEmail().contains(email), "Email does not same as entered by tenant.");
		Assert.assertTrue(tenantEnterInfoAndAcceptTermsPage.getPopupPhone().contains(phone), "Phone does not same as entered by tenant.");
		
		tenantEnterInfoAndAcceptTermsPage.clickConfirmButton();
		tenantEnterInfoAndAcceptTermsPage.clickIagreeButton();
		
		tenantAuthorizeReportPage = site.goToTenantAuthorizeReportPage();
		tenantAuthorizeReportPage.clickAgreeChkBox();
		tenantAuthorizeReportPage.setFullName(fName);
		tenantAuthorizeReportPage.setSSN(sssLastFourDigit);
		tenantAuthorizeReportPage.clickAuthorizeButton();
		
		driver.switchTo().alert().accept();
		
		paymentPage = site.goToPaymentPage();
		paymentPage.enterCCField(ccNo);
		paymentPage.enterCCExpMonth(ccExpirationMonth);
		paymentPage.enterCCExpYear(ccExpirationYear);
		paymentPage.enterCCVerification(verificationCode);
		paymentPage.clickPaymentNowButton();
		
		paymentPage.waitForAnElement(By.xpath("id('maincont')/div[2]/p"));		
		Assert.assertTrue(paymentPage.getPaymentConfirmationNote().contains("Thank you"), "Thank you message does not appears screen.");
		
		paymentPage.clickMyAccountDropArrow();
		paymentPage.clickMessageLink();
		messagePage = site.goToMessagePage();
		
		Assert.assertTrue(messagePage.getTenantMessageDetails("1").getText().contains(initiatedScreeningMessage), "Initiated screening message does not appears screen.");
		
		messagePage.clickMyAccountDropArrow();
		messagePage.clickSignOutLink();
		
		homePageBeforeLoginPage = site.goToLoggedOutPage();
		
	}

}
