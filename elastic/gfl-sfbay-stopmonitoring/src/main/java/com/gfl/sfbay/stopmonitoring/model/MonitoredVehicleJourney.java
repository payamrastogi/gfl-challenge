package com.gfl.sfbay.stopmonitoring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonitoredVehicleJourney
{
	@JsonProperty("LineRef")
	private String lineRef;
	@JsonProperty("DirectionRef")
	private String directionRef;
	@JsonProperty("FramedVehicleJourneyRef")
	private FramedVehicleJourneyRef framedVehicleJourneyRef;
	@JsonProperty("PublishedLineName")
	private String publishedLineName;
	@JsonProperty("OperatorRef")
	private String operatorRef;
	@JsonProperty("Monitored")
	private boolean monitored;
	@JsonProperty("VehicleLocation")
	private VehicleLocation vehicleLocation;
	@JsonProperty("MonitoredCall")
	private MonitoredCall monitoredCall;
	public String getLineRef() {
		return lineRef;
	}
	public void setLineRef(String lineRef) {
		this.lineRef = lineRef;
	}
	public String getDirectionRef() {
		return directionRef;
	}
	public void setDirectionRef(String directionRef) {
		this.directionRef = directionRef;
	}
	public FramedVehicleJourneyRef getFramedVehicleJourneyRef() {
		return framedVehicleJourneyRef;
	}
	public void setFramedVehicleJourneyRef(FramedVehicleJourneyRef framedVehicleJourneyRef) {
		this.framedVehicleJourneyRef = framedVehicleJourneyRef;
	}
	public String getPublishedLineName() {
		return publishedLineName;
	}
	public void setPublishedLineName(String publishedLineName) {
		this.publishedLineName = publishedLineName;
	}
	public String getOperatorRef() {
		return operatorRef;
	}
	public void setOperatorRef(String operatorRef) {
		this.operatorRef = operatorRef;
	}
	public boolean isMonitored() {
		return monitored;
	}
	public void setMonitored(boolean monitored) {
		this.monitored = monitored;
	}
	public VehicleLocation getVehicleLocation() {
		return vehicleLocation;
	}
	public void setVehicleLocation(VehicleLocation vehicleLocation) {
		this.vehicleLocation = vehicleLocation;
	}
	public MonitoredCall getMonitoredCall() {
		return monitoredCall;
	}
	public void setMonitoredCall(MonitoredCall monitoredCall) {
		this.monitoredCall = monitoredCall;
	}
}
