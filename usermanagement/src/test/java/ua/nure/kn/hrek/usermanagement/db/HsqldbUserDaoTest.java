package ua.nure.kn.hrek.usermanagement.db;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import ua.nure.kn.hrek.usermanagement.User;

class HsqldbUserDaoTest extends TestCase {

	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;
	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();
		connectionFactory = new ConnectionFactoryImpl();
		dao = new HsqldbUserDao(connectionFactory);
	
	}

	@Test
	void testCreate() {
		try {
			User user = new User();
			user.setFirstName("Egor");
			user.setLastName("Grek");
			
			user.setDateOfBirthday(new Date());
			assertNull(user.getId());
			
			user = dao.create(user);
			
			assertNotNull(user);
			assertNotNull(user.getId());
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.toString());
		}
	}

}
