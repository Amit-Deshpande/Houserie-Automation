package com.rentalroost.automation.houserieqa.processor.PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.rentalroost.automation.houserieqa.HouserieWebPage;

public class MyOrderHistoryPage extends HouserieWebPage{

	public MyOrderHistoryPage(WebDriver driver) {		
		super(driver);		
	}
	
	@FindBy(xpath="id('maincont')/div/div/div[1]")
	private WebElement pageTitle;
	
	@FindBy(xpath="id('order_collection')/div[3]/div[1]/a[1]")
	private WebElement cancelOrderButton;
	
	@FindBy(id="change_payment_option_btn")
	private WebElement changeOrderOptionsButton;
	
	@FindBy(xpath="id('order_collection')/div[2]/ul/li[3]/h4")
	private WebElement orderStatusReport;
	
	@FindBy(xpath="html/body/div[12]")
	private WebElement changePaymentOptionDialog;
	
	@FindBy(id="pays_landlord")
	private WebElement paysLandlordButton;
	
	@FindBy(id="pays_tenant")
	private WebElement paysTenantButton;
	
	@FindBy(xpath="html/body/div[12]/div[3]/div/button[1]")
	private WebElement changePaymentOptionButton;
	
	@FindBy(xpath="html/body/div[12]/div[3]/div/button[2]")
	private WebElement cancelButton;
	
	public String getPageTitle(){
		return pageTitle.getText().trim();
	}
	
	public void clickCancelOrderButton(){
		cancelOrderButton.click();
	}
	
	public void clickChangeOrderOptionsButton(){
		changeOrderOptionsButton.click();
	}
	
	public String getOrderStatusReport(){
		return orderStatusReport.getText().trim();
	}
	
	public WebElement getChangePaymentOptionDialog(){
		return changePaymentOptionDialog;
	}
	
	public void clickPaysLandlordButton(){
		paysLandlordButton.click();
	}
	
	public void clickPaysTenantButton(){
		paysTenantButton.click();
	}
	
	public void clickChangePaymentOptionButton(){
		changePaymentOptionButton.click();
	}
	
	public void clickCancelButton(){
		cancelButton.click();
	}
	
	public WebElement getOrderHistoryTableDetails(String row, String column){
		WebElement getData = driver.findElement(By.xpath("id('TenScr_link')/a[" + row + "]/li/span[" + column + "]"));
		return getData;
	}

	@Override
	public List<WebElement> pageElementsToWait() {
		List<WebElement> myElementList = new ArrayList<WebElement>();
		myElementList.add(pageTitle);
		return myElementList;
	}

	@Override
	public String setPageName() {
		return null;
	}

}
