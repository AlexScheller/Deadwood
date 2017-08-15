public class DeadwoodController implements ViewListener {

	private DeadwoodView view;
	private DeadwoodGame model;

	public DeadwoodController(DeadwoodView view, DeadwoodGame model) {
		this.view = view;
		this.model = model;
		this.view.setListener(this);
	}

	public void playerActs() {
		model.getCurrentPlayer().act();
	}

	public void playerRehearses() {
		model.getCurrentPlayer().rehearse();
	}

	public void playerMovesTo(String where) {
		model.getCurrentPlayer().move(where);
	}

	public void playerDoesNothing() {
		model.nextTurn();
	}

	public void playerTakesRole(String which) {
		model.getCurrentPlayer().takeRole(which);
	}

	public void playerUpgrades(int level) {
		model.getCurrentPlayer().upgrade(level);
	}

}