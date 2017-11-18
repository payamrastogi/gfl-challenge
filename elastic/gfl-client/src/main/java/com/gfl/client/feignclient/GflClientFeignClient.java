package com.gfl.client.feignclient;

import com.gfl.client.response.StandardGflResponse;
import com.gfl.client.response.StandardResponse;
import com.gfl.client.search.GflServiceSearch;
import com.gfl.client.util.Config;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.jackson.JacksonDecoder;

public class GflClientFeignClient
{
	private Config config;
	
	public GflClientFeignClient(Config config)
	{
		this.config = config;
	}
	
	public GflServiceSearch createClient()
	{
		GflServiceSearch search = Feign.builder()
                .decoder(new GsonDecoder())
                .target(GflServiceSearch.class, config.getGflServiceUrl());
		return search;
	}

	public StandardResponse getResponse(GflServiceSearch search, String stopCode)
	{
		StandardResponse response = search.getResponse(stopCode);
		return response;
	}
}
