package ua.nure.kn.hrek.usermanagement;


import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

public class UserTest extends TestCase{
	
	private final Integer leapYear = 2000;
	private final Integer notLeapYear = 2001;
	
	private User user;
	private final String firstName = "Egor";
	private final String lastName = "Grek";
	private final String fullNameCorrect = "Grek, Egor";
	private Date dateOfBirthdayCommon;
	private final Integer dateOfBirthdayCommonCorrect = 19;
	private Date dateOfBirthdayLeapYear29Feb;
	private final Integer dateOfBirthday29FebCorrect = 19;
	private Date dateOfBirthdayLeapYear28Feb;
	private final Integer dateOfBirthdayLeap28FebCorrect = 19;
	private Date dateOfBirthdayLeapYear1Mart;
	private final Integer dateOfBirthdayLeap1MartCorrect = 19;
	private Date dateOfBirthday1January;
	private final Integer dateOfBirthday1JanuaryCorrect = 19;
	private Date dateOfBirthdayNotLeapYear28Feb;
	private final Integer dateOfBirthdayNotLeap28FebCorrect = 18;
	private Date dateOfBirthdayNotLeapYear1Mart;
	private final Integer dateOfBirthdayNotLeap1MartCorrect = 18;
	private Date dateOfBirthday31December;
	private final Integer dateOfBirthday31DecemberCorrect = 18;
	
	private Date dateOfBirthdayCurrentDay;
	private Integer dateOfBirthdayCurrentDayCorrect;
	
	private Date dateOfBirthdayNextDay;
	private Integer dateOfBirthdayNextDayCorrect;
	
	private Date dateOfBirthdayPrevDay;
	private Integer dateOfBirthdayPrevDayCorrect;
	
	@BeforeEach
	public void setUp() throws Exception {
		user = new User();
		
		Calendar calendar = Calendar.getInstance();
		
		dateOfBirthdayCurrentDay = calendar.getTime();
		dateOfBirthdayCurrentDay.setYear(leapYear);
		dateOfBirthdayCurrentDayCorrect = calendar.getTime().getYear() - leapYear;
		
		dateOfBirthdayNextDay = calendar.getTime();
		dateOfBirthdayNextDay.setYear(leapYear);
		dateOfBirthdayNextDay.setDate(dateOfBirthdayCurrentDay.getDate() + 1);
		dateOfBirthdayNextDayCorrect = calendar.getTime().getYear() - leapYear - 1;
		
		dateOfBirthdayPrevDay = calendar.getTime();
		dateOfBirthdayPrevDay.setYear(leapYear);
		dateOfBirthdayPrevDay.setDate(dateOfBirthdayCurrentDay.getDate() - 1);
		dateOfBirthdayPrevDayCorrect = calendar.getTime().getYear() - leapYear;
		
		calendar.set(leapYear, Calendar.JANUARY, 11);
		dateOfBirthdayCommon = calendar.getTime();
		
		calendar.set(leapYear, Calendar.JANUARY, 1);
		dateOfBirthday1January = calendar.getTime();
		
		calendar.set(leapYear, Calendar.DECEMBER, 31);
		dateOfBirthday31December = calendar.getTime();
		
		calendar.set(leapYear, Calendar.FEBRUARY, 29);
		dateOfBirthdayLeapYear29Feb = calendar.getTime();
		
		calendar.set(leapYear, Calendar.FEBRUARY, 28);
		dateOfBirthdayLeapYear28Feb = calendar.getTime();
		
		calendar.set(leapYear, Calendar.MARCH, 1);
		dateOfBirthdayLeapYear1Mart = calendar.getTime();
		
		calendar.set(notLeapYear, Calendar.FEBRUARY, 28);
		dateOfBirthdayNotLeapYear28Feb = calendar.getTime();
		
		calendar.set(notLeapYear, Calendar.MARCH, 1);
		dateOfBirthdayNotLeapYear1Mart = calendar.getTime();
		
	}
	
	@Test
	public void testGetFullName() {
		user.setFirstName(this.firstName);
		user.setLastName(this.lastName);
		assertEquals(this.fullNameCorrect, user.getFullName());
	}
	
	@Test
	public void testGetAgeCommon() {
		user.setDateOfBirthday(dateOfBirthdayCommon);
		assertEquals(this.dateOfBirthdayCommonCorrect, user.getAge());
	}
	
	@Test
	public void testGetAge1January() {
		user.setDateOfBirthday(dateOfBirthday1January);
		assertEquals(this.dateOfBirthday1JanuaryCorrect, user.getAge());
	}
	
	@Test
	public void testGetAge31December() {
		user.setDateOfBirthday(dateOfBirthday31December);
		assertEquals(this.dateOfBirthday31DecemberCorrect, user.getAge());
	}
	
	@Test
	public void testGetAgeNotLeap28Feb() {
		user.setDateOfBirthday(this.dateOfBirthdayNotLeapYear28Feb);
		assertEquals(this.dateOfBirthdayNotLeap28FebCorrect, user.getAge());
	}
	@Test
	public void testGetAgeNotLeap1Mart() {
		user.setDateOfBirthday(this.dateOfBirthdayNotLeapYear1Mart);
		assertEquals(this.dateOfBirthdayNotLeap1MartCorrect, user.getAge());
	}
	
	@Test
	public void testGetAgeLeap28Feb() {
		user.setDateOfBirthday(this.dateOfBirthdayLeapYear28Feb);
		assertEquals(this.dateOfBirthdayLeap28FebCorrect, user.getAge());
	}
	
	@Test
	public void testGetAgeLeap1Mart() {
		user.setDateOfBirthday(this.dateOfBirthdayLeapYear1Mart);
		assertEquals(this.dateOfBirthdayLeap1MartCorrect, user.getAge());
	}
	
	@Test
	public void testGetAgeLeap29Feb() {
		user.setDateOfBirthday(this.dateOfBirthdayLeapYear29Feb);
		assertEquals(this.dateOfBirthday29FebCorrect, user.getAge());
	}
	
	@Test
	public void testGetAgeCurrentDay() {
		user.setDateOfBirthday(this.dateOfBirthdayCurrentDay);
		assertEquals(this.dateOfBirthdayCurrentDayCorrect, user.getAge());
	}
	
	@Test
	public void testGetAgeNextDay() {
		user.setDateOfBirthday(this.dateOfBirthdayNextDay);
		assertEquals(this.dateOfBirthdayNextDayCorrect, user.getAge());
	}
	
	@Test
	public void testGetAgePrevDay() {
		user.setDateOfBirthday(this.dateOfBirthdayPrevDay);
		assertEquals(this.dateOfBirthdayPrevDayCorrect, user.getAge());
	}
	
	
}
