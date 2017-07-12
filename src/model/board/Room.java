package model.board;

import java.util.Set;

public abstract class Room {

	protected int id;
	protected String name;
	protected List<Room> neighbors;
	// protected Set<Player> playersInside;

	public Room (RoomInfo ri) {
		this.id = ri.id;
		this.name = ri.name;
	}

	public String getName() {
		return this.name;
	}

	// For terminal use
	public listNeighbors() {
		System.out.println("Neighbors:")
		for (Room r : neighbors) {
			System.out.println("\t" + r.toString());
		}
	}

	@Override
	public abstract String toString();

}