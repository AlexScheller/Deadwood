package model.loading;

public class GameLoader {

	private static final GameLoader instance = new GameLoader();

	public GameLoader() {}

	// public List<Card> loadCards(List<CardInfo> cis) {
	public void loadGame() {
		XMLParser xp = XMLParser.getInstance();
		CardLoader cl = CardLoader.getInstance();
		BoardLoader bl = BoardLoader.getInstance();
		cl.loadCards(xp.parseCardInfos());
		bl.loadRooms(xp.parseRoomInfos());
	}

	public static GameLoader getInstance() { return instance; }

}