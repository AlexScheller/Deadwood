/*
 * json processing library can be found here:
 *
 * https://github.com/stleary/JSON-java
 *
 * documentation can be found here:
 *
 * http://stleary.github.io/JSON-java/index.html
 */
package view.loading;

import java.io.File;
import java.io.FileInputStream;

import java.awt.Point;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONDataParser {

	private static final JSONDataParser instance = new JSONDataParser();

	// only one level up because of classpath
	private final String boardPath = "../resources/jsondata/board.json";
	private final String cardPath = "../resources/jsondata/cards.json";

	private JSONDataParser() {}

	// public Map<String, Integer> getCardFilenamesToIds() {
	// 	Map<String, Integer> ret = new HashMap<>();
	// 	try {
	// 		JSONTokener jt = new JSONTokener(new FileInputStream(new File(cardPath)));
	// 		Iterator cards = (new JSONObject(jt)).getJSONArray("cards").iterator();
	// 		while (cards.hasNext()) {
	// 			JSONObject card = (JSONObject) cards.next();
	// 			String fileName = card.getString("img");
	// 			int sceneId  = card.getJSONObject("scene").getInt("number");
	// 			// System.out.println("putting (" + fileName + "," + sceneId + ")");
	// 			ret.put(fileName, sceneId);
	// 		}
	// 	} catch (Exception e) {
	// 		e.printStackTrace();
	// 		System.exit(1);
	// 	}
	// 	return ret;
	// }

	public Map<Integer, Map<String, Point>> parseCardIdToRoleOrigins() {
		Map<Integer, Map<String, Point>> ret = new HashMap<>();
		try {
			JSONTokener jt = new JSONTokener(new FileInputStream(new File(cardPath)));
			Iterator cards = (new JSONObject(jt)).getJSONArray("cards").iterator();
			while (cards.hasNext()) {
				JSONObject card = (JSONObject) cards.next();
				// CardInfo ci = new CardInfo();
				// ci.title = card.getString("name");
				int imageId = card.getInt("id");
				Map<String, Point> starringOrigins = new HashMap<>(); 
				Iterator stars = card.getJSONArray("parts").iterator();
				while (stars.hasNext()) {
					JSONObject star = (JSONObject) stars.next();
					String name = star.getString("name");
					JSONObject area = star.getJSONObject("area");
					int x = area.getInt("x");
					int y = area.getInt("y");
					starringOrigins.put(name, new Point(x, y));
				}
				ret.put(imageId, starringOrigins);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return ret;
	}

	// public List<CardInfo> parseCardInfo() {
	// 	List<CardPanelInfo> ret = new ArrayList<>();
	// 	try {
	// 		JSONTokener jt = new JSONTokener(new FileInputStream(new File(cardPath)));
	// 		Iterator cards = (new JSONObject(jt)).getJSONArray("cards").iterator();
	// 		while (cards.hasNext()) {
	// 			JSONObject card = (JSONObject) cards.next();
	// 			CardInfo ci = new CardInfo();
	// 			ci.title = card.getString("name");
	// 			ci.imageId = card.getInt("id");
	// 			Map<String, Point> starringOrigins = new HashMap<>(); 
	// 			Iterator stars = card.getJSONArray("parts").iterator();
	// 			while (stars.hasNext()) {
	// 				JSONObject star = (JSONObject) stars.next();
	// 				String name = star.getString("name");
	// 				JSONObject area = start.getJSONObject("area");
	// 				int x = area.getInt("x");
	// 				int y = area.getInt("y");
	// 				starringOrigins.put(name, new Point(x, y));
	// 			}
	// 			ci.starringOrigins = starringOrigins;
	// 			ret.add(ci);
	// 		}
	// 	} catch (Exception e) {
	// 		e.printStackTrace();
	// 		System.exit(1);
	// 	}
	// 	return ret;
	// }

	public List<RoomInfo> parseRoomPanelInfo() {
		List<RoomInfo> ret = new ArrayList<>();
		try {
			JSONTokener jt = new JSONTokener(new FileInputStream(new File(boardPath)));
			JSONObject rooms = (new JSONObject(jt)).getJSONObject("rooms");
			Iterator sets = rooms.getJSONArray("sets").iterator();
			while (sets.hasNext()) {

				RoomInfo ri = new RoomInfo();
				JSONObject set = (JSONObject) sets.next();
				ri.roomType = RoomInfo.Type.MOVIE_SET;
				ri.name = set.getString("name");
				int x = set.getJSONObject("area").getInt("x");
				int y = set.getJSONObject("area").getInt("y");
				ri.cardOrigin = new Point(x, y);

				// takes
				JSONArray takes = set.getJSONArray("takes");
				Point[] takeOrigins = new Point[takes.length()];
				Iterator takesItr = takes.iterator();
				while (takesItr.hasNext()) {
					JSONObject take = (JSONObject) takesItr.next();
					x = take.getJSONObject("area").getInt("x");
					y = take.getJSONObject("area").getInt("y");
					int number = take.getInt("number");
					takeOrigins[number - 1] = new Point(x, y);
				}
				ri.takeOrigins = takeOrigins;

				// off card roles
				Map<String, Point> extraOrigins = new HashMap<>();
				Iterator parts = set.getJSONArray("parts").iterator();
				while (parts.hasNext()) {
					JSONObject part = (JSONObject) parts.next();
					// note these values should really be changed in
					// the data set
					x = part.getJSONObject("area").getInt("x") + 3;
					y = part.getJSONObject("area").getInt("y") + 3;
					extraOrigins.put(part.getString("name"), new Point(x, y));
				}
				ri.extraOrigins = extraOrigins;

				ret.add(ri);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return ret;
	}

	public static JSONDataParser getInstance() { return instance; }

}