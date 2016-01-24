package com.fit2cloud.sdk.model;

public class LaunchConfiguration {
	private long id;
	private long groupId;
	private String name;
	private String description;
	private Long credentialId;
	private String credentialName;
	private String cloudProvider;
	private int sshPort;
	private Long keyPasswordId;
	private String launchConfiguration;
	private Long created;
	private String agentInstallMethod;
	private Long initScriptId;
	private String keyPasswordName;
	private String initScriptName;
	private String status;
	private Long applicationRevisionId;
	private String applicationRevisionName;
	private String pluginDesc;
	private String pluginIcon;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getCredentialId() {
		return credentialId;
	}
	public void setCredentialId(Long credentialId) {
		this.credentialId = credentialId;
	}
	public String getCredentialName() {
		return credentialName;
	}
	public void setCredentialName(String credentialName) {
		this.credentialName = credentialName;
	}
	public String getCloudProvider() {
		return cloudProvider;
	}
	public void setCloudProvider(String cloudProvider) {
		this.cloudProvider = cloudProvider;
	}
	public int getSshPort() {
		return sshPort;
	}
	public void setSshPort(int sshPort) {
		this.sshPort = sshPort;
	}
	public Long getKeyPasswordId() {
		return keyPasswordId;
	}
	public void setKeyPasswordId(Long keyPasswordId) {
		this.keyPasswordId = keyPasswordId;
	}
	public String getLaunchConfiguration() {
		return launchConfiguration;
	}
	public void setLaunchConfiguration(String launchConfiguration) {
		this.launchConfiguration = launchConfiguration;
	}
	public Long getCreated() {
		return created;
	}
	public void setCreated(Long created) {
		this.created = created;
	}
	public String getAgentInstallMethod() {
		return agentInstallMethod;
	}
	public void setAgentInstallMethod(String agentInstallMethod) {
		this.agentInstallMethod = agentInstallMethod;
	}
	public Long getInitScriptId() {
		return initScriptId;
	}
	public void setInitScriptId(Long initScriptId) {
		this.initScriptId = initScriptId;
	}
	public String getKeyPasswordName() {
		return keyPasswordName;
	}
	public void setKeyPasswordName(String keyPasswordName) {
		this.keyPasswordName = keyPasswordName;
	}
	public String getInitScriptName() {
		return initScriptName;
	}
	public void setInitScriptName(String initScriptName) {
		this.initScriptName = initScriptName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getApplicationRevisionId() {
		return applicationRevisionId;
	}
	public void setApplicationRevisionId(Long applicationRevisionId) {
		this.applicationRevisionId = applicationRevisionId;
	}
	public String getApplicationRevisionName() {
		return applicationRevisionName;
	}
	public void setApplicationRevisionName(String applicationRevisionName) {
		this.applicationRevisionName = applicationRevisionName;
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
}