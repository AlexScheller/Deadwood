package view.graphics;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class BoardPanel extends JPanel {

	private Image board;

	public BoardPanel() {
		setLayout(null); // absolute positioning is used
		this.board = new ImageIcon("../resources/board.png").getImage();
		
		// the below is hard-coded for testing
		CardPanel cardPanel = new CardPanel(new ImageIcon("../resources/cards/01.png").getImage(), 21, 69);
		cardPanel.setBounds();
		add(cardPanel);
	}

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