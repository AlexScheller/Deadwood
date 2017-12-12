package view.graphics;

import java.awt.Point;
import java.awt.Image;
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
	private Point HoverPlayerPoint;
	// PROTOTYPE_CHAIN: 1
	private Image nullImage;

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
		this.HoverPlayerPoint = new Point(0, 60);
		// PROTOTYPE_CHAIN: 1
		this.nullImage = AssetBank.getInstance().getAsset("g6");
	}

	// PROTOTYPE_CHAIN: 1
	public void displayHoveredPlayer(Image playerImage) {
		getGraphics().drawImage(playerImage, HoverPlayerPoint.x, HoverPlayerPoint.y, null);
	}

	// PROTOTYPE_CHAIN: 1
	public void removeHoveredPlayer() {
		getGraphics().drawImage(nullImage, HoverPlayerPoint.x, HoverPlayerPoint.y, null);
	}

}