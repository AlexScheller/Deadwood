package view.graphics.menu;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PossibleUpgradesComponent extends JComponent {

	private PossibleActionsComponent listener;

	private JButton cashButton;
	private JButton creditsButton;
	private JComboBox cashRanks;
	private JComboBox creditRanks;

	public PossibleUpgradesComponent(PossibleActionsComponent pac) {
		setLayout(null);
		this.listener = pac;
		initButtons();
	}

	private void initButtons() {

		this.cashButton = new JButton("Cash:");
		cashButton.setFocusPainted(false);
		cashButton.setBounds(0, 0, 100, 20);
		cashButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.upgradeSelected((Integer) cashRanks.getSelectedItem(), "cash");
			}
		});
		add(cashButton);

		this.creditsButton = new JButton("Credits:");
		creditsButton.setFocusPainted(false);
		creditsButton.setBounds(100, 0, 100, 20);
		creditsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.upgradeSelected((Integer) creditRanks.getSelectedItem(), "credits");
			}
		});
		add(creditsButton);

		Integer[] ranks = {2, 3, 4, 5, 6};

		this.cashRanks = new JComboBox(ranks);
		cashRanks.setSelectedIndex(0);
		cashRanks.setBounds(0, 20, 100, 20);
		add(cashRanks);

		this.creditRanks = new JComboBox(ranks);
		creditRanks.setSelectedIndex(0);
		creditRanks.setBounds(100, 20, 100, 20);
		add(creditRanks);
	}

}