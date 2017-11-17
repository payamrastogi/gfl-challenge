package com.gfl.service.feignclient;

import com.gfl.service.response.ElasticResponse;
import com.gfl.service.response.StandardElasticResponse;
import com.gfl.service.search.ElasticSearch;
import com.gfl.service.util.Config;

import feign.Feign;
import feign.jackson.JacksonDecoder;

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
                .decoder(new JacksonDecoder())
                .target(ElasticSearch.class, config.getGflElasticUrl());
		return search;
	}
	//Whether this method should be created or not
	//or should it be the part of any other class eg ElasticSearch
	public StandardElasticResponse getResponse(ElasticSearch search, String stopCode)
	{
		StandardElasticResponse response = search.getAgencyName(stopCode);
		return response;
	}
}
