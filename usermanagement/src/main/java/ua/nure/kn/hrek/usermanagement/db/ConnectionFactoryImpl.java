package ua.nure.kn.hrek.usermanagement.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryImpl implements ConnectionFactory {
	
	private String url;
	private String user;
	private String password;
	private String driver;
	
	public ConnectionFactoryImpl(String driver, String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
		this.driver = driver;
	}

	@Override
	public Connection createConnection() throws DatabaseException {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DatabaseException(e);
		}
	}

}
