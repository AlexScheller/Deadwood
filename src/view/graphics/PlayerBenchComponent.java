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

	public void takePlayerComponent(PlayerComponent npc) {
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

	public void removePlayerComponent(int playerId) {
		// TEMP
		remove(players.get(playerId));
	}

}