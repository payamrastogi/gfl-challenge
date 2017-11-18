package com.gfl.service.resource;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.threadPool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gfl.service.feignclient.ElasticFeignClient;
import com.gfl.service.feignclient.SfBayFeignClient;
import com.gfl.service.response.GflResponse;
import com.gfl.service.response.StandardElasticResponse;
import com.gfl.service.response.StandardOperatorResponse;
import com.gfl.service.response.StandardResponse;
import com.gfl.service.response.StandardStopMonitoringResponse;
import com.gfl.service.response.StatusResponse;
import com.gfl.service.response.StopMonitoringResponse;
import com.gfl.service.search.ElasticSearch;
import com.gfl.service.search.SfBaySearch;
import com.gfl.service.util.Config;
import com.gfl.service.util.DateUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
public class GflResource
{
	private static Logger logger = LoggerFactory.getLogger(GflResource.class);
	
	public static void main(String args[])
	{
		port(8883);
		int maxThreads = 8;
		int minThreads = 2;
		int timeOutMillis = 30000;
		threadPool(maxThreads, minThreads, timeOutMillis);
		Config config = initConfig();
		
		get("/gfl/search/stopCode/:stopCode", (request, response) -> 
		{
			String stopCode = request.params(":stopCode");
			List<GflResponse> list = getResponse(config, stopCode);
			if(!list.isEmpty())
			{
				return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(list)));
			}
			else
				return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, new Gson().toJson("Result for the Stop Code: "+ stopCode+ "  not found")));
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
	
	public static List<GflResponse> getResponse(Config config, String stopCode)
	{
		List<GflResponse> responseList = new ArrayList<>(); 
		String agencyName = getAgencyName(config, stopCode);
		logger.debug(agencyName);
		String agencyCode = getAgencyCode(config, agencyName);
		logger.debug(agencyCode);
		
		StandardStopMonitoringResponse stopResponse = getStopMonitoringResponse(config, agencyCode, stopCode);		
		if("SUCCESS".equals(stopResponse.getStatus()))
		{
			
			List<StopMonitoringResponse> list  = stopResponse.getData();
			for(StopMonitoringResponse sr : list)
			{
				String busNo = getBusNo(sr);
				String arrivalTime = DateUtil.getTimeIn12HrFormat(getArrivalTime(sr));
				logger.debug(busNo + " "+ arrivalTime);
				responseList.add(new GflResponse(stopCode, agencyName,agencyCode, busNo, arrivalTime));
			}
		}
		else
		{
			logger.error(stopResponse.toString());
		}
    		return responseList;
	}
	
	public static String getAgencyName(Config config, String stopCode)
	{
		ElasticFeignClient ec = new ElasticFeignClient(config);
		ElasticSearch elasticSearch = ec.createClient();
		StandardElasticResponse elasticResponse = ec.getResponse(elasticSearch, stopCode);
		String agencyName = null;
		if("SUCCESS".equals(elasticResponse.getStatus())) 
		{
			agencyName = elasticResponse.getData().getAgencyName();
			logger.debug("agencyName: "+agencyName);
		}
		else
		{
			logger.error(elasticResponse.getStatus());
		}
		return agencyName;
	}
	
	public static String getAgencyCode(Config config, String agencyName)
	{
		String agencyCode  = null;
		
		SfBayFeignClient sc = new SfBayFeignClient(config);
		logger.debug(config.getGflSfBayUrl());
		SfBaySearch search = sc.createClient();
		StandardOperatorResponse scResponse = sc.getResponse(search, agencyName);

		if("SUCCESS".equals(scResponse.getStatus()))
		{
			agencyCode = scResponse.getData().getAgencyCode();
			logger.debug("agencyCode: "+agencyCode);
		}
		else
		{
			logger.error(scResponse.getStatus());
		}
		return agencyCode;
	}
	
	public static StandardStopMonitoringResponse getStopMonitoringResponse(Config config, String agencyCode, String stopCode)
	{
		SfBayFeignClient sc = new SfBayFeignClient(config);
		logger.debug(config.getGflSfBayUrl());
		SfBaySearch search = sc.createClient();
		StandardStopMonitoringResponse stopResponse = sc.getResponse(search, agencyCode, stopCode);
		return stopResponse;
	}
	
	public static String getBusNo(StopMonitoringResponse stopResponse)
	{
		return stopResponse.getBusNo();
	}
	
	public static String getArrivalTime(StopMonitoringResponse stopResponse)
	{
		return  stopResponse.getArrivalTime();
	}
	
	
	
	public static void sendResponse(String url, String slackResponse) throws ClientProtocolException, IOException
	{
		HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);
    		JsonObject j = new JsonObject();
    		j.addProperty("response_type", "in_channel");
    		j.addProperty("text", slackResponse);
    		
    		StringEntity params =new StringEntity(j.toString());
    		request.addHeader("content-type", "application/json");
    		request.setEntity(params);
        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();

        // Read the contents of an entity and return it as a String.
        String content = EntityUtils.toString(entity);
        logger.debug(content);
        //JsonArray arr = new Gson().fromJson(content, JsonArray.class);
        //JsonObject obj = (JsonObject) arr.get(0);
        //System.out.println(obj.get("Id") + " " + obj.get("Name") + " " + obj.get("ContactTelephoneNumber"));
	}
}
