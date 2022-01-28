package com.thdev.jqbd.query.builders;

public class FieldBuilder implements Builder {

	private String[] fields = new String[0];
	
	public String[] getFields() {
		return fields;
	}
	
	public void setFields(String... fields) {
		this.fields = fields;
	}
	
	
	/*
	 * Returns: 
	 * " Test, Test2 FROM " or
	 * " * FROM "
	 */
	public String build() {
		if(fields.length == 0)
			return "*";
		
		String response = "";
		
		for(String field : fields) {
			response += field + ", ";
		}
				
		return response.substring(0, response.length() - 2);
	}

	
}
