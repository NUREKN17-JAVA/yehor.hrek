package ua.nure.kn.hrek.usermanagement.agent;

import jade.core.Agent;

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

}
