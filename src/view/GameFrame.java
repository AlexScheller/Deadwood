package view;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

// import view.graphics.CardComponent;
import view.graphics.menu.MenuPanel;
import view.graphics.menu.ActionType;
import view.graphics.board.BoardPanel;
import view.graphics.board.PlayerComponent;
import view.graphics.board.RoleComponent;
import view.graphics.PlayerInfo;
import view.events.ChildEventListener;
import static view.graphics.Dimensions.*;

import model.player.PlayerContext;
import model.ModelListener;
import view.loading.AssetBank;

// TODO: put board and menu panels in JScrollpanes if the
// native resolution is too low.
public class GameFrame
	extends JFrame
	implements DeadwoodView, ChildEventListener {
	// TODO: apply this formatting across code base?

	private BoardPanel bp;
	private MenuPanel mp;

	private PlayerComponent[] players;
	private RoleComponent requestingRole;
	private char[] playerColors;

	private ViewListener listener;

	public GameFrame() {};

	public void initUI(BoardPanel bp, MenuPanel mp) {
		setLayout(null); // absolute positioning is used
		this.playerColors = new char[] {'b', 'c', 'g', 'o', 'p', 'r', 'v', 'y'};
		this.requestingRole = null;
		this.bp = bp;
		this.mp = mp;
		add(mp);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int sWidth = screenSize.width;
		int sHeight = screenSize.height;
		// TODO: Fix this super janky code
		if (sWidth < BOARD_LENGTH + MENU_LENGTH || sHeight < BOARD_HEIGHT) {
			setSize(1200, BOARD_HEIGHT - 200);
			mp.setBounds(1000, 0, MENU_LENGTH, MENU_HEIGHT);
			JScrollPane jsp = new JScrollPane(bp);
			// jsp.setBounds(0, 0, sWidth - MENU_LENGTH, sHeight - 10);
			jsp.setBounds(0, 0, 1000, MENU_HEIGHT - 200);
			// jsp.setSize(100, 100);
			jsp.getHorizontalScrollBar().setMaximum(228);
			jsp.getHorizontalScrollBar().setValue(228);
			add(jsp);
			System.out.println("resolution too low, using scroll pane");
		} else {
			setSize(BOARD_LENGTH + MENU_LENGTH, BOARD_HEIGHT);
			add(bp);
		}
		setTitle("Deadwood!");
		setLocationRelativeTo(null); // center window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void begin(int numPlayers) {
		listener.newGameRequest(numPlayers);
		repaint();
	}

	/* ChildEventListener methods */
	// @Override
	// public void newGameButtonClickEvent() {
	// 	System.out.println("new game event intercepted");
	// 	// number of players is currently hard-coded for
	// 	// testing purposes
	// 	listener.newGameRequest(7);
	// 	repaint();
	// }

	@Override
	public void roleClickEvent(RoleComponent which) {// String which) {
		// System.out.println("role click event intercepted: " + which);
		requestingRole = which;
		listener.playerTakeRoleRequest(which.getName());
	}

	@Override
	public void playerClickEvent(int id) {
		System.out.println("player click event intercepted: " + id);
	}

	// PROTOTYPE_CHAIN: 1
	@Override
	public void playerHoverEvent(int id) {
		mp.displayHoveredPlayer(listener.getPlayerContext(id), playerColors[id]);
	}

	// PROTOTYPE_CHAIN: 1
	@Override
	public void playerUnHoverEvent() {
		mp.removeHoveredPlayer();
	}

	@Override
	public void actionButtonClicked(ActionType at) throws IllegalArgumentException {
		if (at == ActionType.END_TURN) {
			listener.endTurnRequest();
		} else if (at == ActionType.REHEARSE) {
			listener.playerRehearseRequest();
		} else if (at == ActionType.ACT) {
			listener.playerActRequest();
		} else {
			throw new IllegalArgumentException("No such action: " + at);
		}
	}

	// @Override
	// public void rehearseButtonClickEvent() {
	// 	listener.playerRehearseRequest();
	// 	// mp.updateCurrentPlayerDisplay(listener.getPlayerContext(id), playerColors[id]);
	// }

	@Override
	public void roomClickEvent(String where) {
		listener.playerMoveRequest(where);
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
	public void newPlayersEvent(PlayerContext[] contexts) {
		this.players = new PlayerComponent[contexts.length];
		for (int i = 0; i < contexts.length; i++) {
			System.out.println(contexts[i].id);
			PlayerComponent pc = new PlayerComponent(contexts[i], playerColors[contexts[i].id], this);
			players[pc.getId()] = pc;
		}
	}

	@Override
	public void newDayEvent() {
		// TEMP
		System.out.println("new day event intercepted");
		mp.newMessage("new day event intercepted");
	}

	// PROTOTYPE_CHAIN: 3
	@Override
	public void newTurnEvent(int id) {
		mp.update(listener.getPlayerContext(id), playerColors[id]);
		// hard coded for testing
		PlayerContext pc = new PlayerContext();
		pc.acting = true;
		pc.canRehearse = true;
	}

	public void updateCurrentPlayerContext(PlayerContext pc) {
		mp.update(pc, playerColors[pc.id]);
	}

	@Override
	public void sceneWrapEvent(String setName, String sceneName) {
		// TEMP
		System.out.println("scene: " + sceneName + " wrapping in: " + setName);
		bp.wrapOnSet(setName);
	}

	@Override
	public void takeFinishEvent(String setName) {
		bp.takeFinishEvent(setName);
		mp.newMessage("Success! Take finished in " + setName);
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
		System.out.println("moving player: " + players[playerId].getId());
		bp.movePlayer(players[playerId], from, to);
	}

	@Override
	public void playerTakesRoleEvent(int playerId) {
		// this seems bad practice, but a player will
		// never take a roll unless that action was
		// requested from the view.
		players[playerId].takeRole(requestingRole);
		// requestingRole.takePlayerComponent(players[playerId]);
		mp.newMessage("player taking role: " + requestingRole.getName());
		requestingRole = null;
		// bo.assignRoleToPlayer(players[playerId], which);
	}

	@Override
	public void playerActsEvent(String who, int roll) {
		mp.newMessage(who + " acts, rolling a " + roll);
	}

	// @Override
	// public void playerRehearses() {
	// 	// TEMP
	// 	System.out.println("player rehearses");
	// }

}