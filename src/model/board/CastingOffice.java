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

	public int[] getUpgradePricesAvailableWith(Player p, String which) throws IllegalArgumentException, IllegalStateException {
		int maxUpgrade = getMaxUpgradeAvailableWith(p, which);
		int minUpgrade = p.getRank() + 1;
		if (maxUpgrade >= minUpgrade) {
			int currencyIndex = -1;
			if (which.equals("dollars")) {
				currencyIndex = 0;
			} else if (which.equals("credits")) {
				currencyIndex = 1;
			} else {
				throw new IllegalArgumentException("No such method of purchase: " + which);
			}
			// ex: player is rank 2, and can rank up to rank 4,
			// ret = new int[(4 - 3) + 1] == new int[2] == "int[rank 3, rank 4]"
			int[] ret = new int[(maxUpgrade - minUpgrade) + 1];
			//  the base accounts for 1- to 0- based indexing,
			// and upgrades beginning at level 2
			int base = minUpgrade - 2;
			for (int i = 0; i <= (maxUpgrade - minUpgrade); i++) {
				// ex: same as above,
				// ret[0] = upgradeTable[1 + 0] = rank 3 
				// ret[1] = upgradeTable[1 + 1] = rank 4
				ret[i] = upgradeTable[base + i][currencyIndex];
			}
			return ret;
		} else {
			throw new IllegalStateException("no upgrades available");
		}
	}

	// TODO: this is kind of clunky
	// public int[][] getUpgradePricesAvailable(Player p) throws IllegalStateException {
	// 	int maxUpgrade = getMaxUpgradeAvailable(p);
	// 	int minUpgrade = p.getRank() + 1;
	// 	int[][] ret;
	// 	if (maxUpgrade >= minUpgrade) {
	// 		ret = new int[(maxUpgrade - minUpgrade) + 1][2];
	// 		for (int i = 0; i <= (maxUpgrade - minUpgrade); i++) {
	// 			ret[i] = Arrays.copyOf(upgradeTable[((minUpgrade - 1) + i) - 1], 2);
	// 		}
	// 	} else {
	// 		throw new IllegalStateException("no upgrades available");
	// 	}
	// 	return ret;
	// }

	private int getMaxUpgradeAvailableWith(Player p, String which) throws IllegalArgumentException {
		int maxUpgrade = 1;
		int currency = 0;
		int currencyIndex = -1;
		if (which.equals("dollars")) {
			currency = p.getDollars();
			currencyIndex = 0;
		} else if (which.equals("credits")) {
			currency = p.getCredits();
			currencyIndex = 1;
		} else {
			throw new IllegalArgumentException("No such method of purchase: " + which);
		}
		for (int i = 0; i < upgradeTable.length; i++) {
			if (currency >= upgradeTable[i][currencyIndex]) {
				maxUpgrade++;
			} else {
				break;
			}
		}
		return maxUpgrade;
	}

	public int getMaxUpgradeAvailable(Player p) {
		return Math.max(getMaxUpgradeAvailableWith(p, "dollars"),
				   getMaxUpgradeAvailableWith(p, "credits"));
	}



}