package com.gfl.sfbay.resource;

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

import com.gfl.commons.exception.ApiKeyNotProvidedException;
import com.gfl.commons.exception.HostNameNotProvidedException;
import com.gfl.sfbay.feignclient.OperatorFeignClient;
import com.gfl.sfbay.feignclient.SfBaySearch;
import com.gfl.sfbay.feignclient.StopMonitoringFeignClient;
import com.gfl.sfbay.model.operator.OperatorResponseModel;
import com.gfl.sfbay.model.stopmonitoring.MonitoredStopVisit;
import com.gfl.sfbay.model.stopmonitoring.StopMonitoringResponseModel;
import com.gfl.sfbay.response.OperatorResponse;
import com.gfl.sfbay.response.StandardResponse;
import com.gfl.sfbay.response.StatusResponse;
import com.gfl.sfbay.response.StopMonitoringResponse;
import com.gfl.sfbay.util.Config;
import com.google.gson.Gson;

public class GflSfBayResource
{
	private static Logger logger = LoggerFactory.getLogger(GflSfBayResource.class);
	private static Map<String, String> agencyNameCodeMap;
	
	public static void main(String args[])
	{
		Config config = initConfig();
		port(config.getPort());
		threadPool(config.getMaxThreads(), config.getMinThreads(), config.getTimeOutMillis());
		
		
		get("/gfl/sfbay/search/agencyName/:agencyName", (request, response) -> 
		{
			String agencyName = request.params(":agencyName");
			String agencyCode = getAgencyCode(config, agencyName);
			if(agencyCode!=null)
			{
				return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(new OperatorResponse(agencyName, agencyCode))));
			}
			else
				return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, new Gson().toJson("Agency Code for the Agency Name: "+ agencyName+ "  not found"))); 
		}); 
		
		get("/gfl/sfbay/search/:agencyCode/:stopCode", (request, response) -> 
		{
			String agencyCode = request.params(":agencyCode");
			String stopCode = request.params(":stopCode");
			StopMonitoringResponseModel stopResponse = getStopMonitoringResponse(config, agencyCode, stopCode);		
			List<StopMonitoringResponse> list = getResponse(stopResponse, agencyCode, stopCode);
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
			//File file = new File("src/main/resources/config.properties");
			File file = new File("./gfl-sfbay/config.properties");
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
		catch(ApiKeyNotProvidedException ae)
		{
			logger.error(ae.getMessage());
			System.exit(1);
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
			SfBaySearch operatorSearch = oc.createClient();
			List<OperatorResponseModel>  ocResponse = oc.getResponse(operatorSearch);
			agencyNameCodeMap = oc.getAgencyNameCodeMap(ocResponse);
		}
		if(agencyNameCodeMap!=null)
		{
			if(agencyNameCodeMap.containsKey(agencyName))
			{
				agencyCode = agencyNameCodeMap.get(agencyName);
				logger.debug("agencyCode: " + agencyCode);
			}
		}
		return agencyCode;
	}
	
	public static StopMonitoringResponseModel getStopMonitoringResponse(Config config, String agencyCode, String stopCode)
	{
		StopMonitoringFeignClient sc = new StopMonitoringFeignClient(config);
		SfBaySearch search = sc.createClient();
		StopMonitoringResponseModel stopResponse = sc.getResponse(search, agencyCode, stopCode);
		return stopResponse;
	}
	
	public static List<StopMonitoringResponse> getResponse(StopMonitoringResponseModel stopResponse, String agencyCode, String stopCode)
	{
		List<StopMonitoringResponse> list = new ArrayList<>();
		if(stopResponse!=null 
				&& stopResponse.getServiceDelivery()!=null 
				&& stopResponse.getServiceDelivery().getStopMonitoringDelivery()!=null
				&& stopResponse.getServiceDelivery().getStopMonitoringDelivery().getMonitoredStopVisits()!=null
				&& !stopResponse.getServiceDelivery().getStopMonitoringDelivery().getMonitoredStopVisits().isEmpty())
		{
			List<MonitoredStopVisit> monitoredVisits = stopResponse.getServiceDelivery().getStopMonitoringDelivery().getMonitoredStopVisits();
			if(monitoredVisits.isEmpty())
			{
				logger.error("StopMonitoringResponse: monitoredVisits is empty");
			}
			else
			{
				for(MonitoredStopVisit monitoredVisit:monitoredVisits)
				{
					String busNo = getBusNo(monitoredVisit);
					String arrivalTime = getArrivalTime(monitoredVisit);
					if(busNo!=null && arrivalTime!=null)
					{
						list.add(new StopMonitoringResponse(agencyCode, stopCode, busNo, arrivalTime));
					}
				}
			}
		} 
		else
		{
			logger.error("Error in getting StopMonitoringResponse" + stopResponse.toString());
		}
		return list;
	}
	
	public static String getBusNo(MonitoredStopVisit monitoredVisit)
	{
		String busNo = null;
		if(monitoredVisit.getMonitoredVehicleJourney()!=null
				&& monitoredVisit.getMonitoredVehicleJourney().getLineRef()!=null)
		{
				busNo = monitoredVisit.getMonitoredVehicleJourney().getLineRef();
		}
		return busNo;
	}
	
	public static String getArrivalTime(MonitoredStopVisit monitoredVisit)
	{
		String arrivalTime = null;
		if(monitoredVisit.getMonitoredVehicleJourney().getMonitoredCall()!=null
				&& monitoredVisit.getMonitoredVehicleJourney().getMonitoredCall().getAimedArrivalTime()!=null)
		{
				arrivalTime = monitoredVisit.getMonitoredVehicleJourney().getMonitoredCall().getAimedArrivalTime();
		}
		return arrivalTime;
	}
}
