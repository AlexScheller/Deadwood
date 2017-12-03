package view.graphics;

import java.awt.Point;
import java.awt.Graphics;
import javax.swing.JPanel;

public abstract class RoomPanel extends JPanel {

	protected Point occupantsOrigin;

	protected abstract void paintOccupants(Graphics g);

}