package ua.nure.kn.hrek.usermanagement.agent;

import java.util.Collection;

import jade.core.AID;
import jade.core.Agent;
import ua.nure.kn.hrek.usermanagement.db.DaoFactory;
import ua.nure.kn.hrek.usermanagement.db.DatabaseException;

public class SearchAgent extends Agent {

	@Override
	protected void setup() {
		super.setup();
		System.out.println(getAID().getName() + " started.");
	}

	@Override
	protected void takeDown() {
		System.out.println(getAID().getName() + " terminated.");
		super.takeDown();
	}
	
	public void search(String firstName, String lastName) throws SearchException{
		try {
			Collection users = DaoFactory.getInstance().getUserDao().find(firstName, lastName);
			if(users.size() > 0) {
				showUsers(users);
			}else {
				addBehaviour(new SearchRequestBehaviour(new AID[] {}, firstName, lastName));
			}
		}catch(DatabaseException e){
			throw new SearchException(e);
		}
	}
	
	private void showUsers(Collection users) {
		//TODO отобразить найденных пользователей
	}

}
