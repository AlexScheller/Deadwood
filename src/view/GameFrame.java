package view;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
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
		// It would be odd for the second case to be true if
		// the first one isn't.
		if (sWidth < GAME_WIDTH || sHeight < GAME_HEIGHT) {
			// TODO: Dynamically adjust game size, rather than
			// just switching between the 2 hard coded sizes.
			setSize(COMPACT_GAME_WIDTH, COMPACT_GAME_HEIGHT);
			JScrollPane jsp = new JScrollPane(bp);
			jsp.setBounds(0, 0, COMPACT_BOARD_WIDTH, COMPACT_BOARD_HEIGHT);
			add(jsp);
			mp.setBounds(COMPACT_BOARD_WIDTH, 0,
						 COMPACT_MENU_WIDTH, COMPACT_MENU_HEIGHT);
		} else {
			setSize(GAME_WIDTH, GAME_HEIGHT);
			mp.setBounds(BOARD_WIDTH, 0, MENU_WIDTH, MENU_HEIGHT);
			bp.setBounds(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
			add(bp);
		}
		setTitle("Deadwood!");
		// setLocationRelativeTo(null); // center window
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
		} else if (at == ActionType.UPGRADE) {
			//listener.getPossibleUpgradesRequest();
			mp.displayUpgradeChoices();
		// for debugging
		} else if (at == ActionType.END_DAY) {
			listener.endDayRequest();
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
	public void newDayEvent(int curr, int left) {
		// TEMP
		System.out.println("new day event intercepted");
		// bp.newDay();
		mp.updateDayLabel(curr, left);
		mp.newMessage("new day event intercepted");
		repaint();
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
		// mp.update()
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