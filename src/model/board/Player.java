package model.board;

import java.util.Scanner;

public class Player {

	private int playerID = -1;
	private String name = "generic";

	private int rank = 1;
	private int money = 0;
	private int credits = 0;
	private int rehersalChips = 0;

	private Role currentRole;
	private Room currentRoom;
	private SceneCard currentScene;

	private Random dice;

	public Player(int id, Room initialRoom) {
		this.playerID = id;
		this.currentRoom = initialRoom;
		this.dice = new Random();
	}

	public Player(int id, initialRoom, int credits) {
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
			currentScene.removeShot();
			currentRole.payOnSucess();
		}
	}

	public void rehearse() {
		if (rehersalChips == currentScene.getBudget()) {
			rehersalChips == 0;
			currentRoom.wrap();
		} else {
			rehersalChips++;
		}
	}

	public void takeRole(String which) {
		this.currentRole(currentRoom.getRole(which));
	}

	public void move(String where) {
		this.currentRoom = currentRoom.getNeighbor(where);
	}

	public void upgrade(int level) {
		this.currentRoom.upgrade(this, level);
	}

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