package model.player;

import java.util.Random;

import model.events.ChildEventListener;
import model.board.room.Room;
import model.board.room.MovieSet;
import model.board.room.CastingOffice;
import model.board.role.Role;

import view.graphics.PlayerInfo;

public class Player {

	private int id = -1;
	private String name = "generic";

	private int rank = 1;
	private int dollars = 0;
	private int credits = 0;
	private int rehearsalTokens = 0;

	private boolean hasMoved = false;

	private Role currentRole;
	private Room currentRoom;
	private Room initialRoom; // this seems like real bad oop
	// private SceneCard currentScene;

	private Random dice;

	// private ModelListener listener;

	private ChildEventListener listener;

	/* Constructors */

	public Player(int id, Room initialRoom) {
		this.id = id;
		this.name = Integer.toString(id);
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

	public void setListener(ChildEventListener cel) {
		this.listener = cel;
	}

	/* Functional Methods */

	private int roll() {
		return 1 + dice.nextInt(6);
	}

	public void act() throws IllegalStateException {
		if (isActing()) {
			int roll = rehearsalTokens + roll();
			MovieSet roomAsSet = (MovieSet) currentRoom;
			// having to provide the current role still seems
			// a little wrong
			listener.playerActsEvent(roll);
			roomAsSet.playerActs(roll, currentRole);
			endTurn();
		} else {
			throw new IllegalStateException("player not in a role");
		}
	}

	public void rehearse() {
		if (isActing()) {
			MovieSet roomAsSet = (MovieSet) currentRoom;
			if (rehearsalTokens < (roomAsSet.getBudget() - 1)) {
				rehearsalTokens++;
				listener.playerRehearsesEvent();
				endTurn();
			} else {
				throw new IllegalStateException("player already has max rehearsal tokens");
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
		this.rehearsalTokens = 0;
	}

	public void takeRole(String which) throws IllegalStateException, IllegalArgumentException {
		if (currentRoom instanceof MovieSet) {
			MovieSet roomAsSet = (MovieSet) currentRoom;
			Role newRole = roomAsSet.getRole(which);
			if (rank >= newRole.getRankRequired()) {
				this.currentRole = newRole;
				currentRole.takeActor(this);
				listener.playerTakesRoleEvent();
				endTurn();
			} else {
				throw new IllegalStateException("role to high a rank for current player");
			}
		} else {
			throw new IllegalStateException("current room is not a set");
		}
	}

	// TODO: fix this jank
	public void resetMove() {
		this.hasMoved = false;
	}

	// PROTOTYPE_CHAIN: 2
	public void endTurn() {
		this.hasMoved = false;
		listener.playerEndsTurnEvent();
	}

	public void move(String where) throws IllegalArgumentException, IllegalStateException {
		if (canMove()) {
			String from = currentRoom.getName();
			this.currentRoom = currentRoom.getNeighbor(where);
			this.hasMoved = true;
			System.out.println("moving");
			listener.playerMovesEvent(id, from, where);
			if (!hasActionLeft()) {
				endTurn();
			}
		} else {
			throw new IllegalStateException("player cannot move");
		}
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
			listener.playerUpgradesEvent();
			endTurn();
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
			ret += "\nRehearsal tokens: " + this.rehearsalTokens;
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

	// might be better named as "set rank"
	public void rankUp(int rank) {
		this.rank = rank;
	}

	public boolean canMove() {
		return (!hasMoved && !isActing());
	}

	public boolean canTakeNewRole() {
		if (currentRoom instanceof MovieSet) {
			MovieSet asSet = (MovieSet) currentRoom;
			return (!isActing() && !asSet.isWrapped() && hasRolesAvailable());
		}
		return false;
	}

	private boolean hasActionLeft() {
		return (canTakeNewRole() || canUpgrade() || canMove() || canAct()); 
	}

	public boolean isActing() {
		return (currentRole != null);
	}

	public int getMaxUpgradeAvailable(String currency) throws IllegalStateException, IllegalArgumentException {
		if (currentRoom instanceof CastingOffice) {
			CastingOffice asOffice = (CastingOffice) currentRoom;
			int amount = 0;
			if (currency.equals("dollars")) {
				amount = dollars;
			} else if (currency.equals("credits")) {
				amount = credits;
			} else {
				throw new IllegalArgumentException("no such currency: " + currency);
			}
			return asOffice.getMaxUpgradeAvailable(currency, amount);
		} else {
			throw new IllegalStateException("current room is not the Casting Office");
		}
	}

	// TODO: check money
	public boolean canUpgrade() {
		if ((currentRoom instanceof CastingOffice) && rank < 6) {
			// hasUpgradesAvailable()
			return (getMaxUpgradeAvailable("dollars") > this.rank ||
					getMaxUpgradeAvailable("credits") > this.rank);
		}
		return false;
	}

	public boolean canRehearse() {
		if (isActing()) {
			MovieSet roomAsSet = (MovieSet) currentRoom;
			return rehearsalTokens < (roomAsSet.getBudget() - 1);
		}
		return false;
	}

	// This quite incorrect, as it depends on
	// on the context of when this question is asked.
	// For instance, if for some reason this question is
	// asked when it's not this player's turn, it will
	// still return true, despite that fact that the
	// player shouldn't actually be able to take any actions
	// at all. Currently I believe the code is managed such
	// that this won't be called in an improper context, but
	// perhaps there should be some sort of "isMyTurn" boolean,
	// or some shared lockout semaphore between the players.
	private boolean canAct() {
		return isActing();
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
		return Integer.toString(id + 1);
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

	public PlayerContext toContext() {
		PlayerContext ret = new PlayerContext();
		ret.acting = isActing();
		ret.canRehearse = canRehearse();
		ret.canUpgrade= canUpgrade();
		ret.id = id;
		ret.name = getName();
		ret.rank = rank;
		ret.dollars = dollars;
		ret.credits = credits;
		ret.rehearsalTokens = rehearsalTokens;
		return ret;
	}

}