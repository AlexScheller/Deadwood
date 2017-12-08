package view.loading;

import java.util.Map;
import java.util.HashMap;

import java.awt.Point;
import java.awt.Image;
/*
 * AssetBank.java serves as memory cache for images
 * and positional Data used throughout the game.
 * Certain images in the game are "firm-coded" in
 * their respective files, but anything that may need
 * to be accessed on demand, images of cards, dice, etc.
 * is located here.
 *
 */
public class AssetBank {

	private static final AssetBank instance = new AssetBank();

	private Map<String, Image> nameToImage;
	private Map<Integer, Image> idToImage;
	private Map<Integer, Map<String, Point>> idToRoleOrigins;
	
	private AssetBank() {
		this.nameToImage = new HashMap<>();
		this.idToImage = new HashMap<>();
	}

	public void setIdToRoleOrigins(Map<Integer, Map<String, Point>> itro) {
		this.idToRoleOrigins = itro;
	}

	public void putAsset(String name, Image asset){
		nameToImage.put(name, asset);
	}

	public void putAsset(int id, Image asset) {
		idToImage.put(id, asset);
	}

	public Image getAsset(String assetName) throws IllegalArgumentException {
		Image ret = null;
		if (nameToImage.containsKey(assetName)) {
			ret = nameToImage.get(assetName);
		} else {
			throw new IllegalArgumentException("No such image from name: " + assetName);
		}
		return ret;
	}

	public Image getAsset(int assetId) throws IllegalArgumentException {
		Image ret = null;
		if (idToImage.containsKey(assetId)) {
			ret = idToImage.get(assetId);
		} else {
			throw new IllegalArgumentException("No such image from id: " + assetId);
		}
		return ret;
	}

	// TODO: see if there is a better way of doing this
	public Map<String, Point> getRoleOrigins(int cardId) {
		Map<String, Point> ret = null;
		if (idToRoleOrigins.containsKey(cardId)) {
			ret = idToRoleOrigins.get(cardId);
		} else {
			throw new IllegalArgumentException("No such image from id: " + cardId);
		}
		return ret;
	}

	public static AssetBank getInstance() { return instance; }

}