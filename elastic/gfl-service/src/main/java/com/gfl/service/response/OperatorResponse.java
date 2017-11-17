package com.gfl.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OperatorResponse implements Response
{
	@JsonProperty("agencyName")
	private String agencyName;
	@JsonProperty("agencyCode")
	private String agencyCode;
	
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
}
