package model.board;

public class RoomFactory {

	private static final RoomFactory instance = new RoomFactory();

	private RoomFactory () {}

	public Room getRoom(RoomInfo ri) {
		if (ri.roomType == RoomInfo.Type.MOVIE_SET) {
			return new MovieSet(ri);
		} else if (ri.roomType == RoomInfo.Type.OFFICE) {
			return new CastingOffice(ri);
		} else if (ri.roomType == RoomInfo.Type.TRAILER) {
			return new Trailers(ri);
		} else {
			throw new IllegalArgumentException("room type \"" + ri.roomType + "\" not recognized");
		}
	}

	public static RoomFactory getInstance() { return instance; }

}