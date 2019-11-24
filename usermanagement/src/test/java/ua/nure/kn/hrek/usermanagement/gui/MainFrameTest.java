package ua.nure.kn.hrek.usermanagement.gui;

import java.awt.Component;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mockobjects.dynamic.Mock;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import ua.nure.kn.hrek.usermanagement.User;
import ua.nure.kn.hrek.usermanagement.db.DaoFactory;
import ua.nure.kn.hrek.usermanagement.db.DaoFactoryImpl;
import ua.nure.kn.hrek.usermanagement.db.MockDaoFactory;
import ua.nure.kn.hrek.usermanagement.db.MockUserDao;

class MainFrameTest extends JFCTestCase {

	private MainFrame mainFrame;

	private Mock mockUserDao;
	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();

		Properties properties = new Properties();
		properties.setProperty("dao.factory", MockDaoFactory.class.getName());
		DaoFactory.init(properties);
		mockUserDao = ((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao();
		
		mockUserDao.expectAndReturn("findAll", new ArrayList());
		setHelper(new JFCTestHelper());
		mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}

	@AfterEach
	protected void tearDown() throws Exception {
		try {
			mockUserDao.verify();
			mainFrame.setVisible(false);
			getHelper().cleanUp(this);
			super.tearDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Component find(Class componentClass, String name) {
		NamedComponentFinder finder;
		finder = new NamedComponentFinder(componentClass, name);
		finder.setWait(0);
		Component component = finder.find(mainFrame, 0);
		assertNotNull("Could not find component '" + name + "'", component);

		return component;
	}

	@Test
	public void testBrowseControls() {
		find(JPanel.class, "browsePanel");
		JTable table = (JTable) find(JTable.class, "userTable");
		assertEquals(3, table.getColumnCount());
		assertEquals("ID", table.getColumnName(0));
		assertEquals("Имя", table.getColumnName(1));
		assertEquals("Фамилия", table.getColumnName(2));

		find(JButton.class, "addButton");
		find(JButton.class, "editButton");
		find(JButton.class, "deleteButton");
		find(JButton.class, "detailsButton");
	}

	@Test
	public void testAddUser() {
		try {
			String firstName = "John";
			String lastName = "Doe";
			Date currentDate = new Date();
			
			User user = new User(firstName, lastName, currentDate);
			
			User expectedUser = new User(new Long(1), firstName, lastName, currentDate);
			
			mockUserDao.expectAndReturn("create", user, expectedUser);
			
			ArrayList users = new ArrayList();
			users.add(expectedUser);
			mockUserDao.expectAndReturn("findAll", users);
			
			
			JTable table = (JTable) find(JTable.class, "userTable");
			assertEquals(0, table.getRowCount());
			JButton addButton = (JButton) find(JButton.class, "addButton");

			getHelper().enterClickAndLeave(new MouseEventData(this, addButton));

			find(JPanel.class, "addPanel");

			JTextField firstNameField = (JTextField) find(JTextField.class, "firstNameField");
			JTextField lastNameField = (JTextField) find(JTextField.class, "lastNameField");
			JTextField dateOfBirthField = (JTextField) find(JTextField.class, "dateOfBirthField");
			JButton okButton = (JButton) find(JButton.class, "okButton");
			JButton cancelButton = (JButton) find(JButton.class, "cancelButton");

			
			getHelper().sendString(new StringEventData(this, firstNameField, firstName));
			getHelper().sendString(new StringEventData(this, lastNameField, lastName));
			DateFormat formatter = DateFormat.getDateInstance();
			String date = formatter.format(currentDate);
			getHelper().sendString(new StringEventData(this, dateOfBirthField, date));

			getHelper().enterClickAndLeave(new MouseEventData(this, okButton));

			find(JPanel.class, "browsePanel");
			table = (JTable) find(JTable.class, "userTable");
			assertEquals(1, table.getRowCount());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
