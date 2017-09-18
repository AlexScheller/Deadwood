package model.board;

public class ExtraRole extends Role {

	public ExtraRole(RoleInfo ri) {
		super(ri);
	}

	public void success() {
		this.residentActor.earnDollars(1);
		this.residentActor.earnCredits(1);
	}

	public void failure() {
		this.residentActor.earnDollars(1);
	}

	public void payBonus() {
		this.residentActor.earnDollars(this.rankRequired);
	}

}