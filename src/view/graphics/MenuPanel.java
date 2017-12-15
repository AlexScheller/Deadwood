package view.graphics;

import java.awt.Font;
import java.awt.Point;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GraphicalView;
import view.events.ChildEventListener;
import view.loading.AssetBank;
import static view.graphics.Dimensions.*;

public class MenuPanel extends JPanel {

	private PlayerInfoComponent hpic;
	private PlayerInfoComponent cpic;

	private PossibleActionsComponent pac;

	private JTextArea jta;
	// private JScrollPane jsp;

	public MenuPanel(ChildEventListener cel) {
		setLayout(null);
		setBounds(BOARD_LENGTH, 0, MENU_LENGTH, MENU_HEIGHT);
		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cel.newGameButtonClickEvent();
			}
		});
		newGameButton.setBounds(0, 0, 200, 50);
		add(newGameButton);

		this.hpic = new PlayerInfoComponent(new Point(0, 50), "Hovered player:");
		// PROTOTYPE_CHAIN: 3
		// this.currentPlayerInfoOrigin = new Point(0, 200);
		this.cpic = new PlayerInfoComponent(new Point(0, 150), "Current player:");
		add(cpic);

		this.pac = new PossibleActionsComponent(cel);
		pac.setBounds(0, 250, 200, 200);
		add(pac);

		this.jta = new JTextArea(10, 10);
		jta.setFont(new Font("Helvetica", Font.BOLD, 12));
		jta.setLineWrap(true);
		jta.setWrapStyleWord(true);
		JScrollPane jsp = new JScrollPane(jta);
		jsp.setBounds(0, 450, 200, 200);
		add(jsp);
	}

	// PROTOTYPE_CHAIN: 1
	public void displayHoveredPlayer(PlayerInfo pi, char color) {
		hpic.setInfo(pi, color);
		add(hpic);
		repaint();
	}

	// PROTO_TYPE_CHAIN: 3
	public void displayCurrentPlayer(PlayerInfo pi, char color) {
		cpic.setInfo(pi, color);
		repaint();
	}

	// PROTOTYPE_CHAIN: 1
	public void removeHoveredPlayer() {
		remove(hpic);
		repaint();
		// getGraphics().drawImage(nullImage, HoverPlayerPoint.x, HoverPlayerPoint.y, null);
	}

	public void newMessage(String msg) {
		jta.append("> " + msg + "\n");
	}

}