package model.players;

import java.util.Random;

import model.board.*;

public class Player {

	private int playerID = -1;
	private String name = "generic";

	private int rank = 1;
	private int dollars = 0;
	private int credits = 0;
	private int rehersalChips = 0;

	private boolean hasMoved = false;

	private Role currentRole;
	private Room currentRoom;
	// private SceneCard currentScene;

	private Random dice;

	/* Constructors */

	public Player(int id, Room initialRoom) {
		this.playerID = id;
		this.currentRoom = initialRoom;
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

	/* Functional Methods */

	private int roll() {
		return 1 + dice.nextInt(6);
	}

	public String act() throws IllegalStateException {
		if (isActing()) {
			int roll = rehersalChips + roll();
			MovieSet roomAsSet = (MovieSet) currentRoom;
			String ret = "Player " + playerID + " rolled a " + roll +  " ... ";
			if (roll >= roomAsSet.getBudget()) {
				// roomAsSet.removeShot();
				// TODO: find a way to make the order of success and 
				// removeShot irrelevant. perhaps the player shouldn't
				// know this much about the room's logic.
				currentRole.success();
				ret += "Player succeeds!\n" + "dollars: " + dollars + "\ncredits: " + credits + "\n";
				ret += roomAsSet.removeShot();
			} else {
				currentRole.failure();
				ret += "Player fails...\n" + "dollars: " + dollars + "\ncredits: " + credits + "\n";
			}
			return ret;
		} else {
			throw new IllegalStateException("player not in a role");
		}
	}

	public String rehearse() {
		// System.out.println("rehearsing");
		if (isActing()) {
			MovieSet roomAsSet = (MovieSet) currentRoom;
			if (rehersalChips == roomAsSet.getBudget()) {
				rehersalChips = 0;
				return roomAsSet.wrap();
			} else {
				rehersalChips++;
				return "Player " + (playerID + 1) + " now has " + rehersalChips + " rehearsal chips";
			}
		} else {
			throw new IllegalStateException("player not in a role");
		}
		// System.out.println("rehearsed");
	}

	public void earnDollars(int dollars) {
		this.dollars += dollars;
	}

	public void earnCredits(int credits) {
		this.credits += credits;
	}

	public void finishRole() {
		this.currentRole = null;
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

	/* below is for debugging and is therefore use at your own risk */
	public void teleport(Room where) {
		this.currentRoom = where;
		this.hasMoved = true;
	}
	/* above is for debugging */

	public void move(String where) throws IllegalArgumentException {
		this.currentRoom = currentRoom.getNeighbor(where);
		this.hasMoved = true;
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
		String ret = "Player: " + Integer.toString(playerID + 1);
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
		return name;
	}

	public int getID() {
		return playerID;
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