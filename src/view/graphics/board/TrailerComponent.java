package view.graphics.board;

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
		this.pbc = new PlayerBenchComponent(new Point(0, 0));
		add(pbc);
		// setOpaque(false);
	}

}