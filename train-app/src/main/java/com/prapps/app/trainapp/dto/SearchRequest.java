package com.prapps.app.trainapp.dto;

import java.io.Serializable;
import java.util.Calendar;

public class SearchRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String from;
	private String to;
	private Calendar date;
	private String fromTime;
	private String toTime;
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public String getFromTime() {
		return fromTime;
	}
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

}
