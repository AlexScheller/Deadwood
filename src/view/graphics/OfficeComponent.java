package view.graphics;

import java.awt.Point;
import java.awt.Graphics;

import view.events.ChildEventListener;

public class OfficeComponent extends RoomComponent {

	public OfficeComponent(ChildEventListener cel) {
		setLayout(null);
		// temporarily hard coded for testing purposes
		this.pbc = new PlayerBenchComponent(new Point(9, 459));
		pbc.takePlayerComponent(new PlayerComponent(1, 'o', cel));
		add(pbc);
	}

	// @Override
	// public void paintComponent(Graphics g) {
	// 	paintOccupants(g);
	// }

}