package view.graphics;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import view.loading.RoomPanelInfo;

public class BoardPanel extends JPanel {

	private Image board;
	private Map<String, RoomPanel> rooms;

	public BoardPanel(List<RoomPanelInfo> rpis) {
		setLayout(null); // absolute positioning is used
		this.board = new ImageIcon("../resources/board.png").getImage();
		
		this.rooms = new HashMap<>();
		// the below is hard-coded for testing
		Image placeHolderCardImage = new ImageIcon("../resources/cards/01.png").getImage();
		Image clapper  = new ImageIcon("../resources/clapper.png").getImage();
		Image die = new ImageIcon("../resources/dice/g6.png").getImage();
		for (RoomPanelInfo rpi : rpis) {
			// CardPanel ncp = new CardPanel(placeHolderCardImage,
			// 							  rpi.cardPanelOrigin);
			// ncp.setBounds();
			CardPanel ncp = new CardPanel(placeHolderCardImage);
			SetPanel sp = new SetPanel(rpi, clapper, die);
			sp.setBounds();
			sp.setCardPanel(ncp);
			rooms.put(rpi.name, sp);
			add(sp);
		}
		//CardPanel cardPanel = new CardPanel(placeHolderCard, 21, 69);
		
		// cardPanel.setBounds();
		// add(cardPanel);
		
		// SetPanel sp = new SetPanel();
		// sp.setCardPanel(cardPanel);
		// sp.setBounds();
		// add(sp);
		

		// NOTE: It might be bad practice/a bug to not have "bounds"
		// in the SetPanel object, rather only it's children have
		// bounds.
	}

	// public BoardPanel() {
	// 	setLayout(null); // absolute positioning is used
	// 	this.board = new ImageIcon("../resources/board.png").getImage();
		
	// 	// the below is hard-coded for testing
	// 	CardPanel cardPanel = new CardPanel(new ImageIcon("../resources/cards/01.png").getImage(), 21, 69);
	// 	// cardPanel.setBounds();
	// 	// add(cardPanel);
		
	// 	SetPanel sp = new SetPanel();
	// 	sp.setCardPanel(cardPanel);
	// 	sp.setBounds();
	// 	add(sp);
	// 	// NOTE: It might be bad practice/a bug to not have "bounds"
	// 	// in the SetPanel object, rather only it's children have
	// 	// bounds.
	// }

	@Override
	public void paintComponent(Graphics g) {
		// System.out.println("Board painted");
		super.paintComponent(g);
		g.drawImage(board, 0, 0, null);
	}

	// TODO: decide whether to wrap setbounds here, and have the class
	// itself determine where it should go, or have the GraphicalView
	// decide. I lean towards the former, since the layout of the game
	// is being determined absolutely anyway.
	public void setBounds() {
		setBounds(0, 0, board.getWidth(null), board.getHeight(null));
	}

}