package controller;

public interface ControllerListener {

	public void play();

	// PROTOTYPE_CHAIN: 2
	public void playerEndsTurn();

	public void playerActs();

	public void playerRehearses();

	public void playerMoves(String where);

	public void playerTakesRole(String which);

	public void playerUpgrades(int rank, String currency);

}