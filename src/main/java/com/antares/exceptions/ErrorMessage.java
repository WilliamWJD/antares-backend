package com.antares.exceptions;

public class ErrorMessage {
	private Integer status;
	private String message;
	private Long timeStamp;
	
	public ErrorMessage() {
	}
		
	public ErrorMessage(Integer status, String message, Long timeStamp) {
		super();
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "ErrorMessage [status=" + status + ", message=" + message + ", timeStamp=" + timeStamp + "]";
	}
}
