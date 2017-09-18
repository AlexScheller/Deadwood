package model.board;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class MovieSet extends Room {

	private SceneCard scene;
	private Map<String, ExtraRole> roles;
	private int takesLeft;

	public MovieSet (RoomInfo ri) {
		super(ri);
		this.takesLeft = ri.takesLeft;
		this.roles = new HashMap<>();
		RoleFactory rf = RoleFactory.getInstance();
		for (RoleInfo r : ri.roleInfos) {
			this.roles.put(r.name, (ExtraRole)rf.getRole(r));
		}
	}

	public void setSceneCard(SceneCard sc) {
		this.scene = sc;
	}

	public String removeShot() {
		this.takesLeft--;
		if (takesLeft == 0) {
			return "takes finished, " + wrap();
		}
		return "takes left: " + takesLeft;
	}

	public String payBonuses() {
		scene.payBonuses();
		for (String key : roles.keySet()) {
			if (roles.get(key).isOccupied()) {
				roles.get(key).payBonus();
				// ret += roles.get(key).getRankRequired();
			}
		}
		return "paying bonuses";
	}

	public void evictPlayers() {
		for (String key : roles.keySet()) {
			if (roles.get(key).isOccupied()) {
				roles.get(key).evictActor();
			}
		}
	}

	public String wrap() {
		String ret = "wrapping scene: " + scene.getTitle();
		if (scene.isOccupied()) {
			ret += " " + payBonuses();
			scene.evictPlayers();
		}
		evictPlayers();
		return ret;
	}
	
	public boolean isWrapped() {
		return (takesLeft == 0);
	}

	public int getBudget() {
		return scene.getBudget();
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