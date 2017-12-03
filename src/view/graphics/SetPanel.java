package view.graphics;

import java.util.Map;
import java.util.HashMap; // delete later

import java.awt.Point;
import java.awt.Image;
import java.awt.Graphics;

import view.loading.CardInfo; // temporary
import view.loading.AssetBank;
import view.loading.RoomPanelInfo;

public class SetPanel extends RoomPanel {

	private CardPanel cardPanel;
	private Point cardPanelOrigin;
	private Image takeImage;
	private Image diceImage;
	private Point[] takeOrigins;
	private int takesFinished;
	private Map<String, DiceSlot> extras;

	// TODO: maybe hard code the image
	public SetPanel(RoomPanelInfo rpi, Image takeImage, Image diceImage) {
		setLayout(null);
		this.takeImage = takeImage;
		this.diceImage = diceImage;
		this.cardPanelOrigin = rpi.cardPanelOrigin;
		this.occupantsOrigin = new Point(cardPanelOrigin);
		occupantsOrigin.y += 115;
		this.takeOrigins = rpi.takeOrigins;
		this.takesFinished = takeOrigins.length; // placeholder
		// set up extra roles
		this.extras = new HashMap<>();
		for (String key : rpi.extraOrigins.keySet()) {
			DiceSlot ds = new DiceSlot();
			ds.setBounds(rpi.extraOrigins.get(key));
			ds.setDieImage(diceImage); // temporarily hard coded
			extras.put(key, ds);
			add(ds);
		}
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
		// cardPanel.flip();
	}

	@Override
	public void paintOccupants(Graphics g) {
		// temporarily hard coded for testing purposes
		Image yellowDie = AssetBank.getInstance().getAsset("y3");
		int x = occupantsOrigin.x;
		int y = occupantsOrigin.y;
		for (int i = 0; i < 4; i++) {
			g.drawImage(yellowDie, x + (i * 40) + 2, y, null);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		// super.paintComponent(g);
		paintOccupants(g);
		for (String key : extras.keySet()) {
			extras.get(key).paintComponent(g);
		}
		for (int i = 0; i < takesFinished; i++) {
			// TODO: these values should really be adjusted in the 
			// dataset, not here.
			int x = takeOrigins[i].x + 2;
			int y = takeOrigins[i].y + 2;
			g.drawImage(takeImage, x, y, null);
		}
	}

	public void setBounds() {
		// these bounds may need adjusting
		setBounds(0, 0, 1200, 900);
	}

}