package com.gfl.client.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config
{	
	private static Logger logger = LoggerFactory.getLogger(Config.class);
	private final String gflServiceHostname;
	private final String gflServicePort;
	private final String slackResponseTemplate;
	private final int slackResponseSize;
	
	private static final String HTTPS="https://";
	
	public String getGflServiceUrl()
	{
		return HTTPS+getGflServiceHostname();
	}
	
	public String getGflServiceHostname() {
		return gflServiceHostname;
	}

	public String getGflServicePort() {
		return gflServicePort;
	}
	
	public String getSlackResponseTemplate() {
		return slackResponseTemplate;
	}
	
	public int getSlackResponseSize() {
		return slackResponseSize;
	}
	
	public Config(File configFile) throws IOException
	{
		this(loadProperties(configFile));
	}
	
	Config(Properties prop)
	{
		this.gflServiceHostname = prop.getProperty("gfl_service_hostname");
		this.gflServicePort = prop.getProperty("gfl_service_port");
		this.slackResponseTemplate = prop.getProperty("slack_response_template");
		int responseSize = 2;
		try
		{
			responseSize = Integer.parseInt(prop.getProperty("slack_response_size"));
		}
		catch(NumberFormatException n)
		{
			logger.error(n.getMessage());
		}
		this.slackResponseSize = responseSize;
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
