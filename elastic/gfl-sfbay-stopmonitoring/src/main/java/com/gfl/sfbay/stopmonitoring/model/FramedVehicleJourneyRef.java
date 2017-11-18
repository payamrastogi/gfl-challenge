package com.gfl.sfbay.stopmonitoring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FramedVehicleJourneyRef
{
	@JsonProperty("DataFrameRef")
	private String dataFrameRef;
	@JsonProperty("DatedVehicleJourneyRef")
	private String datedVehicleJourneyRef;
	
	public String getDataFrameRef() {
		return dataFrameRef;
	}
	public void setDataFrameRef(String dataFrameRef) {
		this.dataFrameRef = dataFrameRef;
	}
	public String getDatedVehicleJourneyRef() {
		return datedVehicleJourneyRef;
	}
	public void setDatedVehicleJourneyRef(String datedVehicleJourneyRef) {
		this.datedVehicleJourneyRef = datedVehicleJourneyRef;
	}
}
