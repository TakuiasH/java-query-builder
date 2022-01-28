package com.thdev.jqbd.query.table;

import com.thdev.jqbd.Database;
import com.thdev.jqbd.query.builders.VarBuilder;
import com.thdev.jqbd.query.functions.Delete;
import com.thdev.jqbd.query.functions.Drop;
import com.thdev.jqbd.query.functions.Insert;
import com.thdev.jqbd.query.functions.Select;
import com.thdev.jqbd.query.functions.Truncate;
import com.thdev.jqbd.query.functions.Update;
import com.thdev.jqbd.query.set.SET;
import com.thdev.jqbd.query.set.SetMap;

public class Table {

	private final Database database;
	private final String name;
	
	private final VarBuilder varBuilder;
	
	public Table(Database database, String name) {
		this.name = name;
		this.database = database;
		this.varBuilder = new VarBuilder(this, database.getConnectionType());
	}
	
	public String getName() {
		return name;
	}
	
	public Database getDatabase() {
		return database;
	}
	
	public VarBuilder create() {
		return varBuilder;
	}
	
	public Select select() { return new Select(database, this); }
	public Select select(String... fields) { return new Select(database, this).field(fields); }
	
	public Delete delete() { return new Delete(database, this); }
	
	public Update update(String column, Object value) { return new Update(database, this).set(column, value); }
	public Update update(SET... sets) { return new Update(database, this).set(sets); }
	public Update update(SetMap sets) { return new Update(database, this).set(sets); }

	public Insert insert(String column, Object value) { return new Insert(database, this).set(column, value); }
	public Insert insert(SET... sets) { return new Insert(database, this).set(sets); }
	public Insert insert(SetMap sets) { return new Insert(database, this).set(sets); }

	public Drop drop() { return new Drop(database, this); }

	public Truncate truncate() { return new Truncate(database, this); }

}
