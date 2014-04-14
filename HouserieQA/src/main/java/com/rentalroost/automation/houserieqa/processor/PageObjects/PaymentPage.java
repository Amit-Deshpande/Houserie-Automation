package com.rentalroost.automation.houserieqa.processor.PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.rentalroost.automation.houserieqa.HouserieWebPage;

public class PaymentPage extends HouserieWebPage{

	public PaymentPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="authorize_pay")
	private WebElement authorizePayChkBox;
	
	@FindBy(id="cc-number")
	private WebElement ccField;
	
	@FindBy(id="cc-ex-month")
	private WebElement ccExpMonth;
	
	@FindBy(id="cc-ex-year")
	private WebElement ccExpYear;
	
	@FindBy(id="ex-csc")
	private WebElement ccVerification;
	
	@FindBy(id="cc-submit")
	private WebElement paymentNowButton;
	
	@FindBy(xpath="id('maincont')/div[2]/p")
	private WebElement paymentConfirmationNote;
	
	public void enterCCField(String CC){
		ccField.sendKeys(CC);
	}
	
	public void enterCCExpMonth(String month){
		ccExpMonth.sendKeys(month);
	}
	
	public void enterCCExpYear(String year){
		ccExpYear.sendKeys(year);
	}
	
	public void enterCCVerification(String veri){
		ccVerification.sendKeys(veri);
	}
	
	public void clickPaymentNowButton(){
		paymentNowButton.click();
	}
	
	public void clickAuthorizePayChkBox(){
		authorizePayChkBox.click();
	}
	
	public String getPaymentConfirmationNote(){
		return paymentConfirmationNote.getText().trim();
	}
	
	public WebElement getTenantsCostDetails(String row, String col){
		WebElement getData = driver.findElement(By.xpath(".//*[@id='collapse1']/div/div/table/tbody/tr[" + row + "]/td[" + col + "]"));
		return getData;
	}

	@Override
	public List<WebElement> pageElementsToWait() {
		List<WebElement> myElementList = new ArrayList<WebElement>();
		myElementList.add(ccField);
		myElementList.add(paymentNowButton);
		return myElementList;
	}

	@Override
	public String setPageName() {
		return null;
	}

}
