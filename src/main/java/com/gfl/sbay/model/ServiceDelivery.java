package com.gfl.sbay.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServiceDelivery
{
	@JsonProperty("ResponseTimestamp")
	public String responseTimestamp;
	@JsonProperty("ProducerRef")
	public String producerRef;
	@JsonProperty("Status")
	public boolean status;
	@JsonProperty("StopMonitoringDelivery")
	public StopMonitoringDelivery stopMonitoringDelivery;
}
