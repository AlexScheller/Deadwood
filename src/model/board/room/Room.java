package model.board.room;

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

	public Room getNeighbor(String which) throws IllegalArgumentException {
		if (neighbors.containsKey(which)) {
			return neighbors.get(which);			
		} else {
			throw new IllegalArgumentException("room: " + which + " not in neighbors");
		}
	}

	protected String getTabbedNeighborStrings() {
		String ret = "neighbors:\n";
		for (String key : neighbors.keySet()) {
			ret += "\t" + neighbors.get(key).getName() + "\n";
		}
		return ret;
	}

	public String[] getNeighborStrings() {
		String[] ret = new String[neighbors.size()];
		int i = 0;
		for (String key : neighbors.keySet()) {
			ret[i] = neighbors.get(key).getName();
			i++;
		}
		return ret;
	}

	@Override
	public abstract String toString();

}