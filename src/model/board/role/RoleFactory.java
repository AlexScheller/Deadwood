package model.board.role;

public class RoleFactory {

	private static final RoleFactory instance = new RoleFactory();

	public RoleFactory() {}

	public Role getRole(RoleInfo ri) {
		if (ri.roleType == RoleInfo.Type.STARRING) {
			return new StarringRole(ri);
		} else if (ri.roleType == RoleInfo.Type.EXTRA) {
			return new ExtraRole(ri);
		} else {
			throw new IllegalArgumentException("Role type \"" + ri.roleType + "\" not recognized");
		}
	}

	public static RoleFactory getInstance() { return instance; }

}