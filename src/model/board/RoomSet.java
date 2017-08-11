package model.board;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class RoomSet {

	private Map<String, Room> rooms;

	public RoomSet(List<RoomInfo> ris) {
		this.rooms = new HashMap<>();
		RoomFactory rf = RoomFactory.getInstance();
		for (RoomInfo ri : ris) {
			rooms.put(ri.name, rf.getRoom(ri));
		}
		assignNeighbors(ris);
	}

	public String toString() {
		String ret = "";
		for (String key : rooms.keySet()) {
			ret += rooms.get(key).toString() + "\n";
		}
		return ret;
	}

	private void assignNeighbors(List<RoomInfo> ris) {
		for (RoomInfo ri : ris) {
			Map<String, Room> neighbors = new HashMap<>();
			for (String n : ri.neighbors) {
				neighbors.put(n, rooms.get(n));
			}
			rooms.get(ri.name).setNeighbors(neighbors);
		}
	}

	public void dealSceneCards(SceneCardSet scs) {
		for (String key : rooms.keySet()) {
			Room r = rooms.get(key);
			if (r instanceof MovieSet) {
				MovieSet ms = (MovieSet) r;
				ms.setSceneCard(scs.deal());
			}
		}
	}

	// public void addRoom(String name, Room newRoom) {
	// 	rooms.put(name, newRoom);
	// }

	public Room getRoom(String which) {
		return rooms.get(which);
	}

	// Note that this is technically less efficient that
	// haveing some counter variable that gets updated
	// when scenes wrap, however the number of scenes
	// is small enough that it doesn't matter, also the code
	// is a lot cleaner this way.
	// public boolean oneSceneLeft() {
	// 	int scenesLeft = 0;
	// 	for (Keyr k : rooms.keySet()) {
	// 		Room curr = rooms.get(k);
	// 		if (curr instanceof MovieSet) {
	// 			if (!curr.wrapped()) {
	// 				scenesLeft++;
	// 			}
	// 		}
	// 	}
	// 	return (scenesLeft == 1);
	// }

}