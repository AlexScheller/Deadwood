package model.loading;

import java.util.List;

import model.board.*;

public class CardLoader {

	private static final CardLoader instance = new CardLoader();

	public CardLoader() {}

	// public List<Card> loadCards(List<CardInfo> cis) {
	public List<Card> loadCards(List<CardInfo> cis) {
		List<Card> ret = new ArrayList<>();
		for (CardInfo ci : cis) {
			ret.add(new Card(ci));
		}
		return ret;
	}

	public static CardLoader getInstance() { return instance; }

}