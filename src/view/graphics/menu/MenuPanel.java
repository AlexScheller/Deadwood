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

	public MenuPanel(ChildEventListener cel) {

		setLayout(null);

		this.dayLabel = new JLabel();
		dayLabel.setBounds(0, 0, 200, 20);
		add(dayLabel);

		this.hpic = new PlayerInfoComponent(new Point(0, 50), "Hovered player");
		
		this.cpic = new PlayerInfoComponent(new Point(0, 165), "Current player");
		add(cpic);

		this.pac = new PossibleActionsComponent(cel);
		pac.setBounds(0, 275, 200, 200);
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

	public void displayHoveredPlayer(PlayerContext pc, char color) {
		hpic.setInfo(pc, color);
		add(hpic);
		repaint();
	}

	public void removeHoveredPlayer() {
		remove(hpic);
		repaint();
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

	public void disableButtons() {
		pac.disableButtons();
	}

}