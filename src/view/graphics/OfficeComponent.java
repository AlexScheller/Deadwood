package view.graphics;

import java.awt.Point;
import java.awt.Graphics;

public class OfficeComponent extends RoomComponent {

	public OfficeComponent() {
		setLayout(null);
		// temporarily hard coded for testing purposes
		this.occupantsOrigin = new Point(9, 459);
	}

	@Override
	public void paintComponent(Graphics g) {
		paintOccupants(g);
	}

}