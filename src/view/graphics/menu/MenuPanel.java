package view.graphics.menu;

import java.awt.Font;
import java.awt.Point;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GraphicalView;
import view.events.ChildEventListener;
import view.loading.AssetBank;

import model.player.PlayerContext;
import static view.graphics.Dimensions.*;

public class MenuPanel extends JPanel {

	private JLabel dayLabel;

	private PlayerInfoComponent hpic;
	private PlayerInfoComponent cpic;

	private PossibleActionsComponent pac;

	private JTextArea jta;
	// private JScrollPane jsp;

	public MenuPanel(ChildEventListener cel) {
		
		// setLayout(new GridBagLayout());

		setLayout(null);
		
		// setBounds(BOARD_LENGTH, 0, MENU_LENGTH, MENU_HEIGHT);
		// JButton newGameButton = new JButton("New Game");
		// newGameButton.addActionListener(new ActionListener() {
		// 	public void actionPerformed(ActionEvent e) {
		// 		cel.newGameButtonClickEvent();
		// 	}
		// });
		// newGameButton.setBounds(0, 0, 200, 50);
		// add(newGameButton);

		this.dayLabel = new JLabel();
		dayLabel.setBounds(0, 0, 200, 20);
		add(dayLabel);

		this.hpic = new PlayerInfoComponent(new Point(0, 50), "Hovered player");
		
		// PROTOTYPE_CHAIN: 3
		// this.currentPlayerInfoOrigin = new Point(0, 200);
		this.cpic = new PlayerInfoComponent(new Point(0, 165), "Current player");
		add(cpic);

		// this.cpic = new PlayerInfoComponent(new Point(0, 165), "Current player");
		// GridBagConstraints c = new GridBagConstraints();
		// c.fill 
		// c.gridx = 0;
		// c.gridy = 1;
		// add(cpic, c);
		// add(cpic);

		this.pac = new PossibleActionsComponent(cel);
		// c = new GridBagConstraints();
		// c.fill = GridBagConstraints.HORIZONTAL;
		// c.gridx = 0;
		// c.gridy = 2;
		pac.setBounds(0, 275, 200, 200);
		// add(pac, c);
		add(pac);

		this.jta = new JTextArea(10, 10);
		jta.setFont(new Font("Helvetica", Font.BOLD, 12));
		jta.setLineWrap(true);
		jta.setWrapStyleWord(true);
		jta.setEditable(false);
		JScrollPane jsp = new JScrollPane(jta);
		jsp.setBounds(0, 450, 200, 200);
		add(jsp);
	}

	// PROTOTYPE_CHAIN: 1
	public void displayHoveredPlayer(PlayerContext pc, char color) {
		hpic.setInfo(pc, color);
		add(hpic);
		repaint();
	}

	// PROTO_TYPE_CHAIN: 3
	// public void updateCurrentPlayerDisplay(PlayerInfo pi, char color) {
	// 	cpic.setInfo(pi, color);
	// 	repaint();
	// }

	// PROTOTYPE_CHAIN: 1
	public void removeHoveredPlayer() {
		remove(hpic);
		repaint();
		// getGraphics().drawImage(nullImage, HoverPlayerPoint.x, HoverPlayerPoint.y, null);
	}

	public void updateDayLabel(int day, int daysLeft) {
		dayLabel.setText("Day: " + day + ", days left: " + daysLeft);
	}

	public void newMessage(String msg) {
		jta.append("> " + msg + "\n");
	}

	public void update(PlayerContext pc, char color) {
		cpic.setInfo(pc, color);
		pac.update(pc);
		repaint();
	}

	public void displayUpgradeChoices(int playerLevel, int cashRankMax,
									  int creditsRankMax) {
		pac.displayUpgradeChoices(playerLevel, cashRankMax, creditsRankMax);
	}

	// public void updateActions(PlayerContext pc) {
	// 	pac.update(pc);
	// }

}