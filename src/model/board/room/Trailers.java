package model.board.room;

public class Trailers extends Room {

	public Trailers (RoomInfo ri) {
		super(ri);
	}

	@Override
	public String toString() {
		return "trailers\n\n" + getTabbedNeighborStrings();
	}

}