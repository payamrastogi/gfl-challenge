package com.gfl.elastic;

import java.io.File;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import com.gfl.elastic.model.ElasticSearch;
import com.gfl.elastic.model.ElasticSearchResponse;
import com.gfl.util.Config;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import feign.Feign;
import feign.jackson.JacksonDecoder;

public class ElasticClient
{
	private Config config;
	
	public ElasticClient(Config config)
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
		ElasticSearchResponse response = search.response(indexName, "stopCode:51130");
		return response;
	}
}
