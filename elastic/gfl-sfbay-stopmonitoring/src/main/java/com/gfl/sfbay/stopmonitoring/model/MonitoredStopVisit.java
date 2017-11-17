package com.gfl.sfbay.stopmonitoring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonitoredStopVisit
{
	@JsonProperty("RecordedAtTime")
	public String recordedAtTime;
	@JsonProperty("MonitoringRef")
	public String monitoringRef;
	@JsonProperty("MonitoredVehicleJourney")
	public MonitoredVehicleJourney monitoredVehicleJourney;
}
