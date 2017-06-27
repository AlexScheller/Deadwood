package model.board;

import java.util.Set;

public abstract class Room {

	protected int id;
	protected String name;
	// protected Set<Player> playersInside;

	public Room (RoomInfo ri) {
		this.id = ri.id;
		this.name = ri.name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public abstract String toString();

}