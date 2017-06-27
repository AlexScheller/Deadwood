package model.loading;

import java.util.List;

import model.board.*;

public class CardLoader {

	private static final CardLoader instance = new CardLoader();

	public CardLoader() {}

	// public List<Card> loadCards(List<CardInfo> cis) {
	public void loadCards(List<CardInfo> cis) {
		Card c;
		for (CardInfo ci : cis) {
			c = new Card(ci);
			System.out.println(c.toString());
		}
	}

	public static CardLoader getInstance() { return instance; }

}