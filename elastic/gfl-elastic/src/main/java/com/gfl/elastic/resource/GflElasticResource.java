package com.gfl.elastic.resource;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.threadPool;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gfl.commons.exception.HostNameNotProvidedException;
import com.gfl.commons.exception.IndexNameNotProvidedException;
import com.gfl.elastic.feignclient.ElasticFeignClient;
import com.gfl.elastic.feignclient.ElasticSearch;
import com.gfl.elastic.model.ElasticSearchResponse;
import com.gfl.elastic.response.ElasticResponse;
import com.gfl.elastic.response.StandardResponse;
import com.gfl.elastic.response.StatusResponse;
import com.gfl.elastic.util.Config;

import com.google.gson.Gson;
public class GflElasticResource 
{
	private static Logger logger = LoggerFactory.getLogger(GflElasticResource.class);
	public static void main(String args[])
	{
		Config config = initConfig();
		port(config.getPort());
		threadPool(config.getMaxThreads(), config.getMinThreads(), config.getTimeOutMillis());
		
		get("/gfl/elastic/search/stopCode/:stopCode", (request, response) -> 
		{
			String stopCode = request.params(":stopCode");
			String agencyName = getAgencyName(config, stopCode);
			if(agencyName!=null)
			{
				//ToDo move to utils
				return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(new ElasticResponse(stopCode, agencyName))));
			}
			else
				return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, new Gson().toJson("Agency Name for the Stop Code: "+ stopCode+ "  not found"))); 
		}); 
	}
	
	//ToDo singleton class
	public static Config initConfig()
	{
		Config config = null;
		try
		{
			//File file = new File("src/main/resources/config.properties");
			File file = new File("./gfl-elastic/config.properties");

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
		catch(IndexNameNotProvidedException ae)
		{
			logger.error(ae.getMessage());
			System.exit(1);
		}
		return config;
	}
	
	public static String getAgencyName(Config config, String stopCode)
	{
		String agencyName = null;
		ElasticFeignClient ec = new ElasticFeignClient(config);
		ElasticSearch elasticSearch = ec.createClient();
		ElasticSearchResponse elasticResponse = ec.getResponse(elasticSearch, config.getElasticIndexName(), "stopCode:"+stopCode);
		if(elasticResponse!=null 
				&& elasticResponse.getHits()!=null 
				&& elasticResponse.getHits().getHits()!=null 
				&& !elasticResponse.getHits().getHits().isEmpty()
				&& elasticResponse.getHits().getHits().get(0).getSource()!=null)
			agencyName = elasticResponse.getHits().getHits().get(0).getSource().getAgency();
		else
		{
			logger.error("Error in getting ElasticSearchResponse");
		}
		return agencyName;
	}
}
