package model.board;

public class Board {

	private RoomSet rs;
	private SceneCardSet cards;

	public Board(RoomSet rs, SceneCardSet scs) {
		this.rs = rs;
		this.cards = scs;
		System.out.println("\tsuccessful!");
	}

	@Override
	public String toString() {
		return rs.toString() + "\n" + cards.toString();
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

}