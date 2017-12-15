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
	// private Point hoveredPlayerInfoOrigin;
	private PlayerInfoComponent hpic;
	// PROTOTYPE_CHAIN: 3
	// private Point currentPlayerInfoOrigin;
	// Image currentPlayerImage
	private PlayerInfoComponent cpic;

	private PossibleActionsComponent pac;

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

		this.pac = new PossibleActionsComponent(cel);
		pac.setBounds(0, 300, 200, 300);
		add(pac);
		// PROTOTYPE_CHAIN: 2
		// JButton endTurnButton = new JButton("End Turn");
		// endTurnButton.addActionListener(new ActionListener () {
		// 	public void actionPerformed(ActionEvent e) {
		// 		cel.endTurnButtonClickEvent();
		// 	}
		// });
		// endTurnButton.setBounds(0, 300, 200, 50);
		// add(endTurnButton);

		// PROTOTYPE_CHAIN: 1
		// this.hoveredPlayerInfoOrigin = new Point(0, 100);

		this.hpic = new PlayerInfoComponent(new Point(0, 100), "Hovered player:");
		// PROTOTYPE_CHAIN: 3
		// this.currentPlayerInfoOrigin = new Point(0, 200);
		this.cpic = new PlayerInfoComponent(new Point(0, 200), "Current player:");
		add(cpic);
	}

	// PROTOTYPE_CHAIN: 1
	public void displayHoveredPlayer(PlayerInfo pi) {
		hpic.setInfo(pi);
		add(hpic);
		repaint();
		// repaint();
		// Graphics g = getGraphics();
		// Point hpio = hoveredPlayerInfoOrigin;
		// String assetString = pi.color + Integer.toString(pi.level);
		// Image playerImage = AssetBank.getInstance().getAsset(assetString);
		// g.drawImage(playerImage, hpio.x, hpio.y, null);
		// g.drawString("Dollars: " + pi.dollars, hpio.x, hpio.y + 55);
		// g.drawString("Credits: " + pi.credits, hpio.x, hpio.y + 70);
		// g.drawString("hello", HoverPlayerPoint.x, 100);
		// getGraphics().drawImage(playerImage, HoverPlayerPoint.x, HoverPlayerPoint.y, null);
	}

	// PROTO_TYPE_CHAIN: 3
	public void displayCurrentPlayer(PlayerInfo pi) {
		cpic.setInfo(pi);
		repaint();
	}
	// public void displayCurrentPlayer(PlayerInfo pi) {
	// 	System.out.println("displaying new player");
	// 	repaint();
	// 	Graphics g = getGraphics();
	// 	Point cpio = currentPlayerInfoOrigin;
	// 	String assetString = pi.color + Integer.toString(pi.level);
	// 	Image playerImage = AssetBank.getInstance().getAsset(assetString);
	// 	g.drawImage(playerImage, cpio.x, cpio.y, null);
	// 	g.drawString("Dollars: " + pi.dollars, cpio.x, cpio.y + 55);
	// 	g.drawString("Credits: " + pi.credits, cpio.x, cpio.y + 70);
	// }

	// PROTOTYPE_CHAIN: 1
	public void removeHoveredPlayer() {
		remove(hpic);
		repaint();
		// getGraphics().drawImage(nullImage, HoverPlayerPoint.x, HoverPlayerPoint.y, null);
	}

}