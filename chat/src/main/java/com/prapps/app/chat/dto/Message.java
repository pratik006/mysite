package com.prapps.app.chat.dto;

import java.io.Serializable;
import java.util.Calendar;

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
	private Calendar time;
	
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

	public Calendar getTime() {
		return time;
	}

	public void setTime(Calendar time) {
		this.time = time;
	}
}
