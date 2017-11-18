package com.gfl.service.resource;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.threadPool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gfl.commons.exception.HostNameNotProvidedException;
import com.gfl.commons.exception.PortNotProvidedException;
import com.gfl.service.feignclient.ElasticFeignClient;
import com.gfl.service.feignclient.SfBayFeignClient;
import com.gfl.service.response.GflResponse;
import com.gfl.service.response.StandardResponse;
import com.gfl.service.response.StatusResponse;
import com.gfl.service.search.ElasticSearch;
import com.gfl.service.search.SfBaySearch;
import com.gfl.service.util.Config;
import com.google.gson.Gson;
public class GflResource
{
	private static Logger logger = LoggerFactory.getLogger(GflResource.class);
	
	public static void main(String args[])
	{
		Config config = initConfig();
		port(config.getPort());
		threadPool(config.getMaxThreads(), config.getMinThreads(), config.getTimeOutMillis());
		
		get("/gfl/search/stopCode/:stopCode", (request, response) -> 
		{
			String stopCode = request.params(":stopCode");
			List<GflResponse> list = getResponse(config, stopCode);
			if(!list.isEmpty())
			{
				String res = new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(list)));
				logger.debug(res);
				return res;
			}
			else
			{
				String res = new Gson().toJson(new StandardResponse(StatusResponse.ERROR, new Gson().toJson("Result for the Stop Code: "+ stopCode+ "  not found")));
				logger.error("Result for the Stop Code: "+ stopCode+ "  not found");
				return res;
			}
		});
	}
	
	public static Config initConfig()
	{
		Config config = null;
		try
		{
			File file = new File("src/main/resources/config.properties");
			if (!file.exists()) 
			{
				System.out.println("config.properties not found @" + file.getAbsolutePath());
				System.exit(1);
			}
			config = new Config(file);
		}
		catch(IOException ie)
		{
			logger.error(ie.getMessage());
			System.exit(1);
		}
		catch(HostNameNotProvidedException he)
		{
			logger.error(he.getMessage());
			System.exit(1);
		}
		catch(PortNotProvidedException ae)
		{
			logger.error(ae.getMessage());
			System.exit(1);
		}
		return config;
	}
	
	public static List<GflResponse> getResponse(Config config, String stopCode) throws JsonParseException, JsonMappingException, IOException
	{
		List<GflResponse> responseList = new ArrayList<>(); 
		String agencyName = getAgencyName(config, stopCode);
		logger.debug(agencyName);
		String agencyCode = getAgencyCode(config, agencyName);
		logger.debug(agencyCode);
		
		StandardResponse stopResponse = getStopMonitoringResponse(config, agencyCode, stopCode);	
		logger.debug(stopResponse.getStatus().toString());
		if("SUCCESS".equals(stopResponse.getStatus().toString()))
		{
			List<Map<String, String>> result = new Gson().fromJson(stopResponse.getData(), List.class);
			for(Map<String, String>  map: result)
			{
				String busNo = map.get("busNo");
				String arrivalTime = map.get("arrivalTime");
				logger.debug(busNo + " "+ arrivalTime);
				responseList.add(new GflResponse(stopCode, agencyName,agencyCode, busNo, arrivalTime));
			}
		}
		else
		{
			logger.error("Error in getting StandardStopMonitoringResponse");
		}
    		return responseList;
	}
	
	public static String getAgencyName(Config config, String stopCode) throws JsonParseException, JsonMappingException, IOException
	{
		ElasticFeignClient ec = new ElasticFeignClient(config);
		ElasticSearch elasticSearch = ec.createClient();
		StandardResponse elasticResponse = ec.getResponse(elasticSearch, stopCode);
		String agencyName = null;
		logger.debug(elasticResponse.getStatus().toString());
		if("SUCCESS".equals(elasticResponse.getStatus().toString())) 
		{
			Map<String, String> result = new Gson().fromJson(elasticResponse.getData(), Map.class);
			agencyName = result.get("agencyName");
			logger.debug("agencyName: "+agencyName);
		}
		else
		{
			logger.error("Error in getting Elastic Response");
		}
		return agencyName;
	}
	
	public static String getAgencyCode(Config config, String agencyName)
	{
		String agencyCode  = null;
		
		SfBayFeignClient sc = new SfBayFeignClient(config);
		logger.debug(config.getGflSfBayUrl());
		SfBaySearch search = sc.createClient();
		StandardResponse scResponse = sc.getResponse(search, agencyName);

		if("SUCCESS".equals(scResponse.getStatus().toString()))
		{
			Map<String, String> result = new Gson().fromJson(scResponse.getData(), Map.class);
			agencyCode = result.get("agencyCode");
			logger.debug("agencyCode: "+agencyCode);
		}
		else
		{
			logger.error("Error in getting Operator Response");
		}
		return agencyCode;
	}
	
	public static StandardResponse getStopMonitoringResponse(Config config, String agencyCode, String stopCode)
	{
		SfBayFeignClient sc = new SfBayFeignClient(config);
		logger.debug(config.getGflSfBayUrl());
		SfBaySearch search = sc.createClient();
		StandardResponse stopResponse = sc.getResponse(search, agencyCode, stopCode);
		logger.debug(stopResponse.toString());
		return stopResponse;
	}
}
