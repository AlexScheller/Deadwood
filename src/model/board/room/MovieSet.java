package model.board.room;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import model.player.Player;

import model.board.role.Role;
import model.board.role.RoleInfo;
import model.board.role.ExtraRole;
import model.board.role.RoleFactory;

import model.board.scene.SceneCard;

public class MovieSet extends Room {

	private SceneCard scene;
	private Map<String, ExtraRole> roles;
	private int numTakes;
	private int takesLeft;

	public MovieSet (RoomInfo ri) {
		super(ri);
		this.numTakes = ri.takesLeft;
		this.takesLeft = ri.takesLeft;
		this.roles = new HashMap<>();
		RoleFactory rf = RoleFactory.getInstance();
		for (RoleInfo r : ri.roleInfos) {
			this.roles.put(r.name, (ExtraRole)rf.getRole(r));
		}
	}

	public void setSceneCard(SceneCard sc) {
		this.takesLeft = numTakes;
		this.scene = sc;
		listener.newSceneInSet(name, sc.getTitle());
	}

	public void playerActs(int roll, Role playersRole) {
		if (roll >= getBudget()) {
			playersRole.success();
			finishTake();
		} else {
			playersRole.failure();
		}
	}

	public void finishTake() {
		this.takesLeft--;
		listener.takeFinishes(name);
		if (takesLeft == 0) {
			wrap();
		}
	}

	public void payBonuses() {
		scene.payBonuses();
		for (String key : roles.keySet()) {
			if (roles.get(key).isOccupied()) {
				roles.get(key).payBonus();
			}
		}
	}

	/* Functional Methods */

	public void evictPlayers() {
		for (String key : roles.keySet()) {
			if (roles.get(key).isOccupied()) {
				roles.get(key).evictActor();
			}
		}
	}

	public void wrap() {
		listener.sceneWraps(name);
		// String ret = "wrapping scene: " + scene.getTitle();
		if (scene.isOccupied()) {
			payBonuses();
			// ret += " " + payBonuses();
			scene.evictPlayers();
		}
		evictPlayers();
		this.takesLeft = 0;
		//return ret;
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

	/* Informational Methods */

	@Override
	public String toString() {
		return "Set: " + this.name + "\n" + getTabbedNeighborStrings() + "\n" + getTabbedRoles();
	}

	public int getTakesLeft() {
		return takesLeft;
	}

	private String getTabbedRoles() {
		String ret = "roles:\n";
		for (String key : roles.keySet()) {
			ret += "\t" + roles.get(key).toString() + "\n";
		}
		return ret;
	}

	public List<String> getRolesAvailableAsList(Player p) {
		List<String> ret = new ArrayList<>();
		for (String key : roles.keySet()) {
			Role curr = roles.get(key);
			if (!curr.isOccupied() && p.getRank() >= curr.getRankRequired()) {
				ret.add(curr.getName());
			}
		}
		return ret;
	}

	public String[] getRolesAvailableAsArray(Player p, String type) throws IllegalArgumentException {
		// System.out.println("Getting roles from Set: " + getName());
		// System.out.println(scene.toString());
		List<String> ret = new ArrayList<>();
		if (type.equals("starring")) {
			ret = scene.getRolesAvailableAsList(p);
		} else if (type.equals("extra")) {
			ret = getRolesAvailableAsList(p);
		} else if (type.equals("both")) {
			ret = scene.getRolesAvailableAsList(p);
			ret.addAll(getRolesAvailableAsList(p));
		} else {
			throw new IllegalArgumentException("role type specified: " + type + " does not exist");
		}
		return ret.toArray(new String[ret.size()]);
	}

}