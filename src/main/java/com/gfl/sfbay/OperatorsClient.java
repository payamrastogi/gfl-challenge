package com.gfl.sfbay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gfl.sbay.model.OperatorResponse;
import com.gfl.sbay.model.OperatorSearch;
import com.gfl.util.Config;
import com.gfl.util.DateUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.jackson.JacksonDecoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

public class OperatorsClient
{	
	private static Logger logger = LoggerFactory.getLogger(OperatorsClient.class);
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
