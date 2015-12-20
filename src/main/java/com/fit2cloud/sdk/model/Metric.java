package com.fit2cloud.sdk.model;

public class Metric {
	private String metricName;
	private String comment;
	private String unit;
	
	public Metric() {
	}
	public Metric(String metricName, String comment, String unit) {
		super();
		this.metricName = metricName;
		this.comment = comment;
		this.unit = unit;
	}
	public String getMetricName() {
		return metricName;
	}
	public void setMetricName(String metricName) {
		this.metricName = metricName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
}
