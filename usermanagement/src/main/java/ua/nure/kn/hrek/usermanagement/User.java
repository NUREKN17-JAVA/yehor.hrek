package ua.nure.kn.hrek.usermanagement;

import java.util.Calendar;
import java.util.Date;

public class User {
	private Long id;
	private String firstName;
	private String lastName;
	private Date dateOfBirthday;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirthday() {
		return dateOfBirthday;
	}
	public void setDateOfBirthday(Date dateOfBirthday) {
		this.dateOfBirthday = dateOfBirthday;
	}
	
	
	
	public String getFullName() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.lastName);
		sb.append(", ");
		sb.append(this.firstName);
		String fullName = sb.toString();
		
		return fullName;
	}
	public Integer getAge() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		Integer currentYear = calendar.get(Calendar.YEAR);
		Integer currentMonth = calendar.get(Calendar.MONTH);
		Integer currentDay = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.setTime(dateOfBirthday);
		Integer year = calendar.get(Calendar.YEAR);
		Integer month = calendar.get(Calendar.MONTH);
		Integer day = calendar.get(Calendar.DAY_OF_MONTH); // берём день месяца, а не года,
															// из за высокостных годов.
		
		Integer age = currentYear - year;
		
		if(month > currentMonth) {
			age = age - 1;
		}else if (month.equals(currentMonth)) {
			if(day > currentDay) {
				age = age - 1;
			}
		}
		
		return age;
	}
	
	
}
