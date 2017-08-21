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
	private SceneCard currentScene;

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

	public void act() {
		int roll = rehersalChips + roll();
		if (roll >= currentScene.getBudget()) {
			MovieSet roomAsSet = (MovieSet) currentRoom;
			roomAsSet.removeShot();
			currentRole.success();
		} else {
			currentRole.failure();
		}
	}

	public void rehearse() {
		if (rehersalChips == currentScene.getBudget()) {
			rehersalChips = 0;
			MovieSet roomAsSet = (MovieSet) currentRoom;
			roomAsSet.wrap();
		} else {
			rehersalChips++;
		}
	}

	public void earnDollars(int dollars) {
		this.dollars += dollars;
	}

	public void earnCredits(int credits) {
		this.credits += credits;
	}

	public void finishRoll() {
		this.currentRole = null;
	}

	public void takeRole(String which) {
		MovieSet roomAsSet = (MovieSet) currentRoom;
		try {
			this.currentRole = roomAsSet.getRole(which);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	// TODO: fix this jank
	public void resetMove() {
		this.hasMoved = false;
	}

	public void move(String where) {
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

	// TODO: implement
	public boolean hasRolesAvailable() {
		return true;
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
		return "Player " + Integer.toString(playerID) + " in room: " + currentRoom.getName();
	}

	public String[] getNeighborStrings() {
		return currentRoom.getNeighborStrings();
	}

	public String[] getRolesAvailable() {
		MovieSet asSet = (MovieSet) currentRoom;
		return asSet.getRolesAvailable();
	}

}