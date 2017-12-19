package controller;

import model.player.PlayerContext;

public interface ControllerListener {

	public void play();

	public PlayerContext getPlayerContext(int id);

	// PROTOTYPE_CHAIN: 2
	public void playerEndsTurn();

	public void playerActs();

	public void playerRehearses();

	public void playerMoves(String where);

	public void playerTakesRole(String which);

	public void playerUpgrades(int rank, String currency);

}