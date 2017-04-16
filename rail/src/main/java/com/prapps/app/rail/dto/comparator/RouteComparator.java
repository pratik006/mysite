package com.prapps.app.rail.dto.comparator;

import java.util.Comparator;

import com.prapps.app.rail.persistence.RouteEntity;

public enum RouteComparator implements Comparator<RouteEntity> {
	INSTANCE;

	public static RouteComparator getInstance() {
        return INSTANCE;
    }
	
	@Override
	public int compare(RouteEntity r1, RouteEntity r2) {
		return r1.getHalt() - r2.getHalt();
	}
}
