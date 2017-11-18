package com.gfl.client.feignclient;

import com.gfl.client.response.StandardGflResponse;
import com.gfl.client.search.GflServiceSearch;
import com.gfl.client.util.Config;

import feign.Feign;
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
                .decoder(new JacksonDecoder())
                .target(GflServiceSearch.class, config.getGflServiceUrl());
		return search;
	}
	//Whether this method should be created or not
	//or should it be the part of any other class eg ElasticSearch
	public StandardGflResponse getResponse(GflServiceSearch search, String stopCode)
	{
		StandardGflResponse response = search.getResponse(stopCode);
		return response;
	}
}
