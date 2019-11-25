package ua.nure.kn.hrek.usermanagement.web;

import java.util.Properties;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mockobjects.dynamic.Mock;
import com.mockrunner.servlet.BasicServletTestCaseAdapter;

import ua.nure.kn.hrek.usermanagement.db.DaoFactory;
import ua.nure.kn.hrek.usermanagement.db.MockDaoFactory;


public abstract class MockServletTestCase extends BasicServletTestCaseAdapter {

	private Mock mockUserDao;
	
	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();
		Properties properties = new Properties();
		properties.setProperty("dao.factory", MockDaoFactory.class.getName());
		DaoFactory.init(properties);
		setMockUserDao(((MockDaoFactory)DaoFactory.getInstance()).getMockUserDao());
	}

	@AfterEach
	protected void tearDown() throws Exception {
		getMockUserDao().verify();
		super.tearDown();
	}

	public Mock getMockUserDao() {
		return mockUserDao;
	}

	public void setMockUserDao(Mock mockUserDao) {
		this.mockUserDao = mockUserDao;
	}
	
}
