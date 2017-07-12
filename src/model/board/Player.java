public class Player {

	private int playerID = -1;
	private String name = "generic";

	private int rank = 1;
	private int money = 0;
	private int credits = 0;
	private int rehersalChips = 0;

	private Room currentRoom;

	public Player(int id) {
		this.playerID = id;
		this.currentRoom = Rooms.getRoom("trailers");
	}

	public Player(int id, int credits) {
		this(id);
		this.credits = credits;
	}

	public void rankUp() { this.rank++; }

	@Override
	public String toString() {
		return "Player " + Integer.toString(playerID);
	}

}