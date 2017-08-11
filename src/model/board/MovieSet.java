package model.board;

import java.util.Set;

public class MovieSet extends Room {

	private SceneCard scene;
	private Set<Role> extras;

	public MovieSet (RoomInfo ri) {
		super(ri);
		//this.extras = ri.roles;
	}

	public void setSceneCard(SceneCard sc) {
		this.scene = sc;
	}

	// TODO IMPLEMENT
	public boolean isWrapped() {
		return true;
	}

	@Override
	public String toString() {
		return "Set: " + this.name + " id: " + Integer.toString(this.id);
	}

}