package com.takuiash.orm.jdbc.query.helpers.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractObjectMap<K, V> implements ObjectMap<K, V> {

	protected Map<K, V> values = new HashMap<K, V>();
	
	public V get(K key) {
		return values.get(key);
	}
	
	public int size() {
		return values.size();
	}
	
	public boolean containsKey(K key) {
		return values.containsKey(key);
	}
	
	public boolean containsValue(V value) {
		return values.containsValue(value);
	}
	
	public Map<K, V> getValues() {
		return Collections.unmodifiableMap(values);
	}
	
	public void put(K key, V value) {
		this.values.put(key, value);
	}
	
}
