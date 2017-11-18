package com.gfl.sfbay.stopmonitoring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StopMonitoringResponseModel
{
	@JsonProperty("ServiceDelivery")
	private ServiceDelivery serviceDelivery;

	public ServiceDelivery getServiceDelivery() {
		return serviceDelivery;
	}

	public void setServiceDelivery(ServiceDelivery serviceDelivery) {
		this.serviceDelivery = serviceDelivery;
	}

	@Override
	public String toString() {
		return "StopMonitoringResponseModel [serviceDelivery=" + serviceDelivery + "]";
	}
}
