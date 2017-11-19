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

	/**
	 * @param search
	 * @param agencyName Agency Name for which the Agency Code to be searched
	 * @return StandardResponse containing Agency Name and Agency Code
	 */
	public StandardResponse  getResponse(SfBaySearch search, String agencyName)
	{
		StandardResponse  response = search.getAgencyCode(agencyName);
		return response;
	}
	
	/**
	 * @param search
	 * @param agencyCode Agency Code and Stop Code for which the bus no and the arrival time needs to be predicted
	 * @param stopCode Agency Code and Stop Code for which the bus no and the arrival time needs to be predicted
	 * @return StandardResponse containing Agency Name,Agency Code, Stop Code, Bus No, and Arrival Time
	 */
	public StandardResponse getResponse(SfBaySearch search, String agencyCode, String stopCode)
	{
		StandardResponse response = search.predict(agencyCode, stopCode);
		return response;
	}
}
