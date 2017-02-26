package com.prapps.app.core.util.time;

import java.util.Calendar;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

@Component
public class TimeUtil {

	public Calendar getCurrentTime() {
		return getCurrentTime();
	}
	
	public Calendar getCurrentTime(TimeZone timeZone) {
		return (Calendar) Calendar.getInstance(timeZone).clone();
	}
	
	public Calendar getCurrentTimeIst() {
		return getCurrentTime(TimeZone.getTimeZone("Asia/Calcutta"));
	}
}
