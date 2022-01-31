package example.models.repositories;


import example.models.entities.UserEntity;

import java.sql.Connection;

import com.takuiash.jqbd.orm.AbstractRepository;
import com.takuiash.jqbd.query.Table;

public class UserRepository extends AbstractRepository<UserEntity> {

	public UserRepository(Connection connection) {
		super(new Table(connection, "Test_Table"), new UserEntity());
	}

}
