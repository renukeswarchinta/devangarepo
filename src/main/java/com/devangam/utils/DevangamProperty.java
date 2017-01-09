package com.devangam.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DevangamProperty {

	
	private static DevangamProperty instance = new DevangamProperty();
	private Properties properties;
	
	private DevangamProperty()
	{
		InputStream is = this.getClass().getResourceAsStream("application.properties");
		properties = new Properties();
		try {
			properties.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static DevangamProperty getInstance()
	{
		return instance;
	}
	
	public String getProperties(String key)
	{
		return properties.getProperty(key);
	}


}
