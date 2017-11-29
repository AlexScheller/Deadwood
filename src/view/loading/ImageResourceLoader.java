package view.loading;

public class ImageResourceLoader {
	
	private static final ImageResourceLoader instance = new ImageResourceLoader();

	private ImageResourceLoader() {}

	public SceneCardImageSet loadSceneCardImages(List<SceneCardImageInfo> sciis) {
		return new SceneCardImageSet(sciis);
	}

	public ImageResourceLoader getInstance() { return this.instance; }

}