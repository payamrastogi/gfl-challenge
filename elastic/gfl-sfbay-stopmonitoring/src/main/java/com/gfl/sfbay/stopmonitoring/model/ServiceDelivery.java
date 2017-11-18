package com.gfl.sfbay.stopmonitoring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServiceDelivery
{
	@JsonProperty("ResponseTimestamp")
	private String responseTimestamp;
	@JsonProperty("ProducerRef")
	private String producerRef;
	@JsonProperty("Status")
	private boolean status;
	@JsonProperty("StopMonitoringDelivery")
	private StopMonitoringDelivery stopMonitoringDelivery;
	public String getResponseTimestamp() {
		return responseTimestamp;
	}
	public void setResponseTimestamp(String responseTimestamp) {
		this.responseTimestamp = responseTimestamp;
	}
	public String getProducerRef() {
		return producerRef;
	}
	public void setProducerRef(String producerRef) {
		this.producerRef = producerRef;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public StopMonitoringDelivery getStopMonitoringDelivery() {
		return stopMonitoringDelivery;
	}
	public void setStopMonitoringDelivery(StopMonitoringDelivery stopMonitoringDelivery) {
		this.stopMonitoringDelivery = stopMonitoringDelivery;
	}
	
}
