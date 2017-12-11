package view.loading;

import java.util.Map;

import java.awt.Point;

public class RoomInfo {

	public enum Type {
		MOVIE_SET,
		TRAILER,
		OFFICE
	}

	public Type roomType;
	public String name;
	public Point cardOrigin;
	public Point[] takeOrigins;
	public Map<String, Point> extraOrigins;

}