package com.takuiash.jqbd.worker.search;

import java.util.List;

public interface SearchResponse<T> {

	public T first();
	public T last();
	public List<T> all();
	public T get(int index);
	public boolean isEmpty();
}
