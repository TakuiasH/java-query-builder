package com.thdev.jqbd.query.builders;

import java.util.ArrayList;
import java.util.List;

import com.thdev.jqbd.ConnectionType;
import com.thdev.jqbd.query.table.Table;
import com.thdev.jqbd.query.var.Variable;

public class VarBuilder implements Builder {

	private final Table table;
	private final ConnectionType connectionType;

	List<Variable> variables = new ArrayList<Variable>();
	
	public VarBuilder(Table table, ConnectionType connectionType) {
		this.table = table;
		this.connectionType = connectionType;
	}
	
	public VarBuilder add(Variable var) {
		variables.add(var);
		return this;
	}
	
	public void execute() {
		String result = "CREATE TABLE IF NOT EXISTS `" + table.getName() + "` (" + build() + ");";
		table.getDatabase().executeUpdate(result);
	}
	
	@Override
	public String build() {
		if(variables.size() == 0)
			throw new NullPointerException("The variables cannot be empty");
		
		String response = "";
		
		for(Variable var : variables) {
			response += var.build(connectionType) + ", ";
		}
				
		return response.substring(0, response.length() - 2);
	}

}
