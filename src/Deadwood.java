import view.ConsoleView;
import view.DeadwoodView;
import view.GraphicalView;
import view.loading.GraphicsLoader;

import model.DeadwoodModel;
import model.loading.GameLoader;

import controller.DeadwoodController;


public class Deadwood {

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				GraphicsLoader gl = GraphicsLoader.getInstance();
				GraphicalView gv = gl.loadGraphicalView();
				DeadwoodController dc = new DeadwoodController(gv);
				gv.setVisible(true);
			}
		});
	}

}