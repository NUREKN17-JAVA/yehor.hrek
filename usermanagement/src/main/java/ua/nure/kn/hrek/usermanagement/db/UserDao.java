package ua.nure.kn.hrek.usermanagement.db;

import java.util.Collection;

import ua.nure.kn.hrek.usermanagement.User;

public interface UserDao {
	User create(User user) throws DatabaseException;
	
	void update(User user) throws DatabaseException;
	
	void dalete(User user) throws DatabaseException;
	
	User find(Long id) throws DatabaseException;
	
	Collection findAll() throws DatabaseException;
	
	void setConnectionFactory(ConnectionFactory connectionFactory);
}
