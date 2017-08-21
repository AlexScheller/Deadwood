package model.board;

import model.board.SceneCardInfo;

import java.util.Map;
import java.util.HashMap;

public class SceneCard {

	private int id;
	private int budget;
	private int number; // necessary?
	private String title;
	private String description;
	private Map<String, Role> roles;

	public SceneCard(SceneCardInfo ci) {
		this.budget = ci.budget;
		this.id = ci.id;
		this.number = ci.number; // necessary?
		this.title = ci.title;
		this.description = ci.description;
		RoleFactory rf = RoleFactory.getInstance();
		this.roles = new HashMap<>();
		for (RoleInfo r : ci.roleInfos) {
			this.roles.put(r.name, rf.getRole(r));
		}
	}

	private String getTabbedRoles() {
		String ret = "roles:\n";
		for (String key : roles.keySet()) {
			ret += "\t" + roles.get(key).toString() + "\n";
		}
		return ret;
	}

	public int getBudget() {
		return this.budget;
	}

	public boolean hasRole(String which) {
		return roles.containsKey(which);
	}

	public Role getRole(String which) {
		return roles.get(which);
	}

	public String[] getRolesAvailable() {
		String[] ret = new String[roles.size()];
		int i = 0;
		for (String key : roles.keySet()) {
			ret[i] = roles.get(key).getName();
		}
		return ret;
	}

	@Override
	public String toString() {
		return "number: " + number + "\ntitle: " + title + "\nDescription: " + description + "\nBudget: " + Integer.toString(budget) + "\n" + getTabbedRoles();
	}

}