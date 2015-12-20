package com.fit2cloud.sdk.model;

public class MetricTop {
	private long serverId;
	private String serverName;
	private long clusterId;
	private String clusterName;
	private long clusterRoleId;
	private String clusterRoleName;
	private String metricName;
	private double metricValue;
	public long getServerId() {
		return serverId;
	}
	public void setServerId(long serverId) {
		this.serverId = serverId;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public long getClusterId() {
		return clusterId;
	}
	public void setClusterId(long clusterId) {
		this.clusterId = clusterId;
	}
	public String getClusterName() {
		return clusterName;
	}
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	public long getClusterRoleId() {
		return clusterRoleId;
	}
	public void setClusterRoleId(long clusterRoleId) {
		this.clusterRoleId = clusterRoleId;
	}
	public String getClusterRoleName() {
		return clusterRoleName;
	}
	public void setClusterRoleName(String clusterRoleName) {
		this.clusterRoleName = clusterRoleName;
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
}
