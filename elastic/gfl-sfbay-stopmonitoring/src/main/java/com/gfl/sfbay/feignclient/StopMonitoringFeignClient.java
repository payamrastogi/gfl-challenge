package com.gfl.sfbay.feignclient;


import com.gfl.sfbay.stopmonitoring.model.StopMonitoringResponseModel;
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
	
	public SfBaySearch createClient()
	{
		SfBaySearch search = Feign.builder()
				.client(new OkHttpClient())
                .decoder(new JacksonDecoder())
                .target(SfBaySearch.class, config.getSfBayUrl());
		return search;
	}
	
	public StopMonitoringResponseModel getResponse(SfBaySearch search, String agencyCode, String stopCode)
	{
		StopMonitoringResponseModel response = search.predict(config.getSfBayApiKey(), agencyCode, stopCode);
		return response;
	}
}
