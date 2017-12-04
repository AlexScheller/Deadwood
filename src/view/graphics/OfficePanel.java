package view.graphics;

import java.awt.Point;
import java.awt.Graphics;

public class OfficePanel extends RoomPanel {

	public OfficePanel() {
		setLayout(null);
		// temporarily hard coded for testing purposes
		this.occupantsOrigin = new Point(9, 459);
		setOpaque(false);
	}

	@Override
	public void paintComponent(Graphics g) {
		// super.paintComponent(g);
		paintOccupants(g);
	}

}