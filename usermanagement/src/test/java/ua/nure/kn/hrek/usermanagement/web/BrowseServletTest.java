package ua.nure.kn.hrek.usermanagement.web;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.nure.kn.hrek.usermanagement.User;

class BrowseServletTest extends MockServletTestCase {

	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();
		createServlet(BrowseServlet.class);
	}

	@Test
	void testBrowse() {
		User user = new User(new Long(1000), "John", "Doe", new Date());
		List list = Collections.singletonList(user);
		getMockUserDao().expectAndReturn("findAll", list);
		doGet();
		Collection collection = (Collection)getWebMockObjectFactory().getMockSession().getAttribute("users");
		assertNotNull("Could not find list of users in session", collection);
		assertSame(list, collection);
		
	}

}
