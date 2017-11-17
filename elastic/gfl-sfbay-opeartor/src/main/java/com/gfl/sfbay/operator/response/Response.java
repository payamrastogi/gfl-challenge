package com.gfl.sfbay.operator.response;

public class Response 
{
	private String agencyName;
	private String agencyCode;
	
	public Response(String agencyName, String agencyCode)
	{
		this.setAgencyCode(agencyCode);
		this.setAgencyName(agencyName);
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
	
	@Override
	public String toString()
	{
		return new StringBuffer().append(getAgencyName() + " : " + getAgencyCode()).toString();
	}
}
