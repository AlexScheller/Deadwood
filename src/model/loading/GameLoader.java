package model.loading;

import model.*;
import model.board.*;

public class GameLoader {

	private static final GameLoader instance = new GameLoader();

	private GameLoader () {}

	// TODO: include player names
	public DeadwoodGame loadGame(int numPlayers) {
		PlayerFactory pf = PlayerFactory.getInstance();
		BoardLoader bl = BoardLoader.getInstance();
		XMLParser xp = XMLParser.getInstance();
		Player[] players = new Player[numPlayers];
		for (int i = 0; i < numPlayers; i++) {
			players[i] = pf.getPlayer(numPlayers);
			System.out.println(players[i].toString());
		}
		Board board = bl.getBoard(xp);
		return new DeadwoodGame(players, board);
	}

	public static GameLoader getInstance() { return instance; }

}