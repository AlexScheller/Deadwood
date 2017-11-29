package view.graphics;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class BoardPanel extends JPanel {

	private Image board;

	public BoardPanel() {
		this.board = new ImageIcon("../resources/board.png").getImage();
	}

	@Override
	public void paintComponent(Graphics g) {
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