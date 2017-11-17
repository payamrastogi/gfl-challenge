package com.gfl.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StopMonitoringResponse 
{
	@JsonProperty("agencyCode")
	private String agencyCode;
	@JsonProperty("stopCode")
	private String stopCode;
	@JsonProperty("busNo")
	private String busNo;
	@JsonProperty("arrivalTime")
	private String arrivalTime;
	
	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public String getStopCode() {
		return stopCode;
	}

	public void setStopCode(String stopCode) {
		this.stopCode = stopCode;
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
	
	@Override
	public String toString()
	{
		return getStopCode()+ " : "+ getBusNo()+" : "+getArrivalTime();
	}
	
}
