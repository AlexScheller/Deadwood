package model.board;

public class StarringRole extends Role {

	public StarringRole(RoleInfo ri) {
		super(ri);
	}

	public void success() {
		if (this.residentActor != null) {
			this.residentActor.earnCredits(2);
		}
	}

	public void failure() {}

}