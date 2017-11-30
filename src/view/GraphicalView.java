package view;

import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

import view.graphics.BoardPanel;
import view.graphics.CardPanel;

public class GraphicalView extends JFrame {

	public GraphicalView(BoardPanel bp) {
		initUI(bp);
	}

	private void initUI(BoardPanel bp) {
		setLayout(null); // absolute positioning is used

		// BoardPanel bp = new BoardPanel();
		add(bp);
		bp.setBounds();
		// Image cardImage = new ImageIcon("../resources/cards/01.png").getImage();
		// CardPanel CardPanel = new CardPanel(cardImage, 50, 50);
		// add(CardPanel);
		// CardPanel.setBounds();

		setSize(1200, 900);
		setTitle("Board");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}