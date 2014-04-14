package com.rentalroost.automation.houserieqa.processor.PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.rentalroost.automation.houserieqa.HouserieWebPage;

public class SelectProductsPage extends HouserieWebPage{

	public SelectProductsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//div[@id='collapse1']/div/div/div[2]/table/tbody/tr[2]/td[4]/input")
	private WebElement ultimateProductRadioButton;
	
	@FindBy(xpath="//div[@id='collapse1']/div/div/div[2]/table/tbody/tr[2]/td[2]/input")
	private WebElement basicProductRadioButton;
	
	@FindBy(xpath="//div[@id='collapse1']/div/div/div[2]/table/tbody/tr[2]/td[3]/input")
	private WebElement premierProductRadioButton;
	
    @FindBy(xpath = "//img[@alt='Custom Sample Report']")    
    private WebElement sampleReportLink;
    
	@FindBy(id="screening_order_tenant_pays_false")
	private WebElement landloardPaysRadioButton;
	
	@FindBy(id="screening_order_tenant_pays_true")
	private WebElement tenantPaysRadioButton;
	
	@FindBy(id="cancel")
	private WebElement cancelButton;
	
	@FindBy(id="back-to-add-tenants")
	private WebElement backButton;
	
	@FindBy(id="select-products-done")
	private WebElement initiateOrderButton;
	
	public void clickBasicProductRadioButton(){
		basicProductRadioButton.click();
	}
	
	public void clickPremierProductRadioButton(){
		premierProductRadioButton.click();
	}
	
	public void clickUltimateProductRadioButton(){
		ultimateProductRadioButton.click();
	}
	
	public void clickSampleReportLink(){
		sampleReportLink.click();
	}
	
	public void clickLandloardPaysRadioButton(){
		landloardPaysRadioButton.click();
	}
	
	public void clickTenantPaysRadioButton(){
		tenantPaysRadioButton.click();
	}

	public void clickCancelButton(){
		cancelButton.click();
	}
	
	public void clickBackButton(){
		backButton.click();
	}
	
	public void clickInitiateOrderButton(){
		initiateOrderButton.click();
	}
	
	public WebElement getTenantsCostDetails(String row, String col){
		WebElement getData = driver.findElement(By.xpath(".//*[@id='collapse1']/div/div[1]/div[2]/table/tbody/tr[" + row + "]/td[" + col + "]"));
		return getData;
	}

	@Override
	public List<WebElement> pageElementsToWait() {
		List<WebElement> myElementList = new ArrayList<WebElement>();
		myElementList.add(sampleReportLink);
		myElementList.add(tenantPaysRadioButton);
		myElementList.add(initiateOrderButton);
		return myElementList;
	}

	@Override
	public String setPageName() {
		return null;
	}

}
