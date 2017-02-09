package com.prapps.app.chat.dto;

import java.io.Serializable;

public class MessageRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String message;
	private Long threadId;
	private int lastIndex;
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Long getThreadId() {
		return threadId;
	}

	public void setThreadId(Long threadId) {
		this.threadId = threadId;
	}

	public int getLastIndex() {
		return lastIndex;
	}
	
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}
}
