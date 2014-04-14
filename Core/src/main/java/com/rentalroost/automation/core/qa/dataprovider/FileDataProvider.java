package com.rentalroost.automation.core.qa.dataprovider;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.DataProvider;



public class FileDataProvider {
	@DataProvider(name = "getDataFromCsvFile")
	public static Iterator<Object[]> getDataFromCsvFile(Method testMethod) throws Exception {
		Map<String, String> arguments = DataProviderUtils.resolveDataProviderArguments(testMethod);
		List<String> lines = FileDataProvider.getRawLinesFromFile(arguments.get("filePath"));
		List<Object[]> data = new ArrayList<Object[]>();
		for (String line : lines) {
			data.add(new Object[] { line });
		}
		return data.iterator();
	}

	public static List<String> getRawLinesFromFile(Method testMethod) throws Exception {
		Map<String, String> arguments = DataProviderUtils.resolveDataProviderArguments(testMethod);
		return FileDataProvider.getRawLinesFromFile(arguments.get("filePath"));
	}

	@SuppressWarnings("unchecked")
	public static List<String> getRawLinesFromFile(String filePath) throws Exception {
		InputStream is = new FileInputStream(new File(filePath));
		List<String> lines = IOUtils.readLines(is, "UTF-8");
		is.close();
		return lines;
	}
}