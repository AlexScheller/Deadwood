package view;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class StartMenuFrame extends JFrame {

	public StartMenuFrame(GraphicalView gv) {
		initUI(gv);
	}

	private void initUI(GraphicalView gv) {
		setLayout(null);
		setSize(400, 200);
		setTitle("Deadwood!");
		Integer[] numPlayers = {2, 3, 4, 5, 6, 7, 8};
		JComboBox numPlayerChooser = new JComboBox(numPlayers);
		numPlayerChooser.setSelectedIndex(0);
		numPlayerChooser.setBounds((getWidth() / 2) + 50,
								   (getHeight() / 2 - 25),
								   50, 50);
		add(numPlayerChooser);
		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gv.newGameButtonClickEvent((Integer) numPlayerChooser.getSelectedItem());
			}
		});
		newGameButton.setBounds((getWidth() / 2) - 100,
								(getHeight() / 2) - 25,
								150, 50);
		newGameButton.setFocusPainted(false);
		add(newGameButton);
		setLocationRelativeTo(null); // center window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}