package com.thdev.jqbd.query.condition;

public enum ConditionType {

	WHERE("WHERE"),
	WHERE_NOT("WHERE NOT"),
	OR ("OR"),
	OR_NOT ("OR NOT"),
	CUSTOM ("CUSTOM");
	
	private final String tag;
	
	private ConditionType(String tag) {
		this.tag = tag;
	}
	
	public String getTag() {
		return tag;
	}
}
