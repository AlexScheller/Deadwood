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
				// removeShot irrelevant.
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
			// TODO: Figure out a way to move all of the logic either
			// onto the player, or onto the role alone.
			this.currentRole = roomAsSet.getRole(which);
			currentRole.takeActor(this);
		} else {
			throw new IllegalStateException("current room is not a set");
		}
	}

	// TODO: fix this jank
	public void resetMove() {
		this.hasMoved = false;
	}

	public void move(String where) throws IllegalArgumentException {
		this.currentRoom = currentRoom.getNeighbor(where);
		this.hasMoved = true;
	}

	public boolean canMove() {
		return (!hasMoved && !isActing());
	}

	public boolean isActing() {
		return (currentRole != null);
	}

	// public void upgrade(int level) {
	// 	if (this.currentRoom instanceof Office) {
	// 		Map<Integer, Integer> 			
	// 	} else {
	// 		throw new IllegalStateException("Player not in office");
	// 	}
	// }

	// TODO: check money
	public boolean canUpgrade() {
		return (currentRoom instanceof CastingOffice);
	}

	// TODO: make sure there are actually roles available,
	// not just that this is a set
	public boolean hasRolesAvailable() {
		if (currentRoom instanceof MovieSet) {
			MovieSet asSet = (MovieSet) currentRoom;
			return !asSet.isWrapped() && getRolesAvailable().length > 0;
		} else {
			return false;
		}
	}

	public int calculateScore() {
		return (dollars + credits + (5 * rank)); 
	}

	public String getName() {
		return name;
	}

	public int getID() {
		return playerID;
	}

	@Override
	public String toString() {
		String ret = "Player: " + Integer.toString(playerID + 1);
		ret += "\nRoom: " + currentRoom.getName();
		ret += "\nDollars: " + this.dollars;
		ret += "\nCredits: " + this.credits;
		if (isActing()) {
			ret += "\nCurrent Role: " + currentRole.getName();
			ret += "\nRehearsal chips: " + this.rehersalChips;
		}
		return ret;	
	}

	public String[] getNeighborStrings() {
		return currentRoom.getNeighborStrings();
	}

	public String[] getRolesAvailable() {
		if (currentRoom instanceof MovieSet) {
			MovieSet asSet = (MovieSet) currentRoom;
			return asSet.getRolesAvailableAsArray();
		} else {
			throw new IllegalStateException("current room is not a set");
		}
	}

}