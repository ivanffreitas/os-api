package com.ivanilson.os.resource.exceptions;

import java.io.Serializable;

public class StandarError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long timestamp;
	private Integer status;
	private String error;
	
	public StandarError() {
		super();
	}
	public StandarError(long timestamp, Integer status, String error) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	

}
