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

public class CardPanel extends JPanel {

	private Image cardFront;
	private Image cardBack;
	private boolean occupied;
	private boolean flipped;
	// private int xOrigin;
	// private int yOrigin;
	// private Point origin;
	private final int width = 205;
	private final int height = 115;

	private Image diceImage; // temporary
	// private Map<String, Point> starringOrigins; // temporary
	private Map<String, DiceSlot> stars;

	// currently unused
	// public CardPanel(Image cardImage, int xOrigin, int yOrigin) {
	// 	setLayout(null); // absolute positioning is used
	// 	this.cardImage = cardImage;
	// 	this.xOrigin = xOrigin;
	// 	this.yOrigin = yOrigin;
	// }

	// public CardPanel(CardPanelInfo cpi) {
	// 	setLayout(null);
	// 	this.cardBack = cpi.cardBack;
	// 	this.cardFront = cpi.cardFront;
	// 	this.starringOrigins = cpi.starringOrigins;
	// }

	// public CardPanel(Image cardImage) {// CardPanelInfo cpi, Image diceImage) {//, Point origin) {
	// 	setLayout(null);
	// 	this.cardImage = cardImage;
	// 	this.diceImage = diceImage;
	// 	// this.origin = origin;
	// 	// this.starringOrigins = cpi.starringOrigins;
	// }

	public CardPanel() {
		setLayout(null);
		this.cardBack = AssetBank.getInstance().getAsset("cardback");
		this.diceImage = AssetBank.getInstance().getAsset("b1");
		this.occupied = false;
		this.flipped = false;
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("Card Clicked");
			}
		});
	}

	public void setNewCard(CardInfo ci) {
		this.cardFront = AssetBank.getInstance().getAsset(ci.imgNumber);
		// this.starringOrigins = ci.starringOrigins;
		this.occupied = true;
		this.stars = new HashMap<>();
		for (String key : ci.starringOrigins.keySet()) {
			DiceSlot ds = new DiceSlot();
			ds.setBounds(ci.starringOrigins.get(key));
			ds.setDieImage(diceImage); // temporarily hard coded
			stars.put(key, ds);
			add(ds);
		}
	}

	public void flip() throws IllegalStateException {
		if (occupied) {
			flipped = !flipped;
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
			// super.paintComponent(g);
			if (flipped) {
				super.paintComponent(g); // draw roles
				g.drawImage(cardFront, 0, 0, null);
				// draw roles
				// for (Point p : starringOrigins.values()) {
				// 	g.drawImage(diceImage, p.x + 1, p.y + 1, null);
				// }
			} else {
				g.drawImage(cardBack, 0, 0, null);
			}
		}
	}

	public void setBounds(Point origin) {
		setBounds(origin.x, origin.y, width, height);
	}

}