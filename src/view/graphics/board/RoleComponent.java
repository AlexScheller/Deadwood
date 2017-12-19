package view.graphics.board;

import java.awt.Image;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JComponent;
// import javax.swing.JPanel;

import view.events.ChildEventListener;
import static view.graphics.Dimensions.*;

public class RoleComponent extends JComponent {
// public class RoleComponent extends JPanel {

	private String name;
	private Image die;
	private Point origin;

	private PlayerComponent pc;
	// private final int height = 40;
	// private final int width = 40;

	// TODO: instantiate this with a role info object
	// and move setBounds to private, to be called in
	// the constructor.
	public RoleComponent(String name, Point origin,
						 ChildEventListener cel) {
		setLayout(null);
		this.name = name;
		this.origin = origin;
		// System.out.println("setting new role component");
		// System.out.println("origin: {" + origin.x + ", " + origin.y + "}");
		setBounds(origin.x, origin.y, DICE_HEIGHT, DICE_WIDTH);

		addMouseListener(new MouseAdapter () {
			public void mouseClicked(MouseEvent e) {
				if (!occupied()) {
					cel.roleClickEvent(RoleComponent.this);
				}
			}
		});
	}

	public String getName() {
		return name;
	}

	public void setDieImage(Image die) {
		this.die = die;
	}

	public void takePlayerComponent(PlayerComponent npc) {
		this.pc = npc;
		pc.move(new Point(0, 0));
		add(pc);
		setOpaque(false);
		repaint();
	}

	public PlayerComponent evictPlayerComponent() {
		PlayerComponent ret = pc;
		pc.leaveRole();
		remove(pc);
		this.pc = null;
		setOpaque(true);
		repaint();
		return ret;
	}

	// public void setBounds(Point origin) {
	// 	this.origin = origin;
	// 	setBounds(origin.x, origin.y, height, width);
	// }

	public boolean occupied() {
		return (pc != null);
	}

	@Override
	public void paintComponent(Graphics g) {
		// g.drawImage(die, origin.x, origin.y, null);
		super.paintComponent(g);
		if (occupied()) {
			// pc.paintComponent(g);
		}
	}

}