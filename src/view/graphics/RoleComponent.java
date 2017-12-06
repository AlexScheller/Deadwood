package view.graphics;

import java.awt.Image;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JComponent;

import view.events.InputEventListener;

public class RoleComponent extends JComponent {

	private String name;
	private Image die;
	private Point origin;
	private final int height = 40;
	private final int width = 40;

	// TODO: instantiate this with a role info object
	// and move setBounds to private, to be called in
	// the constructor.
	public RoleComponent(String name, InputEventListener iel) {
		this.name = name;
		setLayout(null);
		addMouseListener(new MouseAdapter () {
			public void mouseClicked(MouseEvent e) {
				iel.roleClickEvent(name);
			}
		});
	}

	public void setDieImage(Image die) {
		this.die = die;
	}

	public void setBounds(Point origin) {
		this.origin = origin;
		setBounds(origin.x, origin.y, height, width);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(die, origin.x, origin.y, null);
	}

}