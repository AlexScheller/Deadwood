import model.*;

public class Deadwood {

	public static void main(String[] args) {
		// int numPlayers = Integer.parseInt(args[0]);
		DeadwoodGame dg = new GameLoader.loadGame(1);
		// DeadwoodView view = new DeadwoodView(dg);
		// DeadwoodController controller = new DeadwoodController(view, dg);
		dg.play();
	}

}