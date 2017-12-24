package model.events;

public interface ChildEventListener {

	/* Scene Methods */

	public void newSceneInSetEvent(String setName,
							  String sceneTitle,
							  int sceneId);

	public void takeFinishEvent(String setName);

	public void sceneWrapEvent(String setName,
							   String sceneTitle);

	/* Player Methods */

	public void playerMovesEvent(int id, String from, String to);

	public void playerTakesRoleEvent();

	public void playerRehearsesEvent();

	public void playerActsEvent(int roll);

	public void playerUpgradesEvent();

	public void playerEndsTurnEvent();

}