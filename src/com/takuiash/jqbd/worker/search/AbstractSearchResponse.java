package com.takuiash.jqbd.worker.search;

import java.util.Collections;
import java.util.List;

import com.takuiash.jqbd.worker.search.exceptions.EmptyResponseException;

public class AbstractSearchResponse<T> implements SearchResponse<T> {

	private final List<T> results;
	
	public AbstractSearchResponse(List<T> results) {
		this.results = results;
	}
	
	public T first() {
		if(isEmpty())
			throw new EmptyResponseException();
		
		return results.get(0);
	}

	public T last() {
		if(isEmpty())
			throw new EmptyResponseException();
		
		return results.get(results.size()-1);
	}

	public List<T> all() {
		return Collections.unmodifiableList(results);
	}

	public T get(int index) {
		if(isEmpty())
			throw new EmptyResponseException();
		
		return results.get(index);
	}

	public boolean isEmpty() {
		return results.isEmpty();
	}

}