// import view.ConsoleView;
import view.DeadwoodView;
import view.GraphicalView;
// import view.loading.GraphicsLoader;

// import model.DeadwoodModel;
// import model.loading.GameLoader;

import controller.DeadwoodController;


public class Deadwood {

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				DeadwoodController dc = new DeadwoodController();
				GraphicalView gv = new GraphicalView(dc);
				gv.begin();
			}
		});
	}

}