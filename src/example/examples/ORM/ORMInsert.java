package example.examples.ORM;

import com.takuiash.jqbd.connector.Connector;
import com.takuiash.jqbd.orm.Repository;

import example.models.User;

public class ORMInsert {

	public static void execute(Connector connector) {
		System.out.println("Executing insert example with ORM style \n");
		Repository<User> repo = new Repository<User>(new User(), connector);

		User user1 = repo.insert(new User("TakuiasH")); //Return the entity with all arguments passed. (Primary keys, Defaults...)
		System.out.println("User 1: " + user1.getId() + " : " + user1.getUsername() + " : " + user1.getLocale() + "\n");
		
		User user2 = repo.insert(new User("ArantesXYZ", "pt"));
		System.out.println("User 2: " + user2.getId() + " : " + user2.getUsername() + " : " + user2.getLocale() + "\n");
	}
	
}
