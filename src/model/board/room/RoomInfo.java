package model.board.room;

import java.util.List;

import model.board.role.RoleInfo;

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
	public int takesLeft;
	public List<RoleInfo> roleInfos;

}