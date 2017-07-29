package model;

import model.loading.*;

public class DeadwoodGame {

	private Player[] players;
	private int day;
	private Board board;

	public DeadwoodGame(int numPlayers) {
		this.day = 0;
		GameLoader gl = GameLoader.getInstance();
		this.board = gl.getBoard();
		this.players = gl.getPlayers();
	}

	private void play() {
		board.newDay();
		do {
			day++;
			int currentPlayerId = 0;
			while (!board.oneSceneLeft()) {
				players[currentPlayerId++].playTurn();
			}
			board.newDay();
		} while (day <= 4);
		endGame();
	}

	// TODO implement ties
	private void endGame() {
		String currHighScorerName;
		int currHighScore = -1;
		for (Player p : players) {
			if (!(currHighScore == -1)) {
				if (currHighScore < p.calculateScore()) {
					currHighScore = p.calculateScore();
					currHighScorerName = p.getName();
				}
			} else {
				currHighScore = p.calculateScore();
				currHighScorer = p.getName();
			}
		}
		System.out.println(currHighScorerName + " has won!");
	}

}