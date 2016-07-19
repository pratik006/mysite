package com.prapps.app.chat.dto;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	public Message() {
		// TODO Auto-generated constructor stub
	}
	
	public Message(int id, String msg) {
		this.id = id;
		this.msg = msg;
	}
	
	private int id;
	private String msg;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
