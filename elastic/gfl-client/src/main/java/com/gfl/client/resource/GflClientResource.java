package com.gfl.client.resource;

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

import com.gfl.client.feignclient.GflClientFeignClient;
import com.gfl.client.response.GflResponse;
import com.gfl.client.response.StandardGflResponse;
import com.gfl.client.search.GflServiceSearch;
import com.gfl.client.util.Config;
import com.gfl.client.util.DateUtil;
import com.google.gson.JsonObject;

public class GflClientResource 
{
private static Logger logger = LoggerFactory.getLogger(GflClientResource.class);
	
	public static void main(String args[])
	{
		port(8884);
		int maxThreads = 8;
		int minThreads = 2;
		int timeOutMillis = 30000;
		threadPool(maxThreads, minThreads, timeOutMillis);
		Config config = initConfig();
		
		get("/slack511", (request, response) -> 
		{
			String responseUrl = request.raw().getParameter("response_url");
			String stopCode = request.raw().getParameter("text");
			List<GflResponse> responseList = getResponse(config, stopCode);
			List<String> slackResponseList = getSlackResponse(config, responseList);
			sendSlackResponse(responseUrl, slackResponseList);
			response.status(200);
			return null;
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
		catch(IOException e)
		{
			logger.error(e.getMessage());
		}
		return config;
	}
	
	public static List<GflResponse> getResponse(Config config, String stopCode)
	{
		List<GflResponse> responseList = new ArrayList<>();
		GflClientFeignClient ec = new GflClientFeignClient(config);
		GflServiceSearch gflSearch = ec.createClient();
		StandardGflResponse gflResponse = gflSearch.getResponse(stopCode);
		logger.debug(gflResponse.toString());
		if("SUCCESS".equals(gflResponse.getStatus())) 
		{
			responseList = gflResponse.getData();
			logger.debug("responseList size: "+responseList.size());
		}
		else
		{
			logger.error(gflResponse.getStatus());
		}
		return responseList;
	}
	
	public static List<String> getSlackResponse(Config config, List<GflResponse> responseList)
	{
		List<String> slackResponseList = new ArrayList<>();
		int responseSize = Math.min(config.getSlackResponseSize(), responseList.size());
		for(int i=0;i<responseSize;i++)
		{
			GflResponse gflResponse = responseList.get(i);
			slackResponseList.add(getFormattedSlackResponse(config, 
					gflResponse.getAgencyName(), 
					gflResponse.getStopCode(), 
					gflResponse.getBusNo(),
					gflResponse.getArrivalTime()));
		}
		if(slackResponseList.isEmpty())
			logger.error("slack response list is empty");
		return slackResponseList;
	}
	
	public static String getFormattedSlackResponse(Config config, String agencyName, String stopCode, String busNo, String arrivalTime)
	{
		String responseTemplate = config.getSlackResponseTemplate();
		logger.debug(responseTemplate);
		responseTemplate = responseTemplate.replace("#AGENCY_NAME#", agencyName);
		responseTemplate = responseTemplate.replace("#STOP_CODE#", stopCode);
		responseTemplate = responseTemplate.replace("#BUS_NO#", busNo);
		responseTemplate = responseTemplate.replace("#ARRIVAL_TIME#", DateUtil.getTimeIn12HrFormat(arrivalTime));
		logger.debug(responseTemplate);
		return responseTemplate;
	}
	
	public static void sendSlackResponse(String url, List<String> slackResponseList) throws ClientProtocolException, IOException
	{
		HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);
    		
        for(String slackResponse : slackResponseList)
        {
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
        }
	}
}
