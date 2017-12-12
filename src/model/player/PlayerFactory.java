package model.player;

import model.board.room.Room;

public class PlayerFactory {

	private static final PlayerFactory instance = new PlayerFactory();
	private static int playerID = 0;
	private static int numPlayers;
	private final int MAX_PLAYERS = 8;

	private PlayerFactory () {}

	public Player getPlayer(int numPlayers, Room initialRoom) {
		if (numPlayers > MAX_PLAYERS) {
			throw new IllegalArgumentException("max player count exceeded");
		}
		if (numPlayers < 5) {
			return new Player(playerID++, initialRoom);
		} else if (numPlayers == 5) {
			return new Player(playerID++, initialRoom, 2);
		} else if (numPlayers == 6) {
			return new Player(playerID++, initialRoom, 4);
		} else {
			return new Player(playerID++, initialRoom, 0, 2);
		}
	}

	public static PlayerFactory getInstance() { return instance; }

}