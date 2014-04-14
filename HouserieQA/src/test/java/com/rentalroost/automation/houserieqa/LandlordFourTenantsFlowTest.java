package com.rentalroost.automation.houserieqa;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.rentalroost.automation.houserieqa.processor.PageObjects.AddTenantsPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.AsLandlordPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.CouponCodePage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.HomePageBeforeLoginPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.MessagePage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.PaymentPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.SelectProductsPage;

import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_ADDPROPERTY_CITY;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_ADDPROPERTY_LEASEDEPOSITE;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_ADDPROPERTY_LEASERENT;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_ADDPROPERTY_LEASETERM;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_ADDPROPERTY_PHONE;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_ADDPROPERTY_PROPERTYDESCRIPTION;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_ADDPROPERTY_PROPERTYNAME;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_ADDPROPERTY_STREETNAME;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_ADDPROPERTY_STREETNUMBER;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_ADDPROPERTY_STREETSUFFIX;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_ADDPROPERTY_ZIP;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_LOGIN_PASSWORD;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_LANDLORDLOGIN_USERNAME;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTEMAIL_PREFIX;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_EMAIL_SUFFIX;

public class LandlordFourTenantsFlowTest extends HouserieBasicTest{
	
	MessagePage messagePage;
	AsLandlordPage asLandlordPage;
	AddTenantsPage addTenantsPage;
	SelectProductsPage selectProductsPage;
	CouponCodePage couponCodePage;
	PaymentPage paymentPage;
	HomePageBeforeLoginPage homePageBeforeLoginPage;
	
	String baseURL;
	String userName, password, currentTime;
	String propertyName, propertyDescription, streetNumber, streetName, streetSuffix, city, zip, phoneNo, leaseRent, leaseDeposite, leaseTerm;
	int noOfTenants = 4;
	String timeStamp, emailAddress;
	String emailID, prefix, suffix;
	String countOfTenants;
	
	@BeforeClass(alwaysRun=true)
	public void setUp(){
		
		System.out.println("LandlordFourTenantsFlow test case started.");

		baseURL = site.getBaseURL();
		Assert.assertFalse(site.deleteHouserieCookies(baseURL), "Verify Deleting cookies.");
		
	}
	
	@AfterClass(alwaysRun=true)
	public void tearDown(){
		
		System.out.println("LandlordFourTenantsFlow test case end.");
		
	}
	
	//@Test(groups="smoke")
	public void LandlordSingleTenantFlow(){
		
		countOfTenants = Integer.toString(noOfTenants);
		
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
		prefix = houserieLib.getPropertyValue(DEFAULT_TENANTEMAIL_PREFIX);
		suffix = houserieLib.getPropertyValue(DEFAULT_EMAIL_SUFFIX);
		
		emailID = houserieUtils.generateUniqueEmailID(prefix, suffix);
		
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
		addTenantsPage.selectTenantCountDropdown(countOfTenants);
		addTenantsPage = site.goToAddTenantsPage();
		
		addTenantsPage.enterFirstName("Fname"+currentTime);
		addTenantsPage.enterMiddleName("Mname"+currentTime);
		addTenantsPage.enterLastName("Lname"+currentTime);
		addTenantsPage.enterSuffix("Mr.");
		addTenantsPage.enterEmail(emailID);
		
		for(int i=0; i < noOfTenants-1; i++){
			timeStamp = houserieUtils.getCurrentTimeStamp();
			emailAddress = houserieUtils.generateUniqueEmailID(prefix, suffix);
			System.out.println(timeStamp);
			System.out.println(emailAddress);
			addTenantsPage.enterMultipleFirstName(i, "Fname"+timeStamp);
			addTenantsPage.enterMultipleLastName(i, "Lname"+timeStamp);
			addTenantsPage.enterMultipleEmailAddress(i, emailAddress);			
		}
		addTenantsPage.clickNextButton();
		
		System.out.println("Navigating to the select product page.");
		
		selectProductsPage = site.goToSelectProductsPage();
		
	}

}
