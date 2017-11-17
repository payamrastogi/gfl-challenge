package com.gfl.controller;
import static spark.Spark.get;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
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
import com.gfl.elastic.ElasticClient;
import com.gfl.elastic.model.ElasticSearch;
import com.gfl.elastic.model.ElasticSearchResponse;
import com.gfl.sbay.model.OperatorResponse;
import com.gfl.sbay.model.OperatorSearch;
import com.gfl.sbay.model.StopMonitoringResponse;
import com.gfl.sbay.model.StopMonitoringSearch;
import com.gfl.sfbay.OperatorsClient;
import com.gfl.sfbay.StopMonitoringClient;
import com.gfl.util.Config;
import com.gfl.util.DateUtil;
import com.google.gson.JsonObject;
public class Slack511Controller
{
	private static Map<String, String> agencyNameCodeMap;
	private static Logger logger = LoggerFactory.getLogger(Slack511Controller.class);
	public static void main(String args[])
	{
		get("/slack511", (req, res) -> 
		{
			return createClient(req.raw().getParameter("response_url"), req.raw().getParameter("text"));
		});
	}
	
	public static String createClient(String url, String text)
	{
        try 
        {
	        	File file = new File("src/main/resources/config.properties");
	    		//File file = new File("src/main/resources/config.properties");
	    		if (!file.exists()) 
	    		{
	    			System.out.println("config.properties not found @" + file.getAbsolutePath());
	    			System.exit(1);
	    		}
	    		Config config;

    			config = new Config(file);
    			String agencyName = getAgencyName(config, text);
    			logger.debug(agencyName);
    			String agencyCode = getAgencyCode(config, agencyName);
    			logger.debug(agencyCode);
    			
    			StopMonitoringResponse stopResponse = getStopMonitoringResponse(config, agencyCode, text);		
    			String busNo = getBusNo(stopResponse);
    			String time = getArrivalTime(stopResponse);
    			
    			logger.debug(busNo + " "+ time);
    			String arrivalTime = DateUtil.getTimeIn12HrFormat(time);
    					
    			String slackResponse  = getSlackResponse(config, agencyCode, text, busNo, arrivalTime);
    			sendResponse(url, slackResponse);		
		    			

		} 
        catch (IOException e) 
        {
		   e.printStackTrace();
		}
		catch(Exception ex)
		{
		        	
		}
        return "Hello World";
	}
	
	public static String getAgencyName(Config config, String stopCode)
	{
		ElasticClient ec = new ElasticClient(config);
		ElasticSearch elasticSearch = ec .createClient(config.getElasticSearchUrl());
		ElasticSearchResponse elasticResponse = ec .getResponse(elasticSearch, config.getElasticIndexName(), "stopCode:"+stopCode);
		String agencyName = elasticResponse.hits.hits.get(0).source.agency;
		return agencyName;
	}
	
	public static String getAgencyCode(Config config, String agencyName)
	{
		String agencyCode  = "";
		if(agencyNameCodeMap==null)
		{
			OperatorsClient oc = new OperatorsClient(config);
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
	
	
	public static StopMonitoringResponse getStopMonitoringResponse(Config config, String agencyCode, String stopCode)
	{
		StopMonitoringClient sc = new StopMonitoringClient(config);
		StopMonitoringSearch search = sc.createClient(config.getSfBayUrl());
		StopMonitoringResponse stopResponse = sc.getResponse(search, agencyCode, stopCode);
		return stopResponse;
	}
	
	public static String getBusNo(StopMonitoringResponse stopResponse)
	{
		String busNo = stopResponse
				.serviceDelivery
				.stopMonitoringDelivery
				.monitoredStopVisits
				.get(0)
				.monitoredVehicleJourney.lineRef;
		return busNo;
	}
	
	public static String getArrivalTime(StopMonitoringResponse stopResponse)
	{
		String time = stopResponse
				.serviceDelivery
				.stopMonitoringDelivery
				.monitoredStopVisits
				.get(0)
				.monitoredVehicleJourney.monitoredCall.aimedArrivalTime;
		return time;
	}
	
	public static String getSlackResponse(Config config, String agencyName, String stopCode, String busNo, String arrivalTime)
	{
		String responseTemplate = config.getResponseTemplate();
		logger.debug(responseTemplate);
		responseTemplate = responseTemplate.replace("#AGENCY_NAME#", agencyName);
		responseTemplate = responseTemplate.replace("#STOP_CODE#", stopCode);
		responseTemplate = responseTemplate.replace("#BUS_NO#", busNo);
		responseTemplate = responseTemplate.replace("#ARRIVAL_TIME#", arrivalTime);
		logger.debug(responseTemplate);
		return responseTemplate;
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
