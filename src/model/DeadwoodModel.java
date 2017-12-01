package model;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

import model.player.Player;

import model.board.Board;
import model.board.room.Room;

public class DeadwoodModel {

	private Player[] players;
	private int currentPlayerIndex = 0;
	private int day;
	private Board board;

	private ModelListener listener;

	public DeadwoodModel(Player[] players, Board board) {
		this.day = 1;
		this.board = board;
		this.players = players;
		// System.out.println("successful!");
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

	// private void nextTurn() {
	// 	players
	// }

	public void play() {
		board.newDay();
		int firstPlayerId = (new Random()).nextInt(players.length); 
		while (day <= 4) {
			currentPlayerIndex = firstPlayerId;
			while (!board.oneSceneLeft()) {
				// players[(currentPlayerId++) % players.length].playTurn();
				listener.newTurn();
				players[currentPlayerIndex].resetMove();
				currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
			}
			board.newDay();
			for (int i = 0; i < players.length; i++) {
				// maybe find a better way to do this
				players[i].teleport(board.getRoomForTeleport("trailer"));
			}
			listener.newDay();
			day++;
		}
		listener.displayWinners(determineWinners());
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
		String res = players[currentPlayerIndex].act();
		listener.playerActResponse(res);
	}

	public void playerRehearses() throws IllegalStateException {
		String res = players[currentPlayerIndex].rehearse();
		listener.playerRehearseResponse(res);
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