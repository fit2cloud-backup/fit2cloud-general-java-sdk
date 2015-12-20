package com.fit2cloud.sdk.model;

public class ClusterRoleAlertLogging {
	private Long id;
	private Long startTime;
	private Long endTime;
	private Long alertId;
	private String alertType;
	private Long clusterId;
	private Long clusterRoleId;
	private Long serverId;
	private String serverName;
	private Double monitorData;
	private String msg;
	private String last;
	private String clusterName;
	private String vmStatus;
	private String serverOsType;
	private String showStatusPolicy;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public Long getAlertId() {
		return alertId;
	}
	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}
	public String getAlertType() {
		return alertType;
	}
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}
	public Long getClusterId() {
		return clusterId;
	}
	public void setClusterId(Long clusterId) {
		this.clusterId = clusterId;
	}
	public Long getClusterRoleId() {
		return clusterRoleId;
	}
	public void setClusterRoleId(Long clusterRoleId) {
		this.clusterRoleId = clusterRoleId;
	}
	public Long getServerId() {
		return serverId;
	}
	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public double getMonitorData() {
		return monitorData;
	}
	public void setMonitorData(double monitorData) {
		this.monitorData = monitorData;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public String getClusterName() {
		return clusterName;
	}
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	public String getVmStatus() {
		return vmStatus;
	}
	public void setVmStatus(String vmStatus) {
		this.vmStatus = vmStatus;
	}
	public String getShowStatusPolicy() {
		return showStatusPolicy;
	}
	public void setShowStatusPolicy(String showStatusPolicy) {
		this.showStatusPolicy = showStatusPolicy;
	}
	public String getServerOsType() {
		return serverOsType;
	}
	public void setServerOsType(String serverOsType) {
		this.serverOsType = serverOsType;
	}
}