package com.gfl.elastic.response;

public class ElasticResponse 
{
	private String stopCode;
	private String agencyName;
	
	public ElasticResponse(String stopCode, String agencyName)
	{
		this.setStopCode(stopCode);
		this.setAgencyName(agencyName);
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
	
	@Override
	public String toString()
	{
		return new StringBuffer().append(getAgencyName() + " : " + getStopCode()).toString();
	}
}
