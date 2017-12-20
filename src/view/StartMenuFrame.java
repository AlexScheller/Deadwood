package view;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JButton;

public class StartMenuFrame extends JFrame {

	public StartMenuFrame(GraphicalView gv) {
		initUI(gv);
	}

	private void initUI(GraphicalView gv) {
		setLayout(null);
		setSize(400, 200);
		setTitle("Deadwood!");
		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gv.newGameButtonClickEvent();
			}
		});
		newGameButton.setBounds((getWidth() / 2) - 50,
								(getHeight() / 2) - 25,
								100, 50);
		add(newGameButton);
		setLocationRelativeTo(null); // center window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}