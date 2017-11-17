package com.gfl.elastic.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config
{	

	private final String elasticHostname;
	private final String elasticIndexName;
	private static final String HTTPS="https://";
	
	public String getElasticHostname()
	{
		return this.elasticHostname;
	}
	
	public String getElasticSearchUrl()
	{
		return HTTPS+getElasticHostname();
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
}
