import java.util.List;

public class Test {

	private String name;
	private int num;
	private int id;
	private List<Integer> ints;

	public Test(TestInfo ti) {
		this.name = ti.name;
		this.num = ti.num;
		this.id = ti.id;
		this.ints = ti.ints;
	}

}
