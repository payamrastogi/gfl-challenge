package com.gfl.sfbay.model.stopmonitoring;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MonitoredCall
{
	@JsonProperty("StopPointRef")
	private String stopPointRef;
	@JsonProperty("VisitNumber")
	private String visitNumber;
	@JsonProperty("StopPointName")
	private String stopPointName;
	@JsonProperty("VehicleAtStop")
	private String vehicleAtStop;
	@JsonProperty("AimedArrivalTime")
	private String aimedArrivalTime;
	@JsonProperty("AimedDepartureTime")
	private String aimedDepartureTime;
	
	public String getStopPointRef() {
		return stopPointRef;
	}
	public void setStopPointRef(String stopPointRef) {
		this.stopPointRef = stopPointRef;
	}
	public String getVisitNumber() {
		return visitNumber;
	}
	public void setVisitNumber(String visitNumber) {
		this.visitNumber = visitNumber;
	}
	public String getStopPointName() {
		return stopPointName;
	}
	public void setStopPointName(String stopPointName) {
		this.stopPointName = stopPointName;
	}
	public String getVehicleAtStop() {
		return vehicleAtStop;
	}
	public void setVehicleAtStop(String vehicleAtStop) {
		this.vehicleAtStop = vehicleAtStop;
	}
	public String getAimedArrivalTime() {
		return aimedArrivalTime;
	}
	public void setAimedArrivalTime(String aimedArrivalTime) {
		this.aimedArrivalTime = aimedArrivalTime;
	}
	public String getAimedDepartureTime() {
		return aimedDepartureTime;
	}
	public void setAimedDepartureTime(String aimedDepartureTime) {
		this.aimedDepartureTime = aimedDepartureTime;
	}
	
	@Override
	public String toString() {
		return "MonitoredCall [stopPointRef=" + stopPointRef + ", visitNumber=" + visitNumber + ", stopPointName="
				+ stopPointName + ", vehicleAtStop=" + vehicleAtStop + ", aimedArrivalTime=" + aimedArrivalTime
				+ ", aimedDepartureTime=" + aimedDepartureTime + "]";
	}
}
