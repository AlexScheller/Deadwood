package view;

import view.graphics.PlayerInfo;

public interface ViewListener {

	public void newGameRequest(int numPlayers); // , ModelListener ml);

	// PROTOTYPE_CHAIN: 2
	public void endTurnRequest();

	public PlayerInfo getPlayerInfo(int id);

	public void playerActRequest();

	public void playerRehearseRequest();

	public void playerMoveRequest(String where);

	public void playerTakeRoleRequest(String which);

	public void playerUpgradeRequest(int level, String currency);

}