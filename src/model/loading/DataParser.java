package model.loading;

import java.util.List;

import model.board.room.RoomInfo;
import model.board.scene.SceneCardInfo;

public interface DataParser {

	public List<SceneCardInfo> parseSceneCardInfos();

	public List<RoomInfo> parseRoomInfos ();

}