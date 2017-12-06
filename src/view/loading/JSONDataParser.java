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

	public Map<String, String> getCardImageAssociations() {
		Map<String, String> ret = new HashMap<>();
		try {
			JSONTokener jt = new JSONTokener(new FileInputStream(new File(cardPath)));
			Iterator cards = (new JSONObject(jt)).getJSONArray("parts").iterator();
			while (cards.hasNext()) {
				JSONObject card = (JSONObject) cards.next();
				String fileName = card.getString("img");
				String title = card.getString("name");
				ret.put(fileName, title);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return ret;
	}

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
				int x = set.getJSONObject("area").getInt("x");
				int y = set.getJSONObject("area").getInt("y");
				rpi.cardPanelOrigin = new Point(x, y);

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
				rpi.takeOrigins = takeOrigins;

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
				rpi.extraOrigins = extraOrigins;

				ret.add(rpi);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return ret;
	}

	public static JSONDataParser getInstance() { return instance; }

}