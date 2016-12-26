package com.fit2cloud.sdk.model;
public class ServerMetric {
	private Long serverId;
	private Long heartbeatTimestamp;
	private String metricName;
	private double metricValue;
	private String metricUnit;
	public Long getServerId() {
		return serverId;
	}

	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}

	public Long getHeartbeatTimestamp() {
		return heartbeatTimestamp;
	}

	public void setHeartbeatTimestamp(Long heartbeatTimestamp) {
		this.heartbeatTimestamp = heartbeatTimestamp;
	}

	public String getMetricName() {
		return metricName;
	}

	public void setMetricName(String metricName) {
		this.metricName = metricName;
	}

	public double getMetricValue() {
		return metricValue;
	}

	public void setMetricValue(double metricValue) {
		this.metricValue = metricValue;
	}

	public String getMetricUnit() {
		return metricUnit;
	}

	public void setMetricUnit(String metricUnit) {
		this.metricUnit = metricUnit;
	}
}