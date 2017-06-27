package model;

import model.loading.*;

public class DeadwoodGame {

	// Set<Player> players;

	public DeadwoodGame(int numPlayers) {}

	public void begin() {
		GameLoader gl = GameLoader.getInstance();
		gl.loadGame();
	}

}