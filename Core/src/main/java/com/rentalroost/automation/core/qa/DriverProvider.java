package com.rentalroost.automation.core.qa;

import java.io.File;import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.rentalroost.automation.core.qa.utils.ReadFlags;

public class DriverProvider {

	private WebDriver driver;
	private WebDriverBackedSelenium wbSel;
	private DesiredCapabilities cap;

	private static DriverProvider drvProvider;
	private WebDriverEventListener webDriverEventListener = new EventListener();

	private DriverProvider() {

		driver = getDriver();
	}

	public static DriverProvider createDriver() {
		if (drvProvider == null) {
			drvProvider = new DriverProvider();
		}

		return drvProvider;
	}

	/*
	 * Retrieve the desired browser capabilities.
	 */
	private DesiredCapabilities getDesiredCapabilities(String browserName) {

		if (browserName.equalsIgnoreCase("firefox")){						
			cap = DesiredCapabilities.firefox();
			cap.setCapability(FirefoxDriver.PROFILE, getFFProfile());
		}
		else if (browserName.equalsIgnoreCase("ie"))
			cap = DesiredCapabilities.internetExplorer();
		else if (browserName.equalsIgnoreCase("chrome"))
			cap = DesiredCapabilities.chrome();
		else if (browserName.equalsIgnoreCase("htmlunit"))
			cap = DesiredCapabilities.htmlUnit();
		cap.setJavascriptEnabled(true);
		cap.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
		// cap.setCapability(PROFILE, profile);
		return cap;
	}

	class ScreenshootingRemoteWebDriver extends RemoteWebDriver implements TakesScreenshot {

		public ScreenshootingRemoteWebDriver(URL remoteURL, DesiredCapabilities desiredCapabilities) {
			super(remoteURL, desiredCapabilities);
		}

		public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
			String base64 = execute(DriverCommand.SCREENSHOT).getValue().toString();
			return target.convertFromBase64Png(base64);
		}

	}

	/*
	 * The following is the method which creates the WebDriver instance.
	 */
	private WebDriver getDriver() {

		ReadFlags readFlag = ReadFlags.getInstnace();
		String browserName = readFlag.getFlag("browser");
		System.out.println("Browser: " + readFlag.getFlag("browser"));
		System.out.println("Env: " + System.getenv());
		
			if (browserName.equalsIgnoreCase("firefox")) {
				
				FirefoxProfile profile = getFFProfile();

				driver = new FirefoxDriver(profile);
				driver = new EventFiringWebDriver(driver).register(webDriverEventListener);

			} else if (browserName.equalsIgnoreCase("ie")){								File file = new File("C:/Lealta Media/Lealta_Automation/HSR/QA/IEDriverServer_x64_2.26.2/IEDriverServer.exe");				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
				driver = new InternetExplorerDriver();
				driver = new EventFiringWebDriver(driver).register(webDriverEventListener);
			}else if (browserName.equalsIgnoreCase("chrome")){								File file = new File("C:/Lealta Media/Lealta/chromedriver_win_26.0.1383.0/chromedriver.exe");				System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
				driver = new ChromeDriver();
				driver = new EventFiringWebDriver(driver).register(webDriverEventListener);				driver.manage().window().maximize();
			}else if (browserName.equalsIgnoreCase("htmlunit")){
				driver = new HtmlUnitDriver();
				driver = new EventFiringWebDriver(driver).register(webDriverEventListener);
				driver.get("about:blank");
			}
		if (driver instanceof HtmlUnitDriver)
			((HtmlUnitDriver) driver).setJavascriptEnabled(true);
		
		return driver;
	}

	/**
	 * @return
	 */
	private FirefoxProfile getFFProfile() {
		/*FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("extensions.firebug.onByDefault", true);
		profile.setPreference("extensions.firebug.net.enableSites", true);
		profile.setPreference("extensions.firebug.netexport.alwaysEnableAutoExport", true);
		profile.setPreference("extensions.firebug.netexport.autoExportToFile", true);
		profile.setPreference("extensions.firebug.netexport.showPreview", false);
		profile.setPreference("extensions.firebug.netexport.sendToConfirmation", false);*/		
		FirefoxProfile profile = (new ProfilesIni()).getProfile("default");
		return profile;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getWebDriver() {
		return driver;
	}

}
