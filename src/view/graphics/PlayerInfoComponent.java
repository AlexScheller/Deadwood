package view.graphics;

import java.awt.Image;
import java.awt.Point;
import java.awt.Graphics;
import javax.swing.JComponent;

import view.loading.AssetBank;

public class PlayerInfoComponent extends JComponent {

	private Image playerImage;
	private PlayerInfo pi;
	private boolean playing;

	public PlayerInfoComponent(Point origin) {
		setLayout(null);
		setBounds(origin.x, origin.y, 200, 200);
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
			g.drawImage(playerImage, 0, 0, null);
			g.drawString("Dollars: " + pi.dollars, 0, 55);
			g.drawString("Credits: " + pi.credits, 0, 70);
		}
	}

}