package view.loading;

import java.io.File;
import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.json.JSONTokener;
import org.json.JSONObject;
import org.json.JSONArray;

// import model.board.role.RoleInfo;
// import model.board.room.RoomInfo;
// import model.board.scene.SceneCardInfo;

public class JSONDataParser {

	private static final JSONDataParser instance = new JSONDataParser();

	// only one level up because of classpath
	private final String boardPath = "../resources/jsondata/board.json";
	private final String cardPath = "../resources/jsondata/cards.json";

	private JSONDataParser() {}

	// public List<SceneCardImageInfo> parseSceneCardImagesInfos() {
	// 	// System.out.print("\t\tparsing cards...");
	// 	List<SceneCardImageInfo> ret = new ArrayList<>();
	// 	try {
	// 		JSONTokener jt = new JSONTokener (new FileInputStream(new File(cardPath)));
	// 		JSONObject cardJSON = new JSONObject(jt);
	// 		// begin parsing
	// 		Iterator cards = cardJSON.getJSONArray("cards").iterator();
	// 		while (cards.hasNext()) {
	// 			SceneCardImageInfo nscii = new SceneCardInfo();
	// 			JSONObject card = (JSONObject) cards.next();
	// 			nscii.title = card.getString("name");
	// 			nscii.fileName = card.getString("img");
	// 			Iterator parts = card.getJSONArray("parts").iterator();
	// 			Map<String, Integer> roleOffsets = new HashMap<>();
	// 			while (parts.hasNext()) {
	// 				JSONObject part = (JSONObject) parts.next();
	// 				JSONObject area = (JSONObject) part.getJSONObject("area");
	// 				roleLocations.put(part.getString("name"), area.getInt(x));
	// 			}
	// 			ret.add(nscii);
	// 		}
	// 	} catch (Exception e) {
	// 		e.printStackTrace();
	// 		System.exit(1);
	// 	}
	// 	// System.out.println(" successful!");
	// 	return ret;
	// }

	public List<RoomPanelInfo> parseRoomPanelInfo() {
		List<RoomPanelInfo> ret = new ArrayList<>();
		try {
			JSONTokener jt = new JSONTokener(new FileInputStream(new File(boardPath)));
			JSONObject rooms = (new JSONObject(jt)).getJSONObject("rooms");
			Iterator sets = rooms.getJSONArray("sets").iterator();
			while (sets.hasNext()) {
				RoomPanelInfo rpi = new RoomPanelInfo();
				JSONObject set = (JSONObject) sets.next();
				rpi.roomType = RoomPanelInfo.Type.MOVIE_SET;
				rpi.name = set.getString("name");
				rpi.cardPanelXOrigin = set.getJSONObject("area").getInt("x");
				rpi.cardPanelYOrigin = set.getJSONObject("area").getInt("y");
				ret.add(rpi);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return ret;
	}

	// public List<RoomInfo> parseRoomInfos() {
	// 	// System.out.print("\t\tparsing board...");
	// 	List<RoomInfo> ret = new ArrayList<>();
	// 	try {
	// 		JSONTokener jt = new JSONTokener (new FileInputStream(new File(boardPath)));
	// 		JSONObject rooms = (new JSONObject(jt)).getJSONObject("rooms");
	// 		Iterator sets = rooms.getJSONArray("sets").iterator();
	// 		while (sets.hasNext()) {
	// 			RoomInfo setInfo = new RoomInfo();
	// 			JSONObject set = (JSONObject) sets.next();
	// 			setInfo.roomType = RoomInfo.Type.MOVIE_SET;
	// 			setInfo.name = set.getString("name");
	// 			setInfo.takesLeft = set.getJSONArray("takes").length();
	// 			setInfo.roleInfos = parseRoleInfos(set.getJSONArray("parts"), "board");
	// 			setInfo.neighbors = parseNeighbors(set.getJSONArray("neighbors"));
	// 			ret.add(setInfo);
	// 		}
	// 		JSONObject trailer = rooms.getJSONObject("trailer");
	// 		RoomInfo trailerInfo = new RoomInfo();
	// 		trailerInfo.roomType = RoomInfo.Type.TRAILER;
	// 		trailerInfo.name = "trailer";
	// 		trailerInfo.neighbors = parseNeighbors(trailer.getJSONArray("neighbors"));
	// 		ret.add(trailerInfo);
	// 		JSONObject office = rooms.getJSONObject("office");
	// 		RoomInfo officeInfo = new RoomInfo();
	// 		officeInfo.roomType = RoomInfo.Type.OFFICE;
	// 		officeInfo.name = "office";
	// 		officeInfo.neighbors = parseNeighbors(office.getJSONArray("neighbors"));
	// 		ret.add(officeInfo);
	// 	} catch (Exception e) {
	// 		e.printStackTrace();
	// 		System.exit(1);
	// 	}
	// 	// System.out.println(" successful!");
	// 	return ret;
	// } 

	public static JSONDataParser getInstance() { return instance; }

}