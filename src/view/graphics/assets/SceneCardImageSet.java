package view.graphics.assets;

public class SceneCardImageSet {

	private Map<String, SceneCardImage> cardImages;

	public SceneCardImageSet(List<SceneCardImageInfo> sciis) {
		this.cardImages = new HashMap<>();
		for (SceneCardImageInfo scii : sciis) {
			cardImages.put(scii.title, new SceneCardImage(scii));
		}
	}

	public CardImage get(String which) throws IllegalArgumentException {
		if (cardImages.containsKey(which)) {
			return cardImages.get(which);
		} else {
			throw new IllegalArgumentException("Specified card: " + which + " not present in card image set.");
		}
	}

}