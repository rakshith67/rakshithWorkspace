package sorting;

public class SortingMain {

	public static void main(String[] args) {
		int[] array = new int[] { 34, 56, 12, 22, 99, 48, 65, 33, 89, 9, 77, 87 };
		SortingAlgorithms sortingAlgorithms = new SortingAlgorithms();
		sortingAlgorithms.bubbleSort(array);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();

		int[] array2 = new int[] { 34, 56, 12, 22, 99, 48, 65, 33, 89, 9, 77, 87 };
		sortingAlgorithms.bubbleSort(array2);
		for (int i = 0; i < array2.length; i++) {
			System.out.print(array2[i] + " ");
		}
		System.out.println();

		int[] array3 = new int[] { 34, 56, 12, 22, 99, 48, 65, 33, 89, 9, 77, 87 };
		sortingAlgorithms.insertionSort(array3);
		for (int i = 0; i < array3.length; i++) {
			System.out.print(array3[i] + " ");
		}
		System.out.println();

		int[] array4 = new int[] { 34, 56, 12, 22, 99, 48, 65, 33, 89, 9, 77, 87 };
		sortingAlgorithms.mergeSort(array4, 0, array4.length - 1);
		for (int i = 0; i < array4.length; i++) {
			System.out.print(array4[i] + " ");
		}
		System.out.println();

		int[] array5 = new int[] { 34, 56, 12, 22, 99, 48, 65, 33, 89, 9, 77, 87 };
		sortingAlgorithms.quickSort(array5, 0, array5.length - 1);
		for (int i = 0; i < array5.length; i++) {
			System.out.print(array5[i] + " ");
		}
		System.out.println();
	}

}
