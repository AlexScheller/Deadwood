package model;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

import model.player.Player;
import model.player.PlayerContext;

import model.board.Board;
import model.board.room.Room;

import model.events.ChildEventListener;
import controller.ControllerListener;

public class DeadwoodModel
	implements ControllerListener, ChildEventListener {

	private Player[] players;
	private Player currentPlayer;
	private int currentPlayerIndex = 0;
	// private int day;
	private int daysLeft;
	private int currentDay;
	private int unwrappedScenes;
	private boolean playing;
	private Board board;

	private ModelListener listener;

	public DeadwoodModel(Player[] players, Board board) {
		// TODO: technically this doesn't apply for games
		// of 3 or fewer players.
		this.currentDay = 1;
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
		System.out.println("wrapping, scenes left: " + unwrappedScenes);
		if (unwrappedScenes == 1) {
			System.out.println("calling newDay()");
			newDay();
		}
	}

	/* Player methods */
	@Override
	public void playerMovesEvent(int id, String from, String to) {
		listener.playerMoves(id, from, to);
	}

	@Override
	public void playerTakesRoleEvent() {
		listener.playerTakesRoleEvent(currentPlayerIndex);
	}

	@Override
	public void playerRehearsesEvent() {
		listener.updateCurrentPlayerContext(currentPlayer.toContext());
		// playerEndsTurn();
	}

	/* ControllerListener Methods*/
	
	public void play() {
		if (!playing) {
			playing = true;
			// board.newDay();
			currentPlayerIndex = (new Random()).nextInt(players.length);
			currentPlayer = players[currentPlayerIndex];
			// listener.newPlayersEvent(playerstoContexts());
			PlayerContext[] contexts = new PlayerContext[players.length];
			for (int i = 0; i < players.length; i++) {
				contexts[i] = players[i].toContext();
				System.out.println(contexts[i].id);
			}
			listener.newPlayersEvent(contexts);
			newDay();
		}
	}

	public void newDay() {
		daysLeft--;
		unwrappedScenes = 10;
		if (daysLeft == 0) {
			listener.displayWinners(determineWinners());
		} else {
		board.newDay();
			for (Player p : players) {
				p.newDay();
			}
			// return players to the trailers
			for (int i = 0; i < players.length; i++) {
				listener.playerMoves(players[i].getId(), null, "trailer");
			}
			listener.newDayEvent(currentDay, daysLeft);
			currentDay++;
			listener.newTurnEvent(currentPlayerIndex);
		}
	}

	// public void nextTurn() {
	// 	players[currentPlayerIndex].resetMove();
	// 	currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
	// }

	private PlayerContext[] determineWinners() {
		List<PlayerContext> highScorers = new ArrayList<>();
		int currHighScore = -1;
		for (Player p : players) {
			if (!(currHighScore == -1)) {
				if (currHighScore < p.calculateScore()) {
					currHighScore = p.calculateScore();
					// System.out.println("curr high score: " + currHighScore);
					highScorers.clear();
					highScorers.add(p.toContext());
				} else if (currHighScore == p.calculateScore()) {
					// System.out.println("curr high score: " + currHighScore);
					highScorers.add(p.toContext());
				}
			} else {
				currHighScore = p.calculateScore();
				// System.out.println("curr high score: " + currHighScore);
				highScorers.add(p.toContext());
			}
		}
		// System.out.println(highScorers.toString());
		return highScorers.toArray(new PlayerContext[0]);
	}


	public void playerActs() throws IllegalStateException {
		currentPlayer.act();
		// playerEndsTurn();
		// NOTE: This may lead to weirdness when a scene wraps
	}

	public void playerActsEvent(int roll) {
		listener.playerActsEvent(currentPlayer.getName(), roll);
	}

	public void playerEndsTurn() {
		currentPlayer.endTurn();
	}

	public void playerEndsTurnEvent() {
		currentPlayerIndex++;
		currentPlayerIndex %= players.length;
		currentPlayer = players[currentPlayerIndex];
		listener.newTurnEvent(currentPlayerIndex);
	}

	public void playerRehearses() throws IllegalStateException {
		players[currentPlayerIndex].rehearse();
	}

	public void playerMoves(String where) throws IllegalArgumentException {
		currentPlayer.move(where);
		listener.updateCurrentPlayerContext(currentPlayer.toContext());
	}

	public void playerUpgradesEvent() {
		listener.updatePlayer(currentPlayer.toContext());
	}

	public void playerTakesRole(String which) throws IllegalArgumentException, IllegalStateException {
		if (currentPlayer.canTakeNewRole()) {
			currentPlayer.takeRole(which);
			// playerEndsTurn();	
		} else {
			throw new IllegalStateException("player cannot take new role");
		}
	}

	@Override
	public void upgradeInfoRequest() {
		listener.upgradeInfoReturn(currentPlayer.getRank(),
								   currentPlayer.getMaxUpgradeAvailable("dollars"),
								   currentPlayer.getMaxUpgradeAvailable("credits"));
	}

	public void playerUpgrades(int rank, String currency) {
		currentPlayer.upgrade(rank, currency);
	}

	public int getCurrentPlayerId() {
		return currentPlayer.getId();
	}

	public PlayerContext getPlayerContext(int id) {
		return players[id].toContext();
	}

}