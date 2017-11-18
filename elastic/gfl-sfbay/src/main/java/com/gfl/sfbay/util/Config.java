package com.gfl.sfbay.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gfl.commons.exception.ApiKeyNotProvidedException;
import com.gfl.commons.exception.HostNameNotProvidedException;

public class Config
{	
	private static Logger logger = LoggerFactory.getLogger(Config.class);
	private static final int MAX_THREADS=8;
	private static final int MIN_THREADS=2;
	private static final int PORT=8882;
	private static final int TIME_OUT_MILLIS=30000;
	
	private final String sfBayHostname;
	private final String sfBayApiKey;
	private int port;
	private int maxThreads;
	private int minThreads;
	private int timeOutMillis;
	
	private final String HTTPS = "https://";
	
	public int getPort() {
		return port;
	}
	
	public int getMaxThreads() {
		return maxThreads;
	}

	public int getMinThreads() {
		return minThreads;
	}

	public int getTimeOutMillis() {
		return timeOutMillis;
	}

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
	
	public Config(File configFile) throws IOException, com.gfl.commons.exception.HostNameNotProvidedException, ApiKeyNotProvidedException
	{
		this(loadProperties(configFile));
	}
	
	Config(Properties prop) throws com.gfl.commons.exception.HostNameNotProvidedException, ApiKeyNotProvidedException
	{
		this.sfBayHostname = prop.getProperty("sf_bay_hostname");
		this.sfBayApiKey = prop.getProperty("sf_bay_api_key");
		if(this.sfBayHostname==null)
			throw new HostNameNotProvidedException("Please provide the hostname for 511.org transit service in config.properties");
		if(this.sfBayApiKey==null)
			throw new ApiKeyNotProvidedException("Please provide the apikey for 511.org transit service in config.properties");
		try
		{
			this.port = prop.getProperty("port")!=null?Integer.parseInt(prop.getProperty("port")):PORT;
			this.maxThreads = prop.getProperty("max_threads")!=null?Integer.parseInt(prop.getProperty("max_threads")):MAX_THREADS;
			this.minThreads = prop.getProperty("min_threads")!=null?Integer.parseInt(prop.getProperty("min_threads")):MIN_THREADS;
			this.timeOutMillis = prop.getProperty("time_out_millis")!=null?Integer.parseInt(prop.getProperty("time_out_millis")):TIME_OUT_MILLIS;
		}
		catch(NumberFormatException ne)
		{
			logger.error(ne.getMessage());
			logger.error("Starting with default confgiguration");
			this.port = PORT;
			this.maxThreads = MAX_THREADS;
			this.minThreads = MIN_THREADS;
			this.timeOutMillis = TIME_OUT_MILLIS;
		}
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
