package view.graphics.menu;

import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Graphics;
import javax.swing.JComponent;

import model.player.PlayerContext;

import view.loading.AssetBank;

public class PlayerInfoComponent extends JComponent {

	private String title;
	private Image playerImage;
	private PlayerContext pc;
	private boolean playing;
	private Font font;

	public PlayerInfoComponent(Point origin, String title) {
		setLayout(null);
		setBounds(origin.x, origin.y, 200, 200);
		this.title = title;
		this.playing = false;
		this.font = new Font("Helvetica", Font.BOLD, 12);
	}

	public void setInfo(PlayerContext pc, char color) {
		String assetString = color + Integer.toString(pc.rank);
		this.playerImage = AssetBank.getInstance().getAsset(assetString);
		this.pc = pc;
		this.playing = true;
	}

	@Override
	public void paintComponent(Graphics g) {
		if (playing) {
			g.setFont(font);
			g.drawString(title + ": " + pc.name, 0, 10);
			g.drawImage(playerImage, 0, 20, null);
			g.drawString("Dollars: " + pc.dollars, 0, 75);
			g.drawString("Credits: " + pc.credits, 0, 90);
			g.drawString("Rehearsal Chips: " + pc.rehearsalTokens, 0, 105);
		}
	}

}