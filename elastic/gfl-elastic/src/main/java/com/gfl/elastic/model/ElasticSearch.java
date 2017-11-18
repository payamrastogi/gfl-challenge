package com.gfl.elastic.model;

import feign.Param;
import feign.RequestLine;

public interface ElasticSearch
{
	@RequestLine("GET /{indexName}/_search?q={q}")
	ElasticSearchResponse response(@Param("indexName") String indexName, @Param("q") String stopCode);
}
