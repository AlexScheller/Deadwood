package model.board.role;

import model.player.Player;

/*
 * This class represents the base class for roles.
 * A role is the vehicle for players to take part
 * in scenes, either on a card as a star, or off
 * card as an extra. Players are limited to roles
 * which are of equal or lower rank than the player
 * possesses. Success, failure, and final payouts
 * when a scene wraps depend on the type of role.
 */

public abstract class Role {

	protected int rankRequired;
	protected String name;
	protected String line;
	protected Player residentActor;

	public Role(RoleInfo ri) {
		this.rankRequired = ri.rankRequired;
		this.name = ri.name;
		this.line = ri.line;
	}

	/* Functional Methods */

	// A Role must define, depending on its type,
	// how it responds to both successful and
	// unsuccessful rolls on the part of a player.
	public abstract void success();

	public abstract void failure();

	public void takeActor(Player p) {
		this.residentActor = p;
	}

	// Removes the current player, as well as alerts
	// said player that they are being removed.
	public void evictActor() {
		this.residentActor.finishRole();
		this.residentActor = null;
	}

	/* Informational Methods */

	public String getName() {
		return this.name;
	}

	public int getRankRequired() {
		return this.rankRequired;
	}

	public boolean isOccupied() {
		return residentActor != null;
	}

}