package com.gfl.sfbay.stopmonitoring.model;

import feign.Param;
import feign.RequestLine;

public interface StopMonitoringSearch
{
	@RequestLine("GET /transit/StopMonitoring?api_key={apiKey}&format=json&agency={agency}&stopCode={stopCode}")
	StopMonitoringResponse predict(@Param("apiKey") String apiKey, @Param("agency") String agency, @Param("stopCode")String stopCode);
	@RequestLine("GET /transit/StopMonitoring?api_key=a305337b-9f85-4d25-97d4-836b57ff0f17&agency=AC&format=json&stopCode=50118")
	StopMonitoringResponse predict();
}
