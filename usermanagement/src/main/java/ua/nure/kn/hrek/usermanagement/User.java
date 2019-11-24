package ua.nure.kn.hrek.usermanagement;

import java.util.Calendar;
import java.util.Date;

public class User {
	private Long id;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;

	public User(String firstName2, String lastName2, Date currentDate) {
		firstName = firstName2;
		lastName = lastName2;
		dateOfBirth = currentDate;
	}

	public User(Long id2, String firstName2, String lastName2, Date currentDate) {
		id = id2;
		firstName = firstName2;
		lastName = lastName2;
		dateOfBirth = currentDate;
	}

	public User() {
	}

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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirthday) {
		this.dateOfBirth = dateOfBirthday;
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
		calendar.setTime(dateOfBirth);
		Integer year = calendar.get(Calendar.YEAR);
		Integer month = calendar.get(Calendar.MONTH);
		Integer day = calendar.get(Calendar.DAY_OF_MONTH); // берём день месяца, а не года,
															// из за высокостных годов.

		Integer age = currentYear - year;

		if (month > currentMonth) {
			age = age - 1;
		} else if (month.equals(currentMonth)) {
			if (day > currentDay) {
				age = age - 1;
			}
		}

		return age;
	}

	@Override
	public int hashCode() {
		if(this.getId() == null) {
			return 0;
		}
		return this.getId().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if(this.getId() == null && ((User)obj).getId() == null) {
			return true;
		}
		
		return this.getId().equals(((User)obj).getId());
	}

}
