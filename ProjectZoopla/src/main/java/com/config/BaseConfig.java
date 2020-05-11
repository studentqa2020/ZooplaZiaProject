package com.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class BaseConfig {
	
	public static String getConfig(String key) throws Throwable {
		
		Properties prop = new Properties();
		String filePath = "./config.properties";
		FileInputStream fis = new FileInputStream(filePath);
		prop.load(fis);
		return prop.get(key).toString();  // or prop.getProperty(key)
	}

}
