
package com.rentalroost.automation.houserieqa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class HouseriePropertyResolver {

	private static HouseriePropertyResolver houseriePropertyResolver;

	private Properties properties = new Properties();

	private HouseriePropertyResolver() {
		try {
			properties.load(new FileInputStream("src/main/java/resources/TestDataProperties.properties"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Unable to load properties", e);
		} catch (IOException e) {
			throw new RuntimeException("Unable to load properties", e);
		}

	}

	// Do not not worried about Thread synchronization.

	public static HouseriePropertyResolver getInstnace() {

		if (houseriePropertyResolver == null) {
			synchronized (HouseriePropertyResolver.class) {
				if (houseriePropertyResolver == null) {
					houseriePropertyResolver = new HouseriePropertyResolver();
				}
			}

		}

		return houseriePropertyResolver;
	}

	public String getvalue(String key) {
		return (String) properties.get(key);
	}

}
