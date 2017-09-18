package model.board;

import java.util.Stack;
import java.util.Collections;

public class SceneCardSet {

	private Stack<SceneCard> cards;

	public SceneCardSet(Stack<SceneCard> cards) {
		this.cards = cards;
		shuffle();
		// System.out.println(" successful!");
	}

	@Override
	public String toString() {
		String ret = "";
		for (SceneCard sc : cards) {
			ret += sc.toString() + "\n\n";
		}
		return ret;
	}

	public void shuffle() {
		// this.cards.shuffle();
		Collections.shuffle(cards);
	}

	public SceneCard deal() {
		return this.cards.pop();
	}

}