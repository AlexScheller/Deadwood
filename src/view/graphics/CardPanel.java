package view.graphics;

import java.util.Map;
import java.util.HashMap;

import java.awt.Image;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

import view.loading.CardInfo;
import view.loading.AssetBank;
import view.events.InputEventListener;

public class CardPanel extends JPanel {

	private InputEventListener iel;

	private Image cardFront;
	private Image cardBack;

	private boolean occupied;
	private boolean flipped;

	private String title;

	private final int width = 205;
	private final int height = 115;

	private Image diceImage; // temporary
	private Map<String, RoleComponent> stars;

	public CardPanel(InputEventListener iel) {
		this.iel = iel;
		setLayout(null);
		this.cardBack = AssetBank.getInstance().getAsset("cardback");
		this.diceImage = AssetBank.getInstance().getAsset("b1");
		this.occupied = false;
		this.flipped = false;
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// System.out.println("Card Clicked");
				iel.cardClickEvent(title);
				flip();
			}
		});
	}

	public void setNewCard(CardInfo ci) {
		this.title = ci.title;
		this.cardFront = AssetBank.getInstance().getAsset(ci.imgId);
		this.occupied = true;
		this.flipped = false;
		this.stars = new HashMap<>();
		for (String name : ci.starringOrigins.keySet()) {
			RoleComponent rc = new RoleComponent(name, iel);
			rc.setBounds(ci.starringOrigins.get(name));
			rc.setDieImage(diceImage); // temporarily hard coded
			stars.put(name, rc);
		}
	}

	// TODO: look into speeding this up
	public void flip() throws IllegalStateException {
		if (occupied) {
			if (!flipped) {
				for (String key : stars.keySet()) {
					add(stars.get(key));
				}
			} else {
				for (String key : stars.keySet()) {
					remove(stars.get(key));
				}
			}
			flipped = !flipped;
			repaint();
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
		if (occupied) {
			if (flipped) {
				g.drawImage(cardFront, 0, 0, null);
				for (String key : stars.keySet()) {
					stars.get(key).paintComponent(g);
				}
			} else {
				g.drawImage(cardBack, 0, 0, null);
			}
		}
	}

	public void setBounds(Point origin) {
		setBounds(origin.x, origin.y, width, height);
	}

}