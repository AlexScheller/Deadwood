package model.board;

public class PlayerFactory {

	private static final PlayerFactory instance = new PlayerFactory();
	private static int playerID = 0;
	private static int numPlayers;
	private static Room startingRoom;

	private PlayerFactory () {}

	public Player getPlayer(int numPlayers) {
		Room initialRoom = RoomSet.getInstance().getRoom("trailer");
		if (numPlayers == 5) {
			return new Player(playerID++, initialRoom, 2);
		} else if (numPlayers == 6) {
			return new Player(playerID++, initialRoom, 4);
		} else if (numPlayers == 7) {
			return new Player(playerID++, initialRoom, 0, 2);
		} else {
			return new Player(playerID++, initialRoom);
		}
	}

	public static PlayerFactory getInstance() { return instance; }

}