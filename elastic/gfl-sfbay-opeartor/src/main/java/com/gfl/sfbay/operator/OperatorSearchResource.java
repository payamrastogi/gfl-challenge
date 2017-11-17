package com.gfl.sfbay.operator;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.threadPool;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gfl.sfbay.operator.model.OperatorResponse;
import com.gfl.sfbay.operator.model.OperatorSearch;
import com.gfl.sfbay.operator.response.Response;
import com.gfl.sfbay.operator.response.StandardResponse;
import com.gfl.sfbay.operator.response.StatusResponse;
import com.gfl.sfbay.operator.util.Config;
import com.google.gson.Gson;

public class OperatorSearchResource
{
	private static Logger logger = LoggerFactory.getLogger(OperatorSearchResource.class);
	private static Map<String, String> agencyNameCodeMap;
	
	public static void main(String args[])
	{
		port(8882);
		int maxThreads = 8;
		int minThreads = 2;
		int timeOutMillis = 30000;
		threadPool(maxThreads, minThreads, timeOutMillis);
		Config config = initConfig();
		get("/gfl/search/agencyName/:agencyName", (request, response) -> 
		{
			String agencyName = request.params(":agencyName");
			String agencyCode = getAgencyCode(config, agencyName);
			if(agencyCode!=null)
			{
				return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(new Response(agencyName, agencyCode))));
			}
			else
				return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, new Gson().toJson("Agency Code for the Agency Name: "+ agencyName+ "  not found"))); 
		}); 
	}
	
	public static Config initConfig()
	{
		Config config = null;
		try
		{
			File file = new File("src/main/resources/config.properties");
			//File file = new File("src/main/resources/config.properties");
			if (!file.exists()) 
			{
				System.out.println("config.properties not found @" + file.getAbsolutePath());
				System.exit(1);
			}
			config = new Config(file);
		}
		catch(IOException e)
		{
			logger.error(e.getMessage());
		}
		return config;
	}
	
	public static String getAgencyCode(Config config, String agencyName)
	{
		String agencyCode  = null;
		if(agencyNameCodeMap==null)
		{
			OperatorFeignClient oc = new OperatorFeignClient(config);
			logger.debug(config.getSfBayUrl());
			OperatorSearch operatorSearch = oc.createClient(config.getSfBayUrl());
			List<OperatorResponse>  ocResponse = oc.getResponse(operatorSearch);
			agencyNameCodeMap = oc.getAgencyNameCodeMap(ocResponse);
		}
		if(agencyNameCodeMap!=null)
		{
			if(agencyNameCodeMap.containsKey(agencyName))
			{
				agencyCode = agencyNameCodeMap.get(agencyName);
				logger.debug(agencyCode);
			}
		}
		return agencyCode;
	}
}
