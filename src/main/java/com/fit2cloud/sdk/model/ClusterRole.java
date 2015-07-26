package com.fit2cloud.sdk.model;

@SuppressWarnings("restriction")
@javax.xml.bind.annotation.XmlRootElement
public class ClusterRole{
	
	private Long id;
	private Long clusterId;
	private String clusterName;
	private String name;
	private String description;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "ClusterRole [id=" + id + ", clusterId=" + clusterId
				+ ", clusterName=" + clusterName + ", name=" + name
				+ ", description=" + description + "]";
	}
}
