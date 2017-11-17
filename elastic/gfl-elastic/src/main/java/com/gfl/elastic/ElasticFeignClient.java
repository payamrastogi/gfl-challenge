package com.gfl.elastic;

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
	
	public ElasticSearch createClient(String url)
	{
		ElasticSearch search = Feign.builder()
                .decoder(new JacksonDecoder())
                .target(ElasticSearch.class, url);
		return search;
	}
	//Whether this method should be created or not
	//or should it be the part of any other class eg ElasticSearch
	public ElasticSearchResponse getResponse(ElasticSearch search, String indexName, String query)
	{
		ElasticSearchResponse response = search.response(indexName, query);
		return response;
	}
}