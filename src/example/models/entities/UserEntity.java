package example.models.entities;

import com.takuiash.jqbd.entity.DatabaseEntity;
import com.takuiash.jqbd.jdbc.query.helpers.column.Column;
import com.takuiash.jqbd.jdbc.query.helpers.column.ColumnList;
import com.takuiash.jqbd.jdbc.query.helpers.column.Option;

public class UserEntity implements DatabaseEntity {

	private int id;
	private String username;
	private String locale;

	public UserEntity() {}
	
	public UserEntity(String username, String locale) {
		this.username = username;
		this.locale = locale;
	}

	public UserEntity(String username) {
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
	
	public ColumnList getColumnList() {
		ColumnList columns = new ColumnList();
		
		columns.add(new Column("id").addOption(Option.NOT_NULL).addOption(Option.PRIMARY));
		columns.add(new Column("username").addOption(Option.NOT_NULL));
		columns.add(new Column("locale").setDefault("en"));
		
		return columns;
	}
}
