package com.gfl.service.feignclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gfl.service.response.OperatorResponse;
import com.gfl.service.response.StandardOperatorResponse;
import com.gfl.service.response.StandardStopMonitoringResponse;
import com.gfl.service.response.StopMonitoringResponse;
import com.gfl.service.search.SfBaySearch;
import com.gfl.service.util.Config;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.okhttp.OkHttpClient;

public class SfBayFeignClient 
{
	private static Logger logger = LoggerFactory.getLogger(SfBayFeignClient.class);
	private Config config;
	
	public SfBayFeignClient(Config config)
	{
		this.config = config;
	}
	
	public SfBaySearch createClient()
	{

		SfBaySearch search = Feign.builder()
				.client(new OkHttpClient())
                .decoder(new JacksonDecoder())
                .target(SfBaySearch.class, config.getGflSfBayUrl());
		return search;
	}
	//Whether this method should be created or not
	//or should it be the part of any other class eg ElasticSearch
	public StandardOperatorResponse  getResponse(SfBaySearch search, String agencyName)
	{
		StandardOperatorResponse  response = search.getAgencyCode(agencyName);
		return response;
	}
	
	public StandardStopMonitoringResponse getResponse(SfBaySearch search, String agencyCode, String stopCode)
	{
		StandardStopMonitoringResponse response = search.predict(agencyCode, stopCode);
		return response;
	}
}
