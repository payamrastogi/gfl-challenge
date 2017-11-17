package com.gfl.sfbay.operator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gfl.sfbay.operator.model.OperatorResponse;
import com.gfl.sfbay.operator.model.OperatorSearch;
import com.gfl.sfbay.operator.util.Config;

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
	
	public OperatorSearch createClient(String url)
	{

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
	
	public Map<String, String> getAgencyNameCodeMap(List<OperatorResponse> list)
	{
		Map<String, String> map = new HashMap<>();
		if(list==null || list.isEmpty())
			return map;
		for(OperatorResponse response : list)
		{
			map.put(response.name, response.id);
		}
		logger.debug(map+"");
		return map;
	}
}
