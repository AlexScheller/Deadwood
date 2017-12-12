package model.player;

import java.util.Random;

import model.ModelListener;
import model.board.room.Room;
import model.board.room.MovieSet;
import model.board.room.CastingOffice;
import model.board.role.Role;

public class Player {

	private int id = -1;
	private String name = "generic";

	private int rank = 1;
	private int dollars = 0;
	private int credits = 0;
	private int rehersalChips = 0;

	private boolean hasMoved = false;

	private Role currentRole;
	private Room currentRoom;
	private Room initialRoom; // this seems like real bad oop
	// private SceneCard currentScene;

	private Random dice;

	private ModelListener listener;

	/* Constructors */

	public Player(int id, Room initialRoom) {
		this.id = id;
		this.currentRoom = initialRoom;
		this.initialRoom = initialRoom;
		this.dice = new Random();
	}

	public Player(int id, Room initialRoom, int credits) {
		this(id, initialRoom);
		this.credits = credits;
	}

	public Player(int id, Room initialRoom, int credits, int rank) {
		this(id, initialRoom, credits);
		this.rank = rank;
	}

	public Player(int id, Room initialRoom, int credits, int dollars, int rank) {
		this(id, initialRoom, credits, rank);
		this.dollars = dollars;
	}

	public void setListener(ModelListener ml) {
		this.listener = ml;
	}

	/* Functional Methods */

	private int roll() {
		return 1 + dice.nextInt(6);
	}

	public void act() throws IllegalStateException {
		if (isActing()) {
			int roll = rehersalChips + roll();
			MovieSet roomAsSet = (MovieSet) currentRoom;
			// having to provide the current role still seems
			// a little wrong
			roomAsSet.playerActs(roll, currentRole);
		} else {
			throw new IllegalStateException("player not in a role");
		}
	}

	public void rehearse() {
		if (isActing()) {
			MovieSet roomAsSet = (MovieSet) currentRoom;
			if (rehersalChips < (roomAsSet.getBudget() - 1)) {
				rehersalChips++;
				listener.playerRehearses();
			} else {
				throw new IllegalStateException("player already has max rehearsal chips");
			}
		} else {
			throw new IllegalStateException("player not in a role");
		}
	}

	public void earnDollars(int dollars) {
		this.dollars += dollars;
	}

	public void earnCredits(int credits) {
		this.credits += credits;
	}

	public void finishRole() {
		this.currentRole = null;
		this.rehersalChips = 0;
	}

	public void takeRole(String which) throws IllegalStateException, IllegalArgumentException {
		if (currentRoom instanceof MovieSet) {
			MovieSet roomAsSet = (MovieSet) currentRoom;
			this.currentRole = roomAsSet.getRole(which);
			currentRole.takeActor(this);
			// roomAsSet.assignPlayerToRole(this, which);
		} else {
			throw new IllegalStateException("current room is not a set");
		}
	}

	// TODO: fix this jank
	public void resetMove() {
		this.hasMoved = false;
	}

	/* below is for debugging, use at your own risk */
	public void teleport(Room where) {
		this.currentRoom = where;
		// this.hasMoved = true;
	}
	/* above is for debugging */

	public void move(String where) throws IllegalArgumentException {
		this.currentRoom = currentRoom.getNeighbor(where);
		this.hasMoved = true;
	}

	public void newDay() {
		if (isActing()) {
			this.currentRole.evictActor();
		}
		this.currentRoom = initialRoom;
		this.hasMoved = false;
	}

	public void upgrade(int rank, String currency) throws IllegalStateException, IllegalArgumentException {
		if (currentRoom instanceof CastingOffice) {
			CastingOffice asOffice = (CastingOffice) currentRoom;
			asOffice.upgradePlayer(this, rank, currency);
		} else {
			throw new IllegalStateException("Player not in office");
		}
	}

	/* Informational Methods */

	@Override
	public String toString() {
		String ret = "Player: " + Integer.toString(id + 1);
		ret += "\nRoom: " + currentRoom.getName();
		ret += "\nRank: " + rank;
		ret += "\nDollars: " + this.dollars;
		ret += "\nCredits: " + this.credits;
		if (isActing()) {
			ret += "\nCurrent Role: " + currentRole.getName();
			ret += "\nRehearsal chips: " + this.rehersalChips;
			MovieSet asSet = (MovieSet) currentRoom;
			ret += "\nTakes left: " + asSet.getTakesLeft();
		}
		return ret;	
	}

	public int getDollars() {
		return dollars;
	}

	public int getCredits() {
		return credits;
	}

	public void payDollars(int howMany) {
		this.dollars -= howMany;
	}

	public void payCredits(int howMany) {
		this.credits -= howMany;
	}

	// might be better named as "set level"
	public void rankUp(int rank) {
		this.rank = rank;
	}

	public boolean canMove() {
		return (!hasMoved && !isActing());
	}

	public boolean isActing() {
		return (currentRole != null);
	}

	private int getMaxUpgradeAvailable() throws IllegalStateException {
		if (currentRoom instanceof CastingOffice) {
			CastingOffice asOffice = (CastingOffice) currentRoom;
			// TODO: see TODO in getRolesAvailable()
			return asOffice.getMaxUpgradeAvailable(this);
		} else {
			throw new IllegalStateException("Current room is not the Casting Office");
		}
	}

	// public int[][] getUpgradePricesAvailable() {
	// 	if (currentRoom instanceof CastingOffice) {
	// 		CastingOffice asOffice = (CastingOffice) currentRoom;
	// 		// TODO: see TODO in getRolesAvailable()
	// 		return asOffice.getUpgradePricesAvailable(this);
	// 	} else {
	// 		throw new IllegalStateException("Current room is not the CastingOffice");
	// 	}
	// }

	// winner of the longest method name award
	public int[] getUpgradePricesAvailableWith(String which) throws IllegalStateException, IllegalArgumentException {
		if (currentRoom instanceof CastingOffice) {
			CastingOffice asOffice = (CastingOffice) currentRoom;
			return asOffice.getUpgradePricesAvailableWith(this, which);
		} else {
			throw new IllegalStateException("Current room is not the Casting Office");
		}
	}

	// TODO: check money
	public boolean canUpgrade() {
		if ((currentRoom instanceof CastingOffice) && rank < 6) {
			// hasUpgradesAvailable()
			return (getMaxUpgradeAvailable() > this.rank);
		}
		return false;
	}

	public boolean canRehearse() {
		if (isActing()) {
			MovieSet roomAsSet = (MovieSet) currentRoom;
			return rehersalChips < roomAsSet.getBudget();
		}
		return false;
	}

	public boolean hasRolesAvailable() {
		if (currentRoom instanceof MovieSet) {
			MovieSet asSet = (MovieSet) currentRoom;
			return !asSet.isWrapped() && getRolesAvailable("both").length > 0;
		} else {
			return false;
		}
	}

	public int calculateScore() {
		return (dollars + credits + (5 * rank)); 
	}

	public int getRank() {
		return this.rank;
	}

	public String getName() {
		// return name;
		return Integer.toString(id);
	}

	public int getId() {
		return id;
	}

	public String[] getNeighborStrings() {
		return currentRoom.getNeighborStrings();
	}

	public String[] getRolesAvailable(String type) throws IllegalStateException, IllegalArgumentException{
		if (currentRoom instanceof MovieSet) {
			MovieSet asSet = (MovieSet) currentRoom;
			return asSet.getRolesAvailableAsArray(this, type);
		} else {
			throw new IllegalStateException("current room is not a set");
		}
	}

}