package com.takuiash.orm.jdbc.worker.search.generic;

import java.util.List;

import com.takuiash.orm.jdbc.worker.search.AbstractSearchResponse;

public class GenericSearchResponse extends AbstractSearchResponse<SelectData> {

	public GenericSearchResponse(List<SelectData> results) {
		super(results);
	}
	

}
