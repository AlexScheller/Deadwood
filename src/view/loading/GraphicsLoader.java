package view.loading;

import java.io.File;

import java.awt.Image;
import javax.swing.ImageIcon;

import view.GraphicalView;
import view.graphics.MenuPanel;
import view.graphics.BoardPanel;

public class GraphicsLoader {
	
	private static final GraphicsLoader instance = new GraphicsLoader();

	private GraphicsLoader() {}

	public GraphicalView loadGraphicalView() {
		JSONDataParser jp = JSONDataParser.getInstance();
		loadAssets(AssetBank.getInstance(), jp);
		GraphicalView gv = new GraphicalView();
		BoardPanel bp = loadBoard(jp, gv);
		MenuPanel mp = new MenuPanel(gv);
		gv.initUI(bp, mp);
		return gv;
	}

	private BoardPanel loadBoard(JSONDataParser jp, GraphicalView gv) {
		return new BoardPanel(jp.parseRoomPanelInfo(), gv);
	}

	// TODO refactor this whole bottom section

	// TODO: implement as a helper class  in loadAssets instead?
	// TODO: make this function way more robust in terms of avoiding
	// unwanted files from the resources directory.
	private void loadImagesFromDirectory(File dir, AssetBank ab) {
		File[] images = dir.listFiles();
		String assetLoc = "../resources/" + dir.getName() + "/";
		for (File f : images) {
			if (f.isFile()) { // avoid "." and ".."
				// NOTE: this will result in a bug
				// if a provided image files has more
				// than one period in it. Currently
				// those files are curated, so this
				// won't happen unless they are
				// messed with.
				String name = f.getName().split("\\.")[0];
				Image img = new ImageIcon(assetLoc + f.getName()).getImage();
				ab.putAsset(name, img);
			}
		}
	}

	private void loadCardImages(AssetBank ab, Map<String, String> associations) {
		String cardLoc = "../resources/cards/";
		File[] images = (new File(cardLoc)).listFiles();
		for (File f : images) {
			if (f.isFile()) {
				String fileName = f.getName()
				String title = associations.get(fileName);
				Image img = new ImageIcon(cardLoc + name).getImage();
				ab.putAsset(title, img);
			}
		}
	}

	private void loadAssets(AssetBank ab, JSONDataParser jp) {
		File diceFolder = new File("../resources/dice");
		// File cardFolder = new File("../resources/cards");
		loadImagesFromDirectory(diceFolder, ab);
		// loadImagesFromDirectory(cardFolder, ab);
		loadCardImages(ab, jp.getCardImageAssociations());
	}

	public static GraphicsLoader getInstance() { return instance; }

}