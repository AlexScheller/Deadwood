package view.events;

public interface ChildEventListener {

	public void newGameButtonClickEvent();

	public void roleClickEvent(String which);

	public void playerClickEvent(String which);

	// PROTOTYPE_CHAIN: 1
	public void playerHoverEvent(String which);

	// prototype_CHAIN: 1
	public void playerUnHoverEvent();

	public void cardClickEvent(String which);

}