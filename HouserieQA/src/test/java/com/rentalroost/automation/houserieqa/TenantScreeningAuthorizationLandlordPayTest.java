// 1) Login using Landlord and initiate the order for single tenant.
// 2) Do payment by landlord.
// 3) Login using Tenant and fill the authorization.

package com.rentalroost.automation.houserieqa;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_MAIN_TOP_TEXT;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTLOGIN;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_LANDLORDLOGIN_USERNAME;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_LOGIN_PASSWORD;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_FIRSTNAME;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_LASTNAME;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_DATEOFBIRTH;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_SSN;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_STREETNO;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_STREETADDRESS;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_CITY;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_ZIPCODE;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_PHONENO;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_ANNUALSALARY;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_EMAIL_SUFFIX;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTEMAIL_PREFIX;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_SSNLASTFOURDIGIT;

import com.rentalroost.automation.houserieqa.processor.PageObjects.HomePageBeforeLoginPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.MessagePage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.TenantAuthorizeReportPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.TenantEnterInfoAndAcceptTermsPage;

public class TenantScreeningAuthorizationLandlordPayTest extends HouserieBasicTest{
	
	MessagePage messagePage;
	TenantEnterInfoAndAcceptTermsPage tenantEnterInfoAndAcceptTermsPage;
	TenantAuthorizeReportPage tenantAuthorizeReportPage;
	HomePageBeforeLoginPage homePageBeforeLoginPage;
	
	String baseURL;
	String tenantUserName, landlordUserName, password;
	String fName, lName, dob, ssn, streetNo, streetAdd, city, zip, email, phone, annualSalary, emailsuffix, emailprefix;
	String paymethod, currentTime;
	String sssLastFourDigit;
	String actualMainPageText, expectedMainPageText;
	
	@BeforeClass(alwaysRun = true)
	public void setUp() {
		
		System.out.println("TenantScreeningAuthorizationLandlordPay test case started.");
		
		baseURL = site.getBaseURL();
		Assert.assertFalse(site.deleteHouserieCookies(baseURL), "Verify Deleting cookies.");		
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		
		System.out.println("TenantScreeningAuthorizationLandlordPay test case ended.");
		
	}

	@Test(groups="smoke")
    public void TenantScreeningAuthorizationLandlordPay(){
		
		actualMainPageText = houserieLib.getPropertyValue(DEFAULT_MAIN_TOP_TEXT);		
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
		sssLastFourDigit = houserieLib.getPropertyValue(DEFAULT_TENANTINFO_SSNLASTFOURDIGIT);
		landlordUserName = houserieLib.getPropertyValue(DEFAULT_LANDLORDLOGIN_USERNAME);
		tenantUserName = houserieLib.getPropertyValue(DEFAULT_TENANTLOGIN);	
		password = houserieLib.getPropertyValue(DEFAULT_LOGIN_PASSWORD);
		paymethod = "Landlord";
		currentTime = houserieUtils.getCurrentTimeStamp();
		
		houserieLib.initaiteOrder(landlordUserName, tenantUserName, password, paymethod, currentTime);
		
		messagePage = site.goToMessagePage();
		
		messagePage.clickMyAccountDropArrow();
		messagePage.clickSignOutLink();
		
		homePageBeforeLoginPage = PageFactory.initElements(driver, HomePageBeforeLoginPage.class);
		
		email = houserieUtils.generateUniqueEmailID(emailprefix, emailsuffix);
		
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
		
		tenantAuthorizeReportPage.waitForAnElement(By.xpath("id('wrapper')/div[2]/p"));
		Assert.assertTrue(tenantAuthorizeReportPage.getScreeningAuthorizationConfirmationMsg().contains("You have successfully completed the screening authorization for the property"), "screening authorization done message does not appears screen.");
		
		tenantAuthorizeReportPage.clickMyAccountDropArrow();
		tenantAuthorizeReportPage.clickSignOutLink();
		
		homePageBeforeLoginPage = site.goToLoggedOutPage();
		
		expectedMainPageText = homePageBeforeLoginPage.getMainTopText().getText().trim();
		Assert.assertTrue(actualMainPageText.contains(expectedMainPageText), "User is not successfully logged out.");
		
		
	}

}
