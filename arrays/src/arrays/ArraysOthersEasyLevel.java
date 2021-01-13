package arrays;

public class ArraysOthersEasyLevel {

	/**
	 * Let's call an array arr a mountain if the following properties hold:
	 * arr.length >= 3 There exists some i with 0 < i < arr.length - 1 such that:
	 * arr[0] < arr[1] < ... arr[i-1] < arr[i] arr[i] > arr[i+1] > ... >
	 * arr[arr.length - 1] Given an integer array arr that is guaranteed to be a
	 * mountain, return any i such that arr[0] < arr[1] < ... arr[i - 1] < arr[i] >
	 * arr[i + 1] > ... > arr[arr.length - 1].
	 * 
	 * Link: https://leetcode.com/problems/peak-index-in-a-mountain-array/
	 * 
	 */
	public int peakIndexInMountainArray(int[] arr) {
		int start = 0;
		int end = arr.length;
		while (start < end) {
			int mid = (start + end) / 2;
			if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
				return mid;
			}
			if (arr[mid] < arr[mid - 1]) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		return -1;
	}

}
