package model.board;

import java.util.List;

public class RoomInfo {

	public enum Type {
		MOVIE_SET,
		TRAILER,
		OFFICE
	}

	// public int id;
	public Type roomType;
	public String name;
	public String[] neighbors;
	public int numTakes;
	public List<RoleInfo> roleInfos;

}