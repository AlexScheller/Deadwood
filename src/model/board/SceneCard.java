package model.board;

import model.board.SceneCardInfo;

import java.util.List;
import java.util.ArrayList;

public class SceneCard {

	private int id;
	private int budget;
	private int number; // necessary?
	private String title;
	private String description;
	private List<Role> roles;

	public SceneCard(SceneCardInfo ci) {
		this.budget = ci.budget;
		this.id = ci.id;
		this.number = ci.number; // necessary?
		this.title = ci.title;
		this.description = ci.description;
		RoleFactory rf = RoleFactory.getInstance();
		this.roles = new ArrayList<>();
		for (RoleInfo r : ci.roleInfos) {
			this.roles.add(rf.getRole(r));
		}
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
		return "number: " + number + "\ntitle: " + title + "\nDescription: " + description + "\nBudget: " + Integer.toString(budget) + "\n" + getTabbedRoles();
	}

}