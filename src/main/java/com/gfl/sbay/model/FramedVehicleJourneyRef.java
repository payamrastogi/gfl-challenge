package com.gfl.sbay.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FramedVehicleJourneyRef
{
	@JsonProperty("DataFrameRef")
	public String dataFrameRef;
	@JsonProperty("DatedVehicleJourneyRef")
	public String datedVehicleJourneyRef;
}
