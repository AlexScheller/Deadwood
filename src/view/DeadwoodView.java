package view;

import model.ModelListener;

/*
 * Design Notes:
 *
 * The current design requires that the view presents only possible
 * options/allows possible inputs to be presented to the controller.
 */

public interface DeadwoodView extends ModelListener {

	public void setListener(ViewListener vl);

}