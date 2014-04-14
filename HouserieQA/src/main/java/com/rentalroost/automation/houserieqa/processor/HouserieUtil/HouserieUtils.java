package com.rentalroost.automation.houserieqa.processor.HouserieUtil;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.htmlunit.corejs.javascript.JavaScriptException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.rentalroost.automation.houserieqa.HouseriePropertyResolver;
import com.rentalroost.automation.houserieqa.processor.HouserieLib.HouserieLibrary;

public class HouserieUtils {

	private static HouserieUtils houserieUtils;

	// Do not not worried about Thread synchronization.

	public static HouserieUtils getInstnace(WebDriver driver) {

		if (houserieUtils == null) {
			synchronized (HouserieLibrary.class) {
				if (houserieUtils == null) {
					houserieUtils = new HouserieUtils(driver);
				}
			}

		}

		return houserieUtils;
	}

	private WebDriver driver;



	// private contructer as we don't want the object to be created by any tests

	private HouserieUtils(WebDriver driver) {

		System.out.println("Houserie Utils construcation");

		this.driver = driver;

	}
	
	public String getCurrentTimeStamp() {

		// gets the current dateand time
		Calendar now = new GregorianCalendar();
		StringBuffer response = new StringBuffer();
		response.append((now.get(Calendar.DATE))).append(now.get(Calendar.MONTH)).append(now.get(Calendar.YEAR)).append("-");
		response.append(now.get(Calendar.HOUR_OF_DAY)).append(now.get(Calendar.MINUTE)).append(now.get(Calendar.SECOND)).append(now.get(Calendar.MILLISECOND));
   	return response.toString();
	}

	public String generateUniqueEmailID(String Prefix, String suffix){
		StringWriter writer = new StringWriter();
		
		String time = getCurrentTimeStamp();
		
		writer.append(Prefix);
		//writer.append("+");
		writer.append(time);
		writer.append(suffix);
		return writer.toString();		
	}
	
	public static Connection getDBConnection(){
		
		 Connection conn = null; 
		 String url = "jdbc:mysql://192.168.0.129:3306/"; 
		 String dbName = "D_Proc"; 
		 String driver = "com.mysql.jdbc.Driver"; 
		 String userName = "root"; 
		 String password = "HSMysql"; 
		 try { 
		 Class.forName(driver).newInstance(); 
		 conn = DriverManager.getConnection(url + dbName, userName, password); 
		 }
		 catch(Exception e){			 
			 	e.printStackTrace();			 
		 }
		 
		 return conn;
		
	}

	
	/**
	 * Executes a script on an element
	 * @note Really should only be used when the web driver is sucking at exposing
	 * functionality natively
	 * @param script The script to execute
	 * @param element The target of the script, referenced as arguments[0]
	 */
	public void trigger(String script, WebElement element) {
	    ((JavascriptExecutor)driver).executeScript(script, element);
	}

	/** Executes a script
	 * @note Really should only be used when the web driver is sucking at exposing
	 * functionality natively
	 * @param script The script to execute
	 */
	public Object trigger(String script) {
	    return ((JavascriptExecutor)driver).executeScript(script);
	}

	/**
	 * Opens a new tab for the given URL
	 * @param url The URL to 
	 * @throws JavaScriptException If unable to open tab
	 */
	public void openTab(String url) {
	    String script = "var d=document,a=d.createElement('a');a.target='_blank';a.href='%s';a.innerHTML='.';d.body.appendChild(a);return a";
	    Object element = trigger(String.format(script, url));
	    if (element instanceof WebElement) {
	        WebElement anchor = (WebElement) element; anchor.click();
	        trigger("var a=arguments[0];a.parentNode.removeChild(a);", anchor);
	    } else {
	        throw new JavaScriptException(element, "Unable to open tab", 1);
	    }       
	}
	
	/**
	 * Switches to the non-current window
	 */
	public void switchWindow() throws NoSuchWindowException, NoSuchWindowException {
	    Set<String> handles = driver.getWindowHandles();
	    String current = driver.getWindowHandle();
	    handles.remove(current);
	    String newTab = handles.iterator().next();
	    driver.switchTo().window(newTab);
	}
	
	// Utility functions end

}
