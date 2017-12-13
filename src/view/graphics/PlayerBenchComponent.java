package view.graphics;

import java.util.Map;
import java.util.HashMap;

import java.awt.Point;
import javax.swing.JComponent;

import static view.graphics.Dimensions.*;
/*
 * The PlayerBenchComponent class is meant to serve
 * as a container for PlayerComponents inside of rooms.
 * The RoomComponents will simply provide an origin
 * Point for the bench, and the bench will handle the
 * positioning/display of players internally.
 */

// TODO: there's a bug where moving too fast from one
// player component to another fails to trigger the
// on hover event.
public class PlayerBenchComponent extends JComponent {

	// see model.player.PlayerFactory.java
	// or the rulebook.
	private final int MAX_PLAYERS = 8;

	private Point origin;
	private Point nextPlayerSlot;
	private int numPlayers;

	private Map<Integer, PlayerComponent> players;

	public PlayerBenchComponent(Point origin) {
		setLayout(null);
		this.players = new HashMap<>();
		this.numPlayers = 0;
		this.origin = new Point(origin);
		this.nextPlayerSlot = new Point(0, 0);
		// setBounds(origin.x, origin.y, DICE_WIDTH * 4, DICE_HEIGHT * 2);
		setBounds(origin.x, origin.y, (DICE_WIDTH * 4) + 8, (DICE_HEIGHT * 2) + 2);
	}

	public void takePlayerComponent(PlayerComponent npc) throws IllegalStateException {
		if (numPlayers == 8) {
			throw new IllegalStateException("bench already contains 8 players");
		}
		numPlayers++;
		players.put(npc.getId(), npc);
		add(npc);
		npc.move(nextPlayerSlot);
		if (numPlayers == 4) {
			nextPlayerSlot.y += DICE_HEIGHT + 2;
			nextPlayerSlot.x = -DICE_WIDTH;
		}
		nextPlayerSlot.x += DICE_WIDTH + 2;
	}

	public void removePlayerComponent(int playerId) throws IllegalStateException {
		// TEMP
		if (numPlayers == 0) {
			throw new IllegalStateException("bench is empty");
		}
		numPlayers--;
		remove(players.remove(playerId));
		// fill the gap
		int index = 1;
		Point curr = new Point(0, 0);
		for (PlayerComponent pc : players.values()) {
			pc.move(curr);
			if (index == 4) {
				curr.y += DICE_HEIGHT + 2;
				curr.x = -DICE_WIDTH;
			}
			curr.x += DICE_WIDTH + 2;
			index++;
		}
		nextPlayerSlot.move(curr.x, curr.y);
		// repaint()
	}

}