package com.prapps.app.trainapp.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

public class SearchResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Collection<Train> trains;
	
	public SearchResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public SearchResponse(Collection<Train> trains) {
		this.trains = trains;
	}

	public Collection<Train> getTrains() {
		if (trains == null) {
			trains = Collections.emptyList();
		}
		
		return trains;
	}

	public void setTrains(Collection<Train> trains) {
		this.trains = trains;
	}
	
	
}
