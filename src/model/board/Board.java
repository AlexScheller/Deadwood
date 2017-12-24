package model.board;

import model.ModelListener;
import model.board.room.Room;
import model.board.room.RoomSet;
import model.board.scene.SceneCardSet;
import model.events.ChildEventListener;

public class Board {

	private RoomSet rs;
	private SceneCardSet cards;

	private ChildEventListener listener;

	public Board(RoomSet rs, SceneCardSet scs) {
		this.rs = rs;
		this.cards = scs;
	}

	public void setListener(ChildEventListener cel) {
		this.listener = cel;
		rs.setListener(cel);
	}

	@Override
	public String toString() {
		return rs.toString() + "\n" + cards.toString();
	}

	public String currentStateToString() {
		return "";
	}

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

}