package com.gfl.service.search;

import com.gfl.service.response.StandardOperatorResponse;
import com.gfl.service.response.StandardStopMonitoringResponse;

import feign.Param;
import feign.RequestLine;

public interface SfBaySearch
{
	@RequestLine("GET /gfl/sfbay/search/{agencyCode}/{stopCode}")
	StandardStopMonitoringResponse predict(@Param("agencyCode") String agencyCode, @Param("stopCode") String stopCode);
	
	@RequestLine("GET /gfl/sfbay/search/agencyName/{agencyName}")
	StandardOperatorResponse getAgencyCode(@Param("agencyName") String agencyName);
}
