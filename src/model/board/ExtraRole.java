package model.board;

public class ExtraRole extends Role {

	public ExtraRole(RoleInfo ri) {
		super(ri);
	}

	public void success() {
		if (this.residentActor != null) {
			this.residentActor.earnDollars(1);
			this.residentActor.earnCredits(1);
		}
	}

	public void failure() {
		if (this.residentActor != null) {
			this.residentActor.earnDollars(1);
		}
	}

}