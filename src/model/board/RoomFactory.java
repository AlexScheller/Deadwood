package model.board;

public class RoomFactory {

	private static final RoomFactory instance = new RoomFactory();

	private RoomFactory () {}

	public Room getRoom(RoomInfo ri) {
		if (ri.roomType.equals("set")) {
			return new MovieSet(ri);
		} else if (ri.roomType.equals("office")) {
			return new CastingOffice(ri);
		} else if (ri.roomType.equals("trailer")) {
			return new Trailers(ri);
		} else {
			throw new IllegalArgumentException("room type \"" + ri.roomType + "\" not recognized");
		}
	}

	public static RoomFactory getInstance() { return instance; }

}