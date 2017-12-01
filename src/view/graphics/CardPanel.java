package view.graphics;

import java.awt.Image;
import java.awt.Point;
import java.awt.Graphics;
import javax.swing.JPanel;

public class CardPanel extends JPanel {

	private Image cardImage;
	// private int xOrigin;
	// private int yOrigin;
	// private Point origin;
	private final int width = 205;
	private final int height = 115;

	private Image diceImage;
	// private Map<String, Point> starringOrigins;

	// currently unused
	// public CardPanel(Image cardImage, int xOrigin, int yOrigin) {
	// 	setLayout(null); // absolute positioning is used
	// 	this.cardImage = cardImage;
	// 	this.xOrigin = xOrigin;
	// 	this.yOrigin = yOrigin;
	// }

	public CardPanel(Image cardImage) {// CardPanelInfo cpi, Image diceImage) {//, Point origin) {
		setLayout(null);
		this.cardImage = cardImage;
		this.diceImage = diceImage;
		// this.origin = origin;
		// this.starringOrigins = cpi.starringOrigins;
	}

	@Override
	public void paintComponent(Graphics g) {
		// System.out.println("card painted");
		super.paintComponent(g);
		g.drawImage(cardImage, 0, 0, null);
		// placeholder
		// for (Point p : starringOrigins.values()) {
		// 	g.drawImage(diceImage, p.x + 3, p.y + 3, null);
		// }
	}

	public void setBounds(Point origin) {
		setBounds(origin.x, origin.y, width, height);
	}

}