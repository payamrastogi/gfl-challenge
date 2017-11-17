package com.gfl.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ElasticResponse implements Response
{
	@JsonProperty("stopCode")
	private String stopCode;
	@JsonProperty("agencyName")
	private String agencyName;

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
}
