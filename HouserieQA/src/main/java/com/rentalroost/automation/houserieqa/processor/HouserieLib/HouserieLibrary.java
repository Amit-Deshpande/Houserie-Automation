package com.rentalroost.automation.houserieqa.processor.HouserieLib;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

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
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_CC_CREDITCARDNUMBER;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_CC_EXPIRATIONMONTH;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_CC_EXPIRATIONYEAR;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_CC_VERIFICATIONCODE;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_LOGIN_SUCCESS_MESSAGE;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTLOGIN;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTLOGIN2;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTLOGIN3;

import com.rentalroost.automation.core.qa.utils.ReadFlags;
import com.rentalroost.automation.houserieqa.HouseriePropertyResolver;
import com.rentalroost.automation.houserieqa.processor.HouserieUtil.HouserieUtils;
import com.rentalroost.automation.houserieqa.processor.Navigator.HouserieSiteManager;
import com.rentalroost.automation.houserieqa.processor.Navigator.HouserieSiteManager.HouserieSite;
import com.rentalroost.automation.houserieqa.processor.PageObjects.AddTenantsPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.AsLandlordPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.CouponCodePage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.HomePageBeforeLoginPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.MessagePage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.PaymentPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.SelectProductsPage;


public class HouserieLibrary {

	private static HouserieLibrary houserieLib;

	public static HouserieLibrary getInstnace(WebDriver driver) {

	if (houserieLib == null) {
			synchronized (HouserieLibrary.class) {
			if (houserieLib == null) {
				houserieLib = new HouserieLibrary(driver);
				}
			}
	}

	return houserieLib;
	}

	// Define all Page urls
	private HouserieSite site;
	private WebDriver driver;
	private HouseriePropertyResolver resolver;
	private HouserieUtils houserieUtils;

	public HouserieUtils getLealtaUtils() {
	return houserieUtils;
	}

	// private String baseURL;

	// private constructor as we don't want the object to be created by any tests

	private HouserieLibrary(WebDriver driver) {
		System.out.println("Houserie Library construcation");
		site = ((HouserieSiteManager.HouserieSite) HouserieSiteManager.getInstnace().getSite(driver));
		resolver = HouseriePropertyResolver.getInstnace();
		houserieUtils = houserieUtils.getInstnace(driver);
		this.driver = driver;
	}

	public WebDriver getDriver() {
	return driver;
	}
	
	public String getPropertyValue(String key) {
		return HouseriePropertyResolver.getInstnace().getvalue(key);
	}
	
	public boolean loginToHR(String userName, String password){		
		boolean status = false;
		
		HomePageBeforeLoginPage homePageBeforeLoginPage;
		houserieLib.getPropertyValue(DEFAULT_LOGIN_SUCCESS_MESSAGE);
		
		homePageBeforeLoginPage = site.goToHomePageBeforeLoginPage();
		homePageBeforeLoginPage.clickLoginLink();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		homePageBeforeLoginPage.enterUserEmail(userName);
		homePageBeforeLoginPage.enterUserPassword(password);
		homePageBeforeLoginPage.clickLoginButton();
		
		site.goToMessagePage();
		
		status = true;
		return status;
	}
	
