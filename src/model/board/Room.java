package model.board;

import java.util.Set;
import java.util.Map;

public abstract class Room {

	// protected int id;
	protected String name;
	protected Map<String, Room> neighbors;
	// protected Set<Player> playersInside;

	public Room (RoomInfo ri) {
		// this.id = ri.id;
		this.name = ri.name;
	}

	public void setNeighbors(Map<String, Room> neighbors) {
		this.neighbors = neighbors;
	}

	public String getName() {
		return this.name;
	}

	// // For terminal use
	// public listNeighbors() {
	// 	// System.out.println("Neighbors:")
	// 	// for (Room r : neighbors) {
	// 	// 	System.out.println("\t" + r.toString());
	// 	// }
	// }

	@Override
	public String toString() {
		String ret = name + "neighbors:";
		for (String key : neighbors.keySet()) {
			ret += "\n\t" + neighbors.get(key).getName();
		}
		return ret;
	}

}