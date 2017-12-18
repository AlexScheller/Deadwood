package view.graphics.board;

import java.awt.Point;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.JComponent;

import view.loading.AssetBank;

public abstract class RoomComponent extends JComponent {

	// protected Point occupantsOrigin;
	protected PlayerBenchComponent pbc;
	protected String name;
	
	public void evictPlayer(int playerId) {
		pbc.removePlayerComponent(playerId);
	}

	public void acceptPlayer(PlayerComponent npc) {
		pbc.takePlayerComponent(npc);
	}

	// public void setBounds() {
	// 	// these bounds may need adjusting
	// 	setBounds(0, 0, 1200, 900);
	// }

}