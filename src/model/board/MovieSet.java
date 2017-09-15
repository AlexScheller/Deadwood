package model.board;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

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
	
	public boolean isWrapped() {
		return (takesLeft == 0);
	}

	public Role getRole(String which) throws IllegalArgumentException, IllegalStateException {
		Role ret;
		if (roles.containsKey(which)) {
			ret = roles.get(which);
		} else if (scene.hasRole(which)) {
			ret = scene.getRole(which);
		} else {
			throw new IllegalArgumentException("no such role: " + which + " exists in this set or on the card in this set.");
		}
		if (ret.isOccupied()) {
			throw new IllegalStateException("role: " + which + " is already occupied.");
		}
		return ret;
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

	public String[] getRolesAvailableAsArray() {
		// System.out.println("Getting roles from Set: " + getName());
		// System.out.println(scene.toString());
		List<String> ret = scene.getRolesAvailableAsList();
		for (String key : roles.keySet()) {
			if (!roles.get(key).isOccupied()) {
				ret.add(roles.get(key).getName());
			}
		}
		return ret.toArray(new String[ret.size()]);
	}

}