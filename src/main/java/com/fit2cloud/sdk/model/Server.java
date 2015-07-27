package com.fit2cloud.sdk.model;


public class Server {
	
	private Long id;
	private String description;
	private Long clusterId;
	private String clusterName;
	private Long clusterRoleId;
	private String clusterRoleName;
	private String imageId;
	private String vmId;
	private String vmStatus;
	private String vmType;
	private String status;
	private String region;
	private String remoteIP;
	private String localIP;
	private Long created;
	private String heartbeatStatus;
	private String name;
	private String alertType;
	private String zone;
	private String hostname;
	private Long keyPasswordId;
	private String rabbitmqQueue;
	private Long sshPort;
	private String customData;
	private Long credentialId;
	private Long launchConfigurationId;
	private String machineId;
	private String os;
	private String agentVersion;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getVmId() {
		return vmId;
	}
	public void setVmId(String vmId) {
		this.vmId = vmId;
	}
	public String getVmStatus() {
		return vmStatus;
	}
	public void setVmStatus(String vmStatus) {
		this.vmStatus = vmStatus;
	}
	public String getVmType() {
		return vmType;
	}
	public void setVmType(String vmType) {
		this.vmType = vmType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getRemoteIP() {
		return remoteIP;
	}
	public void setRemoteIP(String remoteIP) {
		this.remoteIP = remoteIP;
	}
	public String getLocalIP() {
		return localIP;
	}
	public void setLocalIP(String localIP) {
		this.localIP = localIP;
	}
	public Long getCreated() {
		return created;
	}
	public void setCreated(Long created) {
		this.created = created;
	}
	public String getHeartbeatStatus() {
		return heartbeatStatus;
	}
	public void setHeartbeatStatus(String heartbeatStatus) {
		this.heartbeatStatus = heartbeatStatus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlertType() {
		return alertType;
	}
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public Long getKeyPasswordId() {
		return keyPasswordId;
	}
	public void setKeyPasswordId(Long keyPasswordId) {
		this.keyPasswordId = keyPasswordId;
	}
	public String getRabbitmqQueue() {
		return rabbitmqQueue;
	}
	public void setRabbitmqQueue(String rabbitmqQueue) {
		this.rabbitmqQueue = rabbitmqQueue;
	}
	public Long getSshPort() {
		return sshPort;
	}
	public void setSshPort(Long sshPort) {
		this.sshPort = sshPort;
	}
	public String getCustomData() {
		return customData;
	}
	public void setCustomData(String customData) {
		this.customData = customData;
	}
	public Long getCredentialId() {
		return credentialId;
	}
	public void setCredentialId(Long credentialId) {
		this.credentialId = credentialId;
	}
	public Long getLaunchConfigurationId() {
		return launchConfigurationId;
	}
	public void setLaunchConfigurationId(Long launchConfigurationId) {
		this.launchConfigurationId = launchConfigurationId;
	}
	public String getMachineId() {
		return machineId;
	}
	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getAgentVersion() {
		return agentVersion;
	}
	public void setAgentVersion(String agentVersion) {
		this.agentVersion = agentVersion;
	}
	@Override
	public String toString() {
		return "Server [id=" + id + ", description=" + description
				+ ", clusterId=" + clusterId + ", clusterName=" + clusterName
				+ ", clusterRoleId=" + clusterRoleId + ", clusterRoleName="
				+ clusterRoleName + ", imageId=" + imageId + ", vmId=" + vmId
				+ ", vmStatus=" + vmStatus + ", vmType=" + vmType + ", status="
				+ status + ", region=" + region + ", remoteIP=" + remoteIP
				+ ", localIP=" + localIP + ", created=" + created
				+ ", heartbeatStatus=" + heartbeatStatus + ", name=" + name
				+ ", alertType=" + alertType + ", zone=" + zone + ", hostname="
				+ hostname + ", keyPasswordId=" + keyPasswordId
				+ ", rabbitmqQueue=" + rabbitmqQueue + ", sshPort=" + sshPort
				+ ", customData=" + customData + ", credentialId="
				+ credentialId + ", launchConfigurationId="
				+ launchConfigurationId + ", machineId=" + machineId + ", os="
				+ os + ", agentVersion=" + agentVersion + "]";
	}
}
