package view.graphics;

import java.awt.Point;
import java.awt.Graphics;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.events.ChildEventListener;

public class TrailerComponent extends RoomComponent {

	public TrailerComponent(ChildEventListener cel) {
		setLayout(null);
		// hard coded from the board image
		setBounds(990, 250, 200, 200);
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				cel.roomClickEvent("trailer");
			}
		});
		this.name = "trailer";
		// temporarily hard coded for testing purposes
		this.pbc = new PlayerBenchComponent(new Point(0, 0));
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