package com.fit2cloud.sdk.model;
public class FireEventRequest {
	private String eventName;
	private Integer sourceClusterId;
	private Integer sourceClusterroleId=0;
	private Integer sourceServerId=0;
	private Integer sourceServerroleId=0;
	private Integer correlationEventId=0;
	private String targetClusterroleIds="";
	private String targetServerIds="";
	private String eventData="";

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Integer getSourceClusterId() {
		return sourceClusterId;
	}

	public void setSourceClusterId(Integer sourceClusterId) {
		this.sourceClusterId = sourceClusterId;
	}

	public Integer getSourceClusterroleId() {
		return sourceClusterroleId;
	}

	public void setSourceClusterroleId(Integer sourceClusterroleId) {
		this.sourceClusterroleId = sourceClusterroleId;
	}

	public Integer getSourceServerId() {
		return sourceServerId;
	}
	
	public Integer getSourceServerroleId() {
		return sourceServerroleId;
	}

	public void setSourceServerroleId(Integer sourceServerroleId) {
		this.sourceServerroleId = sourceServerroleId;
	}

	public void setSourceServerId(Integer sourceServerId) {
		this.sourceServerId = sourceServerId;
	}

	public Integer getCorrelationEventId() {
		return correlationEventId;
	}

	public void setCorrelationEventId(Integer correlationEventId) {
		this.correlationEventId = correlationEventId;
	}

	public String getTargetClusterroleIds() {
		return targetClusterroleIds;
	}

	public void setTargetClusterroleIds(String targetClusterroleIds) {
		this.targetClusterroleIds = targetClusterroleIds;
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
	
}