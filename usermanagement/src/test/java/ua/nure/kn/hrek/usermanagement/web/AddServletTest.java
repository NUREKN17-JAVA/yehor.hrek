package ua.nure.kn.hrek.usermanagement.web;

import java.text.DateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.nure.kn.hrek.usermanagement.User;

class AddServletTest extends MockServletTestCase {


	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();
		createServlet(AddServlet.class);
	}

	@Test
	void testAdd() {
		Date date = new Date();
		User user = new User(new Long(1000), "John", "Doe", date);
		User newUser = new User("John", "Doe", date);
		getMockUserDao().expectAndReturn("create", newUser, user);


		addRequestParameter("firstName", "John");
		addRequestParameter("lastName", "Doe");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
	}
	
	@Test
	void testAddEmptyFirstName() {
		Date date = new Date();
		
		addRequestParameter("lastName", "Doe");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		
		assertNotNull("Could not find error message in session scope", errorMessage);
	}
	
	@Test
	void testAddEmptyLastName() {
		Date date = new Date();
		
		addRequestParameter("firstName", "John");
		
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		
		assertNotNull("Could not find error message in session scope", errorMessage);
	}
	
	@Test
	void testAddEmptyDate() {
		addRequestParameter("firstName", "John");
		addRequestParameter("lastName", "Doe");
		
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		
		assertNotNull("Could not find error message in session scope", errorMessage);
	}
	
	@Test
	void testAddDateIncorrect() {
		addRequestParameter("firstName", "John");
		addRequestParameter("lastName", "Doe");
		addRequestParameter("date", "dawwdwa");
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		
		assertNotNull("Could not find error message in session scope", errorMessage);
	}

}
