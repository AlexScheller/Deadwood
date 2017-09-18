package model.board;

import model.players.*;

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

	@Override
	public String toString() {
		return "name: " + name + " rank: " + rankRequired + " line: " + line;
	}

	public String getName() {
		return this.name;
	}

	public int getRankRequired() {
		return this.rankRequired;
	}

	public void takeActor(Player p) {
		this.residentActor = p;
	}

	public boolean isOccupied() {
		return residentActor != null;
	}

	public void evictActor() {
		this.residentActor.finishRole();
		this.residentActor = null;
	}

	public abstract void success();

	public abstract void failure();

}