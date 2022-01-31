package com.takuiash.orm.jdbc.query.helpers.map;

import java.util.Map;

public interface ObjectMap<K, V> {

	public V get(K key);
	public int size();
	public Map<K, V> getValues();
	public void put(K key, V value);
	
}
