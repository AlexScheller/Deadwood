package view.graphics;

import java.awt.Point;
import java.awt.Image;
import java.awt.Graphics;
// import javax.swing.JPanel;

import view.loading.RoomPanelInfo;

public class SetPanel extends RoomPanel {

	private CardPanel cardPanel;
	private Point cardPanelOrigin;
	private Image takeImage;
	private Point[] takeOrigins;
	private int takesFinished;
	// private Map<String, Image>

	// TODO: maybe hard code the image
	public SetPanel(RoomPanelInfo rpi, Image takeImage) {
		setLayout(null);
		this.takeImage = takeImage;
		this.cardPanelOrigin = rpi.cardPanelOrigin;
		this.takeOrigins = rpi.takeOrigins;
		this.takesFinished = takeOrigins.length; // placeholder
		// this class serves only as a container, therefore
		// it should remain invisible.
		setOpaque(false);
	}

	public void setCardPanel(CardPanel cp) {
		// System.out.println("Card Panel set");
		this.cardPanel = cp;
		cardPanel.setBounds(cardPanelOrigin);
		add(cardPanel);
	}

	@Override
	public void paintComponent(Graphics g) {
		// System.out.println("Set paintComponent called");
		super.paintComponent(g);
		for (int i = 0; i < takesFinished; i++) {
			// TODO: these values should really be adjusted in the 
			// dataset, not here.
			int x = takeOrigins[i].x + 2;
			int y = takeOrigins[i].y + 2;
			g.drawImage(takeImage, x, y, null);
		}
	}

	public void setBounds() {
		// these bounds may need adjusting
		setBounds(0, 0, 1200, 900);
	}

}