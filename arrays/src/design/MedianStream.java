package design;

import java.util.PriorityQueue;

public class MedianStream {

	public static void main(String[] args) {
		MedianFinder finder = new MedianFinder();
		finder.addNum(1);
		finder.addNum(2);
		finder.addNum(3);
		System.out.println(finder.findMedian());
		finder.addNum(4);
		finder.addNum(6);
		finder.addNum(7);
		System.out.println(finder.findMedian());
		finder.addNum(8);
		finder.addNum(10);
		finder.addNum(5);
		System.out.println(finder.findMedian());
	}
}

class MedianFinder {

	private PriorityQueue<Integer> large;
	private PriorityQueue<Integer> small;

	/** initialize your data structure here. */
	public MedianFinder() {
		small = new PriorityQueue<>((a, b) -> {
			return b - a;
		});
		large = new PriorityQueue<>();
	}

	public void addNum(int num) {
		large.add(num);
		small.add(large.poll());
		if (large.size() < small.size())
			large.add(small.poll());
	}

	public double findMedian() {
		return large.size() > small.size() ? large.peek() : (small.peek() + large.peek()) / 2.0;
	}
}
