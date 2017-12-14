package view.events;

import view.graphics.RoleComponent;

public interface ChildEventListener {

	public void newGameButtonClickEvent();

	public void roleClickEvent(RoleComponent which);

	public void playerClickEvent(int id);

	// PROTOTYPE_CHAIN: 1
	public void playerHoverEvent(int id);

	// PROTOTYPE_CHAIN: 1
	public void playerUnHoverEvent();

	// PROTOTYPE_CHAIN: 2
	public void endTurnButtonClickEvent();

	// public void cardClickEvent(String where, String which);

	public void roomClickEvent(String where);

}