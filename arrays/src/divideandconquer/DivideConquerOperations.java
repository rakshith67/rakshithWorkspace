package divideandconquer;

import java.util.Arrays;

public class DivideConquerOperations {

	private int[] cache;

	/**
	 * You are a professional robber planning to rob houses along a street. Each
	 * house has a certain amount of money stashed, the only constraint stopping you
	 * from robbing each of them is that adjacent houses have security system
	 * connected and it will automatically contact the police if two adjacent houses
	 * were broken into on the same night. Given a list of non-negative integers
	 * representing the amount of money of each house, determine the maximum amount
	 * of money you can rob tonight without alerting the police. Link:
	 * https://leetcode.com/problems/house-robber/
	 * 
	 * Note: Alternate solution rob(nums, 0, houses.length)
	 * 
	 */
	public void houseThief(int[] houses) {
		cache = new int[houses.length];
		Arrays.fill(cache, -1);
		System.out.println(maxMoney(houses, 0));
	}

	private int maxMoney(int[] houses, int currentIndex) {
		if (currentIndex >= houses.length) {
			return 0;
		}
		int withCurrent = 0;
		if (currentIndex + 2 < houses.length && cache[currentIndex + 2] != -1) {
			withCurrent = houses[currentIndex] + cache[currentIndex + 2];
		} else {
			withCurrent = houses[currentIndex] + maxMoney(houses, currentIndex + 2);
		}
		int withoutCurrent = 0;
		if (currentIndex + 1 < houses.length && cache[currentIndex + 1] != -1) {
			withoutCurrent = cache[currentIndex + 1];
		} else {
			withoutCurrent = maxMoney(houses, currentIndex + 1);
		}
		int result = Math.max(withCurrent, withoutCurrent);
		cache[currentIndex] = result;
		return result;
	}

	/**
	 * You are a professional robber planning to rob houses along a street. Each
	 * house has a certain amount of money stashed. All houses at this place are
	 * arranged in a circle. That means the first house is the neighbor of the last
	 * one. Meanwhile, adjacent houses have security system connected and it will
	 * automatically contact the police if two adjacent houses were broken into on
	 * the same night. Given a list of non-negative integers representing the amount
	 * of money of each house, determine the maximum amount of money you can rob
	 * tonight without alerting the police. Link:
	 * https://leetcode.com/problems/house-robber-ii/submissions/
	 */
	public int houseThiefCircle(int[] nums) {
		if (nums.length == 1) {
			return nums[0];
		}
		return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
	}

	public int rob(int[] nums, int low, int high) {
		int preRobbed = 0, preNotRobbbed = 0, robbed = 0, notRobbed = 0;
		for (int i = low; i <= high; i++) {
			robbed = preNotRobbbed + nums[i];
			notRobbed = Math.max(preRobbed, preNotRobbbed);

			preNotRobbbed = notRobbed;
			preRobbed = robbed;
		}
		return Math.max(robbed, notRobbed);
	}

	/**
	 * You are given weights and values of N items, put these items in a knapsack of
	 * capacity W to get the maximum total value in the knapsack. Note that we have
	 * only one quantity of each item. In other words, given two integer arrays
	 * val[0..N-1] and wt[0..N-1] which represent values and weights associated with
	 * N items respectively. Also given an integer W which represents knapsack
	 * capacity, find out the maximum value subset of val[] such that sum of the
	 * weights of this subset is smaller than or equal to W. You cannot break an
	 * item, either pick the complete item, or don’t pick it (0-1 property). Link:
	 * https://practice.geeksforgeeks.org/problems/0-1-knapsack-problem/0
	 * 
	 */
	public void zeroOneKnapsack(int[] prices, int[] weights, int currentIndex, int maxWeight) {
		System.out.println(maxPrice(prices, weights, 0, maxWeight));
	}

	private int maxPrice(int[] prices, int[] weights, int currentIndex, int maxWeight) {
		if (currentIndex >= prices.length || maxWeight == 0) {
			return 0;
		}
		int withCurrent = 0;
		if (weights[currentIndex] <= maxWeight) {
			withCurrent = prices[currentIndex]
					+ maxPrice(prices, weights, currentIndex + 1, maxWeight - weights[currentIndex]);
		}
		int withoutCurrent = maxPrice(prices, weights, currentIndex + 1, maxWeight);
		return Math.max(withCurrent, withoutCurrent);
	}

