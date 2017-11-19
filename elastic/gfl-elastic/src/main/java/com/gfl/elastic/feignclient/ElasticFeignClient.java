package com.gfl.elastic.feignclient;

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

	/**
	 * @param search
	 * @param indexName Elastic Search Index Name
	 * @param query Query in this case stopCode:<StopCode>
	 * @return
	 */
	public ElasticSearchResponse getResponse(ElasticSearch search, String indexName, String query)
	{
		ElasticSearchResponse response = search.response(indexName, query);
		return response;
	}
}
