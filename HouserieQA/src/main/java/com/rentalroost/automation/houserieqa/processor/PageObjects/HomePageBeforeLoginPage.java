package com.rentalroost.automation.houserieqa.processor.PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.rentalroost.automation.houserieqa.HouserieWebPage;

public class HomePageBeforeLoginPage extends HouserieWebPage{

	public HomePageBeforeLoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="login")
	private WebElement loginLink;
	
	@FindBy(id="sign_up")
	private WebElement signUpLink;
	
	@FindBy(xpath="id('main')/div[1]/p")
	private WebElement mainTopText;
	
	@FindBy(xpath="id('maincont')/div[2]/div/a/img")
	private WebElement viewSampleReportLink;
	
	@FindBy(xpath="id('maincont')/div[3]/table/tbody/tr[4]/td/a/img")
	private WebElement learnMoreButton;
	
	@FindBy(xpath="//form[@id='new_user']/li/input")
	private WebElement userEmail;
	
	@FindBy(xpath="//form[@id='new_user']/li/input[2]")
	private WebElement userPassword;
	
	@FindBy(id="login_btn")
	private WebElement loginButton;
	
	@FindBy(id="user_first_name")
	private WebElement userFirstName;
	
	@FindBy(id="user_last_name")
	private WebElement userLastName;
	
	@FindBy(id="user_middle_name")
	private WebElement userMiddleName;
	
	@FindBy(id="user_suffix")
	private WebElement userSuffix;
	
	@FindBy(id="user_password_confirmation")
	private WebElement retypePassword;
	
	@FindBy(id="reg_btn")
	private WebElement createAccountButton;
	
	public WebElement getMainTopText(){
		return mainTopText;
	}
	
	public WebElement getEmailField(){
		return userEmail;
	}
	
	public WebElement getLoginLink(){
		return loginLink;
	}
	
	public WebElement getSignUpLink(){
		return signUpLink;
	}
	
	public void clickLoginLink(){
		loginLink.click();
	}

	public void clickSignUpLink(){
		signUpLink.click();
	}
	
	public void clickViewSampleReportLink(){
		viewSampleReportLink.click();
	}
	
	public void clickLearnMoreButton(){
		learnMoreButton.click();
	}
	
	public void clickLoginButton(){
		loginButton.click();
	}
	
	public void enterUserEmail(String email){
		userEmail.sendKeys(email);
	}
	
	public void enterUserPassword(String pword){
		userPassword.sendKeys(pword);
	}
	
	public void enterUserFirstName(String fname){
		userFirstName.sendKeys(fname);
	}
	
	public void enterUserLastName(String lname){
		userLastName.sendKeys(lname);
	}
	
	public void enterUserMiddleName(String uName){
		userMiddleName.sendKeys(uName);
	}
	
	public void enterUserSuffix(String suffix){
		userSuffix.sendKeys(suffix);
	}
	
	public void enterRetypePassword(String rePassword){
		retypePassword.sendKeys(rePassword);
	}
	
	public void clickCreateAccountButton(){
		createAccountButton.click();
	}
	
	@Override
	public List<WebElement> pageElementsToWait() {
		List<WebElement> webElements = new ArrayList<WebElement>();
		webElements.add(loginLink);
		webElements.add(signUpLink);
		return webElements;
	}

	@Override
	public String setPageName() {
		return null;
	}

}
