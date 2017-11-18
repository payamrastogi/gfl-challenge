package com.gfl.service.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gfl.service.exception.HostNameNotProvidedException;
import com.gfl.service.exception.PortNotProvidedException;

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
	
	private final String gflElasticHostname;
	private final String gflElasticPort;
	private final String gflSfBayHostname;
	private final String gflSfBayPort;
	
	private static final String HTTP = "http://";
	
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
	
	public String getGflElasticHostname() {
		return gflElasticHostname;
	}

	public String getGflElasticPort() {
		return gflElasticPort;
	}

	public String getGflSfBayHostname() {
		return gflSfBayHostname;
	}

	public String getGflSfBayPort() {
		return gflSfBayPort;
	}

	public String getGflElasticUrl()
	{
		return HTTP+getGflElasticHostname()+":"+getGflElasticPort();
	}
	
	public String getGflSfBayUrl()
	{
		return HTTP+getGflSfBayHostname()+":"+getGflSfBayPort();
	}

	public Config(File configFile) throws IOException, HostNameNotProvidedException, PortNotProvidedException
	{
		this(loadProperties(configFile));
	}
	
	Config(Properties prop) throws HostNameNotProvidedException, PortNotProvidedException
	{
		this.gflElasticHostname = prop.getProperty("gfl_elastic_hostname");
		this.gflElasticPort = prop.getProperty("gfl_elastic_port");
		
		if(this.gflElasticHostname==null)
			throw new HostNameNotProvidedException("Please provide the hostname for gfl elastic service in config.properties");
		if(this.gflElasticPort==null)
			throw new PortNotProvidedException("Please provide the port number for gfl elastic service in config.properties");
		
		this.gflSfBayHostname = prop.getProperty("gfl_sfbay_hostname");
		this.gflSfBayPort = prop.getProperty("gfl_sfbay_port");
		
		if(this.gflSfBayHostname==null)
			throw new HostNameNotProvidedException("Please provide the hostname for gfl sfbay service in config.properties");
		if(this.gflSfBayPort==null)
			throw new PortNotProvidedException("Please provide the port number for gfl sfbay service in config.properties");
		
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
