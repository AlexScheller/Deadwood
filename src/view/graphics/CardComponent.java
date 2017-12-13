package view.graphics;

import java.util.Map;
import java.util.HashMap;

import java.awt.Image;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;

import view.loading.CardInfo;
import view.loading.AssetBank;
import view.events.ChildEventListener;

public class CardComponent extends JComponent {

	private ChildEventListener listener;

	private Image cardFront;
	private Image cardBack;

	private boolean occupied;
	private boolean flipped;

	private String title;
	private String setName;

	private Point origin;
	private final int width = 205;
	private final int height = 115;

	private Image diceImage; // temporary
	private Map<String, RoleComponent> stars;

	public CardComponent(String setName, Point origin, ChildEventListener cel) {
	// public CardPanel(InputEventListener iel) {
		this.setName = setName;
		this.listener = cel;
		this.title = "no scene";
		setLayout(null);
		setBounds(origin);
		this.origin = origin;
		this.cardBack = AssetBank.getInstance().getAsset("cardback");
		this.diceImage = AssetBank.getInstance().getAsset("b1");
		this.occupied = false;
		this.flipped = false;
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// System.out.println("Card Clicked");
				listener.cardClickEvent(setName, title);
				// try {
				// 	flip();
				// } catch (IllegalStateException ex) {
				// 	ex.printStackTrace();
				// 	System.exit(1);
				// }
			}
		});
	}

	// public void setNewCard(String title, int cardId) {
	public void setNewCard(String title, int cardId) {
		this.title = title;
		AssetBank ab = AssetBank.getInstance();
		this.cardFront = ab.getAsset(cardId);
		this.occupied = true;
		// this.flipped = false;
		this.stars = new HashMap<>();
		Map<String, Point> roleOrigins = ab.getRoleOrigins(cardId);
		for (String name : roleOrigins.keySet()) {
			RoleComponent rc = new RoleComponent(name, 
												 roleOrigins.get(name),
												 listener);
			rc.setDieImage(diceImage); // temporarily hard coded
			// rc.takePlayerComponent(new PlayerComponent(4, 'b', listener));
			stars.put(name, rc);
		}
		// PlayerComponent pc = new PlayerComponent(3, 'y', listener);
		// pc.move(new Point(0, 0));
		// add(pc);
		// setBounds(origin);
	}
	
	// TODO: look into speeding this up
	public void flip() throws IllegalStateException {
		if (occupied) {
			if (!flipped) {
				for (String key : stars.keySet()) {
					add(stars.get(key));
				}
				flipped = true;
				repaint();
			}
			// } else {
			// 	for (String key : stars.keySet()) {
			// 		remove(stars.get(key));
			// 	}
			// }
			// flipped = !flipped
			// repaint();
		} else {
			throw new IllegalStateException("Cannot flip, Card Panel unoccupied.");
		}
	}

	public void removeCard() {
		this.cardFront = null;
		this.stars = null;
		this.occupied = false;
	}

	@Override
	public void paintComponent(Graphics g) {
		// System.out.print("painting card: " + title);
		// System.out.println(" at origin {" + origin.x + ", " + origin.y + "}");
		if (occupied) {
			if (flipped) {
				g.drawImage(cardFront, 0, 0, null);
				for (RoleComponent rc : stars.values()) {
					// System.out.println("hello");
					rc.paintComponent(g);
				}
			} else {
				// System.out.println("drawing card back");
				g.drawImage(cardBack, 0, 0, null);
			}
		}
	}

	private void setBounds(Point origin) {
		// System.out.println("setting new origin {" + origin.x + ", " + origin.y + "}");
		setBounds(origin.x, origin.y, width, height);
	}

}