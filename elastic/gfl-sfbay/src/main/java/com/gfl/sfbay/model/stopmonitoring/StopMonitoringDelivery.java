package com.gfl.sfbay.model.stopmonitoring;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StopMonitoringDelivery
{
	@JsonProperty("version")
	private String version;
	@JsonProperty("ResponseTimestamp")
	private String responseTimestamp;
	@JsonProperty("Status")
	private boolean status;
	@JsonProperty("MonitoredStopVisit")
	private List<MonitoredStopVisit> monitoredStopVisits;
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getResponseTimestamp() {
		return responseTimestamp;
	}
	public void setResponseTimestamp(String responseTimestamp) {
		this.responseTimestamp = responseTimestamp;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public List<MonitoredStopVisit> getMonitoredStopVisits() {
		return monitoredStopVisits;
	}
	public void setMonitoredStopVisits(List<MonitoredStopVisit> monitoredStopVisits) {
		this.monitoredStopVisits = monitoredStopVisits;
	}
	
	@Override
	public String toString() {
		return "StopMonitoringDelivery [version=" + version + ", responseTimestamp=" + responseTimestamp + ", status="
				+ status + ", monitoredStopVisits=" + monitoredStopVisits + "]";
	}
}
