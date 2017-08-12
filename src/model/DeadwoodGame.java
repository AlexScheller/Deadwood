package model;

import model.loading.*;
import model.board.*;

public class DeadwoodGame {

	private Player[] players;
	private int day;
	private Board board;

	public DeadwoodGame(Player[] players, Board board) {
		this.day = 0;
		this.board = board;
		this.players = players;
		System.out.println("successful!");
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

	// private void play() {
	// 	board.newDay();
	// 	do {
	// 		day++;
	// 		int currentPlayerId = 0;
	// 		while (!board.oneSceneLeft()) {
	// 			players[(currentPlayerId++) % players.length].playTurn();
	// 		}
	// 		board.newDay();
	// 	} while (day <= 4);
	// 	endGame();
	// }

	// TODO implement ties
	// private void endGame() {
	// 	String currHighScorerName;
	// 	int currHighScore = -1;
	// 	for (Player p : players) {
	// 		if (!(currHighScore == -1)) {
	// 			if (currHighScore < p.calculateScore()) {
	// 				currHighScore = p.calculateScore();
	// 				currHighScorerName = p.getName();
	// 			}
	// 		} else {
	// 			currHighScore = p.calculateScore();
	// 			currHighScorer = p.getName();
	// 		}
	// 	}
	// 	System.out.println(currHighScorerName + " has won!");
	// }

}