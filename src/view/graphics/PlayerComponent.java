package view.graphics;

import java.awt.Image;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JComponent;

import view.events.ChildEventListener;

import view.loading.AssetBank;
import static view.graphics.Dimensions.*;

public class PlayerComponent extends JComponent {

	private int level;
	private char color;
	private Point origin;

	private ChildEventListener listener;

	public PlayerComponent(int level, char color,
						   ChildEventListener cel) {
		// setLayout(null);
		this.level = level;
		this.color = color;
		addMouseListener(new MouseAdapter () {
			public void mouseClicked(MouseEvent e) {
				// System.out.println("mouse clicked on player");
				cel.playerClickEvent(color + Integer.toString(level));
			}
		});
	}

	public void paintComponent(Graphics g) {
		String assetName = color + Integer.toString(level);
		Image dieImage = AssetBank.getInstance().getAsset(assetName);
		// g.drawImage(dieImage, origin.x, origin.y, null);
		// System.out.println("painting player at: " + origin.x  + ", " + origin.y);
		g.drawImage(dieImage, 0, 0, null);
	}

	public void move(Point p) {
		this.origin = new Point(p);
		// System.out.print("setting bounds in player component: ");
		// System.out.println("{" + origin.x + ", " + origin.y + "}");
		setBounds(origin.x, origin.y, DICE_HEIGHT, DICE_WIDTH);		
	}

}