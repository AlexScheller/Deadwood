package model.board;

public class RoleInfo {

	public enum Type {
		STARRING,
		EXTRA
	}

	public Type roleType;
	public String line;
	public String name;
	public int rankRequired;

}