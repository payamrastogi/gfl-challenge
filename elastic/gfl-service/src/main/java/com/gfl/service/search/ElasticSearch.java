package com.gfl.service.search;

import com.gfl.service.response.StandardElasticResponse;

import feign.Param;
import feign.RequestLine;

public interface ElasticSearch
{

	@RequestLine("GET /gfl/elastic/search/stopCode/{stopCode}")
	StandardElasticResponse getAgencyName(@Param("stopCode") String stopCode);
	
	//@RequestLine("GET /{indexName}/_search?q=stopCode:51130")
	//ElasticSearchResponse response(@Param("indexName") String indexName);
}
