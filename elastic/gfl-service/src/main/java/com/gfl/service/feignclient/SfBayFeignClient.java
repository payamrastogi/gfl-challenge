package com.gfl.service.feignclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gfl.service.response.StandardResponse;
import com.gfl.service.util.Config;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.okhttp.OkHttpClient;

public class SfBayFeignClient 
{
	private static Logger logger = LoggerFactory.getLogger(SfBayFeignClient.class);
	private Config config;
	
	public SfBayFeignClient(Config config)
	{
		this.config = config;
	}
	
	public SfBaySearch createClient()
	{

		SfBaySearch search = Feign.builder()
				.client(new OkHttpClient())
                .decoder(new GsonDecoder())
                .target(SfBaySearch.class, config.getGflSfBayUrl());
		return search;
	}
	//Whether this method should be created or not
	//or should it be the part of any other class eg ElasticSearch
	public StandardResponse  getResponse(SfBaySearch search, String agencyName)
	{
		StandardResponse  response = search.getAgencyCode(agencyName);
		return response;
	}
	
	public StandardResponse getResponse(SfBaySearch search, String agencyCode, String stopCode)
	{
		StandardResponse response = search.predict(agencyCode, stopCode);
		return response;
	}
}
