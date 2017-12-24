package view.graphics.board;

import java.awt.Point;
import java.awt.Graphics;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.events.ChildEventListener;

public class OfficeComponent extends RoomComponent {

	public OfficeComponent(ChildEventListener cel) {
		setLayout(null);
		setBounds(8, 460, 200, 200);
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				cel.roomClickEvent("office");
			}
		});
		this.name = "office";
		// temporarily hard coded for testing purposes
		this.pbc = new PlayerBenchComponent(new Point(0, 0));
		// pbc.takePlayerComponent(new PlayerComponent(1, 'o', cel));
		add(pbc);
	}

}