package model.board.role;

public class StarringRole extends Role {

	public StarringRole(RoleInfo ri) {
		super(ri);
	}

	public void success() {
		this.residentActor.earnCredits(2);
	}

	public void payBonus(int howMuch) {
		this.residentActor.earnDollars(howMuch);
	}

}