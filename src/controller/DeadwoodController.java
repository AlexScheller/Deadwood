package controller;

import model.DeadwoodModel;

import view.DeadwoodView;
import view.ViewListener;

/*
 * Design Notes:
 * 
 * The current design assumes all input from the view should be valid,
 * therefore, if any exception caused by bad input is caught by the
 * controller the program will terminate, as it is considered a bug on
 * the view's end. 
 *
 */

public class DeadwoodController implements ViewListener {

	private DeadwoodModel model;

	public DeadwoodController(DeadwoodView view) {//, DeadwoodModel model) {//, boolean logging) {
		// this.view = view;
		this.model = null;
		view.setListener(this);
	}
	
	// TODO: When GUI is implemented log these to a file,
	// rather than printing them to stdout
	private void exitOnException(Exception e) {
		System.out.println("Error: " + e.getMessage());
		e.printStackTrace();
		System.out.println("Exiting...");
		System.exit(1);
	}

	// public void newGameRequest(int numPlayers) {
	// 	if (model == null) {
	// 		try {
	// 			model = GameLoader.getInstance().loadGame(numPlayers);
	// 		} catch (Exception e) {
	// 			exitOnException(e);
	// 		}
	// 	}
	// }

	public void playerActRequest() {
		System.out.println("Player " + (model.getCurrentPlayer().getID() + 1) + " acts");
		try {
			// model.getCurrentPlayer().act();
			model.playerActs();
		} catch (Exception e) {
			exitOnException(e);
		}
	}

	public void playerRehearseRequest() {
		System.out.println("Player " + (model.getCurrentPlayer().getID() + 1) + " rehearses");
		try {
			// model.getCurrentPlayer().rehearse();
			model.playerRehearses();
		} catch (Exception e) {
			exitOnException(e);
		}
	}

	public void playerMoveRequest(String where) {
		try {
			// model.getCurrentPlayer().move(where);
			model.playerMoves(where);
		} catch (Exception e) {
			exitOnException(e);
		}
	}

	// public void playerDoesNothing() {
	// 	// model.nextTurn();
	// }

	public void playerTakeRoleRequest(String which) {
		System.out.println("Player " + (model.getCurrentPlayer().getID() + 1) + " takes roll: " + which);
		try {
			// model.getCurrentPlayer().takeRole(which);
			model.playerTakesRole(which);
			// model.nextTurn();
		} catch (Exception e) {
			exitOnException(e);
		}
	}
	
	public void playerUpgradeRequest(int rank, String currency) {
		try {
			model.playerUpgrades(rank, currency);
		} catch (IllegalStateException e) {
			System.out.println(e.getMessage());
		}
	}

}