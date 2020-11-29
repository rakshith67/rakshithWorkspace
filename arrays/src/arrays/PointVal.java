package arrays;

public class PointVal implements Comparable<PointVal> {

	int x, y, val;

	public PointVal (int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }

	@Override
	public int compareTo(PointVal that) {
		return this.val - that.val;
	}
}
