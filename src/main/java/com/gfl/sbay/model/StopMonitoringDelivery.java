package com.gfl.sbay.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StopMonitoringDelivery
{
	@JsonProperty("version")
	public String version;
	@JsonProperty("ResponseTimestamp")
	public String responseTimestamp;
	@JsonProperty("Status")
	public boolean status;
	@JsonProperty("MonitoredStopVisit")
	public List<MonitoredStopVisit> monitoredStopVisits;
}
