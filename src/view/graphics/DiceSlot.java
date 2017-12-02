package view.graphics;

import java.awt.Image;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JComponent;

public class DiceSlot extends JComponent {

	private Image die;
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
		setBounds(origin.x, origin.y, height, width);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(die, 0, 0, null);
	}

}