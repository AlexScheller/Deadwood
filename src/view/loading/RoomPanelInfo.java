package view.loading;

import java.util.Map;

import java.awt.Point;

public class RoomPanelInfo {

	public enum Type {
		MOVIE_SET,
		TRAILER,
		OFFICE
	}

	public Type roomType;
	public String name;
	// public int cardPanelXOrigin;
	// public int cardPanelYOrigin;
	public Point cardPanelOrigin;
	public Point[] takeOrigins;
	public Map<String, Point> extraOrigins;

}