package arrays;

public class ArraysEasyLevel1 {

	/**
	 * Given two sorted arrays A and B, such that the arrays may have some common
	 * elements. Find the sum of the maximum sum path to reach from the beginning of
	 * any array to end of any of the two arrays. We can switch from one array to
	 * another array only at the common elements. Link:
	 * https://practice.geeksforgeeks.org/problems/max-sum-path-in-two-arrays/1
	 * 
	 */
	public int maxPathSum(int ar1[], int ar2[]) {
		int sum1 = 0;
		int sum2 = 0;
		int result = 0;
		int i = 0;
		int j = 0;
		while (i < ar1.length && j < ar2.length) {
			if (ar1[i] < ar2[j]) {
				sum1 += ar1[i++];
			} else if (ar1[i] > ar2[j]) {
				sum2 += ar2[j++];
			} else {
				result += Math.max(sum1, sum2);
				sum1 = 0;
				sum2 = 0;
				while (i < ar1.length && j < ar2.length && ar1[i] == ar2[j]) {
					result = result + ar1[i++];
					j++;
				}
			}
		}
		while (i < ar1.length) {
			sum1 += ar1[i++];
		}
		while (j < ar2.length) {
			sum2 += ar2[j++];
		}
		result += Math.max(sum1, sum2);
		return result;
	}
}
