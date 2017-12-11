package view.graphics;

import java.awt.Point;
import java.awt.Graphics;

public class TrailerComponent extends RoomComponent {

	public TrailerComponent() {
		setLayout(null);
		// temporarily hard coded for testing purposes
		this.occupantsOrigin = new Point(991, 248);
		// setOpaque(false);
	}

	@Override
	public void paintComponent(Graphics g) {
		// System.out.println("painting trailers");
		paintOccupants(g);
	}

}