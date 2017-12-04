package view;

import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

import view.graphics.CardPanel;
import view.graphics.MenuPanel;
import view.graphics.BoardPanel;

public class GraphicalView extends JFrame {

	public GraphicalView(BoardPanel bp, MenuPanel mp) {
		initUI(bp, mp);
	}

	private void initUI(BoardPanel bp, MenuPanel mp) {
		setLayout(null); // absolute positioning is used

		// BoardPanel bp = new BoardPanel();
		add(bp);
		bp.setBounds();
		// setBounds is called in MenuPanel's constructor
		add(mp);

		// Image cardImage = new ImageIcon("../resources/cards/01.png").getImage();
		// CardPanel CardPanel = new CardPanel(cardImage, 50, 50);
		// add(CardPanel);
		// CardPanel.setBounds();

		// board size + menu size
		setSize(1200 + 200, 900);
		// oddly the image was still partially cropped
		// when tested on windows, until these values were
		// used:
		// setSize(1216, 939);
		setTitle("Deadwood!");
		// centers the window on startup
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}