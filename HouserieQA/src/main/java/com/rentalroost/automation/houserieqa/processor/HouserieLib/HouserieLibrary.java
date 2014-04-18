package com.rentalroost.automation.houserieqa.processor.HouserieLib;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_ANNUALSALARY;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_CITY;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_DATEOFBIRTH;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_FIRSTNAME;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_LASTNAME;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_PHONENO;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_SSNLASTFOURDIGIT;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_STREETADDRESS;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_STREETNO;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTINFO_ZIPCODE;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTLOGIN2;
import static com.rentalroost.automation.houserieqa.PropertyConstants.DEFAULT_TENANTLOGIN3;

import com.rentalroost.automation.houserieqa.HouseriePropertyResolver;
import com.rentalroost.automation.houserieqa.processor.HouserieUtil.HouserieUtils;
import com.rentalroost.automation.houserieqa.processor.Navigator.HouserieSiteManager;
import com.rentalroost.automation.houserieqa.processor.Navigator.HouserieSiteManager.HouserieSite;
import com.rentalroost.automation.houserieqa.processor.PageObjects.AddTenantsPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.AsLandlordPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.CouponCodePage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.HomePageBeforeLoginPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.MessagePage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.MyOrderHistoryPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.PaymentPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.SelectProductsPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.TenantAuthorizeReportPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.TenantEnterInfoAndAcceptTermsPage;


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
	@SuppressWarnings("unused")
	private HouseriePropertyResolver resolver;
	private HouserieUtils houserieUtils;

	public HouserieUtils getLealtaUtils() {
	return houserieUtils;
	}

	// private String baseURL;

	// private constructor as we don't want the object to be created by any tests

	@SuppressWarnings("static-access")
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
	
	public boolean tenantAuthrization(String tenantUserName, String password, String email){
		boolean status = false;
		
		String fName, lName, dob, streetNo, streetAdd, city, zip, phone, annualSalary;
		String ccNo, ccExpirationMonth, ccExpirationYear, verificationCode;
		String sssLastFourDigit;
		
		MessagePage messagePage;
		TenantEnterInfoAndAcceptTermsPage tenantEnterInfoAndAcceptTermsPage;
		PaymentPage paymentPage;
		TenantAuthorizeReportPage tenantAuthorizeReportPage;
		
		fName = houserieLib.getPropertyValue(DEFAULT_TENANTINFO_FIRSTNAME);
		lName = houserieLib.getPropertyValue(DEFAULT_TENANTINFO_LASTNAME);
		dob = houserieLib.getPropertyValue(DEFAULT_TENANTINFO_DATEOFBIRTH);
		streetNo = houserieLib.getPropertyValue(DEFAULT_TENANTINFO_STREETNO);
		streetAdd = houserieLib.getPropertyValue(DEFAULT_TENANTINFO_STREETADDRESS);
		city = houserieLib.getPropertyValue(DEFAULT_TENANTINFO_CITY);
		zip = houserieLib.getPropertyValue(DEFAULT_TENANTINFO_ZIPCODE);
		phone = houserieLib.getPropertyValue(DEFAULT_TENANTINFO_PHONENO);
		annualSalary = houserieLib.getPropertyValue(DEFAULT_TENANTINFO_ANNUALSALARY);		
		ccNo = houserieLib.getPropertyValue(DEFAULT_CC_CREDITCARDNUMBER);
		ccExpirationMonth = houserieLib.getPropertyValue(DEFAULT_CC_EXPIRATIONMONTH);
		ccExpirationYear = houserieLib.getPropertyValue(DEFAULT_CC_EXPIRATIONYEAR);
		verificationCode = houserieLib.getPropertyValue(DEFAULT_CC_VERIFICATIONCODE);
		houserieLib.getPropertyValue(DEFAULT_ADDPROPERTY_PROPERTYNAME);		
		sssLastFourDigit = houserieLib.getPropertyValue(DEFAULT_TENANTINFO_SSNLASTFOURDIGIT);
		houserieUtils.getCurrentTimeStamp();
		
		Assert.assertTrue(houserieLib.loginToHR(tenantUserName, password), "Login is not successfully done.");		
		messagePage = site.goToMessagePage();
		
		System.out.println("Tenant user logged in and navigated to the message page.");
		
		System.out.println(messagePage.getTenantMessageDetails("1").getText());
		
		messagePage.getTenantMessageDetailsForClick("1").click();
		tenantEnterInfoAndAcceptTermsPage = site.goToTenantEnterInfoAndAcceptTermsPage();
		
		System.out.println("User is on the accept terms page.");
		
		tenantEnterInfoAndAcceptTermsPage.setTenantFirstName(fName);
		tenantEnterInfoAndAcceptTermsPage.setTenantLastName(lName);
		tenantEnterInfoAndAcceptTermsPage.setTenantDateOfBirth(dob);
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
		
		System.out.println("User is on the Tenant Authorize Report page.");
		
		tenantAuthorizeReportPage.clickAgreeChkBox();
		tenantAuthorizeReportPage.setFullName(fName);
		tenantAuthorizeReportPage.setSSN(sssLastFourDigit);
		tenantAuthorizeReportPage.clickAuthorizeButton();
		
		tenantAuthorizeReportPage.waitABit(1500);
		
		driver.switchTo().alert().accept();
		
		paymentPage = site.goToPaymentPage();
		
		System.out.println("User is on the payment page.");
		
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
		
		System.out.println("User is on the message page.");
		
		messagePage.clickMyAccountDropArrow();
		messagePage.clickSignOutLink();
		
		System.out.println("User is logged out.");
		
		status = true;
		return status;		
	}
	
	public boolean cancelOrderByLandlord(String landlordUserName, String password){	
		
		MessagePage messagePage;
		MyOrderHistoryPage myOrderHistoryPage;
		HomePageBeforeLoginPage homePageBeforeLoginPage;
		
		String orderBeforeCancelStatus, orderAfterCancelStatus;		
		boolean status = false;
		
		orderBeforeCancelStatus = "Pending";
		orderAfterCancelStatus = "Cancelled";
		
		Assert.assertTrue(houserieLib.loginToHR(landlordUserName, password), "Login is not successfully done.");
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
		
		status = true;
		return status;
	}
	
	
} // end of Library class
