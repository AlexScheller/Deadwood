package model.loading;

import java.util.List;
import model.board.*;

public interface DataParser {

	public List<SceneCardInfo> parseSceneCardInfos();

	public List<RoomInfo> parseRoomInfos ();

}