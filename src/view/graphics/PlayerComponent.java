package view.graphics;

import java.awt.Image;
import java.awt.Point;
import java.awt.Graphics;
import javax.swing.JComponent;

import view.loading.AssetBank;
import static view.graphics.Dimensions.*;

public class PlayerComponent extends JComponent {

	private int level;
	private char color;
	private Point origin;

	public PlayerComponent(int level, char color) {
		setLayout(null);
		this.level = level;
		this.color = color;
	}

	public void paintComponent(Graphics g) {
		String assetName = color + Integer.toString(level);
		Image dieImage = AssetBank.getInstance().getAsset(assetName);
		g.drawImage(dieImage, origin.x, origin.y, null);
	}

	public void move(Point p) {
		this.origin = p;
		setBounds(origin.x, origin.y, DICE_HEIGHT, DICE_WIDTH);		
	}

}