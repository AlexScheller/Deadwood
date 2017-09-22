import model.loading.*;
import model.board.*;
import model.DeadwoodGame;
import view.*;
import controller.*;

public class Deadwood {

	public static void main(String[] args) {
		// int numPlayers = Integer.parseInt(args[0]);
		GameLoader gl = GameLoader.getInstance();
		DeadwoodGame dg = gl.loadGame(1);
		// System.out.println(dg.toString());
		DeadwoodView view = new ConsoleView(dg);
		// the true boolean in the instantiation tells the controller to log
		DeadwoodController controller = new DeadwoodController(view, dg);//, true);
		dg.play();
	}

}