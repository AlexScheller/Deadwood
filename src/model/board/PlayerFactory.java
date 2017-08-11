package model.board;

public class PlayerFactory {

	private static final PlayerFactory instance = new PlayerFactory();
	private static int playerID = 0;
	private static int numPlayers;
	private static Room startingRoom;

	private PlayerFactory () {}

	public Player getPlayer(int numPlayers) {
		if (numPlayers == 5) {
			return new Player(playerID++, 2);
		} else if (numPlayers == 6) {
			return new Player(playerID++, 4);
		} else if (numPlayers == 7) {
			Player ret = new Player(playerID++);
			ret.rankUp();
			return ret;
		} else {
			return new Player(playerID++);
		}
	}

	public static PlayerFactory getInstance() { return instance; }

}