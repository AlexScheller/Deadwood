// TODO: remove this class and references to it. Time has
// shown that this abstraction is unnecessary.

package model.loading;

import java.util.List;

import model.board.room.RoomInfo;
import model.board.scene.SceneCardInfo;

public interface DataParser {

	public List<SceneCardInfo> parseSceneCardInfos();

	public List<RoomInfo> parseRoomInfos ();

}