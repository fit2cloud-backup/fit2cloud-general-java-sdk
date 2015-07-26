package com.fit2cloud.sdk.model;


@SuppressWarnings("restriction")
@javax.xml.bind.annotation.XmlRootElement
public class Cluster {
	
	private Long id;
	private String envType;
	private String name;
	private String description;
	private String rabbitmqExchange;
	private long created;
	
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

	@Override
	public String toString() {
		return "Cluster [id=" + id + ", envType=" + envType + ", name=" + name
				+ ", description=" + description + ", rabbitmqExchange="
				+ rabbitmqExchange + ", created=" + created + "]";
	}
}