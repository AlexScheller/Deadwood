package model.board;

public abstract class Role {

	protected int rankRequired;
	protected String name;
	protected String line;

	public Role(RoleInfo ri) {
		this.rankRequired = ri.rankRequired;
		this.name = ri.name;
		this.line = ri.line;
	}

	@Override
	public String toString() {
		return "name: " + name + " rank: " + rankRequired + " line: " + line;
	}

	// abstract void wrap();

}