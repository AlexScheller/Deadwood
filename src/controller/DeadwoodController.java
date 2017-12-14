package controller;

import model.DeadwoodModel;
import model.ModelListener;
import model.loading.GameLoader;

import view.DeadwoodView;
import view.ViewListener;

public class DeadwoodController implements ViewListener {

	private DeadwoodModel model;
	private DeadwoodView view;

	private ControllerListener listener;

	public DeadwoodController(DeadwoodView view) {//, DeadwoodModel model) {//, boolean logging) {
		this.model = null;
		this.view = view;
		view.setListener(this);
	}

	private void exitOnException(Exception e) {
		System.out.println("Error: " + e.getMessage());
		e.printStackTrace();
		System.out.println("Exiting...");
		System.exit(1);
	}

	public void newGameRequest(int numPlayers) {
		if (model == null) {
			try {
				model = GameLoader.getInstance().loadGame(numPlayers);
				model.setListener(view);
				this.listener = model;
				listener.play();
				// model.play();
			} catch (Exception e) {
				exitOnException(e);
			}
		}
	}

	// PROTOTYPE_CHAIN: 2
	public void endTurnRequest() {
		listener.playerEndsTurn();
	}

	public void playerActRequest() {
		System.out.println("Player " + (model.getCurrentPlayer().getId() + 1) + " acts");
		try {
			// model.getCurrentPlayer().act();
			listener.playerActs();
		} catch (Exception e) {
			exitOnException(e);
		}
	}

	public void playerRehearseRequest() {
		System.out.println("Player " + (model.getCurrentPlayer().getId() + 1) + " rehearses");
		try {
			// model.getCurrentPlayer().rehearse();
			listener.playerRehearses();
		} catch (Exception e) {
			exitOnException(e);
		}
	}

	public void playerMoveRequest(String where) {
		System.out.println("player move request intercepted");
		try {
			// model.getCurrentPlayer().move(where);
			listener.playerMoves(where);
		} catch (Exception e) {
			// TODO: relay to the view that the move
			// request failed. Perhaps this should be
			// done in the model instead.
			// exitOnException(e);
		}
	}

	// public void playerDoesNothing() {
	// 	// model.nextTurn();
	// }

	public void playerTakeRoleRequest(String which) {
		System.out.println("Player " + (model.getCurrentPlayer().getId() + 1) + " takes roll: " + which);
		try {
			// model.getCurrentPlayer().takeRole(which);
			listener.playerTakesRole(which);
			// model.nextTurn();
		} catch (Exception e) {
			exitOnException(e);
		}
	}
	
	public void playerUpgradeRequest(int rank, String currency) {
		try {
			listener.playerUpgrades(rank, currency);
		} catch (IllegalStateException e) {
			System.out.println(e.getMessage());
		}
	}

}