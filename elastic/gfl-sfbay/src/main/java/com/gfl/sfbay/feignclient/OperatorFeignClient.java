package com.gfl.sfbay.feignclient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gfl.sfbay.model.operator.OperatorResponseModel;
import com.gfl.sfbay.util.Config;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.okhttp.OkHttpClient;

public class OperatorFeignClient {
	private static Logger logger = LoggerFactory.getLogger(OperatorFeignClient.class);
	private Config config;
	
	public OperatorFeignClient(Config config)
	{
		this.config = config;
	}
	
	public SfBaySearch createClient()
	{
		SfBaySearch search = Feign.builder()
				.client(new OkHttpClient())
                .decoder(new JacksonDecoder())
                .target(SfBaySearch.class, config.getSfBayUrl());
		return search;
	}

	public List<OperatorResponseModel>  getResponse(SfBaySearch search)
	{
		List<OperatorResponseModel>  response = search.getOperators(config.getSfBayApiKey());
		return response;
	}
	
	public Map<String, String> getAgencyNameCodeMap(List<OperatorResponseModel> list)
	{
		Map<String, String> map = new HashMap<>();
		if(list==null || list.isEmpty())
			return map;
		for(OperatorResponseModel response : list)
		{
			map.put(response.getName(), response.getId());
		}
		logger.debug(map+"");
		return map;
	}
}
