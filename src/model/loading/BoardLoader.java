package model.loading;

import java.util.List;
import java.util.Stack;

import model.board.*;
import model.loading.*;

public class BoardLoader {

	private static final BoardLoader instance = new BoardLoader();

	private BoardLoader() {}

	private RoomSet loadRooms(List<RoomInfo> ris) {
		// System.out.print("\t\tloading rooms...");
		return new RoomSet(ris);
	}

	private SceneCardSet loadCards(List<SceneCardInfo> scis) {
		// System.out.print("\t\tloading cards...");
		Stack<SceneCard> ret = new Stack<SceneCard>();
		for (SceneCardInfo sci : scis) {
			ret.push(new SceneCard(sci));
		}
		return new SceneCardSet(ret);
	}

	public Board getBoard(DataParser p) {
		// System.out.println("\tloading board...");
		RoomSet rs = loadRooms(p.parseRoomInfos());
		SceneCardSet scs = loadCards(p.parseSceneCardInfos());
		return new Board(rs, scs);
	}

	public static BoardLoader getInstance() { return instance; }

}