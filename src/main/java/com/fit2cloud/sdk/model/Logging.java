package com.fit2cloud.sdk.model;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("restriction")
@XmlRootElement
public class Logging {
	private Long id;
	private Long clusterId;
	private String clusterName;
	private Long clusterroleId;
	private String clusterroleName;
	private Long serverId;
	private String serverName;
	private String source;
	private long loggingTimestamp;
	private String level;
	private String msg;
	private String logClass;
	private String logThread;
	private Long eventId;
	private String eventName;
	
	public Long getId() {
		return id;
	}
	public Long getClusterId() {
		return clusterId;
	}
	public String getClusterName() {
		return clusterName;
	}
	public Long getClusterroleId() {
		return clusterroleId;
	}
	public String getClusterroleName() {
		return clusterroleName;
	}
	public Long getServerId() {
		return serverId;
	}
	public String getServerName() {
		return serverName;
	}
	public String getSource() {
		return source;
	}
	public long getLoggingTimestamp() {
		return loggingTimestamp;
	}
	public String getLevel() {
		return level;
	}
	public String getMsg() {
		return msg;
	}
	public String getLogClass() {
		return logClass;
	}
	public String getLogThread() {
		return logThread;
	}
	public Long getEventId() {
		return eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setClusterId(Long clusterId) {
		this.clusterId = clusterId;
	}
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	public void setClusterroleId(Long clusterroleId) {
		this.clusterroleId = clusterroleId;
	}
	public void setClusterroleName(String clusterroleName) {
		this.clusterroleName = clusterroleName;
	}
	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public void setLoggingTimestamp(long loggingTimestamp) {
		this.loggingTimestamp = loggingTimestamp;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setLogClass(String logClass) {
		this.logClass = logClass;
	}
	public void setLogThread(String logThread) {
		this.logThread = logThread;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	@Override
	public String toString() {
		return "Logging [id=" + id + ", clusterId=" + clusterId
				+ ", clusterName=" + clusterName + ", clusterroleId="
				+ clusterroleId + ", clusterroleName=" + clusterroleName
				+ ", serverId=" + serverId + ", serverName=" + serverName
				+ ", source=" + source + ", loggingTimestamp="
				+ loggingTimestamp + ", level=" + level + ", msg=" + msg
				+ ", logClass=" + logClass + ", logThread=" + logThread
				+ ", eventId=" + eventId + ", eventName=" + eventName + "]";
	}
	
}
