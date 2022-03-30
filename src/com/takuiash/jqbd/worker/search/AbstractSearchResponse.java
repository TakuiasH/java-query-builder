package com.takuiash.jqbd.worker.search;

import java.util.Collections;
import java.util.List;

public class AbstractSearchResponse<T> implements SearchResponse<T> {

	private final List<T> results;
	
	public AbstractSearchResponse(List<T> results) {
		this.results = results;
	}
	
	public T first() {
		if(isEmpty())
			return null;
		
		return results.get(0);
	}

	public T last() {
		if(isEmpty())
			return null;
		
		return results.get(results.size()-1);
	}

	public List<T> all() {
		return Collections.unmodifiableList(results);
	}

	public T get(int index) {
		if(isEmpty())
			return null;
		
		return results.get(index);
	}

	public boolean isEmpty() {
		return results.isEmpty();
	}

}
