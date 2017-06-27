package model.board;

import model.board.CardInfo;

public class Card {

	private int id;
	private int budget;
	private int number; // necessary?
	private String title;
	private String description;
	// private Set<Role> roles;

	public Card(CardInfo ci) {
		this.budget = ci.budget;
		this.id = ci.id;
		this.number = ci.number; // necessary?
		this.title = ci.title;
		this.description = ci.description;
		// this.roles = ci.roles;
	}

	public String toString() {
		return title + ": " + description + " Budget: " + Integer.toString(budget);
	}

}