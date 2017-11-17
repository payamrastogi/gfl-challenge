package com.gfl.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StandardElasticResponse 
{
	@JsonProperty("status")
	private String status;
	@JsonProperty("data")
	private ElasticResponse data;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ElasticResponse getData() {
		return data;
	}
	public void setData(ElasticResponse data) {
		this.data = data;
	}
}
