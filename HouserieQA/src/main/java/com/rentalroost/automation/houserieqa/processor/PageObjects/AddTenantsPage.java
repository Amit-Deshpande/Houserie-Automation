package com.rentalroost.automation.houserieqa.processor.PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.rentalroost.automation.houserieqa.HouserieWebPage;

public class AddTenantsPage extends HouserieWebPage{

	public AddTenantsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="screening_order_tenant_count")
	private WebElement tenantCountDropdown;
	
	@FindBy(id="screening_order_flow_landlord")
	private WebElement orderflowLandlordRadioButton;
	
	@FindBy(id="screening_order_flow_property_manager")
	private WebElement orderflowPropertyManagerRadioButton;
	
	@FindBy(id="screening_order_authorization")
	private WebElement authorizationCheckBox;
	
	@FindBy(id="screening_order_target_first_name")
	private WebElement firstName;
	
	@FindBy(id="screening_order_target_middle_name")
	private WebElement middleName;
	
	@FindBy(id="screening_order_target_last_name")
	private WebElement lastName;
	
	@FindBy(id="screening_order_target_suffix")
	private WebElement suffix;
	
	@FindBy(id="screening_order_target_email")
	private WebElement email;
	
	@FindBy(id="cancel")
	private WebElement cancelButton;
	
	@FindBy(id="back-to-select-properties")
	private WebElement backButton;
	
	@FindBy(id="go-to-select-products")
	private WebElement nextButton;
	
	public void selectTenantCountDropdown(String noOfTenants){
		tenantCountDropdown.sendKeys(noOfTenants);
	}
	
	public void selectOrderflowLandlordRadioButton(){
		orderflowLandlordRadioButton.click();
	}
	
	public void selectOrderflowPropertyManagerRadioButton(){
		orderflowPropertyManagerRadioButton.click();
	}
	
	public void selectAuthorizationCheckBox(){
		authorizationCheckBox.click();
	}
	
	public void enterFirstName(String fName){
		firstName.sendKeys(fName);
	}
	
	public void enterMiddleName(String mName){
		middleName.sendKeys(mName);
	}
	
	public void enterLastName(String lName){
		lastName.sendKeys(lName);
	}
	
	public void enterSuffix(String suf){
		suffix.sendKeys(suf);
	}
	
	public void enterEmail(String emailID){
		email.sendKeys(emailID);
	}
	
	public void clickCancelButton(){
		cancelButton.click();
	}
	
	public void clickBackButton(){
		backButton.click();
	}
	
	public void clickNextButton(){
		nextButton.click();
	}
	
	public void enterMultipleFirstName(int number, String fName){
		WebElement firstName = driver.findElement(By.xpath("id('screening_order_related_orders_attributes_"+ number  +"_target_first_name')"));
		firstName.sendKeys(fName);
		
	}
	
	public void enterMultipleLastName(int number, String lName){
		WebElement lastName = driver.findElement(By.xpath("id('screening_order_related_orders_attributes_"+ number  +"_target_last_name')"));
		lastName.sendKeys(lName);
		
	}
	
	public void enterMultipleEmailAddress(int number, String emailID){
		WebElement email = driver.findElement(By.xpath("id('screening_order_related_orders_attributes_"+ number  +"_target_email')"));
		email.sendKeys(emailID);
		
	}

	@Override
	public List<WebElement> pageElementsToWait() {
		List<WebElement> webElements = new ArrayList<WebElement>();
		webElements.add(tenantCountDropdown);
		webElements.add(nextButton);
		return webElements;
	}

	@Override
	public String setPageName() {
		return null;
	}

}
