package model.board;

import model.players.Player;

public class CastingOffice extends Room {

	// First column is dollars, second is credits.
	// would be better as a Map, but java's Map
	// syntax/usage is kind of a pain.
	private int[][] upgradeTable = {{4, 5}, {10, 10}, {18, 15},
							 {28, 20}, {40, 25}};

	/* Constructors */

	public CastingOffice (RoomInfo ri) {
		super(ri);
	}

	/* Functional Methods */

	// if credits == 0 assume dollars
	public void upgradePlayer(Player p, int level, int credits, int dollars) throws IllegalArgumentException {
		if (!((level - 1) > getUpgradesAvailable(p))) {
			System.out.println("hello there!");
		} else {
			throw new IllegalArgumentException("Specified level: " + level + " cannot be afforded by player: " + p.toString());
		}
	}

	/* Informational Methods */

	@Override
	public String toString() {
		return "casting office\n\n" + getTabbedNeighborStrings();
	}

	public int getUpgradesAvailable(Player p) {
		int maxRankAvailable = 1;
		for (int i = 0; i < upgradeTable.length; i++) {
			if (p.getDollars() >= upgradeTable[i][0] ||
				p.getCredits() >= upgradeTable[i][1]) {
				maxRankAvailable++;
			} else {
				break;
			}
		}
		return maxRankAvailable;
	}

}