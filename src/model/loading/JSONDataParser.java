/*
 * json processing library can be found here:
 *
 * https://github.com/stleary/JSON-java
 *
 * documentation can be found here:
 *
 * http://stleary.github.io/JSON-java/index.html
 */

package model.loading;

import java.io.File;
import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.json.JSONTokener;
import org.json.JSONObject;
import org.json.JSONArray;

import model.board.role.RoleInfo;
import model.board.room.RoomInfo;
import model.board.scene.SceneCardInfo;

public class JSONDataParser implements DataParser {

	private static final JSONDataParser instance = new JSONDataParser();

	// only one level up because of classpath
	private final String boardPath = "../resources/jsondata/board.json";
	private final String cardPath = "../resources/jsondata/cards.json";

	private JSONDataParser() {}

	private List<RoleInfo> parseRoleInfos(JSONArray roleInfos, String source) {
		List<RoleInfo> ret = new ArrayList<>();
		Iterator parts = roleInfos.iterator();
		while (parts.hasNext()) {
			RoleInfo ri = new RoleInfo();
			JSONObject part = (JSONObject) parts.next();
			ri.line = part.getString("line");
			ri.name = part.getString("name");
			ri.rankRequired = part.getInt("level");
			if (source.equals("cards")) {
				ri.roleType = RoleInfo.Type.STARRING;
			} else if (source.equals("board")) {
				ri.roleType = RoleInfo.Type.EXTRA;
			} else {
				throw new IllegalArgumentException("Illegal role info source: " + source);
			}
			ret.add(ri);
			// System.out.println("new roll: " + ri.name);
		}
		return ret;
	}

	public List<SceneCardInfo> parseSceneCardInfos() {
		// System.out.print("\t\tparsing cards...");
		List<SceneCardInfo> ret = new ArrayList<>();
		try {
			JSONTokener jt = new JSONTokener (new FileInputStream(new File(cardPath)));
			JSONObject cardJSON = new JSONObject(jt);
			// begin parsing
			Iterator cards = cardJSON.getJSONArray("cards").iterator();
			while (cards.hasNext()) {
				SceneCardInfo nsci = new SceneCardInfo();
				JSONObject card = (JSONObject) cards.next();
				nsci.title = card.getString("name");
				nsci.budget = card.getInt("budget");
				JSONObject scene = (JSONObject) card.getJSONObject("scene");
				nsci.description = scene.getString("text");
				nsci.number = scene.getInt("number");
				nsci.roleInfos = parseRoleInfos(card.getJSONArray("parts"), "cards"); 
				ret.add(nsci);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		// System.out.println(" successful!");
		return ret;
	}

	private String[] parseNeighbors(JSONArray neighbors) {
		String[] ret = new String[neighbors.length()];
		for (int i = 0; i < neighbors.length(); i++) {
			ret[i] = neighbors.getString(i);
		}
		return ret;
	}

	public List<RoomInfo> parseRoomInfos() {
		// System.out.print("\t\tparsing board...");
		List<RoomInfo> ret = new ArrayList<>();
		try {
			JSONTokener jt = new JSONTokener (new FileInputStream(new File(boardPath)));
			JSONObject rooms = (new JSONObject(jt)).getJSONObject("rooms");
			Iterator sets = rooms.getJSONArray("sets").iterator();
			while (sets.hasNext()) {
				RoomInfo setInfo = new RoomInfo();
				JSONObject set = (JSONObject) sets.next();
				setInfo.roomType = RoomInfo.Type.MOVIE_SET;
				setInfo.name = set.getString("name");
				setInfo.takesLeft = set.getJSONArray("takes").length();
				setInfo.roleInfos = parseRoleInfos(set.getJSONArray("parts"), "board");
				setInfo.neighbors = parseNeighbors(set.getJSONArray("neighbors"));
				ret.add(setInfo);
			}
			JSONObject trailer = rooms.getJSONObject("trailer");
			RoomInfo trailerInfo = new RoomInfo();
			trailerInfo.roomType = RoomInfo.Type.TRAILER;
			trailerInfo.name = "trailer";
			trailerInfo.neighbors = parseNeighbors(trailer.getJSONArray("neighbors"));
			ret.add(trailerInfo);
			JSONObject office = rooms.getJSONObject("office");
			RoomInfo officeInfo = new RoomInfo();
			officeInfo.roomType = RoomInfo.Type.OFFICE;
			officeInfo.name = "office";
			officeInfo.neighbors = parseNeighbors(office.getJSONArray("neighbors"));
			ret.add(officeInfo);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		// System.out.println(" successful!");
		return ret;
	} 

	public static JSONDataParser getInstance() { return instance; }



}