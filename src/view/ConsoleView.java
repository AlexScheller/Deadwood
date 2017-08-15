public class ConsoleView implements DeadWoodView, ModelListener {
	
	private ViewListener listener;

	private DeadWoodGame model;

	private Scanner sc;

	public ConsoleView(DeadWoodGame model) {
		this.model = model;
		model.setListener(this);
		this.sc = new Scanner(System.in);
	}

	private void displayGameState() {
		System.out.println(model.currentStateToString());
	}

	private void loopOverChoices(String which, String[] loopOver) {
		boolean choosing = true;
		while (choosing) {
			for (int i = 1; i <= loopOver.length; i++) {
				System.out.println(i + ": " + loopOver[i-1]);
			}
			String choice = sc.next();
			try {
				int choiceIndex = Integer.parseInt(choice);
				if (1 <= choiceIndex <= loopOver.length) {
					choosing = false;
					if (which.equals("move")) {
						listener.playerMovesTo(loopOver[choiceIndex - 1]);
					} else if (which.equals("work")) {
						listener.playerTakesRole(loopOver[choiceIndex - 1]);
					} else if (which.equals("upgrade") {
						listener.playerUpgrades(loopOver[choiceIndex - 1]);
					}
				} else {
					System.out.println("please choose one of the available choices.");
				}
			} catch (NumberFormatException e) {
				System.out.println("please enter a number");
			}
		}
	}

	private void displayChoices() {
		Player curr = model.getCurrentPlayer();
		System.out.println("Player " + curr.getID() + "'s turn...");
		System.out.println(curr.currentStateToString());
		System.out.println("choices:")
		boolean choosing = true;
		if (model.getCurrentPlayer().isActing()) {
			System.out.println("1. act\n2. rehearse");
			System.out.print("choice: ")
			while(choosing) {
				String choice = sc.next();
				if (choice.equals("1")){
					choosing = false;
					listener.playerActs());
				} else if (choice.equals("2")) {
					choosing = false;
					listener.playerRehearses());
				} else {
					System.out.println("please enter only 1 or 2.");
				}
			}
		} else {
			String msg = "1. Do nothing\n2. move\n";
			boolean couldUpgrade = false;
			boolean couldWork = false;
			if (curr.canUpgrade()) {
				msg += "3. upgrade";
				couldUpgrade = true;
			} else if (curr.hasRolesAvailable()) {
				msg += "3. work";
				couldWork = true;
			}
			System.out.print(msg + "\nchoice: ");
			while(choosing) {
				String choice = sc.next();
				if (choice.equals("1")) {
					choosing = false;
					listener.playerDoesNothing();					
				} else if (choice.equals("2")) {
					choosing = false;
					loopOverChoices("move", curr.getNeighborStrings());
				} else if (choice.equals("3") {
					if (couldWork) {
						choosing = false;
						loopOverChoices("work", curr.getRolesAvailable());
					} else if (couldUpgrade) {
						choosing = false;
						loopOverChoices("upgrade", curr.getPossibleUpgrades());
					} else {
						System.out.println("please enter only one of the choices presented.");
					}
				}
			}
		}
	}

	public void update() {
		// displayGameState();
		displayChoices();
	}

	public void setListener(ViewListener vl) {
		this.listener = vl;
	}

}