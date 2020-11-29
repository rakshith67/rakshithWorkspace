package strings;

public class Frequency implements Comparable<Frequency> {
	public char ch;
	public int count;

	public Frequency(char ch, int count) {
		this.ch = ch;
		this.count = count;
	}

	public int compareTo(Frequency freq) {
		if (this.count == freq.count) {
			return ch - freq.ch;
		}
		return freq.count - this.count;
	}
}
