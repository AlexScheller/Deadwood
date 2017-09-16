package model;

import model.loading.*;
import model.board.*;
import model.players.*;
import java.util.Random;

public class DeadwoodGame {

	private Player[] players;
	private int currentPlayerIndex = 0;
	private int day;
	private Board board;

	private ModelListener listener;

	public DeadwoodGame(Player[] players, Board board) {
		this.day = 0;
		this.board = board;
		this.players = players;
		System.out.println("successful!");
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
		do {
			day++;
			currentPlayerIndex = firstPlayerId;
			while (!board.oneSceneLeft()) {
				// players[(currentPlayerId++) % players.length].playTurn();
				listener.newTurn();
				players[currentPlayerIndex].resetMove();
				currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
			}
			board.newDay();
			listener.newDay();
		} while (day <= 4);
		endGame();
	}

	// public void nextTurn() {
	// 	players[currentPlayerIndex].resetMove();
	// 	currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
	// }

	// TODO implement ties
	private void endGame() {
		String currHighScorerName = "";
		int currHighScore = -1;
		for (Player p : players) {
			if (!(currHighScore == -1)) {
				if (currHighScore < p.calculateScore()) {
					currHighScore = p.calculateScore();
					currHighScorerName = p.getName();
				}
			} else {
				currHighScore = p.calculateScore();
				currHighScorerName = p.getName();
			}
		}
		System.out.println(currHighScorerName + " has won!");
	}

	public Player getCurrentPlayer() {
		return players[currentPlayerIndex];
	}

}