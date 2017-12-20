package view;

import view.loading.GraphicsLoader;

import controller.DeadwoodController;

public class GraphicalView {

	private StartMenuFrame smf;
	private GameFrame gf;
	private DeadwoodController dc;

	public GraphicalView(DeadwoodController dc) {
		this.dc = dc;
		this.smf = new StartMenuFrame(this);
	};

	public void begin() {
		smf.setVisible(true);
	}

	public void newGameButtonClickEvent() {
		smf.dispose();
		GraphicsLoader gl = GraphicsLoader.getInstance();
		gf = gl.loadGameFrame();
		dc.setView(gf); // also initialized the model
		int numPlayers = 7;// TODO: get this from the start menu.
		gf.begin(numPlayers);
		gf.setVisible(true);
	}

}