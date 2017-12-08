package view.graphics;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import view.loading.AssetBank;
import view.loading.RoomPanelInfo;
import view.events.InputEventListener;

public class BoardPanel extends JPanel {

	private Image board;
	private Map<String, RoomPanel> rooms;
	// private Map<Integer, Map<>> cardIdToRole

	public BoardPanel(List<RoomPanelInfo> rpis, InputEventListener iel) {
		setLayout(null); // absolute positioning is used
		this.board = new ImageIcon("../resources/board.png").getImage();
		setBounds(0, 0, board.getWidth(null), board.getHeight(null));
		this.rooms = new HashMap<>();
		// the below is hard-coded for testing
		AssetBank ab = AssetBank.getInstance();
		// Image placeHolderCardImage = ab.getAsset("04");
		Image clapper  = new ImageIcon("../resources/clapper.png").getImage();
		Image die = ab.getAsset("g6");
		// TODO put office and trailers into this loop
		for (RoomPanelInfo rpi : rpis) {
			// CardPanel ncp = new CardPanel(placeHolderCardImage,
			// 							  rpi.cardPanelOrigin);
			// ncp.setBounds();
			// CardPanel ncp = new CardPanel(placeHolderCardImage);
			// CardPanel ncp = new CardPanel(iel);
			SetPanel sp = new SetPanel(rpi, clapper, die, iel);
			sp.setBounds();
			// sp.setCardPanel(ncp);
			rooms.put(rpi.name, sp);
			add(sp);
		}
		RoomPanel office = new OfficePanel();
		RoomPanel trailers = new TrailerPanel();
		office.setBounds();
		trailers.setBounds();
		rooms.put("office", office);
		rooms.put("trailers", trailers);
		add(office);
		add(trailers);
	}

	public void newSceneInSet(String where, String which, 
							  int cardId) {
		SetPanel roomAsSet = (SetPanel) rooms.get(where);
		roomAsSet.newScene(which, cardId);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(board, 0, 0, null);
	}

}