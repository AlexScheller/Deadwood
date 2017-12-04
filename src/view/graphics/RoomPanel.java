package view.graphics;

import java.awt.Point;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.JPanel;

import view.loading.AssetBank;

public abstract class RoomPanel extends JPanel {

	protected Point occupantsOrigin;
	protected Image yellowDie = AssetBank.getInstance().getAsset("y3");

	protected void paintOccupants(Graphics g) {
		// temporarily hard coded for testing purposes
		int x = occupantsOrigin.x;
		int y = occupantsOrigin.y;
		for (int i = 0; i < 4; i++) {
			g.drawImage(yellowDie, x + (i * 40) + 2, y, null);
		}
	}

	public void setBounds() {
		// these bounds may need adjusting
		setBounds(0, 0, 1200, 900);
	}

}