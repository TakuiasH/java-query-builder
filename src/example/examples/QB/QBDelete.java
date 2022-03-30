package example.examples.QB;

import com.takuiash.jqbd.query.Table;
import com.takuiash.jqbd.query.helpers.Column;

public class QBDelete {

	public static void execute(Table table) {
		System.out.println("Executing delete example with QueryBuilder style \n");

		int rowsCount = table.delete().where(Column.as("username", "KUMApt")).execute();
		
		System.out.println("KUMApt delete | Rows affected: " + rowsCount + "\n");
	}
	
	
}
