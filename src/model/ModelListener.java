package model;

public interface ModelListener {

	// public void update();

	/* logic triggers */
	public void newTurn();
	public void newDay();

	/* info responses */
	public void playerActResponse(String msg);
	public void playerRehearseResponse(String msg);
	public void displayWinners(String[] winners);

}