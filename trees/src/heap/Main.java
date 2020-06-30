package heap;

public class Main {

	public static void main(String[] arg) {
		System.out.println("The Min Heap is ");
		MinHeap minHeap = new MinHeap(15);
		minHeap.insert(5);
		minHeap.insert(3);
		minHeap.insert(17);
		minHeap.insert(10);
		minHeap.insert(84);
		minHeap.insert(19);
		minHeap.insert(6);
		minHeap.insert(22);
		minHeap.insert(9);
		minHeap.minHeap();

		minHeap.print();
		System.out.println("The Min val is " + minHeap.remove());
		System.out.println("*****************");

		System.out.println("The Max Heap is ");
		MaxHeap maxHeap = new MaxHeap(15);
		maxHeap.insert(5);
		maxHeap.insert(3);
		maxHeap.insert(17);
		maxHeap.insert(10);
		maxHeap.insert(84);
		maxHeap.insert(19);
		maxHeap.insert(6);
		maxHeap.insert(22);
		maxHeap.insert(9);

		maxHeap.print();
		System.out.println("The max val is " + maxHeap.extractMax());
	}
}
