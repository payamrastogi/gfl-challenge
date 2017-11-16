package com.gfl.sfbay;

import java.util.List;
import com.gfl.sbay.model.OperatorResponse;
import com.gfl.sbay.model.OperatorSearch;
import com.gfl.util.Config;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.jackson.JacksonDecoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

public class OperatorsClient
{	
	private Config config;
	
	public OperatorsClient(Config config)
	{
		this.config = config;
	}
	
	public OperatorSearch createClient(String url)
	{
		Gson gson  = new GsonBuilder().setLenient().create();
		OperatorSearch search = Feign.builder()
				.client(new OkHttpClient())
                .decoder(new JacksonDecoder())
                .target(OperatorSearch.class, url);
		return search;
	}
	//Whether this method should be created or not
	//or should it be the part of any other class eg ElasticSearch
	public List<OperatorResponse>  getResponse(OperatorSearch search)
	{
		//OperatorResponse response = search.getOperators(config.getSfBayApiKey());
		List<OperatorResponse>  response = search.getOperators();
		return response;
	}
}
