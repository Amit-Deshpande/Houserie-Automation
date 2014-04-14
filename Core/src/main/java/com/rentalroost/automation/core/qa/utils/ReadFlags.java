package com.rentalroost.automation.core.qa.utils;

import java.util.Properties;

public class ReadFlags {
		private static ReadFlags ipnPropertyResolver;
		public static ReadFlags getInstnace() {

			if (ipnPropertyResolver == null) {
				synchronized (ReadFlags.class) {
					if (ipnPropertyResolver == null) {
						ipnPropertyResolver = new ReadFlags();
					}
				}

			}

			return ipnPropertyResolver;
		}

	

	Properties flags;
	Properties defaultFlags;
	
	private ReadFlags(){
		flags = new Properties();
		init();
		overrideDefaultFlags();
		overrideEnvFlags();
	}
	/*
	 * initialize with default values to flags.
	 */
	private void init(){
		defaultFlags =  new Properties();
		//defaultFlags.setProperty("browser", "htmlunit");
		defaultFlags.setProperty("browser", "firefox");
		defaultFlags.setProperty("remote", "false");
		defaultFlags.setProperty("ipaddress", "localhost");
		defaultFlags.setProperty("port", "8080");
		defaultFlags.setProperty("env", "QA");
		defaultFlags.setProperty("baseURL", "http://houserie:R3nta1r00st@staging.houserie.com/");		//defaultFlags.setProperty("baseURL", "https://www.houserie.com//");	}
	
	/*
	 * override any flags specified from command line.
	 */
	private void overrideDefaultFlags(){
		setFlag("browser", System.getProperty("browser"));
		setFlag("remote", System.getProperty("remote"));
		setFlag("ipaddress", System.getProperty("ipaddress"));
		setFlag("port", System.getProperty("port"));
		setFlag("env", System.getProperty("env"));
		setFlag("baseURL", System.getProperty("baseURL"));
	}
	private void overrideEnvFlags(){
		setFlag("browser", System.getenv("browser"));
		setFlag("remote", System.getenv("remote"));
		setFlag("ipaddress", System.getenv("ipaddress"));
		setFlag("port", System.getenv("port"));
		setFlag("env", System.getenv("env"));
		setFlag("baseURL", System.getProperty("baseURL"));
	}
	
	public Properties getAllFlags(){
		return System.getProperties();
	}
	
	public String getFlag(String flagName){
		return flags.getProperty(flagName, defaultFlags.getProperty(flagName));
	}
	
	private void setFlag(String flagName, String flagValue){
		if(flagValue == null || flagValue.equals(""))
			return;
		
		flags.setProperty(flagName, flagValue);
	}
}
