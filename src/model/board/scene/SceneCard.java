package model.board.scene;

import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.Random;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;

import model.player.Player;

import model.board.role.Role;
import model.board.role.RoleInfo;
import model.board.role.RoleFactory;
import model.board.role.StarringRole;
import model.board.scene.SceneCardInfo;

public class SceneCard {

	// private int id;
	private int budget;
	// private int number; // necessary?
	private String title;
	private String description;
	private Map<String, StarringRole> roles;

	public SceneCard(SceneCardInfo ci) {
		this.budget = ci.budget;
		// this.id = ci.id;
		// this.number = ci.number; // necessary?
		this.title = ci.title;
		this.description = ci.description;
		RoleFactory rf = RoleFactory.getInstance();
		this.roles = new HashMap<>();
		for (RoleInfo r : ci.roleInfos) {
			this.roles.put(r.name, (StarringRole)rf.getRole(r));
		}
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

	public List<String> getRolesAvailableAsList(Player p) {
		List<String> retList = new ArrayList<>();
		for (String key : roles.keySet()) {
			Role curr = roles.get(key);
			if (!curr.isOccupied() && p.getRank() >= curr.getRankRequired()) {
				retList.add(curr.getName());
			}
		}
		return retList;
	}

	public String[] getRolesAvailableAsArray(Player p) {
		List<String> ret = getRolesAvailableAsList(p);
		return ret.toArray(new String[ret.size()]);
	}

	// TODO: see if this can be simplified
	public void payBonuses() {

		// establish dice roles
		int[] diceRoles = new int[this.budget];
		Random r = new Random();
		for (int i = 0; i < this.budget; i++) {
			diceRoles[i] = r.nextInt(6) + 1;
		}
		Arrays.sort(diceRoles);
		// reverse it 
		for (int i = 0; i < (diceRoles.length / 2); i++) {
			int temp = diceRoles[i];
			diceRoles[i] = diceRoles[diceRoles.length - (i + 1)];
			diceRoles[diceRoles.length - (i + 1)] = temp;
		}
		// ArrayUtils.reverse(diceRoles);

		// convert roles into array in descending order
		// by rank required.
		StarringRole[] rolesAsArray = new StarringRole[roles.size()];
		int j = 0;
		for (String key : roles.keySet()) {
			rolesAsArray[j] = roles.get(key);
			j++;
		}
		Arrays.sort(rolesAsArray, new Comparator<Role>() {
			public int compare(Role r1, Role r2) {
				return r1.getRankRequired() - r2.getRankRequired();
			}
		});

		// distribute dice rolls
		for (int i = 0; i < diceRoles.length; i++) {
			if (rolesAsArray[i % rolesAsArray.length].isOccupied()) {
				rolesAsArray[i % rolesAsArray.length].payBonus(diceRoles[i]);
			}
		}
	}

	public void evictPlayers() {
		for (String key : roles.keySet()) {
			if (roles.get(key).isOccupied()) {
				roles.get(key).evictActor();
			}
		}
	}

	public boolean isOccupied() {
		for (String key : roles.keySet()) {
			if (roles.get(key).isOccupied()) {
				return true;
			}
		}
		return false;
	}

	public String getTitle() {
		return this.title;
	}

	// @Override
	// public String toString() {
	// 	return "id: " + id + "\ntitle: " + title + "\nDescription: " + description + "\nBudget: " + Integer.toString(budget) + "\n" + getTabbedRoles();
	// }

	private String getTabbedRoles() {
		String ret = "roles:\n";
		for (String key : roles.keySet()) {
			ret += "\t" + roles.get(key).toString() + "\n";
		}
		return ret;
	}

}