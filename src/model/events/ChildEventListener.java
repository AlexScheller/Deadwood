package model.events;

public interface ChildEventListener {

	public void newSceneInSetEvent(String setName,
							  String sceneTitle,
							  int sceneId);

	public void takeFinishEvent(String setName);

	public void sceneWrapEvent(String setName,
							   String sceneTitle);
}