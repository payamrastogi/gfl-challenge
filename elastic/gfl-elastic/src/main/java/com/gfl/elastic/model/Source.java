package com.gfl.elastic.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Source
{
	@JsonProperty("agency")
	private String agency;
	@JsonProperty("id")
	private long id;
	@JsonProperty("stopCode")
	private long stopCode;
	
	public String getAgency() {
		return agency;
	}
	public void setAgency(String agency) {
		this.agency = agency;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getStopCode() {
		return stopCode;
	}
	public void setStopCode(long stopCode) {
		this.stopCode = stopCode;
	}
}
