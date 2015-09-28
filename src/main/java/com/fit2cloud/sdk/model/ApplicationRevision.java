package com.fit2cloud.sdk.model;

public class ApplicationRevision {

	private Long id;
	private String name;
	private Long created;
	private String description;
	private Long applicationId;
	private String applicationName;
	private String location;
	private Long repoId;
	private String repoType;
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
	public Long getCreated() {
		return created;
	}
	public void setCreated(Long created) {
		this.created = created;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Long getRepoId() {
		return repoId;
	}
	public void setRepoId(Long repoId) {
		this.repoId = repoId;
	}
	public String getRepoType() {
		return repoType;
	}
	public void setRepoType(String repoType) {
		this.repoType = repoType;
	}
}