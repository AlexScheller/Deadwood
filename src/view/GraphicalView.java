package view;

import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

// import view.graphics.CardComponent;
import view.graphics.MenuPanel;
import view.graphics.BoardPanel;
import view.graphics.PlayerComponent;
import view.graphics.PlayerInfo;
import view.events.ChildEventListener;
import static view.graphics.Dimensions.*;

import model.ModelListener;
import view.loading.AssetBank;

public class GraphicalView
	extends JFrame
	implements DeadwoodView, ChildEventListener {
	// TODO: apply this formatting across code base?

	private BoardPanel bp;
	private MenuPanel mp;

	private PlayerComponent[] players;
	private char[] colors;

	private ViewListener listener;

	public GraphicalView() {};

	public void initUI(BoardPanel bp, MenuPanel mp) {
		setLayout(null); // absolute positioning is used
		this.colors = new char[] {'b', 'c', 'g', 'o', 'p', 'r', 'v', 'y'};
		this.bp = bp;
		this.mp = mp;
		add(bp);
		add(mp);
		setSize(BOARD_LENGTH + MENU_LENGTH, BOARD_HEIGHT);
		setTitle("Deadwood!");
		setLocationRelativeTo(null); // center window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/* ChildEventListener methods */

	public void newGameButtonClickEvent() {
		System.out.println("new game event intercepted");
		// number of players is currently hard-coded for
		// testing purposes
		listener.newGameRequest(2);
		// this.players = new PlayerInfo[2];
		// paint();
		repaint();
	}

	public void roleClickEvent(String which) {
		System.out.println("role click event intercepted: " + which);
	}

	public void playerClickEvent(String which) {
		System.out.println("player click event intercepted: " + which);
	}

	// PROTOTYPE_CHAIN: 1
	public void playerHoverEvent(String which) {
		Image playerImage = AssetBank.getInstance().getAsset(which);
		mp.displayHoveredPlayer(playerImage);
	}

	// PROTOTYPE_CHAIN: 1
	public void playerUnHoverEvent() {
		mp.removeHoveredPlayer();
	}

	public void cardClickEvent(String which) {
		System.out.println("card click event intercepted: " + which);
	}

	// DeadwoodView methods

	@Override
	public void setListener(ViewListener vl) {
		this.listener = vl;
	}

	/* ModelListener methods */

	// NOTE: So far this (PlayerInfo) is the only piece of 
	// non-listener shared code between the model and the
	// view. It's practical, but I wonder if it would be
	// better not to share any code at all.
	@Override
	public void newPlayersEvent(PlayerInfo[] infos) {
		this.players = new PlayerComponent[infos.length];
		for (int i = 0; i < infos.length; i++) {
			PlayerComponent pc = new PlayerComponent(infos[i], colors[i], this);
			players[pc.getId()] = pc;
		}
	}

	@Override
	public void newDayEvent() {
		// TEMP
		System.out.println("new day event intercepted");
	}

	@Override
	public void sceneWrapEvent(String setName, String sceneName) {
		// TEMP
		System.out.println("scene: " + sceneName + " wrapping in: " + setName);
	}

	@Override
	public void takeFinishEvent(String setName) {
		// TEMP
		System.out.println("take finished in: " + setName);
	}

	@Override
	public void newSceneInSetEvent(String setName,
								   String sceneName,
								   int sceneId) {
		// TEMP
		System.out.println("new scene: " + sceneName + " in: " + setName);
		bp.newSceneInSet(setName, sceneName, sceneId);
	}

	@Override
	public void playerMoves(int playerId, String from, String to) {
		bp.movePlayer(players[playerId], from, to);
	}

	@Override
	public void playerRehearses() {
		// TEMP
		System.out.println("player rehearses");
	}

}