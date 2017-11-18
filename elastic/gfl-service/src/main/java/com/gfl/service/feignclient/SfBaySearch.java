package com.gfl.service.feignclient;

import com.gfl.service.response.StandardResponse;

import feign.Param;
import feign.RequestLine;

public interface SfBaySearch
{
	@RequestLine("GET /gfl/sfbay/search/{agencyCode}/{stopCode}")
	StandardResponse predict(@Param("agencyCode") String agencyCode, @Param("stopCode") String stopCode);
	
	@RequestLine("GET /gfl/sfbay/search/agencyName/{agencyName}")
	StandardResponse getAgencyCode(@Param("agencyName") String agencyName);
}
