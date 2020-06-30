package heap;

public class MinHeap {

	private int[] heap;
	private int size;
	private int maxsize;

	private static final int FRONT = 1;

	/**
	 * Constructor to initialize an empty min heap with given maximum capacity.
	 */
	public MinHeap(int maxsize) {
		this.maxsize = maxsize;
		this.size = 0;
		heap = new int[this.maxsize + 1];
		heap[0] = Integer.MIN_VALUE;
	}

	/**
	 * Function to return the position of the parent for the node currently at
	 * position
	 */
	private int parent(int position) {
		return position / 2;
	}

	/**
	 * Function to return the position of the left child for the node currently at
	 * position
	 */
	private int leftChild(int position) {
		return (2 * position);
	}

	/**
	 * Function to return the position of the right child for the node currently at
	 * position
	 */
	private int rightChild(int position) {
		return (2 * position) + 1;
	}

	/**
	 * Function that returns true if the passed node is a leaf node
	 */
	private boolean isLeaf(int position) {
		if (position >= (size / 2) && position <= size) {
			return true;
		}
		return false;
	}

	/** Function to swap two nodes of the heap */
	private void swap(int fposition, int sposition) {
		int tmp;
		tmp = heap[fposition];
		heap[fposition] = heap[sposition];
		heap[sposition] = tmp;
	}

	/** Function to heapify the node at position */
	private void minHeapify(int position) {

		// If the node is a non-leaf node and greater than any of its child
		if (!isLeaf(position)) {
			if (heap[position] > heap[leftChild(position)] || heap[position] > heap[rightChild(position)]) {

				// Swap with the left child and heapify the left child
				if (heap[leftChild(position)] < heap[rightChild(position)]) {
					swap(position, leftChild(position));
					minHeapify(leftChild(position));
				}

				// Swap with the right child and heapify the right child
				else {
					swap(position, rightChild(position));
					minHeapify(rightChild(position));
				}
			}
		}
	}

	/** Function to insert a node into the heap */
	public void insert(int element) {
		if (size >= maxsize) {
			return;
		}
		heap[++size] = element;
		int current = size;

		while (heap[current] < heap[parent(current)]) {
			swap(current, parent(current));
			current = parent(current);
		}
	}

	/** Function to print the contents of the heap */
	public void print() {
		for (int i = 1; i <= size / 2; i++) {
			System.out.print(
					" PARENT : " + heap[i] + " LEFT CHILD : " + heap[2 * i] + " RIGHT CHILD :" + heap[2 * i + 1]);
			System.out.println();
		}
	}

	/**
	 * Function to build the minimum heap using the minHeapify
	 */
	public void minHeap() {
		for (int position = (size / 2); position >= 1; position--) {
			minHeapify(position);
		}
	}

	/**
	 * Function to remove and return the minimum element from the heap
	 */
	public int remove() {
		int popped = heap[FRONT];
		heap[FRONT] = heap[size--];
		minHeapify(FRONT);
		return popped;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Heap: ");
		builder.append(values(heap));
		builder.append("\nSize: ");
		builder.append(size);
		builder.append("\tMaxSize: ");
		builder.append(maxsize);
		return builder.toString();
	}

	private Object values(int[] heap2) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < maxsize; i++) {
			builder.append(heap[i]);
			builder.append(" ");
		}
		return builder.toString();
	}
}
