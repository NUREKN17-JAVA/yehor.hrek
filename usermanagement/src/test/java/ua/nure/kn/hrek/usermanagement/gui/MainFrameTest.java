package ua.nure.kn.hrek.usermanagement.gui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;

class MainFrameTest extends JFCTestCase {

	private MainFrame mainFrame;

	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();
		setHelper(new JFCTestHelper());
		mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}

	@AfterEach
	protected void tearDown() throws Exception {
		mainFrame.setVisible(false);
		getHelper().cleanUp(this);;
		super.tearDown();
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
