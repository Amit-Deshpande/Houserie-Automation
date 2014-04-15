package com.rentalroost.automation.houserieqa.processor.PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.rentalroost.automation.houserieqa.HouserieWebPage;

public class AsLandlordPage extends HouserieWebPage {

	public AsLandlordPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="property_listing_title")
	private WebElement propertyName;
	
	@FindBy(id="property_listing_description")
	private WebElement propertyDescription;
	
	@FindBy(id="property_listing_street_number")
	private WebElement streetNumber;
	
	@FindBy(id="property_listing_street_name")
	private WebElement streetName;
	
	@FindBy(id="property_listing_street_suffix")
	private WebElement streetSuffix;
	
	@FindBy(id="property_listing_city")
	private WebElement cityName;
	
	@FindBy(id="property_listing_state")
	private WebElement stateDropDown;
	
	@FindBy(id="property_listing_zip")
	private WebElement zipCode;
	
	@FindBy(id="property_listing_phone_number")
	private WebElement phoneNumber;
	
	@FindBy(id="property_listing_lease_rent")
	private WebElement leaseRent;
	
	@FindBy(id="property_listing_lease_deposit")
	private WebElement leaseDeposit;
	
	@FindBy(id="property_listing_lease_term")
	private WebElement leaseTerm;
	
	@FindBy(id="property_listing_rating_high_end")
	private WebElement highEndPropertyRadioButton;
	
	@FindBy(id="property_listing_rating_moderate_living")
	private WebElement moderateLivingPropertyRadioButton;
	
	@FindBy(id="property_listing_rating_low_end")
	private WebElement lowEndPropertyRadioButton;
		
	@FindBy(name="save_quit")
	private WebElement saveAndExitButton;
	
	@FindBy(xpath="/html/body/div[8]/div/div/div/div[3]/form/div[7]/a/input")
	private WebElement cancelButton;
	
	@FindBy(name="next_btn")
	private WebElement nextButton;
	
	public void enterPropertyName(String property){
		propertyName.sendKeys(property);
	}
	
	public void enterPropertyDescription(String description){
		propertyDescription.sendKeys(description);
	}
	
	public void enterStreetNumber(String streetNo){
		streetNumber.sendKeys(streetNo);
	}
	
	public void enterStreetName(String name){
		streetName.sendKeys(name);
	}
	
	public void enterStreetSuffix(String suffix){
		streetSuffix.sendKeys(suffix);
	}
	
	public void enterCityName(String city){
		cityName.sendKeys(city);
	}
	
	public void enterStateDropDown(String state){
		stateDropDown.sendKeys(state);
	}
	
	public void enterZipCode(String zip){
		zipCode.sendKeys(zip);
	}
	
	public void enterPhoneNumber(String phoneNo){
		phoneNumber.sendKeys(phoneNo);
	}
	
	public void enterLeaseRent(String rent){
		leaseRent.sendKeys(rent);
	}
	
	public void enterLeaseDeposit(String deposit){
		leaseDeposit.sendKeys(deposit);
	}
	
	public void enterLeaseTerm(String term){
		leaseTerm.sendKeys(term);
	}
	
	public void clickHighEndPropertyRadioButton(){
		System.out.println("highEndPropertyRadioButton :" +highEndPropertyRadioButton);
		highEndPropertyRadioButton.click();
	}
	
	public void clickModerateLivingPropertyRadioButton(){
		moderateLivingPropertyRadioButton.click();
	}
	
	public void clickLowEndPropertyRadioButton(){
		lowEndPropertyRadioButton.click();
	}
	
	public void clickSaveAndExitButton(){
		saveAndExitButton.click();
	}
	
	public void clickCancelButton(){
		cancelButton.click();
	}
	
	public void clickNextButton(){
		nextButton.click();
	}

	@Override
	public List<WebElement> pageElementsToWait() {
		List<WebElement> webElements = new ArrayList<WebElement>();
		webElements.add(propertyName);
		webElements.add(nextButton);
		return webElements;
	}

	@Override
	public String setPageName() {
		return null;
	}

}
