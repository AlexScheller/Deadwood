package model.board;

import java.util.Map;
import java.util.HashMap;

public class MovieSet extends Room {

	private SceneCard scene;
	private Map<String, Role> roles;
	private int takesLeft;

	public MovieSet (RoomInfo ri) {
		super(ri);
		this.takesLeft = ri.takesLeft;
		this.roles = new HashMap<>();
		RoleFactory rf = RoleFactory.getInstance();
		for (RoleInfo r : ri.roleInfos) {
			this.roles.put(r.name, rf.getRole(r));
		}
	}

	public void setSceneCard(SceneCard sc) {
		this.scene = sc;
	}

	public void removeShot() {
		this.takesLeft--;
	}

	public void wrap() {
		System.out.print("wrapping");
	}

	// TODO IMPLEMENT
	public boolean isWrapped() {
		return (takesLeft == 0);
	}

	public Role getRole(String which) {
		if (roles.containsKey(which)) {
			return roles.get(which);
		} else if (scene.hasRole(which)) {
			return scene.getRole(which);
		} else {
			throw new IllegalArgumentException("no such role: " + which + " exists in this set or on the card in this set.");
		}
	}

	private String getTabbedRoles() {
		String ret = "roles:\n";
		for (String key : roles.keySet()) {
			ret += "\t" + roles.get(key).toString() + "\n";
		}
		return ret;
	}

	@Override
	public String toString() {
		return "Set: " + this.name + "\n" + getTabbedNeighborStrings() + "\n" + getTabbedRoles();
	}

	public String[] getRolesAvailable() {
		String[] sceneRoles = scene.getRolesAvailable();
		String[] ret = new String[sceneRoles.length + roles.size()];
		int i;
		for (i = 0; i < sceneRoles.length; i++) {
			ret[i] = sceneRoles[i];
		}
		for (String key : roles.keySet()) {
			ret[i] = roles.get(key).getName();
			i++;
		}
		return ret;
	}

}