package model.board;

public class RoomSet {

	private Map<Room> rooms;

	public RoomSet() {

	}

	public Room getRoom(String which) {
		return rooms.get(which);
	}

}