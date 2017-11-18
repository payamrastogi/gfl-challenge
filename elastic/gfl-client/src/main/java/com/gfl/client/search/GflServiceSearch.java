package com.gfl.client.search;

import com.gfl.client.response.StandardGflResponse;

import feign.Param;
import feign.RequestLine;

public interface GflServiceSearch
{
	@RequestLine("GET /gfl/search/stopCode/{stopCode}")
	StandardGflResponse getResponse(@Param("stopCode") String stopCode);
	
	//@RequestLine("GET /{indexName}/_search?q=stopCode:51130")
	//ElasticSearchResponse response(@Param("indexName") String indexName);
}
