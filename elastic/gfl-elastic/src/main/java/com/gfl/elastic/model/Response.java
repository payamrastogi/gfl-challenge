package com.gfl.elastic.model;

public class Response 
{
	private String stopCode;
	private String agencyName;
	
	public Response(String stopCode, String agencyName)
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
