package model.loading;

import model.*;
import model.board.*;
import model.players.*;

public class GameLoader {

	private static final GameLoader instance = new GameLoader();

	private GameLoader () {}

	// TODO: include player names
	public DeadwoodGame loadGame(int numPlayers) {
		System.out.println("loading game...");
		PlayerFactory pf = PlayerFactory.getInstance();
		BoardLoader bl = BoardLoader.getInstance();
		JSONDataParser jp = JSONDataParser.getInstance();
		Board board = bl.getBoard(jp);
		Player[] players = new Player[numPlayers];
		for (int i = 0; i < numPlayers; i++) {
			players[i] = pf.getPlayer(numPlayers, board.getInitialRoom());
		}
		return new DeadwoodGame(players, board);
	}

	public static GameLoader getInstance() { return instance; }

}