package com.gfl.client.search;

import com.gfl.client.response.StandardResponse;

import feign.Param;
import feign.RequestLine;

public interface GflServiceSearch
{
	@RequestLine("GET /gfl/search/stopCode/{stopCode}")
	StandardResponse getResponse(@Param("stopCode") String stopCode);

}
