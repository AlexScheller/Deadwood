package controller;

import view.*;
import model.*;

public class DeadwoodController implements ViewListener {

	// private DeadwoodView view;
	private DeadwoodGame model;

	public DeadwoodController(DeadwoodView view, DeadwoodGame model) {
		// this.view = view;
		this.model = model;
		view.setListener(this);
	}

	public void playerActs() {
		System.out.println("Player: " + model.getCurrentPlayer().getName() + " acts");
		// model.getCurrentPlayer().act();
	}

	public void playerRehearses() {
		System.out.println("Player: " + model.getCurrentPlayer().getName() + " rehearses");
		// model.getCurrentPlayer().rehearse();
	}

	public void playerMovesTo(String where) {
		model.getCurrentPlayer().move(where);
	}

	public void playerDoesNothing() {
		model.nextTurn();
	}

	public void playerTakesRole(String which) {
		System.out.println("Player: " + model.getCurrentPlayer().getName() + " takes roll: " + which);
		// model.getCurrentPlayer().takeRole(which);
	}

	public void playerUpgrades(int level) {
		System.out.println("Player: " + model.getCurrentPlayer().getName() + " upgrades");
		// try {
		// 	model.getCurrentPlayer().upgrade(level);
		// } catch (IllegalStateException e) {
		// 	System.out.println(e.getMessage());
		// }
	}

}