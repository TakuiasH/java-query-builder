package com.takuiash.jqbd.worker.search.generic;

import java.util.List;

import com.takuiash.jqbd.worker.search.AbstractSearchResponse;

public class GenericSearchResponse extends AbstractSearchResponse<SelectData> {

	public GenericSearchResponse(List<SelectData> results) {
		super(results);
	}
	

}
