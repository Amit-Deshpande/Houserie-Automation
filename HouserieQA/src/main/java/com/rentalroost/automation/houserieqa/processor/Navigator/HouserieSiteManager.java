package com.rentalroost.automation.houserieqa.processor.Navigator;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.rentalroost.automation.core.qa.utils.ReadFlags;
import com.rentalroost.automation.houserieqa.processor.PageObjects.AddTenantsPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.AsLandlordPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.CouponCodePage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.HomePageBeforeLoginPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.MessagePage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.MyOrderHistoryPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.PaymentPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.SelectProductsPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.TenantAuthorizeReportPage;
import com.rentalroost.automation.houserieqa.processor.PageObjects.TenantEnterInfoAndAcceptTermsPage;

public class HouserieSiteManager {

	public class HouserieSite {

		private HouserieSite() {

		}

		private WebDriver driver;

		public void setDriver(WebDriver driver) {
			this.driver = driver;
		}

		private WebDriver getDriver() {
			return this.driver;
		}		
		
		
		// deletes the cookies
		public boolean deleteHouserieCookies(String baseUrl) {

			boolean flag = false;
			
			Reporter.log("Deleting cookies");	
			driver.get(baseUrl);
			driver.getCurrentUrl();
			Set<Cookie> cookies = driver.manage().getCookies();	
			if (!cookies.isEmpty()) {
				for (Cookie c : cookies) {
					driver.manage().deleteCookieNamed(c.getName());
					if (!cookies.contains(c))
						flag = true;
				}
			} else
				Reporter.log("Cookies list is empty");
			
			driver.manage().deleteAllCookies();

			return flag;
			
		}
		
		public String getBaseURL() {
			return ReadFlags.getInstnace().getFlag("baseURL");
		}
		
		public HomePageBeforeLoginPage goToHomePageBeforeLoginPage(){
			String testHRURL = getBaseURL();
			getDriver().get(testHRURL);
			
			HomePageBeforeLoginPage homePageBeforeLoginPage = PageFactory.initElements(driver, HomePageBeforeLoginPage.class);			
			return homePageBeforeLoginPage;
		}	
		
		public HomePageBeforeLoginPage goToLoggedOutPage(){
			HomePageBeforeLoginPage homePageBeforeLoginPage = PageFactory.initElements(driver, HomePageBeforeLoginPage.class);			
			return homePageBeforeLoginPage;
		}
		
		public MessagePage goToMessagePage(){
			MessagePage messagePage = PageFactory.initElements(driver, MessagePage.class);
			return messagePage;
		}
		
		public AsLandlordPage goToAsLandlordPage(){
			AsLandlordPage asLandlordPage = PageFactory.initElements(driver, AsLandlordPage.class);
			return asLandlordPage;
		}
		
		public AddTenantsPage goToAddTenantsPage(){
			AddTenantsPage addTenantsPage = PageFactory.initElements(driver, AddTenantsPage.class);
			return addTenantsPage;
		}
		
		public SelectProductsPage goToSelectProductsPage(){
			SelectProductsPage selectProductsPage = PageFactory.initElements(driver, SelectProductsPage.class);
			return selectProductsPage;
		}
		
		public CouponCodePage goToCouponCodePage(){
			CouponCodePage couponCodePage = PageFactory.initElements(driver, CouponCodePage.class);
			return couponCodePage;
		}
		
		public PaymentPage goToPaymentPage(){
			PaymentPage paymentPage = PageFactory.initElements(driver, PaymentPage.class);
			return paymentPage;
		}
		
		public TenantEnterInfoAndAcceptTermsPage goToTenantEnterInfoAndAcceptTermsPage(){
			TenantEnterInfoAndAcceptTermsPage tenantEnterInfoAndAcceptTermsPage = PageFactory.initElements(driver, TenantEnterInfoAndAcceptTermsPage.class);
			return tenantEnterInfoAndAcceptTermsPage;
		}
		
		public TenantAuthorizeReportPage goToTenantAuthorizeReportPage(){
			TenantAuthorizeReportPage tenantAuthorizeReportPage = PageFactory.initElements(driver, TenantAuthorizeReportPage.class);
			return tenantAuthorizeReportPage;
		}
		
		public MyOrderHistoryPage goToMyOrderHistoryPage(){
			MyOrderHistoryPage myOrderHistoryPage = PageFactory.initElements(driver, MyOrderHistoryPage.class);
			return myOrderHistoryPage;
		}
		
		
		
	}

	private static HouserieSiteManager siteManager = new HouserieSiteManager();

	private HouserieSite site;

	private HouserieSiteManager() {
		site = new HouserieSite();
	}

	// Do not not worried about Thread synchronization.

	public static HouserieSiteManager getInstnace() {
		return siteManager;
	}

	public HouserieSite getSite(WebDriver driver) {
		site.setDriver(driver);
		return site;
	}

}
