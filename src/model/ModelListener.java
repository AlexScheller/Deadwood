package model;

import model.player.PlayerContext;

public interface ModelListener {

	// // public void update();

	// /* logic triggers */
	// public void newTurn();
	// public void newDay();

	// /* info responses */
	// public void playerActResponse(String msg);
	// public void playerRehearseResponse(String msg);
	// public void displayWinners(String[] winners);

	/* Main game event methods*/

	public void newDayEvent();

	// PROTOTYPE_CHAIN: 3
	public void newTurnEvent(int playerId);

	/* Movie Set methods */
	
	public void sceneWrapEvent(String setName, String sceneTitle);
	public void takeFinishEvent(String setName);
	public void newSceneInSetEvent(String setName,
								   String sceneTitle,
								   int sceneId);

	// Player methods
	// public void playerRehearses();

	public void playerMoves(int playerId, String from, String to);

	public void newPlayersEvent(PlayerContext[] contexts);

	public void playerTakesRoleEvent(int playerId);

	// public void updateCurrentPlayerInfo(int playerId);

	public void updateCurrentPlayerContext(PlayerContext pc);
	// public void updatePlayerInfo(int playerId, )

}