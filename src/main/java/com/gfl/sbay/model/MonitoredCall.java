package com.gfl.sbay.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonitoredCall
{
	@JsonProperty("StopPointRef")
	public String stopPointRef;
	@JsonProperty("VisitNumber")
	public String visitNumber;
	@JsonProperty("StopPointName")
	public String stopPointName;
	@JsonProperty("VehicleAtStop")
	public String vehicleAtStop;
	@JsonProperty("AimedArrivalTime")
	public String aimedArrivalTime;
	@JsonProperty("AimedDepartureTime")
	public String aimedDepartureTime;
}
