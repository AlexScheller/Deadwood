package view.graphics.menu;

import java.util.Map;
import java.util.HashMap;

import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JComponent;
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

	public PossibleActionsComponent(ChildEventListener cel) {
		setLayout(null);
		// setBounds(p.x, p.y, width, height);
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
		// code for upgrading
		// repaint();
	}

}