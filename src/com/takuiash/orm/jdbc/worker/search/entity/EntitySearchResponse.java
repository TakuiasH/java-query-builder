package com.takuiash.orm.jdbc.worker.search.entity;

import java.util.List;

import com.takuiash.orm.jdbc.worker.search.AbstractSearchResponse;

public class EntitySearchResponse<T> extends AbstractSearchResponse<T> {

	public EntitySearchResponse(List<T> results) {
		super(results);
	}


}
