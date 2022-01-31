package com.takuiash.jqbd.worker.search.entity;

import java.util.List;

import com.takuiash.jqbd.worker.search.AbstractSearchResponse;

public class EntitySearchResponse<T> extends AbstractSearchResponse<T> {

	public EntitySearchResponse(List<T> results) {
		super(results);
	}


}
