package com.gfl.sfbay.feignclient;

import java.util.List;

import com.gfl.sfbay.operator.model.OperatorResponseModel;
import com.gfl.sfbay.stopmonitoring.model.StopMonitoringResponseModel;

import feign.Param;
import feign.RequestLine;

public interface SfBaySearch
{
	@RequestLine("GET /transit/operators?api_key={apiKey}&format=json")
	List<OperatorResponseModel> getOperators(@Param("apiKey") String apiKey);
	
	@RequestLine("GET /transit/StopMonitoring?api_key={apiKey}&format=json&agency={agencyCode}&stopCode={stopCode}")
	StopMonitoringResponseModel predict(@Param("apiKey") String apiKey, @Param("agencyCode") String agency, @Param("stopCode")String stopCode);
}
