package heap;

public class MaxHeap {

	private int[] heap;
	private int size;
	private int maxsize;

	/**
	 * Constructor to initialize an empty max heap with given maximum capacity.
	 */
	public MaxHeap(int maxsize) {
		this.maxsize = maxsize;
		this.size = 0;
		heap = new int[this.maxsize + 1];
		heap[0] = Integer.MAX_VALUE;
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

	/**
	 * A recursive function to max heapify the given subtree. This function assumes
	 * that the left and right subtrees are already heapified, we only need to fix
	 * the root.
	 */
	private void maxHeapify(int position) {
		if (isLeaf(position))
			return;

		if (heap[position] < heap[leftChild(position)] || heap[position] < heap[rightChild(position)]) {

			if (heap[leftChild(position)] > heap[rightChild(position)]) {
				swap(position, leftChild(position));
				maxHeapify(leftChild(position));
			} else {
				swap(position, rightChild(position));
				maxHeapify(rightChild(position));
			}
		}
	}

	/** Function to insert a node into the heap */
	public void insert(int element) {
		heap[++size] = element;

		// Traverse up and fix violated property
		int current = size;
		while (heap[current] > heap[parent(current)]) {
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

	/** Remove an element from max heap */
	public int extractMax() {
		int popped = heap[1];
		heap[1] = heap[size--];
		maxHeapify(1);
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