	public boolean initaiteOrder(String landlordUserName, String tenantUserName, String password, String paymethod, String currentTime){
		boolean status = false;
		
		MessagePage messagePage;
		AsLandlordPage asLandlordPage;
		AddTenantsPage addTenantsPage;
		SelectProductsPage selectProductsPage;
		CouponCodePage couponCodePage;
		PaymentPage paymentPage;
		
		String propertyName, propertyDescription, streetNumber, streetName, streetSuffix, city, zip, phoneNo, leaseRent, leaseDeposite, leaseTerm;
		String ccNo, ccExpirationMonth, ccExpirationYear, verificationCode;		
		
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
		ccNo = houserieLib.getPropertyValue(DEFAULT_CC_CREDITCARDNUMBER);
		ccExpirationMonth = houserieLib.getPropertyValue(DEFAULT_CC_EXPIRATIONMONTH);
		ccExpirationYear = houserieLib.getPropertyValue(DEFAULT_CC_EXPIRATIONYEAR);
		verificationCode = houserieLib.getPropertyValue(DEFAULT_CC_VERIFICATIONCODE);
		
		System.out.println("User is doing login");
		
		Assert.assertTrue(houserieLib.loginToHR(landlordUserName, password), "Login is not successfully done.");
		
		messagePage = site.goToMessagePage();
		
		System.out.println("User is on the Message page.");
		
		messagePage.clickStartScreeningTenantsButton();
		
		asLandlordPage = site.goToAsLandlordPage();
		
		System.out.println("User is on the Landlord page.");
		
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
		System.out.println("Select High End property radio button.");
		asLandlordPage.waitABit(1500);
		asLandlordPage.clickHighEndPropertyRadioButton();
		System.out.println("Selected High End property radio button.");
		asLandlordPage.clickNextButton();
		
		addTenantsPage = site.goToAddTenantsPage();
		
		System.out.println("User is on the Add Tenant page.");
		
		addTenantsPage.enterFirstName("Fname"+currentTime);
		addTenantsPage.enterMiddleName("Mname"+currentTime);
		addTenantsPage.enterLastName("Lname"+currentTime);
		addTenantsPage.enterSuffix("Mr.");
		addTenantsPage.enterEmail(tenantUserName);
		addTenantsPage.clickNextButton();
		addTenantsPage.waitABit(3000);
		selectProductsPage = site.goToSelectProductsPage();
		selectProductsPage.waitABit(3000);
		
	    ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,"+800+");");
	    selectProductsPage.waitABit(5000);

		if(paymethod == "Landlord"){
			selectProductsPage.clickLandloardPaysRadioButton();
		}
		else{
			selectProductsPage.clickTenantPaysRadioButton();
		}
		
		selectProductsPage.clickInitiateOrderButton();
		
		if(paymethod == "Landlord"){
		
			couponCodePage = site.goToCouponCodePage();
			couponCodePage.clickSkipButton();
		
			paymentPage = site.goToPaymentPage();
		
			paymentPage.enterCCField(ccNo);
			paymentPage.enterCCExpMonth(ccExpirationMonth);
			paymentPage.enterCCExpYear(ccExpirationYear);
			paymentPage.enterCCVerification(verificationCode);
			paymentPage.clickPaymentNowButton();
		
			paymentPage.waitForAnElement(By.xpath("id('maincont')/div[2]/p"));
		
			Assert.assertTrue(paymentPage.getPaymentConfirmationNote().contains("Thank you"), "Thank you message does not appears screen.");
		}
		
		System.out.println("User has initiated order.");
		
		selectProductsPage.clickMyAccountDropArrow();
		selectProductsPage.clickMessageLink();
		messagePage = site.goToMessagePage();
		
		System.out.println("User is on the Message page.");
		
