package com.prapps.app.chat.dto;

import java.io.Serializable;
import java.util.List;

public class MessageThread implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final int MSG_BUF_SIZE = 1024;
	
	private List<Message> messages;
	private int currentIndex = -1;

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public void addMessages(Message message) {
		this.messages.add((++currentIndex)%MSG_BUF_SIZE, message);
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}
}
