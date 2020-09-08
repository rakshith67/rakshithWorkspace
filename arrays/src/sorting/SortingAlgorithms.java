package sorting;

public class SortingAlgorithms {

	/**
	 * Bubble sort: Compares the adjacent numbers and sorts it if the current is
	 * greater than the adjacent. It is actually putting the max element at the end
	 * position of that iterative subarray. It is very effective when the elements
	 * are already sorted. O(n2) O(1).
	 */
	public void bubbleSort(int[] array) {
		int length = array.length;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length - 1 - i; j++) {
				if (array[j] > array[j + 1]) {
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * Selection sort: Divides the array into sorted and unsorted array and for each
	 * iteration finds the minimum element in unsorted array and puts it at the end
	 * of the sorted array. O(n2) O(1).
	 */
	public void selectionSort(int[] array) {
		int length = array.length;
		for (int i = 0; i < length; i++) {
			int min = i;
			for (int j = i + 1; j < length; j++) {
				if (array[min] > array[j]) {
					min = j;
				}
			}
			if (min != i) {
				int temp = array[min];
				array[min] = array[i];
				array[i] = temp;
			}
		}
	}

	/**
	 * Insertion sort: Divides the array into sorted and unsorted array and inserts
	 * the current element in the sorted postion in the sorted array. It is done by
	 * swapping the j and j-1th position till the current number less than the jth
	 * position and when we get the actual position put the currentNumber there.
	 * O(n2) O(1).
	 */
	public void insertionSort(int[] array) {
		int length = array.length;
		for (int i = 1; i < length; i++) {
			int j = i;
			int currentNumber = array[i];
			while (j > 0 && array[j - 1] > currentNumber) {
				array[j] = array[j - 1];
				j--;
			}
			array[j] = currentNumber;
		}
	}

	/**
	 * Merge Sort: divides the array into two and then merges them in the ascending
	 * order. O(nlogn) O(n)
	 */
	public void mergeSort(int[] array, int start, int end) {
		if (start < end) {
			int middle = (start + end) / 2;
			mergeSort(array, start, middle);
			mergeSort(array, middle + 1, end);
			merge(array, start, middle, end);
		}
	}

	private void merge(int[] array, int start, int middle, int end) {
		int[] left = new int[middle - start + 1];
		int[] right = new int[end - middle];
		for (int i = 0; i < left.length; i++) {
			left[i] = array[start + i];
		}
		for (int j = 0; j < right.length; j++) {
			right[j] = array[middle + 1 + j];
		}
		int i = 0;
		int j = 0;
		int k = start;
		while (i < left.length && j < right.length) {
			if (left[i] > right[j]) {
				array[k] = right[j];
				j++;
				k++;
			} else {
				array[k] = left[i];
				i++;
				k++;
			}
		}
		while (i < left.length) {
			array[k] = left[i];
			i++;
			k++;
		}
		while (j < right.length) {
			array[k] = right[j];
			j++;
			k++;
		}
	}

	/**
	 * Quick sort: Checks the pivot and divides the left and right sub array and
	 * applies the sorting on those. O(nlogn) O(n)
	 */
	public void quickSort(int[] array, int start, int end) {
		if (start < end) {
			int pivot = partition(array, start, end);
			quickSort(array, start, pivot - 1);
			quickSort(array, pivot + 1, end);
		}
	}

	private int partition(int[] array, int start, int end) {
		int i = start - 1;
		for (int j = start; j <= end; j++) {
			if (array[j] <= array[end]) {
				i++;
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}
		return i;
	}
}
