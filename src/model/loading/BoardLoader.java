package model.loading;

import java.util.List;

import model.board.*;

public class BoardLoader {

	private static final BoardLoader instance = new BoardLoader();

	public BoardLoader() {}

	public void loadRooms(List<RoomInfo> ris) {
		RoomFactory rf = RoomFactory.getInstance();
		Room r;
		for (RoomInfo ri : ris) {
			r = rf.getRoom(ri);
			System.out.println(r.toString());
		}
	}

	public static BoardLoader getInstance() { return instance; }

}