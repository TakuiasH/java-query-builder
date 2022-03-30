package example.examples.ORM;

import com.takuiash.jqbd.connector.Connector;
import com.takuiash.jqbd.orm.Repository;
import com.takuiash.jqbd.query.helpers.Column;

import example.models.User;

public class ORMUpdate {
	
	public static void execute(Connector connector) {
		System.out.println("Executing update example with ORM style \n");
		Repository<User> repo = new Repository<User>(new User(), connector);

		User updateUser = new User();
		updateUser.setLocale("es");
		
		User user = repo.update(updateUser).where(Column.as("username", "ArantesXYZ")).executeAndSelect();
		System.out.println("User updated: " + user.getId() + " : " + user.getUsername() + " : " + user.getLocale() + "\n");

	}
	
}
