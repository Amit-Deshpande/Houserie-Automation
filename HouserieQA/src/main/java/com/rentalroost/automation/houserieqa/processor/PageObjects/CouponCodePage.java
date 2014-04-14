package com.rentalroost.automation.houserieqa.processor.PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.rentalroost.automation.houserieqa.HouserieWebPage;

public class CouponCodePage extends HouserieWebPage{

	public CouponCodePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="code")
	private WebElement couponCodeField;
	
	@FindBy(name="skip")
	private WebElement skipButton;
	
	@FindBy(name="sub_btn")
	private WebElement submitButton;
	
	public void enterCouponCode(String code){
		couponCodeField.sendKeys(code);
	}
	
	public void clickSkipButton(){
		skipButton.click();
	}
	
	public void clickSubmitButton(){
		submitButton.click();
	}

	@Override
	public List<WebElement> pageElementsToWait() {
		List<WebElement> webElements = new ArrayList<WebElement>();
		webElements.add(couponCodeField);
		webElements.add(submitButton);
		return webElements;
	}

	@Override
	public String setPageName() {
		return null;
	}

}
