package com.fit2cloud.sdk.model;

public class ContactGroup {
	private Long id;
	private Long groupId;
	private String name;
	private boolean mailEnable;
	private boolean phoneEnable;
	private boolean weixinEnable;
	private boolean defaultContactGroup;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isMailEnable() {
		return mailEnable;
	}
	public void setMailEnable(boolean mailEnable) {
		this.mailEnable = mailEnable;
	}
	public boolean isPhoneEnable() {
		return phoneEnable;
	}
	public void setPhoneEnable(boolean phoneEnable) {
		this.phoneEnable = phoneEnable;
	}
	public boolean isWeixinEnable() {
		return weixinEnable;
	}
	public void setWeixinEnable(boolean weixinEnable) {
		this.weixinEnable = weixinEnable;
	}
	public boolean isDefaultContactGroup() {
		return defaultContactGroup;
	}
	public void setDefaultContactGroup(boolean defaultContactGroup) {
		this.defaultContactGroup = defaultContactGroup;
	}
}