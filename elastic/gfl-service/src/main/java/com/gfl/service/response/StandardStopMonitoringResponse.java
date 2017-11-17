package com.gfl.service.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StandardStopMonitoringResponse 
{
	@JsonProperty("status")
	private String status;
	@JsonProperty("data")
	private List<StopMonitoringResponse> data;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<StopMonitoringResponse> getData() {
		return data;
	}
	public void setData(List<StopMonitoringResponse> data) {
		this.data = data;
	}
	
	@Override
	public String toString()
	{
		return getStatus()+ " : "+ getData();
	}
}