		status = true;
		return status;
	}
	
	public boolean initaiteOrderWithMultipleTenants(String landlordUserName, String tenantUserName, String password, String paymethod, int noOfTenants){
		boolean status = false;
		
		MessagePage messagePage;
		AsLandlordPage asLandlordPage;
		AddTenantsPage addTenantsPage;
		SelectProductsPage selectProductsPage;
		CouponCodePage couponCodePage;
		PaymentPage paymentPage;
		
		String propertyName, propertyDescription, streetNumber, streetName, streetSuffix, city, zip, phoneNo, leaseRent, leaseDeposite, leaseTerm;
		String ccNo, ccExpirationMonth, ccExpirationYear, verificationCode;	
		String currentTime, timeStamp;
		String tenantLogin0, tenantLogin1, countOfTenants;
		String tenantLoginID;
		
		tenantLogin0 = houserieLib.getPropertyValue(DEFAULT_TENANTLOGIN2);
		tenantLogin1 = houserieLib.getPropertyValue(DEFAULT_TENANTLOGIN3);
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
		ccNo = houserieLib.getPropertyValue(DEFAULT_CC_CREDITCARDNUMBER);
		ccExpirationMonth = houserieLib.getPropertyValue(DEFAULT_CC_EXPIRATIONMONTH);
		ccExpirationYear = houserieLib.getPropertyValue(DEFAULT_CC_EXPIRATIONYEAR);
		verificationCode = houserieLib.getPropertyValue(DEFAULT_CC_VERIFICATIONCODE);
		currentTime = houserieUtils.getCurrentTimeStamp();
		
		ArrayList<String> testUser = new ArrayList<String>();
		testUser.add(tenantLogin0);
		testUser.add(tenantLogin1);
		
		System.out.println("User is doing login");
		
		Assert.assertTrue(houserieLib.loginToHR(landlordUserName, password), "Login is not successfully done.");
		
		messagePage = site.goToMessagePage();
		
		System.out.println("User is on the Message page.");
		
		messagePage.clickStartScreeningTenantsButton();
		
		asLandlordPage = site.goToAsLandlordPage();
		
		System.out.println("User is on the Landlord page.");
		
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
		System.out.println("Select High End property radio button.");
		asLandlordPage.waitABit(1500);
		asLandlordPage.clickHighEndPropertyRadioButton();
		System.out.println("Selected High End property radio button.");
		asLandlordPage.clickNextButton();
		
		countOfTenants = Integer.toString(noOfTenants);
		
		addTenantsPage = site.goToAddTenantsPage();
		
		System.out.println("User is on the Add Tenants page.");
		
		addTenantsPage.selectTenantCountDropdown(countOfTenants);
		addTenantsPage = site.goToAddTenantsPage();
		
		addTenantsPage.enterFirstName("Fname"+currentTime);
		addTenantsPage.enterMiddleName("Mname"+currentTime);
		addTenantsPage.enterLastName("Lname"+currentTime);
		addTenantsPage.enterSuffix("Mr.");
		addTenantsPage.enterEmail(tenantUserName);
		
		for(int i=0; i < noOfTenants-1; i++){
			timeStamp = houserieUtils.getCurrentTimeStamp();
			System.out.println(timeStamp);
			tenantLoginID = testUser.get(i);
			System.out.println(tenantLoginID);
			addTenantsPage.enterMultipleFirstName(i, "Fname"+timeStamp);
			addTenantsPage.enterMultipleLastName(i, "Lname"+timeStamp);
			addTenantsPage.enterMultipleEmailAddress(i, tenantLoginID);			
		}		
		
		addTenantsPage.clickNextButton();
		
		addTenantsPage.waitABit(3000);
		selectProductsPage = site.goToSelectProductsPage();
		selectProductsPage.waitABit(3000);
		
	    ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,"+1100+");");
	    selectProductsPage.waitABit(5000);

		if(paymethod == "Landlord"){
			selectProductsPage.clickLandloardPaysRadioButton();
		}
		else{
			selectProductsPage.clickTenantPaysRadioButton();
		}
		
		selectProductsPage.clickInitiateOrderButton();
		
		if(paymethod == "Landlord"){
		
			couponCodePage = site.goToCouponCodePage();
			couponCodePage.clickSkipButton();
		
			paymentPage = site.goToPaymentPage();
		
			paymentPage.enterCCField(ccNo);
			paymentPage.enterCCExpMonth(ccExpirationMonth);
			paymentPage.enterCCExpYear(ccExpirationYear);
			paymentPage.enterCCVerification(verificationCode);
			paymentPage.clickPaymentNowButton();
		
			paymentPage.waitForAnElement(By.xpath("id('maincont')/div[2]/p"));
		
			Assert.assertTrue(paymentPage.getPaymentConfirmationNote().contains("Thank you"), "Thank you message does not appears screen.");
		}
		
		System.out.println("User has initiated order.");
		
		selectProductsPage.clickMyAccountDropArrow();
		selectProductsPage.clickMessageLink();
		messagePage = site.goToMessagePage();
		
		System.out.println("User is on the Message page.");
		
		status = true;
		return status;
	}
	
	
} // end of LealtaLibrary class
