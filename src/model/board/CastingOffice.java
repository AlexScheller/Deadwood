package model.board;

import java.util.Arrays;
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
		if (!(level >= getMaxUpgradeAvailable(p))) {
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

	// TODO: this is kind of clunky
	public int[][] getUpgradePricesAvailable(Player p) throws IllegalStateException {
		int maxUpgrade = getMaxUpgradeAvailable(p);
		int minUpgrade = p.getRank() + 1;
		int[][] ret;
		if (maxUpgrade >= minUpgrade) {
			ret = new int[(maxUpgrade - minUpgrade) + 1][2];
			for (int i = 0; i <= (maxUpgrade - minUpgrade); i++) {
				ret[i] = Arrays.copyOf(upgradeTable[((minUpgrade - 1) + i) - 1], 2);
			}
		} else {
			throw new IllegalStateException("no upgrades available");
		}
		return ret;
	}

	public int getMaxUpgradeAvailable(Player p) {
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