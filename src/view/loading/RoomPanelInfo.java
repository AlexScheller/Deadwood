package view.loading;

public class RoomPanelInfo {

	public enum Type {
		MOVIE_SET,
		TRAILER,
		OFFICE
	}

	public Type roomType;
	public String name;
	public int cardPanelXOrigin;
	public int cardPanelYOrigin;

}