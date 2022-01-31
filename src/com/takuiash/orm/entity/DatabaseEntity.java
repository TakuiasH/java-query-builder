package com.takuiash.orm.entity;

import com.takuiash.orm.jdbc.query.helpers.column.ColumnList;

public interface DatabaseEntity {
	public ColumnList getColumnList();
}
