package com.gfl.sfbay.stopmonitoring.response;

public class Response 
{
	private String agencyCode;
	private String stopCode;
	private String busNo;
	private String arrivalTime;
	
	public Response(String agencyCode, String stopCode, String busNo, String arrivalTime)
	{
		this.setAgencyCode(agencyCode);
		this.setStopCode(stopCode);
		this.setBusNo(busNo);
		this.setArrivalTime(arrivalTime);
	}
	
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
		return new StringBuffer().append(getAgencyCode() + " : "+getStopCode()+" : "+getBusNo()+" : "+getArrivalTime()).toString();
	}
}
