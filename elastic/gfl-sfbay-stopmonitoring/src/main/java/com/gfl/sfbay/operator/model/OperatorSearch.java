package com.gfl.sfbay.operator.model;

import java.util.List;
import feign.Param;
import feign.RequestLine;

public interface OperatorSearch
{
	@RequestLine("GET /transit/operators?api_key={apiKey}&format=json")
	List<OperatorResponseModel> getOperators(@Param("apiKey") String apiKey);
	
	@RequestLine("GET /transit/operators?api_key=a305337b-9f85-4d25-97d4-836b57ff0f17&format=json")
	List<OperatorResponseModel>  getOperators();
}
