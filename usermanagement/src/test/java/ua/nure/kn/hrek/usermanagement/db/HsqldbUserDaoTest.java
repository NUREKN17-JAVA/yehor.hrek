package ua.nure.kn.hrek.usermanagement.db;

import java.util.Collection;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

import ua.nure.kn.hrek.usermanagement.User;

class HsqldbUserDaoTest extends DatabaseTestCase {

	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;
	
	private final Date DATE_OF_BIRTH_UPDATE2 = new Date(2000-01-11);
	private final Date DATE_OF_BIRTH_UPDATE = new Date(2000-02-29);
	private final String LAST_NAME_UPDATE2 = "Egor";
	private final String FIRST_NAME_UPDATE2 = "Grek";
	private final String FIRST_NAME2 = "Bill";
	private final String LAST_NAME2 = "Gates";
	private final long ID = 1000L;

	private final String LAST_NAME_UPDATE = "Gates";
	private final String FIRST_NAME_UPDATE = "Bill";
	private final String LAST_NAME = "Due";
	private final String FIRST_NAME = "John";

	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();
		dao = new HsqldbUserDao(connectionFactory);
	}

	@Test
	void testCreate() {
		try {
			User user = new User();
			user.setFirstName(LAST_NAME_UPDATE2);
			user.setLastName(FIRST_NAME_UPDATE2);

			user.setDateOfBirth(new Date());
			assertNull(user.getId());

			user = dao.create(user);

			assertNotNull(user);
			assertNotNull(user.getId());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testFindAll() {
		try {
			Collection collection = dao.findAll();
			assertNotNull("Collection is null", collection);
			assertEquals("Collection size.", 2, collection.size());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	@Test
	public void testUpdate() throws DatabaseException{
		User user = new User();
		user.setFirstName(FIRST_NAME_UPDATE);
		user.setLastName(LAST_NAME_UPDATE);
		user.setDateOfBirth(DATE_OF_BIRTH_UPDATE);
		User userToChek = dao.create(user);
		assertEquals(FIRST_NAME_UPDATE,userToChek.getFirstName());
		assertEquals(LAST_NAME_UPDATE,userToChek.getLastName());
		assertEquals(DATE_OF_BIRTH_UPDATE,userToChek.getDateOfBirth());
		userToChek.setFirstName(FIRST_NAME_UPDATE2);
		userToChek.setLastName(LAST_NAME_UPDATE2);
		userToChek.setDateOfBirth(DATE_OF_BIRTH_UPDATE2);
		dao.update(userToChek);
		assertEquals(FIRST_NAME_UPDATE2,userToChek.getFirstName());
		assertEquals(LAST_NAME_UPDATE2,userToChek.getLastName());
		assertEquals(DATE_OF_BIRTH_UPDATE2,userToChek.getDateOfBirth());

	}
	
	@Test
	public void testDelete() throws DatabaseException{
		User user = new User();
		user.setFirstName(FIRST_NAME_UPDATE);
		user.setLastName(LAST_NAME_UPDATE);
		user.setDateOfBirth(DATE_OF_BIRTH_UPDATE);
		User userToChek = dao.create(user);
		assertNotNull(userToChek.getId());
		dao.delete(userToChek);
		User user2 = dao.find(userToChek.getId());
		assertNotNull(user2);
		assertNotNull(user2.getId());
	}
	
	@Test
	public void testFind() throws DatabaseException{
		User user = dao.find(ID);
		assertEquals("Wrong LAST_NAME_UPDATE2",LAST_NAME2, user.getLastName());
		assertEquals("Wrong FIRST_NAME_UPDATE2",FIRST_NAME2, user.getFirstName());

	}

	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory = new ConnectionFactoryImpl("org.hsqldb.jdbcDriver", "jdbc:hsqldb:file:db/usermanagement",
				"sa", "");
		return new DatabaseConnection(connectionFactory.createConnection());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
		return dataSet;
	}

}
