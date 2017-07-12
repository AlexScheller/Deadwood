package model.loading;

public class GameLoader {

	private static final GameLoader instance = new GameLoader();

	public GameLoader() {}

	// TODO: include player names
	public void loadGame(int numPlayers) {
		XMLParser xp = XMLParser.getInstance();
		CardLoader cl = CardLoader.getInstance();
		BoardLoader bl = BoardLoader.getInstance();
		PlayerFactory pf = PlayerFactory.getInstance(numPlayers);
		cl.loadCards(xp.parseCardInfos());
		RoomSet rooms = bl.loadRooms(xp.parseRoomInfos());
		Player[] players = new Player[numPlayers];
		for (int i = 0; i < numPlayers; i++) {
			players[i] = pf.getPlayer();
			System.out.println(players[i].toString());
		}
	}

	public static GameLoader getInstance() { return instance; }

}