package com.fit2cloud.sdk.model;

public class CloudCredential {
	private long id;
	private long groupId;
	private String name;
	private String credential;
	private String status;
	private long created;
	private long serverCount;
	private int lbCount;
	private int rdsCount;
	private String syncStatus;
	private Long lastUpdate;
	private String cloudProvider;
	private String customData;
	private String description;
	private String pluginDesc;
	private String pluginIcon;
	private long imageCount;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCredential() {
		return credential;
	}
	public void setCredential(String credential) {
		this.credential = credential;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getCreated() {
		return created;
	}
	public void setCreated(long created) {
		this.created = created;
	}
	public long getServerCount() {
		return serverCount;
	}
	public void setServerCount(long serverCount) {
		this.serverCount = serverCount;
	}
	public int getLbCount() {
		return lbCount;
	}
	public void setLbCount(int lbCount) {
		this.lbCount = lbCount;
	}
	public int getRdsCount() {
		return rdsCount;
	}
	public void setRdsCount(int rdsCount) {
		this.rdsCount = rdsCount;
	}
	public String getSyncStatus() {
		return syncStatus;
	}
	public void setSyncStatus(String syncStatus) {
		this.syncStatus = syncStatus;
	}
	public Long getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Long lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getCloudProvider() {
		return cloudProvider;
	}
	public void setCloudProvider(String cloudProvider) {
		this.cloudProvider = cloudProvider;
	}
	public String getCustomData() {
		return customData;
	}
	public void setCustomData(String customData) {
		this.customData = customData;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPluginDesc() {
		return pluginDesc;
	}
	public void setPluginDesc(String pluginDesc) {
		this.pluginDesc = pluginDesc;
	}
	public String getPluginIcon() {
		return pluginIcon;
	}
	public void setPluginIcon(String pluginIcon) {
		this.pluginIcon = pluginIcon;
	}
	public long getImageCount() {
		return imageCount;
	}
	public void setImageCount(long imageCount) {
		this.imageCount = imageCount;
	}
}
