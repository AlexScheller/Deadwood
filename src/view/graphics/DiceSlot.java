package view.graphics;

import java.awt.Image;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JComponent;

public class DiceSlot extends JComponent {

	private Image die;
	private Point origin;
	private final int height = 40;
	private final int width = 40;

	public DiceSlot() {
		setLayout(null);
		addMouseListener(new MouseAdapter () {
			public void mouseClicked(MouseEvent e) {
				System.out.println("dice slot clicked");
			}
		});
	}

	public void setDieImage(Image die) {
		this.die = die;
	}

	public void setBounds(Point origin) {
		// System.out.println("setting bounds");
		this.origin = origin;
		// this doesn't seem to work when the dice slots
		// are removed from the hierarchy.
		setBounds(origin.x, origin.y, height, width);
	}

	@Override
	public void paintComponent(Graphics g) {
		// System.out.println("drawing dice");
		g.drawImage(die, origin.x, origin.y, null);
	}

}