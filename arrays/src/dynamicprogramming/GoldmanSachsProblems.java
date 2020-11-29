package dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoldmanSachsProblems {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(5);
		list.add(2);
		list.add(3);
		list.add(1);
		list.add(4);
		System.out.println(maxPairsWithThreeIncreasing(list));

		List<List<Integer>> matrix = new ArrayList<>();
		List<Integer> list1 = new ArrayList<>();
		list1.add(1);
		list1.add(3);
		list1.add(4);
		matrix.add(list1);
		List<Integer> list2 = new ArrayList<>();
		list2.add(5);
		list2.add(2);
		list2.add(9);
		matrix.add(list2);
		List<Integer> list3 = new ArrayList<>();
		list3.add(8);
		list3.add(7);
		list3.add(6);
		matrix.add(list3);
		System.out.println(specialElementsMatrix(matrix));

		int[] array = new int[] { 6, 45, 78, -23, -56, -77, -9, 3, 7, 7, 66, 99, 86, -100, 68, 98, -56, -34, 94, 65,
				88 };
		int[] result = meandaringArray(array);
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + " ");
		}
	}

	public static int specialElementsMatrix(List<List<Integer>> matrix) {
		int rows = matrix.size();
		int columns = matrix.get(0).size();
		int[] rowMax = new int[rows];
		int[] rowMin = new int[rows];
		int[] columnMax = new int[columns];
		int[] columnMin = new int[columns];
		for (int i = 0; i < rows; i++) {
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < columns; j++) {
				int value = matrix.get(i).get(j);
				if (value > max) {
					max = value;
				}
				if (value < min) {
					min = value;
				}
			}
			rowMax[i] = max;
			rowMin[i] = min;
		}
		for (int i = 0; i < rows; i++) {
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < columns; j++) {
				int value = matrix.get(j).get(i);
				if (value > max) {
					max = value;
				}
				if (value < min) {
					min = value;
				}
			}
			columnMax[i] = max;
			columnMin[i] = min;
		}
		int count = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				int value = matrix.get(i).get(j);
				boolean isSpecial = false;
				if (value == rowMax[i] || value == rowMin[i]) {
					if (hasDuplicates(matrix, i, value, true)) {
						return -1;
					}
					isSpecial = true;
				}
				if (value == columnMax[j] || value == columnMin[j]) {
					if (hasDuplicates(matrix, j, value, false)) {
						return -1;
					}
					isSpecial = true;
				}
				if (isSpecial) {
					count++;
				}
			}
		}
		return count;
	}

	private static boolean hasDuplicates(List<List<Integer>> matrix, int index, int value, boolean isRow) {
		int count = 0;
		if (isRow) {
			List<Integer> list = matrix.get(index);
			for (Integer inte : list) {
				if (value == inte) {
					count++;
				}
			}
		} else {
			for (int i = 0; i < matrix.size(); i++) {
				if (matrix.get(i).get(index) == value) {
					count++;
				}
			}
		}
		return count > 1;
	}

	public static int maxPairsWithThreeIncreasing(List<Integer> list) {
		return getCountThreeLongestSubsequence(list, true) + getCountThreeLongestSubsequence(list, false);
	}

	private static int getCountThreeLongestSubsequence(List<Integer> list, boolean isIncreasing) {
		int length = list.size();
		int[] dpCache = new int[length];
		if (length == 0) {
			return 0;
		}
		Arrays.fill(dpCache, 1);
		int count = 0;
		for (int i = 1; i < length; i++) {
			for (int j = 0; j < i; j++) {
				if (isIncreasing) {
					if (list.get(j) < list.get(i)) {
						if (1 + dpCache[j] > 2) {
							count += (1 + dpCache[j]) - 2;
						}
						if (dpCache[j] + 1 > dpCache[i]) {
							dpCache[i] = dpCache[j] + 1;
						}
					}
				} else {
					if (list.get(j) > list.get(i)) {
						if (1 + dpCache[j] > 2) {
							count += (1 + dpCache[j]) - 2;
						}
						if (dpCache[j] + 1 > dpCache[i]) {
							dpCache[i] = dpCache[j] + 1;
						}
					}
				}
			}
		}
		return count;
	}

	public static int[] meandaringArray(int[] array) {
		Arrays.sort(array);
		int low = 0;
		int high = array.length - 1;
		int[] result = new int[array.length];
		int k = 0;
		while (low <= high) {
			if (low == high) {
				result[k] = low;
				break;
			}
			result[k++] = array[high];
			result[k++] = array[low];
			low++;
			high--;

		}
		return result;
	}

}
