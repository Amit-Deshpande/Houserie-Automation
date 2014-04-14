package com.rentalroost.automation.houserieqa.processor.PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.rentalroost.automation.houserieqa.HouserieWebPage;

public class TenantEnterInfoAndAcceptTermsPage extends HouserieWebPage{

	public TenantEnterInfoAndAcceptTermsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="rental_app_given_name")
	private WebElement firstName;
	
	@FindBy(id="rental_app_family_name")
	private WebElement lastName;
	
	@FindBy(id="rental_app_date_of_birth")
	private WebElement dateOfBirth;
	
	@FindBy(id="rental_app_ssn_number")
	private WebElement sssNo;
	
	@FindBy(id="rental_app_address_line1")
	private WebElement streetNo;
	
	@FindBy(id="rental_app_street_name")
	private WebElement streetAddress;
	
	@FindBy(id="rental_app_municipality")
	private WebElement city;
	
	@FindBy(id="rental_app_state_id")
	private WebElement state;
	
	@FindBy(id="rental_app_zip_code")
	private WebElement zipCode;
	
	@FindBy(id="rental_app_country_code")
	private WebElement countryCode;
	
	@FindBy(id="rental_app_email_address")
	private WebElement emailID;
	
	@FindBy(id="rental_app_telephone")
	private WebElement phoneNo;
	
	@FindBy(id="rental_app_annual_income")
	private WebElement annualSalary;
	
	@FindBy(id="add_alias")
	private WebElement addAliasButton;
	
	@FindBy(id="rental_app_alliases_attributes_0_given_name")
	private WebElement givenName;
	
	@FindBy(id="rental_app_alliases_attributes_0_family_name")
	private WebElement familyName;
	
	@FindBy(id="qualify-information")
	private WebElement nextButton;
	
	@FindBy(xpath="id('collapse3')/ul/li[5]/a/img")
	private WebElement cancelButton;
	
	
	@FindBy(id="popup-fname")
	private WebElement popupFirstName;
	
	@FindBy(id="popup-lname")
	private WebElement popupLastName;
	
	@FindBy(id="popup-dob")
	private WebElement popupdob;
	
	@FindBy(id="popup-ssn")
	private WebElement popupSSN;
	
	@FindBy(id="popup-streetno")
	private WebElement popupStreetno;
	
	@FindBy(id="popup-streetaddress")
	private WebElement popupStreetAddress;
	
	@FindBy(id="popup-city")
	private WebElement popupCity;
	
	@FindBy(id="popup-state")
	private WebElement popupState;
	
	@FindBy(id="popup-zipcode")
	private WebElement popupZipcode;
	
	@FindBy(id="popup-cc")
	private WebElement popupCC;
	
	@FindBy(id="popup-email")
	private WebElement popupEmail;
	
	@FindBy(id="popup-phone")
	private WebElement popupPhone;
	
	@FindBy(id="confirm")
	private WebElement confirmButton;
	
	@FindBy(xpath=".//*[@id='terms-cond']/div[3]/input[1]")
	private WebElement iagreeButton;
	
	public void setTenantFirstName(String fname){
		firstName.clear();
		firstName.sendKeys(fname);
	}
	
	public void setTenantLastName(String lname){
		lastName.clear();
		lastName.sendKeys(lname);
	}
	
	public void setTenantDateOfBirth(String dofb){
		dateOfBirth.clear();
		dateOfBirth.sendKeys(dofb);
	}
	
	public void setTenantSSNNumber(String ssnNumber){
		sssNo.clear();
		sssNo.sendKeys(ssnNumber);
	}
	
	public void setTenantStreetNumber(String streetNumber){
		streetNo.clear();
		streetNo.sendKeys(streetNumber);
	}
	
	public void setTenantStreetAddress(String streetAdd){
		streetAddress.clear();
		streetAddress.sendKeys(streetAdd);
	}
	
	public void setTenantCityAddress(String cityName){
		city.clear();
		city.sendKeys(cityName);
	}
	
	public void setTenantState(String stateName){
		state.sendKeys(stateName);
	}
	
	public void setTenantZipCode(String zip){
		zipCode.clear();
		zipCode.sendKeys(zip);
	}
	
	public void setTenantCountryCode(String country){
		countryCode.sendKeys(country);
	}
	
	public void setTenantEmailID(String email){
		emailID.clear();
		emailID.sendKeys(email);
	}
	
	public void setTenantPhoneNumber(String phone){
		phoneNo.clear();
		phoneNo.sendKeys(phone);
	}
	
	public void setTenantAnnualSalary(String salary){
		annualSalary.clear();
		annualSalary.sendKeys(salary);
	}
	
	public void clickNextButton(){
		nextButton.click();
	}
	
	public void clickCancelButton(){
		cancelButton.click();
	}
	
	public String getPopupFirstName(){
		return popupFirstName.getText().trim();
	}
	
	public String getPopupLastName(){
		return popupLastName.getText().trim();
	}
	
	public String getPopupdob(){
		return popupdob.getText().trim();
	}
	
	public String getPopupSSN(){
		return popupSSN.getText().trim();
	}
	
	public String getPopupStreetno(){
		return popupStreetno.getText().trim();
	}
	
	public String getPopupStreetAddress(){
		return popupStreetAddress.getText().trim();
	}
	
	public String getPopupCity(){
		return popupCity.getText().trim();
	}
	
	public String getPopupState(){
		return popupState.getText().trim();
	}
	
	public String getPopupZipcode(){
		return popupZipcode.getText().trim();
	}
	
	public String getPopupCC(){
		return popupCC.getText().trim();
	}
	
	public String getPopupEmail(){
		return popupEmail.getText().trim();
	}
	
	public String getPopupPhone(){
		return popupPhone.getText().trim();
	}
	
	public void clickConfirmButton(){
		confirmButton.click();
	}
	
	public void clickIagreeButton(){
		iagreeButton.click();
	}
	
	
	// Add Alias	
	public void clickaddAliasButton(){
		addAliasButton.click();
	}
	
	public void setGivenName(String given){
		givenName.sendKeys(given);
	}
	
	public void setFamilyName(String family){
		familyName.sendKeys(family);
	}

	@Override
	public List<WebElement> pageElementsToWait() {
		List<WebElement> myElementList = new ArrayList<WebElement>();		
		myElementList.add(nextButton);
		myElementList.add(emailID);
		return myElementList;
	}

	@Override
	public String setPageName() {
		return null;
	}

}
