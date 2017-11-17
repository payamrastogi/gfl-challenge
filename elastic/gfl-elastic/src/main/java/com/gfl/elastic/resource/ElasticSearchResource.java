package com.gfl.elastic.resource;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.threadPool;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gfl.elastic.feignclient.ElasticFeignClient;
import com.gfl.elastic.model.ElasticSearch;
import com.gfl.elastic.model.ElasticSearchResponse;
import com.gfl.elastic.response.Response;
import com.gfl.elastic.response.StandardResponse;
import com.gfl.elastic.response.StatusResponse;
import com.gfl.elastic.util.Config;

import com.google.gson.Gson;
public class ElasticSearchResource 
{
	private static Logger logger = LoggerFactory.getLogger(ElasticSearchResource.class);
	public static void main(String args[])
	{
		port(8881);
		int maxThreads = 8;
		int minThreads = 2;
		int timeOutMillis = 30000;
		threadPool(maxThreads, minThreads, timeOutMillis);
		Config config = initConfig();
		get("/gfl/elastic/search/stopCode/:stopCode", (request, response) -> 
		{
			String stopCode = request.params(":stopCode");
			String agencyName = getAgencyName(config, stopCode);
			if(agencyName!=null)
			{
				return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(new Response(stopCode, agencyName))));
			}
			else
				return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, new Gson().toJson("Agency Name for the Stop Code: "+ stopCode+ "  not found"))); 
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
	
	public static String getAgencyName(Config config, String stopCode)
	{
		String agencyName = null;
		ElasticFeignClient ec = new ElasticFeignClient(config);
		ElasticSearch elasticSearch = ec .createClient();
		ElasticSearchResponse elasticResponse = ec .getResponse(elasticSearch, config.getElasticIndexName(), "stopCode:"+stopCode);
		if(elasticResponse!=null && elasticResponse.hits!=null && elasticResponse.hits.hits!=null && !elasticResponse.hits.hits.isEmpty())
			agencyName = elasticResponse.hits.hits.get(0).source.agency;
		return agencyName;
	}
}
