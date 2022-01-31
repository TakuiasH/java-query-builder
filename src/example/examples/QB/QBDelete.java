package example.examples.QB;

import java.sql.SQLException;

import com.takuiash.jqbd.query.Table;

public class QBDelete {

	public static void execute(Table table) throws SQLException {
		System.out.println("Executing delete example with QueryBuilder style \n");

		int rowsCount = table.delete().where("username", "KUMApt").execute();
		
		System.out.println("KUMApt delete | Rows affected: " + rowsCount + "\n");
	}
	
	
}
