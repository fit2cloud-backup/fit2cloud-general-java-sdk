package com.fit2cloud.sdk.model;


public class Event {
	private Long id;
	private Long accountId;
	private Long sourceClusterId;
	private String eventName;
	private String eventUuid;
	private Long eventTimestamp;
	private Long correlationEventId;
	private Long sourceClusterroleId;
	private Long sourceServerroleId;
	private Long sourceServerId;
	private String targetClusterRoleIds;
	private String targetServerIds;
	private String eventData;
	private String eventSource;
	private String eventSourceVersion;
	private Long createdTime;
	private Long handledEventServerId;
	private Integer responseCode;
	private Long handledEventId;
	private Long handledEventServerClusterRoleId;
	private Long handledEventServerServerRoleId;
	private Long schedulerTaskId;
	private Long schedulerTimestamp;
	private String schedulerExecuteResult;
	private Long scriptId;
	private String scriptName;
	private String scriptTrigger;
	private String status;
	private Long usedTime;
	
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
	public Long getSourceClusterId() {
		return sourceClusterId;
	}
	public void setSourceClusterId(Long sourceClusterId) {
		this.sourceClusterId = sourceClusterId;
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
	public Long getEventTimestamp() {
		return eventTimestamp;
	}
	public void setEventTimestamp(Long eventTimestamp) {
		this.eventTimestamp = eventTimestamp;
	}
	public Long getCorrelationEventId() {
		return correlationEventId;
	}
	public void setCorrelationEventId(Long correlationEventId) {
		this.correlationEventId = correlationEventId;
	}
	public Long getSourceClusterroleId() {
		return sourceClusterroleId;
	}
	public void setSourceClusterroleId(Long sourceClusterroleId) {
		this.sourceClusterroleId = sourceClusterroleId;
	}
	public Long getSourceServerroleId() {
		return sourceServerroleId;
	}
	public void setSourceServerroleId(Long sourceServerroleId) {
		this.sourceServerroleId = sourceServerroleId;
	}
	public Long getSourceServerId() {
		return sourceServerId;
	}
	public void setSourceServerId(Long sourceServerId) {
		this.sourceServerId = sourceServerId;
	}
	public String getTargetClusterRoleIds() {
		return targetClusterRoleIds;
	}
	public void setTargetClusterRoleIds(String targetClusterRoleIds) {
		this.targetClusterRoleIds = targetClusterRoleIds;
	}
	public String getTargetServerIds() {
		return targetServerIds;
	}
	public void setTargetServerIds(String targetServerIds) {
		this.targetServerIds = targetServerIds;
	}
	public String getEventData() {
		return eventData;
	}
	public void setEventData(String eventData) {
		this.eventData = eventData;
	}
	public String getEventSource() {
		return eventSource;
	}
	public void setEventSource(String eventSource) {
		this.eventSource = eventSource;
	}
	public String getEventSourceVersion() {
		return eventSourceVersion;
	}
	public void setEventSourceVersion(String eventSourceVersion) {
		this.eventSourceVersion = eventSourceVersion;
	}
	public Long getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}
	public Long getHandledEventServerId() {
		return handledEventServerId;
	}
	public void setHandledEventServerId(Long handledEventServerId) {
		this.handledEventServerId = handledEventServerId;
	}
	public Integer getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}
	public Long getHandledEventId() {
		return handledEventId;
	}
	public void setHandledEventId(Long handledEventId) {
		this.handledEventId = handledEventId;
	}
	public Long getHandledEventServerClusterRoleId() {
		return handledEventServerClusterRoleId;
	}
	public void setHandledEventServerClusterRoleId(
			Long handledEventServerClusterRoleId) {
		this.handledEventServerClusterRoleId = handledEventServerClusterRoleId;
	}
	public Long getHandledEventServerServerRoleId() {
		return handledEventServerServerRoleId;
	}
	public void setHandledEventServerServerRoleId(
			Long handledEventServerServerRoleId) {
		this.handledEventServerServerRoleId = handledEventServerServerRoleId;
	}
	public Long getSchedulerTaskId() {
		return schedulerTaskId;
	}
	public void setSchedulerTaskId(Long schedulerTaskId) {
		this.schedulerTaskId = schedulerTaskId;
	}
	public Long getSchedulerTimestamp() {
		return schedulerTimestamp;
	}
	public void setSchedulerTimestamp(Long schedulerTimestamp) {
		this.schedulerTimestamp = schedulerTimestamp;
	}
	public String getSchedulerExecuteResult() {
		return schedulerExecuteResult;
	}
	public void setSchedulerExecuteResult(String schedulerExecuteResult) {
		this.schedulerExecuteResult = schedulerExecuteResult;
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
		this.usedTime = usedTime;
	}
}
