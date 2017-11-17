package com.gfl.sfbay.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config
{	
	private final String sfBayHostname;
	private final String sfBayApiKey;
	private final String HTTPS = "https://";
	
	public String getSfBayHostname()
	{
		return this.sfBayHostname;
	}
	
	public String getSfBayUrl()
	{
		return HTTPS+getSfBayHostname();
	}
	
	public String getSfBayApiKey()
	{
		return this.sfBayApiKey;
	}
	
	public Config(File configFile) throws IOException
	{
		this(loadProperties(configFile));
	}
	
	Config(Properties prop)
	{
		this.sfBayHostname = prop.getProperty("sf_bay_hostname");
		this.sfBayApiKey = prop.getProperty("sf_bay_api_key");
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
}
