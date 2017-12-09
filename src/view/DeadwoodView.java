package view;

import model.ModelListener;

/*
 * Design Notes:
 *
 * The current design requires that the view presents only possible
 * options/allows possible inputs to be presented to the controller.
 */

public interface DeadwoodView extends ModelListener {

	// public void update();

	// public void newTurn();
	// public void newDay();

	public void setListener(ViewListener vl);

}