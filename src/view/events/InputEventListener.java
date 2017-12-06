package view.events;

public interface InputEventListener {

	public void newGameEvent();

	public void roleClickEvent(String which);

	public void cardClickEvent(String which);

}