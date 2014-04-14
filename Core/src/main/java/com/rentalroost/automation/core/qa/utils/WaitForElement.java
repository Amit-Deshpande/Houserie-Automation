package com.rentalroost.automation.core.qa.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
 
public class WaitForElement implements ExpectedCondition {
 
	By findCondition;
 
	public WaitForElement(By by) {
		this.findCondition = by;
	}
 

	public Boolean apply(WebDriver driver) {
		driver.findElement(this.findCondition);
		return Boolean.valueOf(true);
	}

	public Object apply(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
 
 
}