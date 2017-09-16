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

	private void loopOverChoices(String which, String[] loopOver) {
		boolean choosing = true;
		System.out.println("========");
		while (choosing) {
			for (int i = 1; i <= loopOver.length; i++) {
				System.out.println(i + ": " + loopOver[i-1]);
			}
			System.out.print("========\nchoice: ");
			String choice = sc.next();
			try {
				int choiceIndex = Integer.parseInt(choice);
				if (1 <= choiceIndex && choiceIndex <= loopOver.length) {
					choosing = false;
					if (which.equals("move")) {
						listener.playerMoveRequest(loopOver[choiceIndex - 1]);
					} else if (which.equals("work")) {
						listener.playerTakeRoleRequest(loopOver[choiceIndex - 1]);
					} else if (which.equals("upgrade")) {
						listener.playerUpgradeRequest(Integer.parseInt(loopOver[choiceIndex - 1]));
					}
					// update();
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
		System.out.println(curr.toString());
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
							loopOverChoices("work", curr.getRolesAvailable());
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
							loopOverChoices("work", curr.getRolesAvailable());
						} else if (couldUpgrade) {
							choosing = false;
							//loopOverChoices("upgrade", curr.getPossibleUpgrades());
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

	public void newTurn() {
		int currentPlayer = model.getCurrentPlayer().getID();
		System.out.println("{}==== new turn: Player " + (currentPlayer + 1) + " ===={}");
		displayChoices();
	}

	public void newDay() {
		System.out.println("pass");
	}

	public void setListener(ViewListener vl) {
		this.listener = vl;
	}

}