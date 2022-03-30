package example.models;

import com.takuiash.jqbd.orm.Entity;
import com.takuiash.jqbd.query.helpers.Option;
import com.takuiash.jqbd.query.helpers.Row;
import com.takuiash.jqbd.query.helpers.TableSetup;
import com.takuiash.jqbd.query.helpers.Row.FieldType;

public class User implements Entity {

	private int id;
	private String username;
	private String locale;

	public User() {}
	
	public User(String username, String locale) {
		this.username = username;
		this.locale = locale;
	}

	public User(String username) {
		this.username = username;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getLocale() {
		return locale;
	}
	
	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	public TableSetup getSetup() {
		TableSetup setup = new TableSetup("users");
		
		setup.addRow(new Row("id", FieldType.INT).addOptions(Option.PRIMARY, Option.AUTO_INCREMENT));
		setup.addRow(new Row("username", FieldType.VARCHAR).addOptions(Option.UNIQUE));
		setup.addRow(new Row("locale", FieldType.VARCHAR).setDefault("en"));
		
		return setup;
	}
}
