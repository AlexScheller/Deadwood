package model.board.room;

import java.util.Arrays;

import model.player.Player;

public class CastingOffice extends Room {

	// First column is dollars, second is credits.
	// would be better as a Map, but java's Map
	// syntax/usage is kind of a pain.
	private int[][] upgradeTable = {{4, 5}, // rank 2 ...
									{10, 10},
									{18, 15},
									{28, 20},
									{40, 25}}; // ... rank 6

	/* Constructors */

	public CastingOffice (RoomInfo ri) {
		super(ri);
	}

	/* Functional Methods */

	// if credits == 0 assume dollars
	public void upgradePlayer(Player p, int rank, String currency) throws IllegalArgumentException {
		if (currency.equals("dollars")) {
			p.payDollars(upgradeTable[rank-2][0]);
		} else if (currency.equals("credits")) {
			p.payCredits(upgradeTable[rank-2][1]);
		} else {
			throw new IllegalArgumentException("Specified currency: " + currency + " not valid.");
		}
		p.rankUp(rank);
	}

	/* Informational Methods */

	@Override
	public String toString() {
		return "casting office\n\n" + getTabbedNeighborStrings();
	}

	public int getMaxUpgradeAvailable(String currency, int amount) throws IllegalArgumentException {
		System.out.println("getting max upgrade with currency" + currency + " and amount: " + amount);
		int currencyIndex = -1;
		if (currency.equals("dollars")) {
			currencyIndex = 0;
		} else if (currency.equals("credits")) {
			currencyIndex = 1;
		} else {
			throw new IllegalArgumentException("No such method of purchase: " + currency);
		}
		int i = 0;
		while (i < upgradeTable.length && upgradeTable[i][currencyIndex] <= amount) {
			i++;
		}
		i--; // since last case failed.
		// account for the fact that upgrades begin at 2;
		int ret = (i > -1) ? i + 2 : 0;
		return ret;
	}

}