package view.graphics;

import java.util.Map;
import java.util.HashMap; // delete later

import java.awt.Point;
import java.awt.Image;
import java.awt.Graphics;
// import javax.swing.JPanel;

import view.loading.CardInfo; // temporary
import view.loading.RoomPanelInfo;

public class SetPanel extends RoomPanel {

	private CardPanel cardPanel;
	private Point cardPanelOrigin;
	private Image takeImage;
	private Image diceImage;
	private Point[] takeOrigins;
	private int takesFinished;
	private Map<String, Point> extraOrigins;

	// TODO: maybe hard code the image
	public SetPanel(RoomPanelInfo rpi, Image takeImage, Image diceImage) {
		setLayout(null);
		this.takeImage = takeImage;
		this.diceImage = diceImage;
		this.cardPanelOrigin = rpi.cardPanelOrigin;
		this.takeOrigins = rpi.takeOrigins;
		this.takesFinished = takeOrigins.length; // placeholder
		this.extraOrigins = rpi.extraOrigins;
		// this class serves only as a container, therefore
		// it should remain invisible.
		setOpaque(false);
	}

	public void setCardPanel(CardPanel cp) {
		// System.out.println("Card Panel set");
		this.cardPanel = cp;
		cardPanel.setBounds(cardPanelOrigin);
		add(cardPanel);

		// the below hard coded for testing purposes
		CardInfo ci = new CardInfo();
		
		ci.imgNumber = "03";
		Map<String, Point> starringOrigins = new HashMap<>();
		starringOrigins.put("Auctioneer", new Point(53, 47));
		starringOrigins.put("General Custer", new Point(115, 47));
		ci.starringOrigins = starringOrigins;
		
		cardPanel.setNewCard(ci);
		cardPanel.flip();
	}

	@Override
	public void paintComponent(Graphics g) {
		// System.out.println("Set paintComponent called");
		super.paintComponent(g);
		for (int i = 0; i < takesFinished; i++) {
			// TODO: these values should really be adjusted in the 
			// dataset, not here.
			int x = takeOrigins[i].x + 2;
			int y = takeOrigins[i].y + 2;
			g.drawImage(takeImage, x, y, null);
		}
		// placeholder
		for (Point p : extraOrigins.values()) {
			g.drawImage(diceImage, p.x + 3, p.y + 3, null);
		}
	}

	public void setBounds() {
		// these bounds may need adjusting
		setBounds(0, 0, 1200, 900);
	}

}