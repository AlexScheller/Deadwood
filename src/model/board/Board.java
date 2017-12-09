package model.board;

import model.ModelListener;
import model.board.room.Room;
import model.board.room.RoomSet;
import model.board.scene.SceneCardSet;
import model.events.ChildEventListener;

public class Board {

	private RoomSet rs;
	private SceneCardSet cards;

	// private int scenesLeft;

	// private ModelListener listener;

	private ChildEventListener listener;

	public Board(RoomSet rs, SceneCardSet scs) {
		this.rs = rs;
		this.cards = scs;
		// this.scenesLeft = rs.numSets();
		// System.out.println("\tsuccessful!");
	}

	// public void setBoardEventListener(BoardEventListener bel) {
	// 	rs.setBoardEventListener(bel);
	// }

	public void setListener(ChildEventListener cel) {
		this.listener = cel;
		rs.setListener(cel);
	}

	// public void setListener(ModelListener ml) {
	// 	this.listener = ml;
	// 	rs.setListener(ml);
	// }

	@Override
	public String toString() {
		return rs.toString() + "\n" + cards.toString();
	}

	public String currentStateToString() {
		return "";
	}

	// private void dealRooms() {
	// 	rs.dealSceneCards(cards);
	// }

	public void newDay() {
		// dealRooms();
		rs.dealSceneCards(cards);
	}

	public Room getInitialRoom() {
		return rs.getRoom("trailer");
	}

	public boolean oneSceneLeft() {
		return rs.oneSceneLeft();
	}

	/* for debugging only */
	public Room getRoomForTeleport(String which) {
		return rs.getRoom(which);
	}
	/* above is for debugging only */

}