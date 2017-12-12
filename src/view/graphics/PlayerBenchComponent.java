package view.graphics;

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
public class PlayerBenchComponent extends JComponent {

	// see model.player.PlayerFactory.java
	// or the rulebook.
	private final int MAX_PLAYERS = 8;

	private Point origin;
	private Point nextPlayerSlot;
	private int numPlayers;

	public PlayerBenchComponent(Point origin) {
		setLayout(null);
		this.numPlayers = 0;
		this.origin = new Point(origin);
		this.nextPlayerSlot = new Point(0, 0);
		// setBounds(origin.x, origin.y, DICE_WIDTH * 4, DICE_HEIGHT * 2);
		setBounds(origin.x, origin.y, DICE_WIDTH * 4, DICE_HEIGHT * 2);
	}

	public void takePlayerComponent(PlayerComponent npc) {
		add(npc);
		npc.move(nextPlayerSlot);
		if (numPlayers == 4) {
			nextPlayerSlot.y += DICE_HEIGHT;
			nextPlayerSlot.x = -DICE_WIDTH;
		}
		nextPlayerSlot.x += DICE_WIDTH;
	}

}