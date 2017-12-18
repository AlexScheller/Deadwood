package view.graphics.menu;

import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Graphics;
import javax.swing.JComponent;

import view.graphics.PlayerInfo;

import view.loading.AssetBank;

public class PlayerInfoComponent extends JComponent {

	private String title;
	private Image playerImage;
	private PlayerInfo pi;
	private boolean playing;
	private Font font;

	public PlayerInfoComponent(Point origin, String title) {
		setLayout(null);
		setBounds(origin.x, origin.y, 200, 200);
		this.title = title;
		this.playing = false;
		this.font = new Font("Helvetica", Font.BOLD, 12);
	}

	public void setInfo(PlayerInfo pi, char color) {
		String assetString = color + Integer.toString(pi.level);
		this.playerImage = AssetBank.getInstance().getAsset(assetString);
		this.pi = pi;
		this.playing = true;
	}

	@Override
	public void paintComponent(Graphics g) {
		if (playing) {
			g.setFont(font);
			g.drawString(title, 0, 10);
			g.drawImage(playerImage, 0, 20, null);
			g.drawString("Dollars: " + pi.dollars, 0, 75);
			g.drawString("Credits: " + pi.credits, 0, 90);
			g.drawString("Rehearsal Chips: " + pi.rehearsalTokens, 0, 105);
		}
	}

}