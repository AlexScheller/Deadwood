package view.graphics;

import java.util.Map;

import java.awt.Image;
import java.awt.Point;
import java.awt.Graphics;
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
	private Map<String, Point> starringOrigins;

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
		this.cardBack = AssetBank.getInstance().getAsset("cardback");
		this.diceImage = AssetBank.getInstance().getAsset("b1");
		this.occupied = false;
		this.flipped = false;
	}

	public void setNewCard(CardInfo ci) {
		this.cardFront = AssetBank.getInstance().getAsset(ci.imgNumber);
		this.starringOrigins = ci.starringOrigins;
		this.occupied = true;
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
		this.starringOrigins = null;
		this.occupied = false;
	}

	@Override
	public void paintComponent(Graphics g) {
		if (occupied) {
			super.paintComponent(g);
			if (flipped) {
				g.drawImage(cardFront, 0, 0, null);
				// draw roles
				for (Point p : starringOrigins.values()) {
					g.drawImage(diceImage, p.x + 1, p.y + 1, null);
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