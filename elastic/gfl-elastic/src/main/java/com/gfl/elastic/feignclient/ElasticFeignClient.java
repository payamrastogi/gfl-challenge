package com.gfl.elastic.feignclient;

import com.gfl.elastic.model.ElasticSearch;
import com.gfl.elastic.model.ElasticSearchResponse;
import com.gfl.elastic.util.Config;

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
                .target(ElasticSearch.class, config.getElasticSearchUrl());
		return search;
	}

	public ElasticSearchResponse getResponse(ElasticSearch search, String indexName, String query)
	{
		ElasticSearchResponse response = search.response(indexName, query);
		return response;
	}
}
