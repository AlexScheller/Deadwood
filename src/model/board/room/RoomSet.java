package model.board.room;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import model.board.scene.SceneCardSet;

import model.events.ChildEventListener;

/*
 * The RoomSet class serves as the primary container for
 * Room objects in the Board. It handles some high-level
 * functionality concerning the Rooms as a group.
 */

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

	public void setListener(ChildEventListener cel) {
		for (Room r : rooms.values()) {
			r.setListener(cel);
		}
	}

	// After rooms are loaded, the implicit neighbor-graph
	// must be established.
	private void assignNeighbors(List<RoomInfo> ris) {
		for (RoomInfo ri : ris) {
			Map<String, Room> neighbors = new HashMap<>();
			for (String n : ri.neighbors) {
				neighbors.put(n, rooms.get(n));
			}
			rooms.get(ri.name).setNeighbors(neighbors);
		}
	}

	// When a new day event occurs, each room is dealt
	// a new scene card.
	public void dealSceneCards(SceneCardSet scs) {
		for (Room r : rooms.values()) {
			if (r instanceof MovieSet) {
				MovieSet ms = (MovieSet) r;
				ms.setSceneCard(scs.deal());
			}
		}
	}

	public Room getRoom(String which) {
		return rooms.get(which);
	}

}