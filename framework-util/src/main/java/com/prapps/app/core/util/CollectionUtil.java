package com.prapps.app.core.util;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.BeanUtils;

public class CollectionUtil {

	public static <U,T> Collection<T> copyProperties(Iterable<U> sourceList, Class<T> targetClass) throws InstantiationException, IllegalAccessException {
		Collection<T> collection = new ArrayList<>();
		for (U source : sourceList) {
			T target = targetClass.newInstance();
			BeanUtils.copyProperties(source, target);
			collection.add(target);
		}
		return collection;
	}
}
