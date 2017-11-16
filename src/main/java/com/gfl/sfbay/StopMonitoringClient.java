package com.gfl.sfbay;

import com.gfl.sbay.model.StopMonitoringResponse;
import com.gfl.sbay.model.StopMonitoringSearch;
import com.gfl.util.Config;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.jackson.JacksonDecoder;
import feign.okhttp.OkHttpClient;

public class StopMonitoringClient
{
	private Config config;
	
	public StopMonitoringClient(Config config)
	{
		this.config = config;
	}
	
	public StopMonitoringSearch createClient(String url)
	{
		StopMonitoringSearch search = Feign.builder()
				.client(new OkHttpClient())
                .decoder(new JacksonDecoder())
                .target(StopMonitoringSearch.class, url);
		return search;
	}
	//Whether this method should be created or not
	//or should it be the part of any other class eg ElasticSearch
	public StopMonitoringResponse getResponse(StopMonitoringSearch search)
	{
		//OperatorResponse response = search.getOperators(config.getSfBayApiKey());
		StopMonitoringResponse response = search.predict();
		return response;
	}
	
	public StopMonitoringResponse getResponse(StopMonitoringSearch search, String agencyCode, String stopCode)
	{
		//OperatorResponse response = search.getOperators(config.getSfBayApiKey());
		StopMonitoringResponse response = search.predict(config.getSfBayApiKey(), agencyCode, stopCode);
		return response;
	}
}
