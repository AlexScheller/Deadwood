package view;

import javax.swing.JFrame;

import view.graphics.BoardPanel;

public class GraphicalView extends JFrame {

	public GraphicalView() {
		initUI();
	}

	private void initUI() {
		setLayout(null); // absolute positioning is used

		BoardPanel bp = new BoardPanel();
		add(bp);
		bp.setBounds();

		setSize(1200, 900);
		setTitle("Board");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}