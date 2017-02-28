package com.fit2cloud.sdk.model;
public class GroupEnv {
	private Long id;
	private String name;
	private Long defaultContactGroupId;
	private Long portMonitorContactGroupId;
	private Long siteMonitorContactGroupId;
	private Long created;
	private Long billingPlanId=1l;
	private String description;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getDefaultContactGroupId() {
		return defaultContactGroupId;
	}
	public void setDefaultContactGroupId(Long defaultContactGroupId) {
		this.defaultContactGroupId = defaultContactGroupId;
	}
	public Long getPortMonitorContactGroupId() {
		return portMonitorContactGroupId;
	}
	public void setPortMonitorContactGroupId(Long portMonitorContactGroupId) {
		this.portMonitorContactGroupId = portMonitorContactGroupId;
	}
	public Long getSiteMonitorContactGroupId() {
		return siteMonitorContactGroupId;
	}
	public void setSiteMonitorContactGroupId(Long siteMonitorContactGroupId) {
		this.siteMonitorContactGroupId = siteMonitorContactGroupId;
	}
	public Long getCreated() {
		return created;
	}
	public void setCreated(Long created) {
		this.created = created;
	}
	public Long getBillingPlanId() {
		return billingPlanId;
	}
	public void setBillingPlanId(Long billingPlanId) {
		this.billingPlanId = billingPlanId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
