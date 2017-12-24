package view.graphics.menu;

import java.util.Map;
import java.util.HashMap;

import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import view.events.ChildEventListener;

import model.player.PlayerContext;

/*
 * Design note: The program is currently setup so that
 * the buttons for possible player actions are added
 * and removed dynamically based on context. Additionally
 * when clicked they emit an ActionType. This design is
 * probably over-engineered, since there are only a few
 * actions a player could take from the menu choices. With
 * the current design however, adding new rules/choices will
 * take less work/code than if all options required their
 * own methods in the ChildEventListener. 
 */

public class PossibleActionsComponent extends JComponent {

	// private JButton actButton;
	// private JButton endTurnButton;
	// private JButton rehearseButton;

	// private PlayerContext pc;

	private Map<ActionType, JButton> actionButtons;

	private final int buttonHeight = 50;

	private ChildEventListener listener;

	private PossibleUpgradesComponent puc;

	public PossibleActionsComponent(ChildEventListener cel) {
		setLayout(null);
		// setBounds(p.x, p.y, width, height);
		this.puc = new PossibleUpgradesComponent(this);
		this.listener = cel;
		this.actionButtons = new HashMap<>();
		initButtons();
	}

	private void initButtons() {

		JButton actButton = new JButton("Act");
		actButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.actionButtonClicked(ActionType.ACT);
			}
		});
		actionButtons.put(ActionType.ACT, actButton);

		JButton rehearseButton = new JButton("Rehearse");
		rehearseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.actionButtonClicked(ActionType.REHEARSE);
			}
		});
		actionButtons.put(ActionType.REHEARSE, rehearseButton);

		JButton endTurnButton = new JButton("End Turn");
		endTurnButton.setFocusPainted(false);
		endTurnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.actionButtonClicked(ActionType.END_TURN);
			}
		});
		actionButtons.put(ActionType.END_TURN, endTurnButton);

		JButton upgradeButton = new JButton("Upgrade");
		upgradeButton.setFocusPainted(false);
		upgradeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.actionButtonClicked(ActionType.UPGRADE);
			}
		});
		actionButtons.put(ActionType.UPGRADE, upgradeButton);

		// for debugging
		JButton endDayButton = new JButton("End Day");
		endDayButton.setFocusPainted(false);
		endDayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.actionButtonClicked(ActionType.END_DAY);
			}
		});
		actionButtons.put(ActionType.END_DAY, endDayButton);


	}

	// TODO: display only upgrades that are possible,
	// instead of causing an error/false/exception
	// when attempting to upgrade to a level the player
	// either cannot afford or shouldn't be able to upgrade
	// to.
	public void displayUpgradeChoices(int playerLevel, int cashRankMax,
									  int creditsRankMax) {
		removeAll();
		puc.setBounds(0, 0, getWidth(), getHeight());
		puc.initButtons(playerLevel, cashRankMax, creditsRankMax);
		add(puc);
		// Currently this needs to be called to get drop down
		// arrows for some reason.
		puc.revalidate();
		puc.repaint();
		repaint();
	}

	public void upgradeSelected(int rank, String currency) {
		listener.upgradeSelected(rank, currency);
		// System.out.println("rank selected: " + rank + ", with currency: " + currency);
	}

	public void update(PlayerContext pc) {
		removeAll(); // clear the container
		System.out.println("pc can rehearse: " + pc.canRehearse);
		int numDisplayed = 0;
		JButton curr = actionButtons.get(ActionType.END_TURN);
		curr.setBounds(0, numDisplayed * 50, 200, buttonHeight);
		add(curr);
		numDisplayed++;
		if (pc.acting) {
			curr = actionButtons.get(ActionType.ACT);
			curr.setBounds(0, numDisplayed * 50, 200, buttonHeight);
			add(curr);
			numDisplayed++;
			if (pc.canRehearse) {
				curr = actionButtons.get(ActionType.REHEARSE);
				curr.setBounds(0, numDisplayed * 50, 200, buttonHeight);
				add(curr);
				numDisplayed++;
			}
		}
		if (pc.canUpgrade) {
			System.out.println("can upgrade");
			curr = actionButtons.get(ActionType.UPGRADE);
			curr.setBounds(0, numDisplayed * 50, 200, buttonHeight);
			add(curr);
			numDisplayed++;
			// displayUpgradeButtons(numDisplayed);
		}
		// for debugging
		// curr = actionButtons.get(ActionType.END_DAY);
		// curr.setBounds(0, numDisplayed * 50, 200, buttonHeight);
		// add(curr);
		repaint();
	}

}