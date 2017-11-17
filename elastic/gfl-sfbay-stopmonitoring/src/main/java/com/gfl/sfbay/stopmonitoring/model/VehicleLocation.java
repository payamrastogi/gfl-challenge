package com.gfl.sfbay.stopmonitoring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VehicleLocation
{
	@JsonProperty("Longitude")
	public double longitude;
	@JsonProperty("Latitude")
	public double latitude;
}
