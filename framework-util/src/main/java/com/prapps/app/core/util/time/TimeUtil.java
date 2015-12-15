package com.prapps.app.core.util.time;

import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class TimeUtil {

	public Calendar getCurrentTime() {
		return (Calendar) Calendar.getInstance().clone();
	}
}
