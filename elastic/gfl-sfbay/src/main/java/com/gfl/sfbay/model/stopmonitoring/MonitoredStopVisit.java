package com.gfl.sfbay.model.stopmonitoring;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonitoredStopVisit
{
	@JsonProperty("RecordedAtTime")
	private String recordedAtTime;
	@JsonProperty("MonitoringRef")
	private String monitoringRef;
	@JsonProperty("MonitoredVehicleJourney")
	private MonitoredVehicleJourney monitoredVehicleJourney;
	
	public String getRecordedAtTime() {
		return recordedAtTime;
	}
	public void setRecordedAtTime(String recordedAtTime) {
		this.recordedAtTime = recordedAtTime;
	}
	public String getMonitoringRef() {
		return monitoringRef;
	}
	public void setMonitoringRef(String monitoringRef) {
		this.monitoringRef = monitoringRef;
	}
	public MonitoredVehicleJourney getMonitoredVehicleJourney() {
		return monitoredVehicleJourney;
	}
	public void setMonitoredVehicleJourney(MonitoredVehicleJourney monitoredVehicleJourney) {
		this.monitoredVehicleJourney = monitoredVehicleJourney;
	}
	
	@Override
	public String toString() {
		return "MonitoredStopVisit [recordedAtTime=" + recordedAtTime + ", monitoringRef=" + monitoringRef
				+ ", monitoredVehicleJourney=" + monitoredVehicleJourney + "]";
	}
}
