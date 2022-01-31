package com.takuiash.jqbd.query.helpers.map;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
	
	public Set<Entry<K, V>> entrySet() {
		return values.entrySet();
	}
	
	public Set<K> keys() {
		return values.keySet();
	}
	
	public Collection<V> values() {
		return values.values();
	}
	
	public Map<K, V> map() {
		return Collections.unmodifiableMap(values);
	}
	
	public void put(K key, V value) {
		this.values.put(key, value);
	}
	
}
