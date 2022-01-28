package com.thdev.jqbd.query.base;

import java.util.List;

import com.thdev.jqbd.query.response.DatabaseResponse;
import com.thdev.jqbd.query.response.ResponseMap;

public interface Gettable {

	public DatabaseResponse getResponse();
	
	public default boolean isEmpty() {
		return getResponse().isEmpty();
	}
	
	public default int size() {
		return getResponse().size();
	}
	
	public default List<ResponseMap> all() {
		return getResponse().all();
	}
	
	public default ResponseMap get(int index) {		
		return getResponse().get(index);
	}
	
	public default ResponseMap first() {
		return getResponse().first();
	}
	
	public default ResponseMap last() {		
		return getResponse().last();
	}
	
}
