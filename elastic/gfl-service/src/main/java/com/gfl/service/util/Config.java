package com.gfl.service.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config
{	
	private final String gflElasticHostname;
	private final String gflElasticPort;
	private final String gflSfBayHostname;
	private final String gflSfBayPort;
	private final String slackResponseTemplate;
	
	private static final String HTTP = "http://";
	
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

	public String getSlackResponseTemplate() {
		return slackResponseTemplate;
	}
	
	public String getGflElasticUrl()
	{
		return HTTP+getGflElasticHostname()+":"+getGflElasticPort();
	}
	
	public String getGflSfBayUrl()
	{
		return HTTP+getGflSfBayHostname()+":"+getGflSfBayPort();
	}

	public Config(File configFile) throws IOException
	{
		this(loadProperties(configFile));
	}
	
	Config(Properties prop)
	{
		this.gflElasticHostname = prop.getProperty("gfl_elastic_hostname");
		this.gflElasticPort = prop.getProperty("gfl_elastic_port");
		
		this.gflSfBayHostname = prop.getProperty("gfl_sfbay_hostname");
		this.gflSfBayPort = prop.getProperty("gfl_sfbay_port");
		
		this.slackResponseTemplate = prop.getProperty("slack_response_template");
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
