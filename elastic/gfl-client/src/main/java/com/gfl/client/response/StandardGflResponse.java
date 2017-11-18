package com.gfl.client.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StandardGflResponse 
{
	@JsonProperty("status")
	private String status;
	@JsonProperty("data")
	private List<GflResponse> data;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<GflResponse> getData() {
		return data;
	}
	public void setData(List<GflResponse> data) {
		this.data = data;
	}
}
