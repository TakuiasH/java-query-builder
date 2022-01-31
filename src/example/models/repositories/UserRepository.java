package example.models.repositories;


import example.models.entities.UserEntity;

import java.sql.Connection;

import com.takuiash.orm.repository.AbstractRepository;
import com.takuiash.orm.table.Table;

public class UserRepository extends AbstractRepository<UserEntity> {

	public UserRepository(Connection connection) {
		super(new Table(connection, "Test_Table"), new UserEntity());
	}

}
