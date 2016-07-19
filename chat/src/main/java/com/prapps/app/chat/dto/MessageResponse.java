package com.prapps.app.chat.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MessageResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Message> messages;
	private int lastIndex;
	
	public MessageResponse() {
		
	}
	
	public MessageResponse(int lastIndex, List<Message> messages) {
		this.lastIndex = lastIndex;
		this.messages = messages;
	}

	public List<Message> getMessages() {
		if (messages == null) {
			messages = new ArrayList<Message>();
		}
		
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}
}
