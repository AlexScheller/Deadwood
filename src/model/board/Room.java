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

	protected String getTabbedNeighborStrings() {
		String ret = "neighbors:\n";
		for (String key : neighbors.keySet()) {
			ret += "\t" + neighbors.get(key).getName() + "\n";
		}
		return ret;
	}

	@Override
	public abstract String toString();

}