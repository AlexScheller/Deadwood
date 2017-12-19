package view.graphics.board;

import java.awt.Image;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JComponent;

import view.events.ChildEventListener;

// import view.graphics.PlayerInfo;

import model.player.PlayerContext;

import view.loading.AssetBank;
import static view.graphics.Dimensions.*;

public class PlayerComponent extends JComponent {

	private int id;
	private int rank;
	private int dollars;
	private int credits;
	private char color;
	private Point origin;

	private RoleComponent currentRole;
	private RoomComponent currentRoom;

	private ChildEventListener listener;

	public PlayerComponent(PlayerContext pc, char color, ChildEventListener cel) {
		this.id = pc.id;
		this.rank = pc.rank;
		this.dollars = pc.dollars;
		this.credits = pc.credits;
		this.color = color;
		System.out.println("setting color: " + color);
		addMouseListener(new MouseAdapter () {
			public void mouseClicked(MouseEvent e) {
				// System.out.println("mouse clicked on player");
				cel.playerClickEvent(id);
			}
			// PROTOTYPE_CHAIN: 1
			public void mouseEntered(MouseEvent e) {
				cel.playerHoverEvent(id);
			}
			// PROTOTYPE_CHAIN: 1
			public void mouseExited(MouseEvent e) {
				cel.playerUnHoverEvent();
			}
		});
	}

	public int getId() {
		return id;
	}

	// IN_PROGRESS
	public void takeRole(RoleComponent rc) {
		currentRoom.evictPlayer(this.id);
		rc.takePlayerComponent(this);
		this.currentRole = rc;
	}

	public void leaveRole() {
		this.currentRole = null;
	}

	public void moveToRoom(RoomComponent rc) {
		this.currentRoom = rc;
		rc.acceptPlayer(this);
	}

	public void paintComponent(Graphics g) {
		String assetName = color + Integer.toString(rank);
		Image dieImage = AssetBank.getInstance().getAsset(assetName);
		// g.drawImage(dieImage, origin.x, origin.y, null);
		// System.out.println("painting player at: " + origin.x  + ", " + origin.y);
		g.drawImage(dieImage, 0, 0, null);
	}

	public void move(Point p) {
		this.origin = new Point(p);
		// System.out.print("setting bounds in player component: ");
		// System.out.println("{" + origin.x + ", " + origin.y + "}");
		setBounds(origin.x, origin.y, DICE_HEIGHT, DICE_WIDTH);		
	}

}