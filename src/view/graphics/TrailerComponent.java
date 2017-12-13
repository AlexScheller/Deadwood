package view.graphics;

import java.awt.Point;
import java.awt.Graphics;

import view.events.ChildEventListener;

public class TrailerComponent extends RoomComponent {

	public TrailerComponent(ChildEventListener cel) {
		setLayout(null);
		this.name = "trailer";
		// temporarily hard coded for testing purposes
		this.pbc = new PlayerBenchComponent(new Point(991, 248));
		// pbc.takePlayerComponent(new PlayerComponent(1, 'o', cel));
		add(pbc);
		// setOpaque(false);
	}

	// @Override
	// public void paintComponent(Graphics g) {
	// 	// System.out.println("painting trailers");
	// 	paintOccupants(g);
	// }

}