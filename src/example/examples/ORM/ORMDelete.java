package example.examples.ORM;

import com.takuiash.jqbd.connector.Connector;
import com.takuiash.jqbd.orm.Repository;

import example.models.User;

public class ORMDelete {

	public static void execute(Connector connector) {
		System.out.println("Executing delete example with ORM style \n");
		Repository<User> repo = new Repository<User>(new User(), connector);
		
		int rowsCount = repo.delete(new User("Freizao"));
		System.out.println("Freizao delete | Rows affected: " + rowsCount + "\n");
	}
	
}
