package com.gfl.sfbay.operator.model;

import java.util.List;
import feign.Param;
import feign.RequestLine;

public interface OperatorSearch
{
	@RequestLine("GET /transit/operators?api_key={apiKey}&format=json")
	List<OperatorResponseModel> getOperators(@Param("apiKey") String apiKey);

}
