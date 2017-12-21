package view.graphics;

public final class Dimensions {

	private Dimensions () {};

	// regular

	public static final int BOARD_HEIGHT = 900;
	public static final int BOARD_WIDTH = 1200;

	public static final int MENU_HEIGHT = BOARD_HEIGHT;
	public static final int MENU_WIDTH = 200;

	public static final int GAME_HEIGHT = BOARD_HEIGHT;
	public static final int GAME_WIDTH = BOARD_WIDTH + MENU_WIDTH;

	// compact dimensions

	public static final int COMPACT_BOARD_HEIGHT = 720;
	public static final int COMPACT_BOARD_WIDTH = 1080;

	public static final int COMPACT_MENU_HEIGHT = COMPACT_BOARD_HEIGHT;
	public static final int COMPACT_MENU_WIDTH = MENU_WIDTH;

	public static final int COMPACT_GAME_HEIGHT = COMPACT_BOARD_HEIGHT;
	public static final int COMPACT_GAME_WIDTH = COMPACT_BOARD_WIDTH + COMPACT_MENU_WIDTH;

	// common dimensions

	public static final int DICE_HEIGHT = 40;
	public static final int DICE_WIDTH = 40;

	public static final int CARD_HEIGHT = 115;
	public static final int CARD_WIDTH = 205;


}