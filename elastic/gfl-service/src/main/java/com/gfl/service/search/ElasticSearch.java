package com.gfl.service.search;

import com.gfl.service.response.StandardResponse;

import feign.Param;
import feign.RequestLine;

public interface ElasticSearch
{

	@RequestLine("GET /gfl/elastic/search/stopCode/{stopCode}")
	StandardResponse getAgencyName(@Param("stopCode") String stopCode);
}
