package view;

import java.util.Scanner;

import model.player.Player;

import model.DeadwoodModel;
import model.ModelListener;

// NOTE: this is currently just a dummy class, as major
// changes to the design of the model and ModelListener
// took place during the development of the GraphicalView

// TODO: make this class compliant with updated design
public class ConsoleView implements DeadwoodView, ModelListener {
	
	private ViewListener listener;

	@Override
	public void setListener(ViewListener vl) {
		this.listener = vl;
	}

	@Override
	public void sceneWraps(String where) {
		// TEMP
		System.out.println("scene wrapping in: " + where);
	}

	@Override
	public void takeFinishes(String where) {
		// TEMP
		System.out.println("take finished in: " + where);
	}

	@Override
	public void newSceneInSet(String where, String title) {
		// TEMP
		System.out.println("new scene: " + title + " in: " + where);
	}

	@Override
	public void playerRehearses() {
		// TEMP
		System.out.println("player rehearses");
	}

	/*
	private DeadwoodModel model;

	// private final String ANSIRed = "\u001b[31m";
	// private final String ANSIGreen = "\u001b[32m";
	// private final String ANSIEsc = "\u001b[0m";

	private Scanner sc;

	public ConsoleView(DeadwoodModel model) {
		this.model = model;
		model.setListener(this);
		this.sc = new Scanner(System.in);
	}

	private void displayGameState() {
		System.out.println("========\n" + model.currentStateToString());
	}

	// TODO: Currently there is a weird mishmash of code reuse and
	// specialization concerning the choices available to the player.
	// Perhaps find a way to find a more uniform solution.

	// TODO: rewrite this as loopOverMoveChoices, since that's the
	// only choice left in this function
	private void loopOverChoices(String which, String[] choices) {
		boolean choosing = true;
		System.out.println("========");
		while (choosing) {
			for (int i = 1; i <= choices.length; i++) {
				System.out.println(i + ": " + choices[i-1]);
			}
			System.out.print("========\nchoice: ");
			String choice = sc.next();
			try {
				int choiceIndex = Integer.parseInt(choice);
				if (1 <= choiceIndex && choiceIndex <= choices.length) {
					choosing = false;
					if (which.equals("move")) {
						listener.playerMoveRequest(choices[choiceIndex - 1]);
						// The only instance in which a player may take another
						// action is after moving.
						displayChoices();
					}
				} else {
					System.out.println("========\nplease choose one of the available choices.");
				}
			} catch (NumberFormatException e) {
				System.out.println("========\nplease enter a number");
			}
		}
	}

	private void loopOverUpgradeChoices(int minUpgrade, int[] dollarCosts, int[] creditCosts) {
		boolean choosing = true;
		while (choosing) {
			System.out.println("========\nRank | credits | dollars");
			System.out.println("------------------------");
			int maxLength = Math.max(dollarCosts.length, creditCosts.length);
			for (int i = 0; i < maxLength; i++) {
				System.out.print("  " + (i + minUpgrade) + "  |");
				if (i < dollarCosts.length) {
					System.out.print("   " + dollarCosts[i] + "    ");
					if (dollarCosts[i] < 10) {
						System.out.print(" ");
					}
				} else {
					System.out.print("         ");
				}
				System.out.print("|");
				if (i < creditCosts.length) {
					System.out.println("   " + creditCosts[i]);
				} else {
					System.out.println();
				}
			}
			// currently the below isn't fully implemented
			System.out.print("========\nchoice: ");
			String choice = sc.next();
			try {
				int choiceIndex = Integer.parseInt(choice);
				if (minUpgrade <= choiceIndex && choiceIndex <= ((maxLength - 1) + minUpgrade)) {
					choosing = false;
					// System.out.println("Hello!");
					int dollarChoiceMax = (dollarCosts.length - 1) + minUpgrade;
					int creditChoiceMax = (creditCosts.length - 1) + minUpgrade;
					if (choiceIndex <= dollarChoiceMax && choiceIndex <= creditChoiceMax) {
						boolean choosingAgain = true;
						while (choosingAgain) {
							System.out.println("Pay with dollars or credits?\n1. dollars\n2. credits");
							System.out.println("========\nchoice: ");
							String currencyChoice = sc.next();
							try {
								int newChoiceIndex = Integer.parseInt(currencyChoice);
								if (newChoiceIndex == 1) {
									choosingAgain = false;
									listener.playerUpgradeRequest(choiceIndex, "dollars");
								} else if (newChoiceIndex == 2) {
									choosingAgain = false;
									listener.playerUpgradeRequest(choiceIndex, "credits");
								} else {
									System.out.println("========\nplease choose one of the available choices.");
								}
							} catch (NumberFormatException e) {
								System.out.println("========\nplease enter a number");
							}
						}
					} else if (choiceIndex > dollarChoiceMax) {
						System.out.println("hiya");
						System.out.println(choiceIndex);
						listener.playerUpgradeRequest(choiceIndex, "credits");
					} else {
						listener.playerUpgradeRequest(choiceIndex, "dollars");
					}
				} else {
					System.out.println("========\nplease choose one of the available choices.");
				}
			} catch (NumberFormatException e) {
				System.out.println("========\nplease enter a number");
			}
		}
	}

	private void loopOverRoleChoices(String[] starring, String[] extras) {
		boolean choosing = true;
		System.out.println("========");
		while (choosing) {
			int i = 1;
			if (starring.length > 0) {
				System.out.println("Starring:");
				while ((i-1) < starring.length) {
					System.out.println(i + ": " + starring[i-1]);
					i++;
				}
			}
			if (extras.length > 0) {
				System.out.println("Extra:");
				while ((i-1) < (extras.length + starring.length)) {
					System.out.println(i + ": " + extras[(i - starring.length)-1]);
					i++;
				}
			}
			System.out.print("========\nchoice: ");
			String choice = sc.next();
			try {
				int choiceIndex = Integer.parseInt(choice);
				if (1 <= choiceIndex && choiceIndex <= (starring.length + extras.length)) {
					choosing = false;
					if (starring.length > 0) {
						// TODO: The next few lines might contain a bug?
						if (choiceIndex <= starring.length) {
							listener.playerTakeRoleRequest(starring[choiceIndex - 1]);
						} else {
							listener.playerTakeRoleRequest(extras[(choiceIndex - starring.length) - 1]);
						}
					} else {
						// we know that since there was a role available,
						// and there were no starring roles, there must be
						// an extra.
						listener.playerTakeRoleRequest(extras[choiceIndex - 1]);
					}
				} else {
					System.out.println("========\nplease choose one of the available choices.");
				}
			} catch (NumberFormatException e) {
				System.out.println("========\nplease enter a number");
			}
		}
	}

	private void displayChoices() {
		Player curr = model.getCurrentPlayer();
		// System.out.println("=======\nPlayer " + (curr.getID() + 1) + "'s turn...");
		// System.out.println(curr.currentStateToString());
		System.out.println("========\n" + curr.toString());
		System.out.println("========\nchoices:");
		boolean choosing = true;
		if (curr.isActing()) {
			while(choosing) {
				System.out.println("1. act");
				boolean couldRehearse = curr.canRehearse();
				if (couldRehearse) {
					System.out.println("2. rehearse");
				}
				System.out.print("========\nchoice: ");
				String choice = sc.next();
				if (choice.equals("quit")) {
					System.out.println("========\nexiting...");
					System.exit(0);
				} else if (choice.equals("1")){
					choosing = false;
					listener.playerActRequest();
				} else if (couldRehearse && choice.equals("2")) {
					choosing = false;
					listener.playerRehearseRequest();
				} else {
					System.out.print("========\nplease enter only 1");
					if (couldRehearse) {
						System.out.println(" or 2.");
					} else {
						System.out.println();
					}
				}
			}
		} else {
			while(choosing) {
				String msg = "1. Do nothing";
				boolean canMove = false;
				boolean couldUpgrade = false;
				boolean couldWork = false;
				String nextChoice = "2.";
				if (curr.canMove()) {
					canMove = true;
					msg += "\n" + nextChoice + " move";
					nextChoice = "3.";
				}
				if (curr.canUpgrade()) {
					msg += "\n" + nextChoice + " upgrade";
					couldUpgrade = true;
				} else if (curr.hasRolesAvailable()) {
					msg += "\n" + nextChoice + " work";
					couldWork = true;
				}
				System.out.print(msg + "\n========\nchoice: ");
				String choice = sc.next();
				if (choice.equals("quit")) {
					System.out.println("========\nexiting...");
					System.exit(0);
				// below for debugging only
				} else if (choice.equals("cheat")) {
					System.out.println("Cheater!");
					curr.earnDollars(5);
					curr.earnCredits(5);
					System.out.println("========\n" + curr.toString());
				} else if (choice.equals("tele")) {
					String whereTo = sc.next();
					System.out.println("Teleporting to: " + whereTo);
					curr.teleport(model.getRoomForTeleport(whereTo));
					System.out.println("========\n" + curr.toString());
				// above for debugging only
				} else if (choice.equals("1")) {
					choosing = false;
					// listener.playerDoesNothing();
					// update();			
				} else if (choice.equals("2")) {
					choosing = false;
					if (canMove) {
						loopOverChoices("move", curr.getNeighborStrings());
					} else {
						if (couldWork) {
							choosing = false;
							loopOverRoleChoices(curr.getRolesAvailable("starring"), curr.getRolesAvailable("extra"));
						} else if (couldUpgrade) {
							choosing = false;
							// the first parameter is more or less exposing player/game logic in its
							// current form
							loopOverUpgradeChoices(curr.getRank() + 1,
												   curr.getUpgradePricesAvailableWith("dollars"),
												   curr.getUpgradePricesAvailableWith("credits"));
						} else {
							System.out.println("=======\nplease enter only one of the choices presented.");
						}
					}
				} else if (choice.equals("3")) {
					if (!canMove) {
						System.out.println("========\nplease enter only one of the choices presented.");
					} else {
						if (couldWork) {
							choosing = false;
							loopOverRoleChoices(curr.getRolesAvailable("starring"), curr.getRolesAvailable("extra"));
						} else if (couldUpgrade) {
							choosing = false;
							// the first parameter is more or less exposing player/game logic in its
							// current form
							loopOverUpgradeChoices(curr.getRank() + 1,
												   curr.getUpgradePricesAvailableWith("dollars"),
												   curr.getUpgradePricesAvailableWith("credits"));
						} else {
							System.out.println("========\nplease enter only one of the choices presented.");
						}
					}
				} else {
					System.out.println("========\nplease enter only one of the choices presented.");
				}
			}
		}
	}

	// logic triggers

	@Override
	public void newTurn() {
		int currentPlayer = model.getCurrentPlayer().getID();
		System.out.println("{}==== new turn: Player " + (currentPlayer + 1) + " ===={}");
		displayChoices();
	}

	@Override
	public void newDay() {
		System.out.println("new day, players returning to trailers");
	}

	// response triggers 

	// for the console view, the code for both act response
	// and rehearse response are the same, but that may not
	// be so for different views.
	@Override
	public void playerActResponse(String msg) {
		System.out.println("========\n" + msg);
	}

	@Override
	public void playerRehearseResponse(String msg) {
		System.out.println("========\n" + msg);
	}

	// TODO: display points as well
	@Override
	public void displayWinners(String[] winners) {
		if (winners.length == 1) {
			System.out.println(winners[0] + " has won!");
		} else {
			System.out.print(winners[0]);
			for (int i = 1; i < winners.length; i++) {
				System.out.print(", " + winners[i]);
			}
			System.out.println(" have tied!");
		}
	}
	*/

}