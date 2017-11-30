package view.loading;

import view.GraphicalView;
import view.graphics.BoardPanel;

public class GraphicsLoader {
	
	private static final GraphicsLoader instance = new GraphicsLoader();

	private GraphicsLoader() {}

	public GraphicalView loadGraphicalView() {
		JSONDataParser jp = JSONDataParser.getInstance();
		BoardPanel bp = loadBoard(jp);
		return new GraphicalView(bp);
	}

	private BoardPanel loadBoard(JSONDataParser jp) {
		return new BoardPanel(jp.parseRoomPanelInfo());
	}

	public static GraphicsLoader getInstance() { return instance; }

}