package com.fit2cloud.sdk.model;

public class ClusterRole{
	
	private Long id;
	private Long clusterId;
	private String clusterName;
	private String name;
	private String description;
	private int onlineRunningServerNumber;
	private int vmNumber;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getOnlineRunningServerNumber() {
		return onlineRunningServerNumber;
	}
	public void setOnlineRunningServerNumber(int onlineRunningServerNumber) {
		this.onlineRunningServerNumber = onlineRunningServerNumber;
	}
	public int getVmNumber() {
		return vmNumber;
	}
	public void setVmNumber(int vmNumber) {
		this.vmNumber = vmNumber;
	}
	@Override
	public String toString() {
		return "ClusterRole [id=" + id + ", clusterId=" + clusterId
				+ ", clusterName=" + clusterName + ", name=" + name
				+ ", description=" + description
				+ ", onlineRunningServerNumber=" + onlineRunningServerNumber
				+ ", vmNumber=" + vmNumber + "]";
	}
}
