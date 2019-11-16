package ua.nure.kn.hrek.usermanagement.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import ua.nure.kn.hrek.usermanagement.User;

public class HsqldbUserDao implements UserDao {

	private ConnectionFactory connectionFactory;

	private String INSERT_QUERY = "INSERT INTO users (firstname, lastname, dateofbirth) VALUES(?, ?, ?);";

	public HsqldbUserDao(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public User create(User user) throws DatabaseException {

		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setDate(3, (Date) user.getDateOfBirthday());
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
		// TODO Auto-generated method stub
		return null;
	}

}
