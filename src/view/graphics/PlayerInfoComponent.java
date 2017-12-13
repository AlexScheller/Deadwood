package view.graphics;

import java.awt.Image;
import java.awt.Point;
import java.awt.Graphics;
import javax.swing.JComponent;

import view.loading.AssetBank;

public class PlayerInfoComponent extends JComponent {

	private String title;
	private Image playerImage;
	private PlayerInfo pi;
	private boolean playing;

	public PlayerInfoComponent(Point origin, String title) {
		setLayout(null);
		setBounds(origin.x, origin.y, 200, 200);
		this.title = title;
		this.playing = false;
	}

	public void setInfo(PlayerInfo pi) {
		String assetString = pi.color + Integer.toString(pi.level);
		this.playerImage = AssetBank.getInstance().getAsset(assetString);
		this.pi = pi;
		this.playing = true;
	}

	@Override
	public void paintComponent(Graphics g) {
		if (playing) {
			g.drawString(title, 0, 10);
			g.drawImage(playerImage, 0, 20, null);
			g.drawString("Dollars: " + pi.dollars, 0, 75);
			g.drawString("Credits: " + pi.credits, 0, 90);
		}
	}

}