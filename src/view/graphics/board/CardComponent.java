package view.graphics.board;

import java.util.Map;
import java.util.HashMap;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;

import view.loading.CardInfo;
import view.loading.AssetBank;
import view.events.ChildEventListener;

import static view.graphics.Dimensions.*;

public class CardComponent extends JComponent {

	private ChildEventListener listener;

	private Image cardFront;
	private Image cardBack;

	private boolean occupied;
	private boolean flipped;
	private boolean wrapped;

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
		this.wrapped = false;
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// System.out.println("Card Clicked");
				// listener.cardClickEvent(setName, title);
				listener.roomClickEvent(setName);
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
		this.wrapped = false;
		// this.flipped = false;
		this.stars = new HashMap<>();
		Map<String, Point> roleOrigins = ab.getRoleOrigins(cardId);
		for (String roleName : roleOrigins.keySet()) {
			RoleComponent rc = new RoleComponent(roleName, 
												 roleOrigins.get(roleName),
												 listener);
			rc.setDieImage(diceImage); // temporarily hard coded
			// rc.takePlayerComponent(new PlayerComponent(4, 'b', listener));
			stars.put(roleName, rc);
		}
		// PlayerComponent pc = new PlayerComponent(3, 'y', listener);
		// pc.move(new Point(0, 0));
		// add(pc);
		// setBounds(origin);
	}

	public void wrap() {
		this.wrapped = true;
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
				if (wrapped) {
					BufferedImage greyScale = new BufferedImage(CARD_WIDTH, CARD_HEIGHT, BufferedImage.TYPE_BYTE_GRAY);
					greyScale.getGraphics().drawImage(cardFront, 0, 0, null);
					Graphics2D g2d = (Graphics2D) g;
					g2d.drawImage(greyScale, 0, 0, null);
					// draw wrapped
					g2d.setColor(Color.RED);
					g2d.setFont(new Font("Helvetica", Font.ITALIC, 40));
					g2d.rotate(Math.toRadians(-20));
					g2d.drawString("WRAPPED", -22, 104);
				} else {
					g.drawImage(cardFront, 0, 0, null);
					for (RoleComponent rc : stars.values()) {
						// System.out.println("hello");
						rc.paintComponent(g);
					}
				}
			} else {
				// System.out.println("drawing card back");
				g.drawImage(cardBack, 0, 0, null);
			}
		}
	}

	public Iterable<RoleComponent> getRoles() {
		return stars.values();
	}

	private void setBounds(Point origin) {
		// System.out.println("setting new origin {" + origin.x + ", " + origin.y + "}");
		setBounds(origin.x, origin.y, width, height);
	}

}