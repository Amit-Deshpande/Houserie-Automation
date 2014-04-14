package com.rentalroost.automation.houserieqa.processor.PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.rentalroost.automation.houserieqa.HouserieWebPage;

public class TenantAuthorizeReportPage extends HouserieWebPage{

	public TenantAuthorizeReportPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="screening_authorization_is_agree")
	private WebElement agreeChkBox;
	
	@FindBy(id="screening_authorization_name")
	private WebElement fullName;
	
	@FindBy(id="screening_authorization_ssn")
	private WebElement SSN;
	
	@FindBy(id="authorize_pay")
	private WebElement authorizePayChkBox;
	
	@FindBy(name="order_btn")
	private WebElement authorizeButton;
	
	@FindBy(xpath="id('wrapper')/div[2]/p")
	private WebElement screeningAuthorizationConfirmationMsg;
	
	public void clickAgreeChkBox(){
		agreeChkBox.click();
	}
	
	public void setFullName(String fName){
		fullName.sendKeys(fName);
	}
	
	public void setSSN(String ssn){
		SSN.sendKeys(ssn);
	}
	
	public void clickAuthorizePayChkBox(){
		authorizePayChkBox.click();
	}
	
	public void clickAuthorizeButton(){
		authorizeButton.click();
	}

	public String getScreeningAuthorizationConfirmationMsg(){
		return screeningAuthorizationConfirmationMsg.getText().trim();
	}

	@Override
	public List<WebElement> pageElementsToWait() {
		List<WebElement> myElementList = new ArrayList<WebElement>();
		myElementList.add(agreeChkBox);
		myElementList.add(authorizeButton);
		return myElementList;
	}

	@Override
	public String setPageName() {
		return null;
	}

}
