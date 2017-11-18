package com.gfl.elastic.feignclient;

import com.gfl.elastic.model.ElasticSearchResponse;

import feign.Param;
import feign.RequestLine;

public interface ElasticSearch
{
	@RequestLine("GET /{indexName}/_search?q={q}")
	ElasticSearchResponse response(@Param("indexName") String indexName, @Param("q") String stopCode);
}
