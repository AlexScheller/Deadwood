package view.graphics;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GraphicalView;
import view.events.ChildEventListener;
import static view.graphics.Dimensions.*;

public class MenuPanel extends JPanel {

	public MenuPanel(ChildEventListener cel) {
		setLayout(null);
		setBounds(BOARD_LENGTH, 0, MENU_LENGTH, MENU_HEIGHT);
		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cel.newGameEvent();
			}
		});
		newGameButton.setBounds(0, 0, 200, 50);
		add(newGameButton);
	}

}