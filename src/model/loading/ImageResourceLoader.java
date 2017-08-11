package loading;

public class ImageResourceLoader {
	
	private static final ImageResourceLoader instance = new ImageResourceLoader();

	private ImageResourceLoader() {}

	public ImageResourceLoader getInstance() { return this.instance; }

}