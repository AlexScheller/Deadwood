package model;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

import model.player.Player;

import model.board.Board;
import model.board.room.Room;

// Not a fan of importing from the view
import view.graphics.PlayerInfo;

import model.events.ChildEventListener;
import controller.ControllerListener;

public class DeadwoodModel
	implements ControllerListener, ChildEventListener {

	private Player[] players;
	private int currentPlayerIndex = 0;
	// private int day;
	private int daysLeft;
	private int unwrappedScenes;
	private boolean playing;
	private Board board;

	private ModelListener listener;

	public DeadwoodModel(Player[] players, Board board) {
		this.daysLeft = 4;
		this.unwrappedScenes = 10;
		this.playing = false;
		this.board = board;
		this.players = players;
		board.setListener(this);
		for (Player p : players) {
			p.setListener(this);
		}
	}

	public void setListener(ModelListener ml) {
		this.listener = ml;
	}

	// For debugging purposes
	@Override
	public String toString() {
		String playerStrings = "";
		for (Player p : players) {
			playerStrings += p.toString() + "\n";
		}
		return playerStrings + board.toString();
	}

	public String currentStateToString() {
		String ret = "";
		for (Player p : players) {
			ret += p.toString() + "\n";
		}
		return ret;
	}

	/* ChildEventListenerMethods */

	/* Scene methods */

	@Override
	public void newSceneInSetEvent(String setName, String sceneTitle,
								   int setId) {
		listener.newSceneInSetEvent(setName, sceneTitle, setId);
	}

	@Override
	public void takeFinishEvent(String setName) {
		listener.takeFinishEvent(setName);
	}

	@Override
	public void sceneWrapEvent(String setName, String sceneTitle) {
		listener.sceneWrapEvent(setName, sceneTitle);
		unwrappedScenes--;
		if (unwrappedScenes == 1) {
			newDay();
		}
	}

	/* Player methods */
	@Override
	public void playerMovesEvent(int id, String from, String to) {
		System.out.println("finally telling view to update from move");
		listener.playerMoves(id, from, to);
	}

	// this method collects necessary data from the
	// players to be shipped to the view.
	private PlayerInfo[] playersToPlayerInfos() {
		PlayerInfo[] ret = new PlayerInfo[players.length];
		for (int i = 0; i < players.length; i++) {
			PlayerInfo pi = new PlayerInfo();
			Player curr = players[i];
			pi.id = curr.getId();
			pi.level = curr.getRank();
			pi.dollars = curr.getDollars();
			pi.credits = curr.getCredits();
			ret[i] = pi;
		}
		return ret;
	}

	/* ControllerListener Methods*/
	
	public void play() {
		if (!playing) {
			playing = true;
			// board.newDay();
			currentPlayerIndex = (new Random()).nextInt(players.length);
			listener.newPlayersEvent(playersToPlayerInfos());
			for (int i = 0; i < players.length; i++) {
				listener.playerMoves(players[i].getId(), null, "trailer");
			}
			newDay();
		}
		// while (day <= 4) {
		// 	currentPlayerIndex = firstPlayerId;
		// 	while (!board.oneSceneLeft()) {
		// 		// players[(currentPlayerId++) % players.length].playTurn();
		// 		listener.newTurn();
		// 		players[currentPlayerIndex].resetMove();
		// 		currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
				
		// 	}
		// 	board.newDay();
		// 	for (int i = 0; i < players.length; i++) {
		// 		// maybe find a better way to do this
		// 		players[i].teleport(board.getRoomForTeleport("trailer"));
		// 	}
		// 	listener.newDay();
		// 	day++;
		// }
		// listener.displayWinners(determineWinners());
	}

	public void newDay() {
		daysLeft--;
		unwrappedScenes = 10;
		if (daysLeft == 0) {
			System.out.println("game over bud");
		}
		board.newDay();
		for (Player p : players) {
			p.newDay();
		}
	}

	// public void nextTurn() {
	// 	players[currentPlayerIndex].resetMove();
	// 	currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
	// }

	private String[] determineWinners() {
		// String currHighScorerName = "";
		List<String> highScorers = new ArrayList<>();
		int currHighScore = -1;
		for (Player p : players) {
			if (!(currHighScore == -1)) {
				if (currHighScore < p.calculateScore()) {
					currHighScore = p.calculateScore();
					highScorers.clear();
					highScorers.add(p.getName());
				} else if (currHighScore == p.calculateScore()) {
					highScorers.add(p.getName());
				}
			} else {
				currHighScore = p.calculateScore();
				highScorers.add(p.getName());
			}
		}
		return highScorers.toArray(new String[0]);
	}


	public void playerActs() throws IllegalStateException {
		players[currentPlayerIndex].act();
		// String res = players[currentPlayerIndex].act();
		// listener.playerActResponse(res);
	}

	public void playerRehearses() throws IllegalStateException {
		players[currentPlayerIndex].rehearse();
		// String res = players[currentPlayerIndex].rehearse();
		// listener.playerRehearseResponse(res);
	}

	public void playerMoves(String where) throws IllegalArgumentException {
		players[currentPlayerIndex].move(where);
	}

	/* for debugging */
	public Room getRoomForTeleport(String which) {
		return board.getRoomForTeleport(which);
	}
	/* for debugging */

	public void playerTakesRole(String which) throws IllegalArgumentException, IllegalStateException {
		players[currentPlayerIndex].takeRole(which);
	}

	public void playerUpgrades(int rank, String currency) {
		players[currentPlayerIndex].upgrade(rank, currency);
	}

	public Player getCurrentPlayer() {
		return players[currentPlayerIndex];
	}

}