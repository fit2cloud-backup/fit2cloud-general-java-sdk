package com.fit2cloud.sdk.model;


public class Tag {
	
	private Long id;
	private String name;
	private String value;
	private Long serverId;
	private Long clusterRoleId;
	private Long clusterId;
	private String serverName;
	private String clusterName;
	private String clusterRoleName;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Long getServerId() {
		return serverId;
	}
	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}
	public Long getClusterRoleId() {
		return clusterRoleId;
	}
	public void setClusterRoleId(Long clusterRoleId) {
		this.clusterRoleId = clusterRoleId;
	}
	public Long getClusterId() {
		return clusterId;
	}
	public void setClusterId(Long clusterId) {
		this.clusterId = clusterId;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getClusterName() {
		return clusterName;
	}
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	public String getClusterRoleName() {
		return clusterRoleName;
	}
	public void setClusterRoleName(String clusterRoleName) {
		this.clusterRoleName = clusterRoleName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
