package view.graphics.board;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import java.awt.Image;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import view.loading.AssetBank;
import view.loading.RoomInfo;
import view.events.ChildEventListener;

public class BoardPanel extends JPanel {

	private Image board;
	private Map<String, RoomComponent> rooms;
	// private Map<Integer, Map<>> cardIdToRole

	public BoardPanel(List<RoomInfo> ris,
					  ChildEventListener cel) {
		setLayout(null); // absolute positioning is used
		this.board = new ImageIcon("../resources/board.png").getImage();
		// setBounds(0, 0, board.getWidth(null), board.getHeight(null));
		setPreferredSize(new Dimension(board.getWidth(null), board.getHeight(null)));
		this.rooms = new HashMap<>();
		// the below is hard-coded for testing
		AssetBank ab = AssetBank.getInstance();
		// Image placeHolderCardImage = ab.getAsset("04");
		Image clapper  = new ImageIcon("../resources/clapper.png").getImage();
		Image die = ab.getAsset("g6");
		// TODO put office and trailers into this loop
		for (RoomInfo ri : ris) {
			// CardPanel ncp = new CardPanel(placeHolderCardImage,
			// 							  rpi.cardPanelOrigin);
			// ncp.setBounds();
			// CardPanel ncp = new CardPanel(placeHolderCardImage);
			// CardPanel ncp = new CardPanel(iel);
			SetComponent sc = new SetComponent(ri, clapper, die, cel);
			sc.setBounds();
			// sp.setCardPanel(ncp);
			rooms.put(ri.name, sc);
			add(sc);
		}
		RoomComponent office = new OfficeComponent(cel);
		RoomComponent trailer = new TrailerComponent(cel);
		// office.setBounds();
		// trailer.setBounds();
		rooms.put("office", office);
		rooms.put("trailer", trailer);
		add(office);
		add(trailer);
		// PlayerComponent pc = new PlayerComponent(3, 'y', cel);
		// pc.move(new Point(0, 0));
		// add(pc);
	}

	public void newSceneInSet(String where, String which, 
							  int cardId) {
		SetComponent roomAsSet = (SetComponent) rooms.get(where);
		roomAsSet.newScene(which, cardId);
	}

	public void wrapOnSet(String where) {
		SetComponent roomAsSet = (SetComponent) rooms.get(where);
		roomAsSet.wrap();
	}

	public void takeFinishEvent(String where) {
		SetComponent roomAsSet = (SetComponent) rooms.get(where);
		roomAsSet.takeFinished();
	}

	public void movePlayer(PlayerComponent pc, String from, String to) {
		System.out.println(from + " : " + to);
		if (from != null) {
			rooms.get(from).evictPlayer(pc.getId());
		}
		// leave role
		pc.moveToRoom(rooms.get(to));
		// rooms.get(to).acceptPlayer(pc);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(board, 0, 0, null);
	}

}