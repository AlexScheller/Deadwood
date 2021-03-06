package view.graphics.board;

import java.util.Map;
import java.util.HashMap; // delete later

import java.awt.Point;
import java.awt.Image;
import java.awt.Graphics;

import view.loading.CardInfo; // temporary
import view.loading.AssetBank;
import view.loading.RoomInfo;
import view.events.ChildEventListener;
import static view.graphics.Dimensions.*;

public class SetComponent extends RoomComponent {

	private CardComponent card;
	private Point cardOrigin;
	private Image takeImage;
	private Point[] takeOrigins;
	private int takesFinished;
	private boolean filming;
	private Map<String, RoleComponent> extras;

	// TODO: maybe hard code the image
	public SetComponent(RoomInfo ri, Image takeImage,
						ChildEventListener cel) {
		setLayout(null);
		this.name = ri.name;
		this.filming = false;
		this.takeImage = takeImage;
		this.cardOrigin = ri.cardOrigin;
		Point pbcOrigin = new Point(cardOrigin.x, cardOrigin.y + CARD_HEIGHT);
		this.pbc = new PlayerBenchComponent(pbcOrigin);
		// pbc.takePlayerComponent(new PlayerComponent(1, 'o', cel));
		add(pbc);
		// this.occupantsOrigin = new Point(cardOrigin);
		// occupantsOrigin.y += 115;
		this.takeOrigins = ri.takeOrigins;
		this.takesFinished = 0;
		// set up extra roles
		this.extras = new HashMap<>();
		for (String roleName : ri.extraOrigins.keySet()) {
			RoleComponent rc = new RoleComponent(roleName,
												 ri.extraOrigins.get(roleName),
												 cel);
			extras.put(roleName, rc); // HERE IS THE BUG
			// rc.takePlayerComponent(new PlayerComponent(3, 'g', cel));
			add(rc);
		}
		this.card = new CardComponent(name, cardOrigin, cel);
		add(card);
	}

	public void newScene(String which, int cardId) {
		filming = false;
		card.setNewCard(which, cardId);
	}

	@Override
	public void acceptPlayer(PlayerComponent pc) {
		super.acceptPlayer(pc);
		if (!filming) {
			filming = true;
			card.flip();
		}
		// repaint();
	}

	public void takeFinished() {
		takesFinished++;
		repaint();
	}

	public void wrap() {
		for (RoleComponent rc : extras.values()) {
			System.out.println("role name: " + rc.getName());
			if (rc.occupied()) {
				System.out.println(rc.getName() + " occupied");
				super.acceptPlayer(rc.evictPlayerComponent());
			}
		}
		for (RoleComponent rc : card.getRoles()) {
			System.out.println("role name: " + rc.getName());
			if (rc.occupied()) {
				System.out.println(rc.getName() + " occupied");
				super.acceptPlayer(rc.evictPlayerComponent());
			}
		}
		takesFinished = 0;
		card.wrap();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// paintOccupants(g);
		// for (RoleComponent rc : extras.values()) {
		// 	// rc.paintComponent(g);
		// }
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