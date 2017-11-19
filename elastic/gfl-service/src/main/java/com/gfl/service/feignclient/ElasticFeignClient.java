package com.gfl.service.feignclient;

import com.gfl.service.response.StandardResponse;
import com.gfl.service.util.Config;

import feign.Feign;
import feign.gson.GsonDecoder;

public class ElasticFeignClient
{
	private Config config;
	
	public ElasticFeignClient(Config config)
	{
		this.config = config;
	}
	
	public ElasticSearch createClient()
	{
		ElasticSearch search = Feign.builder()
                .decoder(new GsonDecoder())
                .target(ElasticSearch.class, config.getGflElasticUrl());
		return search;
	}

	
	/**
	 * @param search 
	 * @param stopCode Stop Code for which Agency Name to be searched
	 * @return StandardResponse containing Agency Name and Stop Code
	 */
	public StandardResponse getResponse(ElasticSearch search, String stopCode)
	{
		StandardResponse response = search.getAgencyName(stopCode);
		return response;
	}
}
