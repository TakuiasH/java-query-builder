package com.takuiash.jqbd.query.helpers.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public interface ObjectMap<K, V> {

	public V get(K key);
	public int size();
	public Map<K, V> map();
	public void put(K key, V value);
	public Set<Entry<K, V>> entrySet();
	public Set<K> keys();
	public Collection<V> values();

}
