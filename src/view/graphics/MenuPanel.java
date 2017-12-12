package view.graphics;

import java.awt.Point;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GraphicalView;
import view.events.ChildEventListener;
import view.loading.AssetBank;
import static view.graphics.Dimensions.*;

public class MenuPanel extends JPanel {

	// PROTOTYPE_CHAIN: 1
	private Point PlayerInfoOrigin;
	// PROTOTYPE_CHAIN: 1
	// private Image nullImage;

	public MenuPanel(ChildEventListener cel) {
		setLayout(null);
		setBounds(BOARD_LENGTH, 0, MENU_LENGTH, MENU_HEIGHT);
		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cel.newGameButtonClickEvent();
			}
		});
		newGameButton.setBounds(0, 0, 200, 50);
		add(newGameButton);
		// PROTOTYPE_CHAIN: 1
		this.PlayerInfoOrigin = new Point(0, 100);
		// PROTOTYPE_CHAIN: 1
		// this.nullImage = AssetBank.getInstance().getAsset("g6");
	}

	// PROTOTYPE_CHAIN: 1
	public void displayHoveredPlayer(PlayerInfo pi) {
		Graphics g = getGraphics();
		Point pio = PlayerInfoOrigin;
		String assetString = pi.color + Integer.toString(pi.level);
		Image playerImage = AssetBank.getInstance().getAsset(assetString);
		g.drawImage(playerImage, pio.x, pio.y, null);
		g.drawString("Dollars: " + pi.dollars, pio.x, pio.y + 55);
		g.drawString("Credits: " + pi.credits, pio.x, pio.y + 70);
		// g.drawString("hello", HoverPlayerPoint.x, 100);
		// getGraphics().drawImage(playerImage, HoverPlayerPoint.x, HoverPlayerPoint.y, null);
	}

	// PROTOTYPE_CHAIN: 1
	public void removeHoveredPlayer() {
		repaint();
		// getGraphics().drawImage(nullImage, HoverPlayerPoint.x, HoverPlayerPoint.y, null);
	}

}