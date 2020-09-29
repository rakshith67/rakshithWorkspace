package sorting;

public class HeapByArray {
	int[] array;
	int sizeOfTree;

	public HeapByArray(int size) {
		array = new int[size + 1];
		this.sizeOfTree = 0;
	}

	public void insertInHeap(int value) {
		array[sizeOfTree + 1] = value;
		sizeOfTree++;
		heapifyBottomToTop(sizeOfTree);
	}

	public void heapifyBottomToTop(int index) {
		int parent = index / 2;
		if (index <= 1) {
			return;
		}
		if (array[index] > array[parent]) {
			int temp = array[index];
			array[index] = array[parent];
			array[parent] = temp;
		}
		heapifyBottomToTop(parent);
	}

	public int extractHeadOfHeap() {
		if (sizeOfTree == 0) {
			return -1;
		} else {
			int extractedValue = array[1];
			array[1] = array[sizeOfTree];
			sizeOfTree--;
			heapifyTopToBottom(1);
			return extractedValue;
		}
	}

	public void heapifyTopToBottom(int index) {
		int left = index * 2;
		int right = (index * 2) + 1;
		int smallestChild = 0;

		if (sizeOfTree < left) { // If there is no child of this node, then nothing to do. Just return.
			return;
		} else if (sizeOfTree == left) { // If there is only left child of this node, then do a comparison and return.
			if (array[index] > array[left]) {
				int tmp = array[index];
				array[index] = array[left];
				array[left] = tmp;
			}
			return;
		} else { // If both children are there
			if (array[left] < array[right]) {
				smallestChild = left;
			} else {
				smallestChild = right;
			}
			if (array[index] > array[smallestChild]) {
				int tmp = array[index];
				array[index] = array[smallestChild];
				array[smallestChild] = tmp;
			}
		}
		heapifyTopToBottom(smallestChild);
	}

}
