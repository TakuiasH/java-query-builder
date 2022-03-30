package example.examples.ORM;

import com.takuiash.jqbd.connector.Connector;
import com.takuiash.jqbd.orm.Repository;
import com.takuiash.jqbd.query.helpers.Column;

import example.models.User;

public class ORMSelect {

	public static void execute(Connector connector) {
		System.out.println("Executing select example with ORM style \n");
		Repository<User> repo = new Repository<User>(new User(), connector);

		User user = repo.select().where(Column.as("username", "TakuiasH")).first();
		System.out.println("User: " + user.getId() + " : " + user.getUsername() + " : " + user.getLocale() + "\n");
	}
	
}
