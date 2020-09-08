package greedy;

public class Activity implements Comparable<Activity> {
	public int start;
	public int finish;
	public int number;

	public Activity(int start, int finish, int number) {
		this.start = start;
		this.finish = finish;
		this.number = number;
	}

	public Activity(int start, int finish) {
		this(start, finish, 0);
	}

	public int compareTo(Activity act) {
		return finish - act.finish;
	}
}
