package model.loading;

import java.util.List;

import model.board.*;

public class BoardLoader {

	private static final BoardLoader instance = new BoardLoader();

	public BoardLoader() {}

	private RoomSet loadRooms(List<RoomInfo> ris) {
		RoomFactory rf = RoomFactory.getInstance();
		Room r;
		for (RoomInfo ri : ris) {
			r = rf.getRoom(ri);
			System.out.println(r.toString());
		}
	}

	private Stack<Card> loadCards(List<SceneCardInfo> scis) {
		Stack<SceneCard> ret = new Stack<SceneCard>();
		for (SceneCardInfo sci : scis) {
			ret.push(new SceneCard(sci));
		}
		return ret;
	}

	public Board getBoard(XMLParse xp) {
		RoomSet rs = loadRooms(xp.parseRoomInfos());
		SceneCardSet scs = loadCards(xp.parseSceneCardInfos());
		return new Board(rs, scs);
	}

	public static BoardLoader getInstance() { return instance; }

}