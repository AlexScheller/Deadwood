package view;

import model.*;
import model.players.*;
import java.util.Scanner;

public class ConsoleView implements DeadwoodView, ModelListener {
	
	private ViewListener listener;

	private DeadwoodGame model;

	private Scanner sc;

	public ConsoleView(DeadwoodGame model) {
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

	// TODO: find a way to signify whether a role is starring or extra
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
					} else if (which.equals("upgrade")) {
						listener.playerUpgradeRequest(Integer.parseInt(choices[choiceIndex - 1]));
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
				System.out.println("1. act\n2. rehearse");
				System.out.print("========\nchoice: ");
				String choice = sc.next();
				if (choice.equals("quit")) {
					System.out.println("========\nexiting...");
					System.exit(0);
				} else if (choice.equals("1")){
					choosing = false;
					listener.playerActRequest();
					// update();
				} else if (choice.equals("2")) {
					choosing = false;
					listener.playerRehearseRequest();
					// update();
				} else {
					System.out.println("========\nplease enter only 1 or 2.");
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
							// loopOverChoices("upgrade", curr.getPossibleUpgrades());
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
							// loopOverChoices("upgrade", curr.getPossibleUpgrades());
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

	// public void update() {
	// 	displayGameState();
	// 	displayChoices();
	// }

	/* logic triggers */
	public void newTurn() {
		int currentPlayer = model.getCurrentPlayer().getID();
		System.out.println("{}==== new turn: Player " + (currentPlayer + 1) + " ===={}");
		displayChoices();
	}

	public void newDay() {
		System.out.println("pass");
	}

	/* response triggers */

	// for the console view, the code for both act response
	// and rehearse response are the same, but that may not
	// be so for different views.
	public void playerActResponse(String msg) {
		System.out.println("========\n" + msg);
	}

	public void playerRehearseResponse(String msg) {
		System.out.println("========\n" + msg);
	}

	// TODO: display points as well
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

	public void setListener(ViewListener vl) {
		this.listener = vl;
	}

}