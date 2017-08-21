package view;

public interface ViewListener {

	public void playerActs();

	public void playerRehearses();

	public void playerMovesTo(String where);

	public void playerDoesNothing();

	public void playerTakesRole(String which);

	public void playerUpgrades(int level);

}