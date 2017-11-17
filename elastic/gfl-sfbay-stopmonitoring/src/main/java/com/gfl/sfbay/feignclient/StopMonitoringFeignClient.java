package com.gfl.sfbay.feignclient;


import com.gfl.sfbay.stopmonitoring.model.StopMonitoringResponseModel;
import com.gfl.sfbay.stopmonitoring.model.StopMonitoringSearch;
import com.gfl.sfbay.util.Config;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.okhttp.OkHttpClient;

public class StopMonitoringFeignClient
{
	private Config config;
	
	public StopMonitoringFeignClient(Config config)
	{
		this.config = config;
	}
	
	public StopMonitoringSearch createClient()
	{
		StopMonitoringSearch search = Feign.builder()
				.client(new OkHttpClient())
                .decoder(new JacksonDecoder())
                .target(StopMonitoringSearch.class, config.getSfBayUrl());
		return search;
	}
	//Whether this method should be created or not
	//or should it be the part of any other class eg ElasticSearch
	public StopMonitoringResponseModel getResponse(StopMonitoringSearch search)
	{
		//OperatorResponse response = search.getOperators(config.getSfBayApiKey());
		StopMonitoringResponseModel response = search.predict();
		return response;
	}
	
	public StopMonitoringResponseModel getResponse(StopMonitoringSearch search, String agencyCode, String stopCode)
	{
		//OperatorResponse response = search.getOperators(config.getSfBayApiKey());
		//StopMonitoringResponseModel response = search.predict(config.getSfBayApiKey(), agencyCode, stopCode);
		StopMonitoringResponseModel response = search.predict();
		return response;
	}
}
