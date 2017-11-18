package com.gfl.client.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StandardGflResponse 
{
	@JsonProperty("status")
	private String status;
	@JsonProperty("data")
	private List<GflResponse> data;
	@JsonProperty("message")
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public StandardGflResponse()
	{
		
	}
	
	public StandardGflResponse(String status, List<GflResponse> data) {
		this(status, data, null);
	}
	
	public StandardGflResponse(String status, List<GflResponse> data, String message) {
		this.status = status;
		this.data = data;
		this.message = message;
	}
	
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
	
	@Override
	public String toString()
	{
		return this.getStatus()+ " : "+ getData();
	}
}
