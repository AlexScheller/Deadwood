package model.board;

public class SceneCardSet {

	private Stack<SceneCard> cards;

	public SceneCardSet(Stack<SceneCard> cards) {
		this.cards = cards;
	}

	public void shuffle() {
		this.cards.shuffle();
	}

	public SceneCard deal() {
		return this.cards.pop();
	}

}