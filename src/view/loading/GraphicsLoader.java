package view.loading;

import java.io.File;

import java.util.Map;

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
		loadAssetBank(AssetBank.getInstance(), jp);
		GraphicalView gv = new GraphicalView();
		BoardPanel bp = loadBoard(jp, gv);
		MenuPanel mp = new MenuPanel(gv);
		gv.initUI(bp, mp);
		return gv;
	}

	private BoardPanel loadBoard(JSONDataParser jp, GraphicalView gv) {
		return new BoardPanel(jp.parseRoomPanelInfo(), gv);
	}

	// TODO: refactor/rewrite this whole bottom section

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

	private void loadCardImages(AssetBank ab, Map<String, Integer> filenamesToIds) {
		String cardLoc = "../resources/cards/";
		File[] images = (new File(cardLoc)).listFiles();
		for (File f : images) {
			if (f.isFile()) {
				String fileName = f.getName();
				if (!fileName.equals("cardback.png")) {
					// System.out.println("getting on file name: " + fileName);
					int cardId = filenamesToIds.get(fileName);
					Image img = new ImageIcon(cardLoc + fileName).getImage();
					ab.putAsset(cardId, img);
				}
			}
		}
		Image cardback = new ImageIcon(cardLoc + "cardback.png").getImage();
		ab.putAsset("cardback", cardback);
	}

	private void loadAssetBank(AssetBank ab, JSONDataParser jp) {
		File diceFolder = new File("../resources/dice");
		// File cardFolder = new File("../resources/cards");
		loadImagesFromDirectory(diceFolder, ab);
		// loadImagesFromDirectory(cardFolder, ab);
		loadCardImages(ab, jp.getCardFilenamesToIds());
		ab.setIdToRoleOrigins(jp.parseCardIdToRoleOrigins());
	}

	public static GraphicsLoader getInstance() { return instance; }

}