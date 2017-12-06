package view;

public interface ViewListener {

	public void newGameRequest(int numPlayers); // , ModelListener ml);

	public void playerActRequest();

	public void playerRehearseRequest();

	public void playerMoveRequest(String where);

	public void playerTakeRoleRequest(String which);

	public void playerUpgradeRequest(int level, String currency);

}