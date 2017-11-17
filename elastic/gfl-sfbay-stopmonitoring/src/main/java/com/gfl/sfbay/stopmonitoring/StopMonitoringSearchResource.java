package com.gfl.sfbay.stopmonitoring;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.threadPool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gfl.sfbay.stopmonitoring.model.MonitoredStopVisit;
import com.gfl.sfbay.stopmonitoring.model.StopMonitoringResponse;
import com.gfl.sfbay.stopmonitoring.model.StopMonitoringSearch;
import com.gfl.sfbay.stopmonitoring.response.Response;
import com.gfl.sfbay.stopmonitoring.response.StandardResponse;
import com.gfl.sfbay.stopmonitoring.response.StatusResponse;
import com.gfl.sfbay.stopmonitoring.util.Config;
import com.google.gson.Gson;

public class StopMonitoringSearchResource
{
	private static Logger logger = LoggerFactory.getLogger(StopMonitoringSearchResource.class);
	
	public static void main(String args[])
	{
		port(8883);
		int maxThreads = 8;
		int minThreads = 2;
		int timeOutMillis = 30000;
		threadPool(maxThreads, minThreads, timeOutMillis);
		Config config = initConfig();
		get("/gfl/sfbay/stopMonitoring/search/:agencyCode/:stopCode", (request, response) -> 
		{
			String agencyCode = request.params(":agencyCode");
			String stopCode = request.params(":stopCode");
			StopMonitoringResponse stopResponse = getStopMonitoringResponse(config, agencyCode, stopCode);		
			List<Response> list = getResponse(stopResponse, agencyCode, stopCode);
			if(!list.isEmpty())
			{
				return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(list)));
			}
			else
				return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, new Gson().toJson("No Information Found"))); 
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
	
	public static StopMonitoringResponse getStopMonitoringResponse(Config config, String agencyCode, String stopCode)
	{
		StopMonitoringFeignClient sc = new StopMonitoringFeignClient(config);
		StopMonitoringSearch search = sc.createClient(config.getSfBayUrl());
		StopMonitoringResponse stopResponse = sc.getResponse(search, agencyCode, stopCode);
		return stopResponse;
	}
	
	public static List<Response> getResponse(StopMonitoringResponse stopResponse, String agencyCode, String stopCode)
	{
		List<Response> list = new ArrayList<>();
		if(stopResponse!=null 
				&& stopResponse.serviceDelivery!=null 
				&& stopResponse.serviceDelivery.stopMonitoringDelivery!=null
				&& stopResponse.serviceDelivery.stopMonitoringDelivery.monitoredStopVisits!=null
				&& !stopResponse.serviceDelivery.stopMonitoringDelivery.monitoredStopVisits.isEmpty())
		{
			List<MonitoredStopVisit> monitoredVisits = stopResponse.serviceDelivery.stopMonitoringDelivery.monitoredStopVisits;
			for(MonitoredStopVisit monitoredVisit:monitoredVisits)
			{
				String busNo = getBusNo(monitoredVisit);
				String arrivalTime = getArrivalTime(monitoredVisit);
				if(busNo!=null && arrivalTime!=null)
				{
					list.add(new Response(agencyCode, stopCode, busNo, arrivalTime));
				}
			}
		} 
		return list;
	}
	
	public static String getBusNo(MonitoredStopVisit monitoredVisit)
	{
		
		String busNo = null;
		if(monitoredVisit.monitoredVehicleJourney!=null
				&& monitoredVisit.monitoredVehicleJourney.lineRef!=null)
		{
				busNo = monitoredVisit.monitoredVehicleJourney.lineRef;
		}
		return busNo;
	}
	
	public static String getArrivalTime(MonitoredStopVisit monitoredVisit)
	{
		String arrivalTime = null;
		if(monitoredVisit.monitoredVehicleJourney.monitoredCall!=null
				&& monitoredVisit.monitoredVehicleJourney.monitoredCall.aimedArrivalTime!=null)
		{
				arrivalTime = monitoredVisit.monitoredVehicleJourney.monitoredCall.aimedArrivalTime;
		}
		return arrivalTime;
	}
}
