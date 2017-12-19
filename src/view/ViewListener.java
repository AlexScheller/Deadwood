package view;

import model.player.PlayerContext;

public interface ViewListener {

	public void newGameRequest(int numPlayers); // , ModelListener ml);

	// PROTOTYPE_CHAIN: 2
	public void endTurnRequest();

	public PlayerContext getPlayerContext(int id);

	public void playerActRequest();

	public void playerRehearseRequest();

	public void playerMoveRequest(String where);

	public void playerTakeRoleRequest(String which);

	public void playerUpgradeRequest(int rank, String currency);

}