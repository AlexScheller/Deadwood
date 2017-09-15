package view;

public interface ViewListener {

	public void playerActRequest();

	public void playerRehearseRequest();

	public void playerMoveRequest(String where);

	public void playerDoesNothing();

	public void playerTakeRoleRequest(String which);

	public void playerUpgradeRequest(int level);

}