package model.board;

import java.util.List;
import java.util.ArrayList;

public class MovieSet extends Room {

	private SceneCard scene;
	private List<Role> roles;
	private int numTakes;

	public MovieSet (RoomInfo ri) {
		super(ri);
		this.numTakes = ri.numTakes;
		this.roles = new ArrayList<>();
		RoleFactory rf = RoleFactory.getInstance();
		for (RoleInfo r : ri.roleInfos) {
			this.roles.add(rf.getRole(r));
		}
	}

	public void setSceneCard(SceneCard sc) {
		this.scene = sc;
	}

	// TODO IMPLEMENT
	public boolean isWrapped() {
		return true;
	}

	private String getTabbedRoles() {
		String ret = "roles:\n";
		for (Role r : roles) {
			ret += "\t" + r.toString() + "\n";
		}
		return ret;
	}

	@Override
	public String toString() {
		return "Set: " + this.name + "\n" + getTabbedNeighborStrings() + "\n" + getTabbedRoles();
	}

}