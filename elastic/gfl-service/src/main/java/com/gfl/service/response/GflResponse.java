package com.gfl.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GflResponse implements Response
{
	@JsonProperty("stopCode")
	private String stopCode;

	@JsonProperty("agencyName")
	private String agencyName;
	
	@JsonProperty("agencyCode")
	private String agencyCode;
	
	@JsonProperty("busNo")
	private String busNo;
	
	@JsonProperty("arrivalTime")
	private String arrivalTime;
	
	public GflResponse()
	{
		
	}
	
	public GflResponse(String stopCode, String agencyName, String agencyCode, String busNo, String arrivalTime) {
		this.stopCode = stopCode;
		this.agencyName = agencyName;
		this.agencyCode = agencyCode;
		this.busNo = busNo;
		this.arrivalTime = arrivalTime;
	}

	public String getStopCode() {
		return stopCode;
	}

	public void setStopCode(String stopCode) {
		this.stopCode = stopCode;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public String getBusNo() {
		return busNo;
	}

	public void setBusNo(String busNo) {
		this.busNo = busNo;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

}