	/**
	 * Given two sequences, find the length of longest subsequence present in both
	 * of them. Both the strings are of uppercase. Link:
	 * https://practice.geeksforgeeks.org/problems/longest-common-subsequence/0
	 * 
	 */
	public void longestCommonSusbsequence(String s1, String s2) {
		System.out.println(LCS(s1, s2, 0, 0));
	}

	private int LCS(String s1, String s2, int index1, int index2) {
		if (index1 == s1.length() || index2 == s2.length()) {
			return 0;
		}
		int isEqual = 0;
		int first = 0;
		int second = 0;
		if (s1.charAt(index1) == s2.charAt(index2)) {
			isEqual = 1 + LCS(s1, s2, index1 + 1, index2 + 1);
		} else {
			first = LCS(s1, s2, index1 + 1, index2);
			second = LCS(s1, s2, index1, index2 + 1);
		}
		return Math.max(isEqual, Math.max(first, second));
	}

	public void longestPalindromeSusbsequence(String s1) {
		System.out.println(LPS(s1, 0, s1.length() - 1));
	}

	private int LPS(String s1, int startIndex, int endIndex) {
		if (startIndex > endIndex) {
			return 0;
		}
		if (startIndex == endIndex) {
			return 1;
		}
		int isEqual = 0;
		int first = 0;
		int second = 0;
		if (s1.charAt(startIndex) == s1.charAt(endIndex)) {
			isEqual = 2 + LPS(s1, startIndex + 1, endIndex - 1);
		} else {
			first = LPS(s1, startIndex + 1, endIndex);
			second = LPS(s1, startIndex, endIndex - 1);
		}
		return Math.max(isEqual, Math.max(first, second));
	}

	public void longestPalindromeSubstring(String s1, String s2) {
		System.out.println(LPSubstring(s1, 0, s1.length() - 1));
	}

	private int LPSubstring(String s1, int startIndex, int endIndex) {
		if (startIndex > endIndex) {
			return 0;
		}
		if (startIndex == endIndex) {
			return 1;
		}
		int isEqual = 0;
		int first = 0;
		int second = 0;
		if (s1.charAt(startIndex) == s1.charAt(endIndex)) {
			int length = endIndex - startIndex - 1;
			if (length == LPSubstring(s1, startIndex + 1, endIndex - 1)) {
				isEqual = 2 + length;
			}
		} else {
			first = LPSubstring(s1, startIndex + 1, endIndex);
			second = LPSubstring(s1, startIndex, endIndex - 1);
		}
		return Math.max(isEqual, Math.max(first, second));
	}

	public void minCostToReachLastCell(int[][] matrix) {
		System.out.println(minCostLastCell(matrix, 0, 0));
	}

	private int minCostLastCell(int[][] matrix, int row, int column) {
		if (row == -1 || column == -1) {
			return Integer.MAX_VALUE;
		}
		if (row == 0 || column == 0) {
			return matrix[0][0];
		}
		int left = matrix[row][column] + minCostLastCell(matrix, row, column - 1);
		int up = matrix[row][column] + minCostLastCell(matrix, row - 1, column);
		return Math.min(left, up);
	}

	public void numberOfPathsLastCell(int[][] matrix, int cost) {
		System.out.println(pathsLastCell(matrix, 0, 0, cost));
	}

	private int pathsLastCell(int[][] matrix, int row, int column, int cost) {
		if (cost < 0) {
			return 0;
		}
		if (row == 0 || column == 0) {
			return matrix[0][0] == cost ? 1 : 0;
		}
		if (row == 0) {
			return pathsLastCell(matrix, row, column - 1, cost - matrix[row][column]);
		}
		if (column == 0) {
			return pathsLastCell(matrix, row - 1, column, cost - matrix[row][column]);
		}
		int left = pathsLastCell(matrix, row, column - 1, cost - matrix[row][column]);
		int up = pathsLastCell(matrix, row - 1, column, cost - matrix[row][column]);
		return left + up;
	}
}
