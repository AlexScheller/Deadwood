package view;

import java.util.Scanner;

import model.player.Player;

import model.DeadwoodModel;
import model.ModelListener;

import model.player.PlayerContext;
// NOTE: this is currently just a dummy class, as major
// changes to the design of the model and ModelListener
// took place during the development of the GraphicalView

// TODO: make this class compliant with updated design
public class ConsoleView
	implements DeadwoodView, ModelListener {
	
	private ViewListener listener;

	@Override
	public void setListener(ViewListener vl) {
		this.listener = vl;
	}

	@Override
	public void newPlayersEvent(PlayerContext[] context) {
		System.out.println("new player info event");
	}

	@Override
	public void newDayEvent(int curr, int left) {
		// TEMP
		System.out.println("new day event intercepted");
	}

	@Override
	public void sceneWrapEvent(String setName, String sceneName) {
		// TEMP
		System.out.println("scene: " + sceneName + " wrapping in: " + setName);
	}

	@Override
	public void takeFinishEvent(String setName) {
		// TEMP
		System.out.println("take finished in: " + setName);
	}

	@Override
	public void newSceneInSetEvent(String setName,
								   String sceneName, 
								   int sceneId) {
		// TEMP
		System.out.println("new scene: " + sceneName + " in: " + setName);
	}

	// @Override
	// public void playerRehearses() {
	// 	// TEMP
	// 	System.out.println("player rehearses");
	// }

	@Override
	public void playerMoves(int playerId, String from, String to) {
		// TEMP
		System.out.println("Player " + playerId + " moves from " + from + " to " + to);
	}

	@Override
	public void playerTakesRoleEvent(int playerId) {
		System.out.println("Player " + playerId + " takes role");
	}

	@Override
	public void playerActsEvent(String who, int roll) {
		System.out.println(who + " acts, rolling a " + roll);
	}

	// PROTOTYPE_CHAIN: 3
	@Override
	public void newTurnEvent(int playerId) {
		System.out.println("New turn for player: " + playerId);
	}

	@Override
	public void updateCurrentPlayerContext(PlayerContext pc) {
		System.out.println("updating");
		// mp.updateCurrentPlayerDisplay(pi);
	}

}