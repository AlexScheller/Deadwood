package model.loading;

public class GameLoader {

	private static final GameLoader instance = new GameLoader();

	private XMLParser xp;
	private BoardLoader bl;
	private PlayerFactory pf;

	public GameLoader(int numPlayers) {
		this.xp = XMLParser.getInstance();
		this.bl = BoardLoader.getInstance();
		this.scl = SceneCardLoader.getInstance();
		this.pf = PlayerFactory.getInstance(numPlayers);
	}

	// TODO: include player names
	public DeadWoodGame loadGame(int numPlayers) {
		Player[] players = new Player[numPlayers];
		for (int i = 0; i < numPlayers; i++) {
			players[i] = pf.getPlayer();
			System.out.println(players[i].toString());
		}
		Board board = bl.loadBoard(xp);
		return new DeadWoodGame(players, board);
	}

	public static GameLoader getInstance() { return instance; }

}