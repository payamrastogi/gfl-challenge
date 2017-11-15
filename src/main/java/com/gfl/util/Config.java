package com.gfl.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config
{	
	private final String sfBayHostName;
	private final String sfBayApiKey;
	private final String sfBayOperators;
	private final String sfBayStopMonitoring;
	
	public String getSfBayHostName()
	{
		return this.sfBayHostName;
	}
	
	public String getSfBayApiKey()
	{
		return this.sfBayApiKey;
	}
	
	public String getSfBayOperators()
	{
		return this.sfBayOperators;
	}
	
	public String getSfBayStopMonitoring()
	{
		return this.sfBayStopMonitoring;
	}
	
	public Config(File configFile) throws IOException
	{
		this(loadProperties(configFile));
	}
	
	Config(Properties prop)
	{
		this.sfBayHostName = prop.getProperty("sf_bay_hostName");
		this.sfBayApiKey = prop.getProperty("sf_bay_api_key");
		this.sfBayOperators = prop.getProperty("sf_bay_operators");
		this.sfBayStopMonitoring = prop.getProperty("sf_bay_stop_monitoring");
	}
	
	private static Properties loadProperties(File file) throws IOException
	{
		Properties prop = new Properties();
	    InputStream is = null;
	    try 
	    {
	    	is = new FileInputStream(file);
	    	prop.load(is);
	    	return prop;
	    }
	    finally 
	    {
	    	is.close();
	    }
	 }
	
	 private final String[] get(Properties prop, String key) 
	 {
		 String value = prop.getProperty(key);
		 if (value != null) 
		 {
			 return value.split(",");    
		 }
		 return null;
	 }
}
