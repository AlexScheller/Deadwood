package view.graphics;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JButton;

public class MenuPanel extends JPanel {

	public MenuPanel() {
		setLayout(null);
		setBounds(1200, 0, 100, 900);
		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("New Game button clicked");
			}
		});
		newGameButton.setBounds(0, 0, 200, 50);
		add(newGameButton);
	}

}