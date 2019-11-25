package ua.nure.kn.hrek.usermanagement.agent;

import ua.nure.kn.hrek.usermanagement.db.DatabaseException;

public class SearchException extends Exception {

	public SearchException(DatabaseException e) {
		super(e);
	}

}
