package view.graphics;

import java.awt.Graphics;
// import javax.swing.JPanel;

public class SetPanel extends RoomPanel {

	private CardPanel cardPanel;
	// private Map<String, Image>

	public SetPanel() {
		setLayout(null);
		// this class serves only as a container, therefore
		// it should remain invisible.
		setOpaque(false);
	}

	public void setCardPanel(CardPanel cp) {
		// System.out.println("Card Panel set");
		this.cardPanel = cp;
		cardPanel.setBounds();
		add(cardPanel);
	}

	@Override
	public void paintComponent(Graphics g) {
		// System.out.println("Set paintComponent called");
		super.paintComponent(g);
	}

	public void setBounds() {
		// these bounds may need adjusting
		setBounds(0, 0, 1200, 900);
	}

}