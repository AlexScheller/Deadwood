public class Player {

	private int playerID = -1;
	private String name = "generic";

	private int rank = 1;
	private int money = 0;
	private int credits = 0;
	private int rehersalChips = 0;

	// temporary
	private Scanner sc;

	private Room currentRoom;

	public Player(int id) {
		this.playerID = id;
		this.currentRoom = Rooms.getRoom("trailers");
		this.sc = new Scanner(System.in);
	}

	public Player(int id, int credits) {
		this(id);
		this.credits = credits;
	}

	public void playTurn() {
		// to be removed once the view is implemented.
		System.out.println("choose your move:");
		
	}

	public void rankUp() { this.rank++; }

	public int calculateScore() {
		return (money + credits + (5 * rank)); 
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Player " + Integer.toString(playerID);
	}

}