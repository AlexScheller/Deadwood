package view.loading;

import java.util.Map;
import java.util.HashMap;

import java.awt.Image;
/*
 * AssetBank.java serves as memory cache for images
 * used throughout the game. Certain images in the
 * game are "firm-coded" in their respective files,
 * but anything that may need to be accessed on demand,
 * images of cards, dice, etc. is located here.
 *
 */
public class AssetBank {

	private static final AssetBank instance = new AssetBank();

	private Map<String, Image> assets;

	private AssetBank() {
		this.assets = new HashMap<>();
	}

	public void putAsset(String name, Image asset){
		assets.put(name, asset);
	}

	public Image getAsset(String which) throws IllegalArgumentException {
		Image ret = null;
		if (assets.containsKey(which)) {
			ret = assets.get(which);
		} else {
			throw new IllegalArgumentException("No such image: " + which);
		}
		return ret;
	}

	public static AssetBank getInstance() { return instance; }

}