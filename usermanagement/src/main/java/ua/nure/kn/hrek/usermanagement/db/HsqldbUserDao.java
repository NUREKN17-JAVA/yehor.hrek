package ua.nure.kn.hrek.usermanagement.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import ua.nure.kn.hrek.usermanagement.User;

public class HsqldbUserDao implements UserDao {

	private ConnectionFactory connectionFactory;

	private String INSERT_QUERY = "INSERT INTO users (firstname, lastname, dateofbirth) VALUES(?, ?, ?);";
	private String SELECT_ALL_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users;";
	
	public HsqldbUserDao(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public User create(User user) throws DatabaseException {

		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setDate(3, new java.sql.Date(user.getDateOfBirth().getTime()));
			int n = statement.executeUpdate();
			if(n != 1) {
				throw new DatabaseException("Number of the inserrted rows: " + n);
			}
			CallableStatement callebleStatement = connection.prepareCall("call IDENTITY()");
			ResultSet keys = callebleStatement.executeQuery();
			if(keys.next()) {
				user.setId(new Long(keys.getLong(1)));
			}
			keys.close();
			callebleStatement.close();
			statement.close();
			connection.close();
			return user;
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	public void update(User user) throws DatabaseException {
		// TODO Auto-generated method stub

	}

	public void dalete(User user) throws DatabaseException {
		// TODO Auto-generated method stub

	}

	public User find(Long id) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection findAll() throws DatabaseException {
		Collection result = new LinkedList();
		
		try {
			Connection connection = connectionFactory.createConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
			while(resultSet.next()) {
				User user = new User();
				user.setId(new Long(resultSet.getLong(1)));
				user.setLastName(resultSet.getString(2));
				user.setFirstName(resultSet.getString(3));
				user.setDateOfBirth(resultSet.getDate(4));
				result.add(user);
			}
		} catch (DatabaseException e) {
			throw e;
		}catch (SQLException e) {
			throw new DatabaseException(e);
		}
		return result;
	}

}
