package view.graphics;

import java.awt.Point;
import java.awt.Graphics;

public class TrailerPanel extends RoomPanel {

	public TrailerPanel() {
		setLayout(null);
		// temporarily hard coded for testing purposes
		this.occupantsOrigin = new Point(991, 248);
		setOpaque(false);
	}

	@Override
	public void paintComponent(Graphics g) {
		// super.paintComponent(g);
		paintOccupants(g);
	}

}