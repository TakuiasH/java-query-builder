package example.examples.delete;

import java.sql.SQLException;

import com.takuiash.jqbd.table.Table;

public class QBDelete {

	public static void execute(Table table) throws SQLException {
		System.out.println("Executing delete example with QueryBuilder style \n");

		int rowsCount = table.delete().where("username", "KUMApt").execute();
		
		System.out.println("KUMApt delete | Rows affected: " + rowsCount + "\n");
	}
	
	
}
