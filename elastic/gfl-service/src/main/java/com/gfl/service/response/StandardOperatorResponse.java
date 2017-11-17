package com.gfl.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StandardOperatorResponse 
{
	@JsonProperty("status")
	private String status;
	@JsonProperty("data")
	private OperatorResponse data;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public OperatorResponse getData() {
		return data;
	}
	public void setData(OperatorResponse data) {
		this.data = data;
	}
}
