package com.thdev.jqbd.query.response;

import java.util.Collections;
import java.util.List;

public class DatabaseResponse {
	
	private final List<ResponseMap> responses;
	
	public DatabaseResponse(List<ResponseMap> responses) {
		this.responses = responses;
	}
	
	public boolean isEmpty() {
		return responses.isEmpty();
	}
	
	public int size() {
		return responses.size();
	}
	
	public List<ResponseMap> all() {
		return Collections.unmodifiableList(responses);
	}
	
	public ResponseMap get(int index) {		
		return responses.get(index);
	}
	
	public ResponseMap first() {
		return responses.get(0);
	}
	
	public ResponseMap last() {		
		return responses.get(responses.size()-1);
	}
}
