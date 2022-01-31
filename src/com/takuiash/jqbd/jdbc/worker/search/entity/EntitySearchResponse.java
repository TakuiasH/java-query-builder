package com.takuiash.jqbd.jdbc.worker.search.entity;

import java.util.List;

import com.takuiash.jqbd.jdbc.worker.search.AbstractSearchResponse;

public class EntitySearchResponse<T> extends AbstractSearchResponse<T> {

	public EntitySearchResponse(List<T> results) {
		super(results);
	}


}
