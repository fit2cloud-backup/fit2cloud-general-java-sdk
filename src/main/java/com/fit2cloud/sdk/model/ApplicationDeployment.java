package com.fit2cloud.sdk.model;

public class ApplicationDeployment {

	private Long id;
	private Long revisionId;
	private Long clusterId;
	private Long clusterRoleId;
	private Long serverId;
	private String deployPolicy;
	private Long startTime;
	private Long endTime;
	private String status;
	private String description;
	private String msg;
	private Long applicationId;
	private Long contactGroupId;
	private Integer gatedLaunchEnable;
	private Long gatedLaunchDeploymentId;
	private Long userId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRevisionId() {
		return revisionId;
	}
	public void setRevisionId(Long revisionId) {
		this.revisionId = revisionId;
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
	public String getDeployPolicy() {
		return deployPolicy;
	}
	public void setDeployPolicy(String deployPolicy) {
		this.deployPolicy = deployPolicy;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public Long getContactGroupId() {
		return contactGroupId;
	}
	public void setContactGroupId(Long contactGroupId) {
		this.contactGroupId = contactGroupId;
	}
	public Integer getGatedLaunchEnable() {
		return gatedLaunchEnable;
	}
	public void setGatedLaunchEnable(Integer gatedLaunchEnable) {
		this.gatedLaunchEnable = gatedLaunchEnable;
	}
	public Long getGatedLaunchDeploymentId() {
		return gatedLaunchDeploymentId;
	}
	public void setGatedLaunchDeploymentId(Long gatedLaunchDeploymentId) {
		this.gatedLaunchDeploymentId = gatedLaunchDeploymentId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}