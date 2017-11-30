package view.graphics;

import java.awt.Image;
import java.awt.Graphics;
import javax.swing.JPanel;

public class CardPanel extends JPanel {

	private Image cardImage;
	private int xOrigin;
	private int yOrigin;
	private final int width = 205;
	private final int height = 115;

	public CardPanel(Image cardImage, int xOrigin, int yOrigin) {
		setLayout(null); // absolute positioning is used
		this.cardImage = cardImage;
		this.xOrigin = xOrigin;
		this.yOrigin = yOrigin;
	}

	@Override
	public void paintComponent(Graphics g) {
		// System.out.println("card painted");
		super.paintComponent(g);
		g.drawImage(cardImage, 0, 0, null);
	}

	public void setBounds() {
		setBounds(xOrigin, yOrigin, width, height);
	}

}