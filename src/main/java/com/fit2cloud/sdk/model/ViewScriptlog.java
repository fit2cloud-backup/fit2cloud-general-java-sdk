package com.fit2cloud.sdk.model;


public class ViewScriptlog {

	private Long id;
	private Long accountId;
	private Long clusterId;
	private String clusterName;
	private Long targetRole;
	private String clusterroleName;
	private Long targetServer;
	private String serverName;
	private String eventSource;
	private Long createdTime;
	private String eventName;
	private String eventUuid;
	private String eventData;
	private Long correlationEventId;
	private Long eventTimestamp;
	private Long scriptId;
	private String scriptName;
	private String scriptTrigger;
	
	private String status;
	private Long usedTime;
	private Long schedulerTaskId;
	
	private String scriptPlatform;
	private String scriptType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
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
	public Long getTargetRole() {
		return targetRole;
	}
	public void setTargetRole(Long targetRole) {
		this.targetRole = targetRole;
	}
	public String getClusterroleName() {
		return clusterroleName;
	}
	public void setClusterroleName(String clusterroleName) {
		this.clusterroleName = clusterroleName;
	}
	public Long getTargetServer() {
		return targetServer;
	}
	public void setTargetServer(Long targetServer) {
		this.targetServer = targetServer;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getEventSource() {
		return eventSource;
	}
	public void setEventSource(String eventSource) {
		this.eventSource = eventSource;
	}
	public Long getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventUuid() {
		return eventUuid;
	}
	public void setEventUuid(String eventUuid) {
		this.eventUuid = eventUuid;
	}
	public String getEventData() {
		return eventData;
	}
	public void setEventData(String eventData) {
		this.eventData = eventData;
	}
	public Long getCorrelationEventId() {
		return correlationEventId;
	}
	public void setCorrelationEventId(Long correlationEventId) {
		this.correlationEventId = correlationEventId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getUsedTime() {
		return usedTime;
	}
	public void setUsedTime(Long usedTime) {
		if(usedTime == null || usedTime.longValue() < 0) {
			this.usedTime = 0l;
		}else {
			this.usedTime = usedTime;
		}
	}
	public Long getSchedulerTaskId() {
		return schedulerTaskId;
	}
	public void setSchedulerTaskId(Long schedulerTaskId) {
		this.schedulerTaskId = schedulerTaskId;
	}
	public Long getEventTimestamp() {
		return eventTimestamp;
	}
	public void setEventTimestamp(Long eventTimestamp) {
		this.eventTimestamp = eventTimestamp;
	}
	public Long getScriptId() {
		return scriptId;
	}
	public void setScriptId(Long scriptId) {
		this.scriptId = scriptId;
	}
	public String getScriptName() {
		return scriptName;
	}
	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}
	public String getScriptTrigger() {
		return scriptTrigger;
	}
	public void setScriptTrigger(String scriptTrigger) {
		this.scriptTrigger = scriptTrigger;
	}
	public String getScriptPlatform() {
		return scriptPlatform;
	}
	public void setScriptPlatform(String scriptPlatform) {
		this.scriptPlatform = scriptPlatform;
	}
	public String getScriptType() {
		return scriptType;
	}
	public void setScriptType(String scriptType) {
		this.scriptType = scriptType;
	}
}
