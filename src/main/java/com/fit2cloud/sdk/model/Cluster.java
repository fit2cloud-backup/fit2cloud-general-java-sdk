package com.fit2cloud.sdk.model;

public class Cluster {
	
	private Long id;
	private String envType;
	private String name;
	private String description;
	private String rabbitmqExchange;
	private long created;
	private Integer serverNumber;
	private Integer roleNumber;
	private Integer onlineRunningServerNumber;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEnvType() {
		return envType;
	}
	public void setEnvType(String envType) {
		this.envType = envType;
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
	public String getRabbitmqExchange() {
		return rabbitmqExchange;
	}
	public void setRabbitmqExchange(String rabbitmqExchange) {
		this.rabbitmqExchange = rabbitmqExchange;
	}
	public long getCreated() {
		return created;
	}
	public void setCreated(long created) {
		this.created = created;
	}
	public Integer getServerNumber() {
		return serverNumber;
	}
	public void setServerNumber(Integer serverNumber) {
		this.serverNumber = serverNumber;
	}
	public Integer getRoleNumber() {
		return roleNumber;
	}
	public void setRoleNumber(Integer roleNumber) {
		this.roleNumber = roleNumber;
	}
	public Integer getOnlineRunningServerNumber() {
		return onlineRunningServerNumber;
	}
	public void setOnlineRunningServerNumber(Integer onlineRunningServerNumber) {
		this.onlineRunningServerNumber = onlineRunningServerNumber;
	}
	@Override
	public String toString() {
		return "Cluster [id=" + id + ", envType=" + envType + ", name=" + name
				+ ", description=" + description + ", rabbitmqExchange="
				+ rabbitmqExchange + ", created=" + created + ", serverNumber="
				+ serverNumber + ", roleNumber=" + roleNumber
				+ ", onlineRunningServerNumber=" + onlineRunningServerNumber
				+ "]";
	}
}