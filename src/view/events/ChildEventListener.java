package view.events;

public interface ChildEventListener {

	public void newGameEvent();

	public void roleClickEvent(String which);

	public void cardClickEvent(String which);

}