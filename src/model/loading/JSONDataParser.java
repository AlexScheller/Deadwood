package model.loading;


import org.json.JSONTokener;
import org.json.JSONObject;
import org.json.JSONArray;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import model.board.*;

public class JSONDataParser implements DataParser {

	private static final JSONDataParser instance = new JSONDataParser();

	// only one level up because of classpath
	private final String boardPath = "../resources/jsondata/board.json";
	private final String cardPath = "../resources/jsondata/cards.json";

	private JSONDataParser() {}

	private List<RoleInfo> parseRoleInfos(JSONArray roleInfos) {
		List<RoleInfo> ret = new ArrayList<>();
		Iterator parts = roleInfos.iterator();
		while (parts.hasNext()) {
			RoleInfo ri = new RoleInfo();
			JSONObject part = (JSONObject) parts.next();
			ri.line = part.getString("line");
			ri.name = part.getString("name");
			ri.rankRequired = part.getInt("level");
		}
		return ret;
	}

	public List<SceneCardInfo> parseSceneCardInfos() {
		System.out.print("\t\tparsing cards...");
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
				nsci.roleInfos = parseRoleInfos(card.getJSONArray("parts")); 
				ret.add(nsci);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println(" successful!");
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
		System.out.print("\t\tparsing board...");
		List<RoomInfo> ret = new ArrayList<>();
		try {
			JSONTokener jt = new JSONTokener (new FileInputStream(new File(boardPath)));
			JSONObject rooms = (new JSONObject(jt)).getJSONObject("rooms");
			Iterator sets = rooms.getJSONArray("sets").iterator();
			while (sets.hasNext()) {
				RoomInfo setInfo = new RoomInfo();
				JSONObject set = (JSONObject) sets.next();
				setInfo.roomType = "set";
				setInfo.name = set.getString("name");
				setInfo.numTakes = set.getJSONArray("takes").length();
				setInfo.roleInfos = parseRoleInfos(set.getJSONArray("parts"));
				setInfo.neighbors = parseNeighbors(set.getJSONArray("neighbors"));
				ret.add(setInfo);
			}
			JSONObject trailer = rooms.getJSONObject("trailer");
			RoomInfo trailerInfo = new RoomInfo();
			trailerInfo.roomType = "trailer";
			trailerInfo.name = "trailer";
			trailerInfo.neighbors = parseNeighbors(trailer.getJSONArray("neighbors"));
			ret.add(trailerInfo);
			JSONObject office = rooms.getJSONObject("office");
			RoomInfo officeInfo = new RoomInfo();
			officeInfo.roomType = "office";
			officeInfo.name = "office";
			officeInfo.neighbors = parseNeighbors(office.getJSONArray("neighbors"));
			ret.add(officeInfo);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println(" successful!");
		return ret;
	} 

	public static JSONDataParser getInstance() { return instance; }



}