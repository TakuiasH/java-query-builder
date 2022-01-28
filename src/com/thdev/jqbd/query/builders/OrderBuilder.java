package com.thdev.jqbd.query.builders;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class OrderBuilder implements Builder {

	private Map<String, OrderType> orders = new HashMap<>();
	
	public Map<String, OrderType> getOrders() {
		return Collections.unmodifiableMap(orders);
	}
	
	public void add(String column, OrderType type) {
		orders.put(column, type);
	}
	
	public void add(String... columns) {
		for(String column : columns) {
			orders.put(column, OrderType.ASC);
		}
	}
	
	public String build() {		
		if(orders.isEmpty())
			return "";
		
		String response = " ORDER BY ";
		
		for(Entry<String, OrderType> order : orders.entrySet()) {
			if(order.getValue() == OrderType.DESC)
				response += order.getKey() + " " + order.getValue().toString() + ", ";
			else
				response += order.getKey() + ", ";
		}
				
		return response.substring(0, response.length() - 2);
	}

}
