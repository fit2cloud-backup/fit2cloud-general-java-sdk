package com.fit2cloud.sdk.model;

import java.awt.*;

/**
 * Created by linjinbo on 2017/9/3.
 */
public class ApplicationDeploymentEventLog {
    private Long id;
    private Long deploymentLogId;
    private String eventName;
    private Long startTime;
    private Long endTime;
    private String status;
    private TextArea eventLog;
    private Integer eventId;
    private Integer responseCode;
    private String msg;
    private Long serverId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeploymentLogId() {
        return deploymentLogId;
    }

    public void setDeploymentLogId(Long deploymentLogId) {
        this.deploymentLogId = deploymentLogId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
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

    public TextArea getEventLog() {
        return eventLog;
    }

    public void setEventLog(TextArea eventLog) {
        this.eventLog = eventLog;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }
}
