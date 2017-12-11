package view.graphics;

import java.util.Map;
import java.util.HashMap; // delete later

import java.awt.Point;
import java.awt.Image;
import java.awt.Graphics;

import view.loading.CardInfo; // temporary
import view.loading.AssetBank;
import view.loading.RoomInfo;
import view.events.ChildEventListener;

public class SetComponent extends RoomComponent {

	private CardComponent card;
	private Point cardOrigin;
	private Image takeImage;
	private Image diceImage;
	private Point[] takeOrigins;
	private int takesFinished;
	private Map<String, RoleComponent> extras;

	// TODO: maybe hard code the image
	public SetComponent(RoomInfo ri, Image takeImage,
					Image diceImage, ChildEventListener cel) {
		setLayout(null);
		this.takeImage = takeImage;
		this.diceImage = diceImage;
		this.cardOrigin = ri.cardOrigin;
		this.occupantsOrigin = new Point(cardOrigin);
		occupantsOrigin.y += 115;
		this.takeOrigins = ri.takeOrigins;
		this.takesFinished = takeOrigins.length; // placeholder
		// set up extra roles
		this.extras = new HashMap<>();
		for (String name : ri.extraOrigins.keySet()) {
			RoleComponent rc = new RoleComponent(name,
												 ri.extraOrigins.get(name),
												 cel);
			rc.setDieImage(diceImage); // temporarily hard coded
			extras.put(name, rc);
			add(rc);
		}
		this.card = new CardComponent(cardOrigin, cel);
		add(card);
		// cardPanel.setBounds(cardPanelOrigin);
		// this class serves only as a container, therefore
		// it should remain invisible.
		// setOpaque(false);
		// TODO: investigate this
		// PlayerComponent pc = new PlayerComponent(3, 'y', cel);
		// // pc.setBounds(0, 0, 40, 40);
		// pc.move(new Point(cardPanelOrigin.x - 40, cardPanelOrigin.y - 40));
		// add(pc);
	}

	// public void setCardPanel(CardPanel cp) {
	// 	// System.out.println("Card Panel set");
	// 	this.cardPanel = cp;
	// 	// cardPanel.setBounds(cardPanelOrigin);
	// 	add(cardPanel);

	// 	// the below hard coded for testing purposes
	// 	// CardInfo ci = new CardInfo();
		
	// 	// ci.imgId = 22;
	// 	// ci.title = "The Life and Times of John Skywater";
	// 	// Map<String, Point> starringOrigins = new HashMap<>();
	// 	// starringOrigins.put("Auctioneer", new Point(53, 47));
	// 	// starringOrigins.put("General Custer", new Point(115, 47));
	// 	// ci.starringOrigins = starringOrigins;
		
	// 	// cardPanel.setNewCard(ci);
	// 	// cardPanel.flip();
	// }

	public void newScene(String which, int cardId) {
		card.setNewCard(which, cardId);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintOccupants(g);
		for (RoleComponent rc : extras.values()) {
			// rc.paintComponent(g);
		}
		for (int i = 0; i < takesFinished; i++) {
			// TODO: these values should really be adjusted in the 
			// dataset, not here.
			int x = takeOrigins[i].x + 2;
			int y = takeOrigins[i].y + 2;
			g.drawImage(takeImage, x, y, null);
		}
		// cardPanel.paintComponent(g);
	}

	public void setBounds() {
		// these bounds may need adjusting
		setBounds(0, 0, 1200, 900);
	}

}