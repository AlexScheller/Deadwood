package view.graphics;

import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import view.events.ChildEventListener;

public class PossibleActionsComponent extends JComponent {

	private JButton actButton;
	private JButton endTurnButton;
	private JButton rehearseButton;
	private final int buttonHeight = 50;

	private ChildEventListener listener;

	public PossibleActionsComponent(ChildEventListener cel) {
		setLayout(null);
		// setBounds(p.x, p.y, width, height);
		this.listener = cel;
		initButtons();
	}

	private void initButtons() {
		this.actButton = new JButton("Act");
		actButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.actButtonClickEvent();
			}
		});
		actButton.setBounds(0, 0, 200, buttonHeight);
		add(actButton);

		this.rehearseButton = new JButton("Rehearse");
		rehearseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.rehearseButtonClickEvent();
			}
		});
		rehearseButton.setBounds(0, buttonHeight, 200, buttonHeight);
		add(rehearseButton);
		
		this.endTurnButton = new JButton("End Turn");
		endTurnButton.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				listener.endTurnButtonClickEvent();
			}
		});
		endTurnButton.setBounds(0, buttonHeight * 2, 200, buttonHeight);
		add(endTurnButton);
	}

}