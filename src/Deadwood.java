import view.ConsoleView;
import view.DeadwoodView;
import view.GraphicalView;

import model.DeadwoodModel;
import model.loading.GameLoader;

import controller.DeadwoodController;


public class Deadwood {

	public static void main(String[] args) {
		// int numPlayers = Integer.parseInt(args[0]);
		GameLoader gl = GameLoader.getInstance();
		// DeadwoodModel dg = gl.loadGame(1);
		DeadwoodModel dg = gl.loadGame(2);
		// System.out.println(dg.toString());
		// DeadwoodView view = new ConsoleView(dg);

		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				GraphicalView gv = new GraphicalView();
				gv.setVisible(true);
			}
		});

		// DeadwoodController controller = new DeadwoodController(view, dg);//, true);
		// dg.play();
	}

}