package com.fit2cloud.sdk.model;

public class PortMonitor {
	private Long id;
	private Long groupId;
	private Long clusterId;
	private String clusterName;
	private Long clusterRoleId;
	private String clusterRoleName;
	private Long serverId;
	private String serverName;
	private String ip;
	private int port;
	private String ipType;
	private int frequency;
	private String status;
	private Long lastCheckTime;
	private Long lastSuccessTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public Long getClusterId() {
		return clusterId;
	}
	public void setClusterId(Long clusterId) {
		this.clusterId = clusterId;
	}
	public String getClusterName() {
		return clusterName;
	}
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	public Long getClusterRoleId() {
		return clusterRoleId;
	}
	public void setClusterRoleId(Long clusterRoleId) {
		this.clusterRoleId = clusterRoleId;
	}
	public String getClusterRoleName() {
		return clusterRoleName;
	}
	public void setClusterRoleName(String clusterRoleName) {
		this.clusterRoleName = clusterRoleName;
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getIpType() {
		return ipType;
	}
	public void setIpType(String ipType) {
		this.ipType = ipType;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getLastCheckTime() {
		return lastCheckTime;
	}
	public void setLastCheckTime(Long lastCheckTime) {
		this.lastCheckTime = lastCheckTime;
	}
	public Long getLastSuccessTime() {
		return lastSuccessTime;
	}
	public void setLastSuccessTime(Long lastSuccessTime) {
		this.lastSuccessTime = lastSuccessTime;
	}
}