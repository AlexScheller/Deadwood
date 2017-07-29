package model.board;

public class Board {

	// private int[][] roomGraph;

	private List<MovieSet> sets;
	private SceneCardSet cards;

	private void dealRooms() {
		for (MovieSet ms : sets) {
			ms.setSceneCard(cards.deal());
		}
	}

	public void newDay() {
		dealRooms();
	}

	public boolean oneSceneLeft() {
		int scenesLeft = 0;
		for (MovieSet ms : sets) {
			if(!ms.wrapped()) {
				scenesLeft++;
			}
		}
		return (scenesLeft == 1);
	}

}