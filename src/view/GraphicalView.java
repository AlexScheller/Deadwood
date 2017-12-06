package view;

import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

import view.graphics.CardPanel;
import view.graphics.MenuPanel;
import view.graphics.BoardPanel;
import view.events.InputEventListener;
import static view.graphics.Dimensions.*;

import model.ModelListener;

public class GraphicalView
	extends JFrame
	implements DeadwoodView, ModelListener, InputEventListener {
	// TODO: apply this formatting across code base?

	private ViewListener listener;

	public GraphicalView() {};

	public void initUI(BoardPanel bp, MenuPanel mp) {
		setLayout(null); // absolute positioning is used
		add(bp);
		add(mp);
		setSize(BOARD_LENGTH + MENU_LENGTH, BOARD_HEIGHT);
		setTitle("Deadwood!");
		setLocationRelativeTo(null); // center window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// InputEventListener methods

	public void newGameEvent() {
		System.out.println("new game event intercepted");
	}

	public void roleClickEvent(String which) {
		System.out.println("role click event intercepted: " + which);
	}

	public void cardClickEvent(String which) {
		System.out.println("card click event intercepted: " + which);
	}

	// DeadwoodView methods

	@Override
	public void setListener(ViewListener vl) {
		this.listener = vl;
	}

	// ModelListener methods

	@Override
	public void newDay() {
		System.out.println("newDay temp");
	}

	@Override
	public void newTurn() {
		System.out.println("newTurn temp");
	}

	@Override
	public void playerActResponse(String response) {
		System.out.println("playerActResponse temp");
	}

	@Override
	public void playerRehearseResponse(String response) {
		System.out.println("playerRehearseResponse temp");
	}

	@Override
	public void displayWinners(String[] winners) {
		System.out.println("displayWinners temp");
	}

}