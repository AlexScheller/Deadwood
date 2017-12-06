package model.board;

import model.board.room.Room;
import model.board.room.RoomSet;
import model.board.scene.SceneCardSet;

public class Board {

	private RoomSet rs;
	private SceneCardSet cards;

	public Board(RoomSet rs, SceneCardSet scs) {
		this.rs = rs;
		this.cards = scs;
		// System.out.println("\tsuccessful!");
	}

	public void setListener(ModelListener ml) {
		this.listener = ml;
		rs.setListener(ml);
	}

	@Override
	public String toString() {
		return rs.toString() + "\n" + cards.toString();
	}

	public String currentStateToString() {
		return "";
	}

	private void dealRooms() {
		rs.dealSceneCards(cards);
	}

	public void newDay() {
		dealRooms();
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