package view.loading;

import java.io.File;

import java.util.Map;

import java.awt.Image;
import javax.swing.ImageIcon;

import view.GraphicalView;
import view.graphics.menu.MenuPanel;
import view.graphics.board.BoardPanel;

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

	// TODO: I know you just coalesced two functions into one for
	// this one, but maybe they should stay separate.
	private void loadImagesFromDirectory(AssetBank ab, File dir, boolean loadingDice) {
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
				if (!name.equals("cardback")) {
					Image img = new ImageIcon(assetLoc + f.getName()).getImage();
					if (loadingDice) {
						ab.putAsset(name, img);
					} else { // loading cards
						int cardId = Integer.parseInt(name);
						ab.putAsset(cardId, img);
					}
				}
			}
		}
		if (!loadingDice) {
			Image cardback = new ImageIcon(assetLoc + "cardback.png").getImage();
			ab.putAsset("cardback", cardback);
		}
	}

	private void loadAssetBank(AssetBank ab, JSONDataParser jp) {
		File diceFolder = new File("../resources/dice");
		File cardFolder = new File("../resources/cards");
		// loadDiceImages(ab, diceFolder);
		// // loadImagesFromDirectory(cardFolder, ab);
		// loadCardImages(ab, cardFolder);
		loadImagesFromDirectory(ab, diceFolder, true);
		loadImagesFromDirectory(ab, cardFolder, false);
		ab.setIdToRoleOrigins(jp.parseCardIdToRoleOrigins());
	}

	public static GraphicsLoader getInstance() { return instance; }

}