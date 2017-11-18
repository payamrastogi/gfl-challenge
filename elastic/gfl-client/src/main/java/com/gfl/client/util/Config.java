package com.gfl.client.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gfl.commons.exception.HostNameNotProvidedException;
import com.gfl.commons.exception.PortNotProvidedException;
public class Config
{	
	private static Logger logger = LoggerFactory.getLogger(Config.class);
	
	private static final int MAX_THREADS=8;
	private static final int MIN_THREADS=2;
	private static final int PORT=8882;
	private static final int TIME_OUT_MILLIS=30000;

	private int port;
	private int maxThreads;
	private int minThreads;
	private int timeOutMillis;
	private final String gflServiceHostname;
	private final String gflServicePort;
	private final String slackResponseTemplate;
	private final String slackErrorResponseTemplate;
	private final int slackResponseSize;
	
	private static final String HTTP="http://";
	
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
	
	public String getGflServiceUrl()
	{
		return HTTP+getGflServiceHostname()+":"+getGflServicePort();
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
	
	public String getSlackErrorResponseTemplate() {
		return slackErrorResponseTemplate;
	}
	
	public Config(File configFile) throws IOException, HostNameNotProvidedException, PortNotProvidedException
	{
		this(loadProperties(configFile));
	}
	
	Config(Properties prop) throws HostNameNotProvidedException, PortNotProvidedException
	{
		this.gflServiceHostname = prop.getProperty("gfl_service_hostname");
		this.gflServicePort = prop.getProperty("gfl_service_port");
		if(this.gflServiceHostname==null)
			throw new HostNameNotProvidedException("Please provide the hostname for gfl service in config.properties");
		if(this.gflServicePort==null)
			throw new PortNotProvidedException("Please provide the port numbet for gfl service in config.properties");
		this.slackResponseTemplate = prop.getProperty("slack_response_template");
		int responseSize = 2;
		try
		{
			responseSize = Integer.parseInt(prop.getProperty("slack_response_size"));
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
		this.slackErrorResponseTemplate=prop.getProperty("slack_error_response_template");
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
