package com.rentalroost.automation.houserieqa.processor.PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.rentalroost.automation.houserieqa.HouserieWebPage;

public class MessagePage extends HouserieWebPage{

	public MessagePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//div[@id='flash_messages']/span")
	private WebElement noticeMessage;
	
	@FindBy(id="cont-1")
	private WebElement landLoardMessageButton;
	
	@FindBy(id="cont-2")
	private WebElement tenantMessageButton;
	
	@FindBy(xpath="id('start_screen')/a/img")
	private WebElement startScreeningTenantsButton;
	
	@FindBy(xpath="id('search')/input[1]")
	private WebElement searchRentalsTextField;
	
	public String getNoticeMessage(){
		return noticeMessage.getText().trim();
	}
	
	public void clickLandLoardMessageButton(){
		landLoardMessageButton.click();
	}
	
	public void clickTenantMessageButton(){
		tenantMessageButton.click();
	}
	
	public void clickStartScreeningTenantsButton(){
		startScreeningTenantsButton.click();
	}
	
	public void enterSearchRentalsTextField(String rental){
		searchRentalsTextField.sendKeys(rental);
	}
	
	public WebElement getLandloardMessageDetails(String row){
		WebElement getData = driver.findElement(By.xpath(".//*[@id='cont-1-1']/ul/li[" + row + "]/p"));
		return getData;
	}
	
	public WebElement getTenantMessageDetails(String row){
		WebElement getData = driver.findElement(By.xpath(".//*[@id='cont-2-1']/ul/li[" + row + "]/p"));
		return getData;
	}
	
	public WebElement getTenantMessageDetailsForClick(String row){
		WebElement getData = driver.findElement(By.xpath(".//*[@id='cont-2-1']/ul/li[" + row + "]/p/a"));
		return getData;
	}	
	
	@Override
	public List<WebElement> pageElementsToWait() {
		List<WebElement> myElementList = new ArrayList<WebElement>();
		myElementList.add(landLoardMessageButton);
		myElementList.add(tenantMessageButton);
		return myElementList;
	}

	@Override
	public String setPageName() {
		return null;
	}

}
