package com.gfl.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config
{	
	private final String sfBayHostname;
	private final String sfBayApiKey;
	private final String sfBayOperators;
	private final String sfBayStopMonitoring;
	private final String elasticHostname;
	private final String elasticIndexName;
	
	public String getSfBayHostname()
	{
		return this.sfBayHostname;
	}
	
	public String getSfBayUrl()
	{
		return "https://"+getSfBayHostname();
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
	
	public String getElasticHostname()
	{
		return this.elasticHostname;
	}
	
	public String getElasticSearchUrl()
	{
		return "https://"+getElasticHostname();
	}
	
	public String getElasticIndexName()
	{
		return this.elasticIndexName;
	}
	
	public Config(File configFile) throws IOException
	{
		this(loadProperties(configFile));
	}
	
	Config(Properties prop)
	{
		this.sfBayHostname = prop.getProperty("sf_bay_hostname");
		this.sfBayApiKey = prop.getProperty("sf_bay_api_key");
		this.sfBayOperators = prop.getProperty("sf_bay_operators");
		this.sfBayStopMonitoring = prop.getProperty("sf_bay_stop_monitoring");
		
		this.elasticHostname = prop.getProperty("elastic_hostname");
		this.elasticIndexName = prop.getProperty("elastic_index_name");
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
