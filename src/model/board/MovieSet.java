package model.board;

import java.util.Set;

public class MovieSet extends Room {

	private Scene scene;
	private Set<Role> extras;

	public MovieSet (RoomInfo ri) {
		super(ri);
		//this.extras = ri.roles;
	}

	@Override
	public String toString() {
		return "Set: " + this.name + " id: " + Integer.toString(this.id);
	}

}