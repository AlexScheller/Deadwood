package view.graphics.assets;

import java.util.Map;
import java.util.HashMap;

public class SceneCardImage extends ImageIcon {

	// Stores the x and y coordinates relative to the origin
	// of the card.
	private Map<String, Integer> roleOffsets;

	public SceneCardImage(SceneCardImageInfo scii) {
		super(scii.filename, scii.title);
		this.roleOffsets = scii.roleOffsets;
	}

	public String getTitle() {
		return getDescription();
	}

}