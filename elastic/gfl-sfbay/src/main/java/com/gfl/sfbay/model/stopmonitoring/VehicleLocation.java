package com.gfl.sfbay.model.stopmonitoring;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VehicleLocation
{
	@JsonProperty("Longitude")
	private double longitude;
	@JsonProperty("Latitude")
	private double latitude;
	
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	@Override
	public String toString() {
		return "VehicleLocation [longitude=" + longitude + ", latitude=" + latitude + "]";
	}
}
