package view.graphics;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import javax.swing.JComponent;

import view.loading.AssetBank;

import model.player.PlayerContext;

/*
 * TODO: This whole class is really rough at the moment, but
 * it serves its purpose for a first version. After code
 * refactoring/cleanup, this class should be revisited.
 */

public class WinnersComponent extends JComponent {

	private int compWidth;
	private int compHeight;
	private PlayerContext[] pacs;
	private char[] colors;
	private int edgeWidth;

	public WinnersComponent(PlayerContext[] pacs, char[] colors) {
		setLayout(null);
		this.pacs = pacs;
		this.colors = colors;
		this.edgeWidth = 4;
		if (pacs.length > 1) {
			int fillWidth = ((pacs.length - 1) * 4);
			int winnersWidth = pacs.length * 40;
			this.compWidth = winnersWidth + fillWidth + (edgeWidth * 2);
		} else {
			this.compWidth = 40 + (edgeWidth * 4);
		}
		this.compHeight = 80;
	}

	public int getHalfWidth() {
		return compWidth / 2;
	}

	public int getHalfHeight() {
		return compHeight / 2;
	}

	public void setBounds(int x, int y) {
		setBounds(x, y, compWidth, compHeight);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		float thickness = 6;
		g2d.setStroke(new BasicStroke(thickness));
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.setColor(Color.black);
		g2d.drawRect(0, 0, getWidth(), getHeight());
		if (pacs.length == 1) {
			g2d.drawString("winner!", 5, 20);
		} else {
			g2d.drawString("winners!", 10, 20);
		}
		AssetBank ab = AssetBank.getInstance();
		if (pacs.length == 1) {
			g.drawImage(ab.getAsset(colors[pacs[0].id] + Integer.toString(pacs[0].rank)), 8, 30, null);
		} else {
			for (int i = 0; i < pacs.length; i++) {
				g.drawImage(ab.getAsset(colors[pacs[i].id] + Integer.toString(pacs[i].rank)), (i * 44) + edgeWidth, 30, null);
			}
		}
	}

}