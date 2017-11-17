package com.gfl.sfbay.stopmonitoring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StopMonitoringResponse
{
	@JsonProperty("ServiceDelivery")
	public ServiceDelivery serviceDelivery;
}
