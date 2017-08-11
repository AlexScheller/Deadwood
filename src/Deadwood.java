import model.loading.*;
import model.board.*;
import model.DeadwoodGame;

public class Deadwood {

	public static void main(String[] args) {
		// int numPlayers = Integer.parseInt(args[0]);
		GameLoader gl = GameLoader.getInstance();
		DeadwoodGame dg = gl.loadGame(1);
		System.out.println(dg.toString());
		// DeadwoodView view = new DeadwoodView(dg);
		// DeadwoodController controller = new DeadwoodController(view, dg);
		// dg.play();
	}

}