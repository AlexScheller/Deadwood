package view.graphics;

import java.awt.Image;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JComponent;

import view.events.ChildEventListener;
import static view.graphics.Dimensions.*;

public class RoleComponent extends JComponent {

	private String name;
	private Image die;
	private Point origin;

	private PlayerComponent pc;
	// private final int height = 40;
	// private final int width = 40;

	// TODO: instantiate this with a role info object
	// and move setBounds to private, to be called in
	// the constructor.
	public RoleComponent(String name, Point origin,
						 ChildEventListener cel) {
		this.name = name;
		setLayout(null);
		this.origin = origin;
		setBounds(origin.x, origin.y, DICE_HEIGHT, DICE_WIDTH);
		// hard coded for testing purposes
		takePlayerComponent(new PlayerComponent(3, 'g'));
		addMouseListener(new MouseAdapter () {
			public void mouseClicked(MouseEvent e) {
				cel.roleClickEvent(name);
			}
		});
	}

	public void setDieImage(Image die) {
		this.die = die;
	}

	public void takePlayerComponent(PlayerComponent pc) {
		this.pc = pc;
		pc.move(origin);
	}

	public void evictPlayerComponent() {
		this.pc = null;
	}

	// public void setBounds(Point origin) {
	// 	this.origin = origin;
	// 	setBounds(origin.x, origin.y, height, width);
	// }

	private boolean occupied() {
		return (pc != null);
	}

	@Override
	public void paintComponent(Graphics g) {
		// g.drawImage(die, origin.x, origin.y, null);
		if (occupied()) {
			pc.paintComponent(g);
		}
	}

}