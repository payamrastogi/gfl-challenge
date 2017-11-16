package com.gfl.sbay.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonitoredVehicleJourney
{
	@JsonProperty("LineRef")
	public String lineRef;
	@JsonProperty("DirectionRef")
	public String directionRef;
	@JsonProperty("FramedVehicleJourneyRef")
	public FramedVehicleJourneyRef framedVehicleJourneyRef;
	@JsonProperty("PublishedLineName")
	public String publishedLineName;
	@JsonProperty("OperatorRef")
	public String operatorRef;
	@JsonProperty("Monitored")
	public boolean monitored;
	@JsonProperty("VehicleLocation")
	public VehicleLocation vehicleLocation;
	@JsonProperty("MonitoredCall")
	public MonitoredCall monitoredCall;
}
