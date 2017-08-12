package model.board;

import java.util.List;

public class MovieSet extends Room {

	private SceneCard scene;
	private List<Role> extras;
	private int numTakes;

	public MovieSet (RoomInfo ri) {
		super(ri);
		this.numTakes = ri.numTakes;
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
		return "Set: " + this.name;
	}

}