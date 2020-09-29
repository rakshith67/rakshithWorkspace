package dynamicprogramming;

import java.util.Arrays;

public class DynamicProgrammingOperations {

	/**
	 * Example for bottom upapproach(starts building the solution from the first and
	 * then going up) where we are storing the results from zero and using them
	 * again for the overlapping intervals to find the solution.
	 */
	public void generateFibonacciBottomUp(int n) {
		int[] series = new int[n + 1];
		series[1] = 1;
		for (int i = 2; i <= n; i++) {
			series[i] = series[i - 1] + series[i - 2];
		}
		for (int i = 0; i <= n; i++) {
			System.out.print(series[i] + " ");
		}
		System.out.println();
	}

	/**
	 * Example for bottom upapproach(starts building the solution from the top and
	 * then goes down and starts building from down and comes up) where we are
	 * storing the results from zero and using them again for the overlapping
	 * intervals to find the solution.
	 */
	public void generateFibonacciTopDown(int n) {
		int[] series = new int[n + 1];
		series[1] = 1;
		generateFibonacci(series, n);
		for (int i = 0; i <= n; i++) {
			System.out.print(series[i] + " ");
		}
		System.out.println();
	}

	private int generateFibonacci(int[] series, int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		if (series[n] == 0) {
			series[n] = generateFibonacci(series, n - 1) + generateFibonacci(series, n - 2);
			return series[n];
		}
		return series[n];
	}

	public void numberFactorTopDown(int n, int[] factors) {
		int[] dpCache = new int[n + 1];
		Arrays.fill(dpCache, -1);
		dpCache[0] = 1;
		dpCache[1] = 1;
		dpCache[2] = 1;
		factorsTopDown(dpCache, factors, n);
		System.out.println("Number factor top down " + dpCache[n]);
	}

	private int factorsTopDown(int[] dpCache, int[] factors, int n) {
		if (n < 0) {
			return 0;
		}
		if (dpCache[n] == -1) {
			int count = 0;
			for (int i = 0; i < factors.length; i++) {
				count += factorsTopDown(dpCache, factors, n - factors[i]);
			}
			dpCache[n] = count;
		}
		return dpCache[n];
	}

	public void numberFactorBottomUp(int n, int[] factors) {
		int[] dpCache = new int[n + 1];
		dpCache[0] = 1;
		dpCache[1] = 1;
		dpCache[2] = 1;
		for (int i = 3; i < n + 1; i++) {
			for (int j = 0; j < factors.length; j++) {
				if (i - factors[j] >= 0) {
					dpCache[i] += dpCache[i - factors[j]];
				}
			}
		}
		System.out.println("Number factor bottom up " + dpCache[n]);
	}

	/**
	 * Given a value N, find the number of ways to make change for N cents, if we
	 * have infinite supply of each of S = { S1, S2, .. , Sm} valued coins. The
	 * order of coins doesn’t matter. For example, for N = 4 and S = {1,2,3}, there
	 * are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}. So output should be 4. For
	 * N = 10 and S = {2, 5, 3, 6}, there are five solutions: {2,2,2,2,2},
	 * {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}. So the output should be 5. Link:
	 * https://practice.geeksforgeeks.org/problems/coin-change/0
	 * 
	 * Hint: To count the total number of solutions, we can divide all set solutions
	 * into two sets. 1) Solutions that do not contain mth coin (or Sm). 2)
	 * Solutions that contain at least one Sm.
	 * 
	 */
	public void coinChange(int[] coins, int amount) {
		int[] dpCache = new int[amount + 1];
		dpCache[0] = 1;
		for (int coinIndex = 0; coinIndex < coins.length; coinIndex++) {
			for (int amountIndex = coins[coinIndex]; amountIndex <= amount; amountIndex++) {
				dpCache[amountIndex] += dpCache[amountIndex - coins[coinIndex]];
			}
		}
		System.out.println(dpCache[amount]);
	}

	/**
	 * You are given coins of different denominations and a total amount of money
	 * amount. Write a function to compute the fewest number of coins that you need
	 * to make up that amount. If that amount of money cannot be made up by any
	 * combination of the coins, return -1. Link:
	 * https://leetcode.com/problems/coin-change/
	 * 
	 * Hint:
	 * 
	 */
	public void minCoins(int[] coins, int amount) {
		int[] dpCache = new int[amount + 1];
		Arrays.fill(dpCache, Integer.MAX_VALUE);
		dpCache[0] = 0;
		for (int amountIndex = 1; amountIndex <= amount; amountIndex++) {
			for (int coinIndex = 0; coinIndex < coins.length; coinIndex++) {
				if (coins[coinIndex] <= amountIndex) {
					int res = dpCache[amountIndex - coins[coinIndex]];
					if (res != Integer.MAX_VALUE && 1 + res < dpCache[amountIndex]) {
						dpCache[amountIndex] = res + 1;
					}
				}
			}
		}
		if (dpCache[amount] == Integer.MAX_VALUE) {
			System.out.println("-1");
			return;
		}
		System.out.println(dpCache[amount]);
	}

	/**
	 * Given an unsorted array of integers, find the length of longest increasing
	 * subsequence. Link:
	 * https://leetcode.com/problems/longest-increasing-subsequence/ Hint: Let
	 * arr[0..n-1] be the input array and L(i) be the length of the LIS ending at
	 * index i such that arr[i] is the last element of the LIS. L(i) = 1 + max( L(j)
	 * ) where 0 < j < i and arr[j] < arr[i]; or L(i) = 1, if no such j exists.
	 * 
	 */
	public void longestIncresingSubsequence(int[] nums) {
		int[] dpCache = new int[nums.length];
		if (nums.length == 0) {
			System.out.println("Longest Increasing Subsequence: 0");
			return;
		}
		Arrays.fill(dpCache, 1);
		for (int i = 1; i < nums.length; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i] && dpCache[j] + 1 > dpCache[i]) {
					dpCache[i] = dpCache[j] + 1;
				}
			}
		}
		int maximum = 0;
		for (int i = 0; i < nums.length; i++) {
			if (dpCache[i] > maximum) {
				maximum = dpCache[i];
			}
		}
		System.out.println("Longest Increasing Subsequence: " + maximum);
	}

	/**
	 * Given a string s and a string t, check if s is subsequence of t. A
	 * subsequence of a string is a new string which is formed from the original
	 * string by deleting some (can be none) of the characters without disturbing
	 * the relative positions of the remaining characters. (ie, "ace" is a
	 * subsequence of "abcde" while "aec" is not). Link:
	 * https://leetcode.com/problems/is-subsequence/
	 */
	public void isSubsequence(String subsequence, String string) {
		int length = string.length();
		int subLength = subsequence.length();
		int j = 0;
		if (subsequence.isEmpty()) {
			System.out.println("Is Subsequence");
			return;
		}
		for (int i = 0; i < length; i++) {
			if (subsequence.charAt(j) == string.charAt(i)) {
				j++;
			}
			if (j == subLength) {
				System.out.println("Is Subsequence");
				return;
			}
		}
		System.out.println("Not a Subsequence");
	}

	/**
	 * You are climbing a stair case. It takes n steps to reach to the top. Each
	 * time you can either climb 1 or 2 steps. In how many distinct ways can you
	 * climb to the top? Link: https://leetcode.com/problems/climbing-stairs/ Hint:
	 * Number factor problem
	 * 
	 */
	public void climbStairs(int number) {
		int[] dpCache = new int[number + 1];
		int[] stairFactors = new int[] { 1, 2 };
		dpCache[1] = 1;
		dpCache[0] = 1;
		for (int i = 2; i <= number; i++) {
			for (int j = 0; j < stairFactors.length; j++) {
				if (i - stairFactors[j] >= 0) {
					dpCache[i] += dpCache[i - stairFactors[j]];
				}
			}
		}
		System.out.println("number of ways to climb stairs " + dpCache[number]);
	}

	/**
	 * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0
	 * indexed). Once you pay the cost, you can either climb one or two steps. You
	 * need to find minimum cost to reach the top of the floor, and you can either
	 * start from the step with index 0, or the step with index 1. Link:
	 * https://leetcode.com/problems/min-cost-climbing-stairs/
	 * 
	 */
	public int minCostClimbingStairs(int[] cost) {
		int[] dpCache = new int[cost.length];
		Arrays.fill(dpCache, Integer.MAX_VALUE);
		if (cost.length == 0 || cost.length == 1) {
			return cost.length;
		}
		dpCache[cost.length - 1] = cost[cost.length - 1];
		dpCache[cost.length - 2] = cost[cost.length - 2];
		for (int i = cost.length - 3; i >= 0; i--) {
			int first = cost[i] + dpCache[i + 1];
			int second = cost[i] + dpCache[i + 2];
			dpCache[i] = Math.min(first, second);
		}
		return Math.min(dpCache[0], dpCache[1]);
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
	public int houseThiefBottomUp(int[] nums) {
		int[] dpCache = new int[nums.length + 1];
		if (nums.length == 0) {
			return 0;
		}
		dpCache[nums.length - 1] = nums[nums.length - 1];
		for (int i = nums.length - 2; i >= 0; i--) {
			int with = nums[i] + dpCache[i + 2];
			int without = dpCache[i + 1];
			dpCache[i] = Math.max(with, without);
		}
		return dpCache[0];
	}

	public void zeroOneKnapsack(int[] prices, int[] weights, int maxWeight, boolean isTopDown) {
		int[][] dpCache = new int[prices.length][maxWeight + 1];
		int maxPrice = 0;
		if (isTopDown) {
			maxPrice = maxPriceTopDown(dpCache, prices, weights, 0, maxWeight);
		} else {
			maxPrice = maxPriceBottomUp(dpCache, prices, weights, maxWeight);
		}
		System.out.println("Max Price " + maxPrice);
	}

	private int maxPriceBottomUp(int[][] dpCache, int[] prices, int[] weights, int maxWeight) {
		if (maxWeight <= 0 || prices.length == 0 || prices.length != weights.length) {
			return 0;
		}
		for (int i = prices.length - 2; i >= 0; i--) {
			for (int j = 0; j <= maxWeight; j++) {
				if (prices[i] <= j) {
					dpCache[i][j] = Math.max(prices[i] + dpCache[i + 1][j - prices[i]], dpCache[i + 1][j]);
				} else {
					dpCache[i][j] = dpCache[i + 1][j];
				}
			}
		}
		return dpCache[0][maxWeight];
	}

	private int maxPriceTopDown(int[][] dpCache, int[] prices, int[] weights, int currentIndex, int maxWeight) {
		if (currentIndex >= prices.length || maxWeight == 0) {
			return 0;
		}
		if (dpCache[currentIndex][maxWeight] != 0) {
			return dpCache[currentIndex][maxWeight];
		}
		int withCurrent = 0;
		if (weights[currentIndex] <= maxWeight) {
			withCurrent = prices[currentIndex]
					+ maxPriceTopDown(dpCache, prices, weights, currentIndex + 1, maxWeight - weights[currentIndex]);
		}
		int withoutCurrent = maxPriceTopDown(dpCache, prices, weights, currentIndex + 1, maxWeight);
		int result = Math.max(withCurrent, withoutCurrent);
		dpCache[currentIndex][maxWeight] = result;
		return result;
	}

	/**
	 * Given two sequences, find the length of longest subsequence present in both
	 * of them. Both the strings are of uppercase. Link:
	 * https://practice.geeksforgeeks.org/problems/longest-common-subsequence/0
	 * 
	 */
	public void longestCommonSusbsequence(String s1, String s2, boolean isTopDown) {
		int[][] dpCache = new int[s1.length()][s2.length()];
		if (isTopDown) {
			System.out.println(lcsTopDown(dpCache, s1, s2, 0, 0));
		} else {
			System.out.println(lcsBottomUp(s1, s2));
		}
	}

	private int lcsTopDown(int[][] dpCache, String s1, String s2, int index1, int index2) {
		if (index1 == s1.length() || index2 == s2.length()) {
			return 0;
		}
		if (dpCache[index1][index2] == 0) {
			int isEqual = 0;
			int first = 0;
			int second = 0;
			if (s1.charAt(index1) == s2.charAt(index2)) {
				isEqual = 1 + lcsTopDown(dpCache, s1, s2, index1 + 1, index2 + 1);
			} else {
				first = lcsTopDown(dpCache, s1, s2, index1 + 1, index2);
				second = lcsTopDown(dpCache, s1, s2, index1, index2 + 1);
			}
			dpCache[index1][index2] = Math.max(isEqual, Math.max(first, second));
		}
		return dpCache[index1][index2];
	}

	private int lcsBottomUp(String s1, String s2) {
		int[][] dpCache = new int[s1.length() + 1][s2.length() + 1];
		for (int i = s1.length(); i >= 1; i--) {
			for (int j = s2.length(); j >= 1; j--) {
				if (s1.charAt(i) == s2.charAt(j)) {
					dpCache[i][j] = Math.max(1 + dpCache[i - 1][j - 1], Math.max(dpCache[i - 1][j], dpCache[i][j - 1]));
				} else {
					dpCache[i][j] = Math.max(dpCache[i - 1][j], dpCache[i][j - 1]);
				}
			}
		}
		return dpCache[0][0];
	}

	/**
	 * Given a string s, find the longest palindromic subsequence's length in s. You
	 * may assume that the maximum length of s is 1000. Link:
	 * https://leetcode.com/problems/longest-palindromic-subsequence/
	 * 
	 */
	public void longestPalindromeSusbsequence(String s1, boolean isTopDown) {
		int[][] dpCache = new int[s1.length()][s1.length()];
		if (isTopDown) {
			System.out.println(LPS(dpCache, s1, 0, s1.length() - 1));
		} else {
			System.out.println(lpsBottomUp(dpCache, s1));
		}
	}

	private int LPS(int[][] dpCache, String s1, int startIndex, int endIndex) {
		if (startIndex > endIndex) {
			return 0;
		}
		if (startIndex == endIndex) {
			return 1;
		}
		if (dpCache[startIndex][endIndex] == 0) {
			int isEqual = 0;
			int first = 0;
			int second = 0;
			if (s1.charAt(startIndex) == s1.charAt(endIndex)) {
				isEqual = 2 + LPS(dpCache, s1, startIndex + 1, endIndex - 1);
			} else {
				first = LPS(dpCache, s1, startIndex + 1, endIndex);
				second = LPS(dpCache, s1, startIndex, endIndex - 1);
			}
			dpCache[startIndex][endIndex] = Math.max(isEqual, Math.max(first, second));
		}
		return dpCache[startIndex][endIndex];
	}

	private int lpsBottomUp(int[][] dpCache, String s1) {
		for (int i = s1.length() - 1; i >= 0; i--) {
			for (int j = 0; j < s1.length(); j++) {
				if (i > j) {
					dpCache[i][j] = 0;
				} else if (i == j) {
					dpCache[i][j] = 1;
				} else {
					if (s1.charAt(i) == s1.charAt(j)) {
						dpCache[i][j] = Math.max(2 + dpCache[i + 1][j - 1],
								Math.max(dpCache[i + 1][j], dpCache[i][j - 1]));
					} else {
						dpCache[i][j] = Math.max(dpCache[i + 1][j], dpCache[i][j - 1]);
					}
				}
			}
		}
		return dpCache[0][s1.length() - 1];
	}

	public void longestPalindromeSubstring(String s) {
		int[][] dpCache = new int[s.length()][s.length()];
		System.out.println(LPSubstring(dpCache, s, 0, s.length() - 1));
	}

	private int LPSubstring(int[][] dpCache, String s1, int startIndex, int endIndex) {
		if (startIndex > endIndex) {
			return 0;
		}
		if (startIndex == endIndex) {
			dpCache[startIndex][endIndex] = 1;
			return 1;
		}
		if (dpCache[startIndex][endIndex] == 0) {
			int isEqual = 0;
			int first = 0;
			int second = 0;
			if (s1.charAt(startIndex) == s1.charAt(endIndex)) {
				int length = endIndex - startIndex - 1;
				if (length == LPSubstring(dpCache, s1, startIndex + 1, endIndex - 1)) {
					isEqual = 2 + length;
				}
			} else {
				first = LPSubstring(dpCache, s1, startIndex + 1, endIndex);
				second = LPSubstring(dpCache, s1, startIndex, endIndex - 1);
			}
			dpCache[startIndex][endIndex] = Math.max(isEqual, Math.max(first, second));
		}
		return dpCache[startIndex][endIndex];
	}

	/**
	 * Given a square grid of size N, each cell of which contains integer cost which
	 * represents a cost to traverse through that cell, we need to find a path from
	 * top left cell to bottom right cell by which total cost incurred is minimum.
	 * You can move in 4 directions : up, down, left an right. Note : It is assumed
	 * that negative cost cycles do not exist in input matrix. Link:
	 * https://practice.geeksforgeeks.org/problems/minimum-cost-path/0,
	 * https://leetcode.com/problems/minimum-path-sum/submissions/
	 * 
	 */
	public void minCostToReachLastCell(int[][] matrix, boolean isTopDown) {
		if (isTopDown) {
			int[][] dpCache = new int[matrix.length][matrix[0].length];
			dpCache[0][0] = matrix[0][0];
			System.out.println(minCostLastCell(dpCache, matrix, matrix.length - 1, matrix[0].length));
		} else {
			System.out.println(micCostLastCellBottomUp(matrix, matrix.length));
		}
	}

	private int minCostLastCell(int[][] dpCache, int[][] matrix, int row, int column) {
		if (row == -1 || column == -1) {
			return Integer.MAX_VALUE;
		}
		if (dpCache[row][column] == 0) {
			int left = minCostLastCell(dpCache, matrix, row, column - 1);
			int up = minCostLastCell(dpCache, matrix, row - 1, column);
			int current = Math.min(left, up);
			if (Integer.MAX_VALUE != current) {
				dpCache[row][column] = matrix[row][column] + current;
			}
		}
		return dpCache[row][column];
	}

	private int micCostLastCellBottomUp(int[][] cost, int size) {
		int i, j;
		int dpCache[][] = new int[size + 1][size + 1];
		dpCache[0][0] = cost[0][0];
		for (i = 1; i <= size; i++) {
			dpCache[i][0] = dpCache[i - 1][0] + cost[i][0];
		}
		for (j = 1; j <= size; j++) {
			dpCache[0][j] = dpCache[0][j - 1] + cost[0][j];
		}
		for (i = 1; i <= size; i++) {
			for (j = 1; j <= size; j++) {
				dpCache[i][j] = Math.min(Math.min(dpCache[i - 1][j - 1], dpCache[i - 1][j]), dpCache[i][j - 1])
						+ cost[i][j];
			}
		}
		return dpCache[size][size];
	}

	/**
	 * Given a N x N matrix where every cell has some number of coins. Count number
	 * of ways to reach bottom right cell of matrix from top left cell with exactly
	 * K coins. We can move to (i+1, j) or (i, j+1) from a cell (i, j). Link:
	 * https://practice.geeksforgeeks.org/problems/number-of-paths-in-a-matrix-with-k-coins/0
	 * 
	 */
	public void numberOfPathsLastCell(int[][] matrix, int cost) {
		int[][] dpCache = new int[matrix.length][matrix[0].length];
		System.out.println(pathsLastCell(dpCache, matrix, matrix.length - 1, matrix[0].length - 1, cost));
	}

	private int pathsLastCell(int[][] dpCache, int[][] matrix, int row, int column, int cost) {
		if (cost < 0 || row < 0 || column < 0) {
			return 0;
		}
		if (row == 0 && column == 0) {
			return matrix[0][0] == cost ? 1 : 0;
		}
		if (dpCache[row][column] == 0) {
			int left = pathsLastCell(dpCache, matrix, row, column - 1, cost - matrix[row][column]);
			int up = pathsLastCell(dpCache, matrix, row - 1, column, cost - matrix[row][column]);
			dpCache[row][column] = left + up;
		}
		return dpCache[row][column];
	}

	/**
	 * Given an array arr of N integers. Find the contiguous sub-array with maximum
	 * sum. Link: https://practice.geeksforgeeks.org/problems/kadanes-algorithm/0
	 * 
	 */
	public int kadanesAlgorithm(int array[]) {
		int size = array.length;
		int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;
		for (int i = 0; i < size; i++) {
			max_ending_here = max_ending_here + array[i];
			if (max_so_far < max_ending_here) {
				max_so_far = max_ending_here;
			}
			if (max_ending_here < 0) {
				max_ending_here = 0;
			}
		}
		return max_so_far;
	}

	/**
	 * Given an array of non-negative integers, you are initially positioned at the
	 * first index of the array. Each element in the array represents your maximum
	 * jump length at that position. Your goal is to reach the last index in the
	 * minimum number of jumps. Link:
	 * https://practice.geeksforgeeks.org/problems/minimum-number-of-jumps/0,
	 * https://leetcode.com/problems/jump-game-ii/ Hint: The main idea is based on
	 * greedy. Let's say the range of the current jump is [curBegin, curEnd],
	 * curFarthest is the farthest point that all points in [curBegin, curEnd] can
	 * reach. Once the current point reaches curEnd, then trigger another jump, and
	 * set the new curEnd with curFarthest, then keep the above steps, as the
	 * following:
	 * 
	 */
	public int minJumpGreedy(int[] A) {
		int jumps = 0, curEnd = 0, curFarthest = 0;
		for (int i = 0; i < A.length - 1; i++) {
			curFarthest = Math.max(curFarthest, i + A[i]);
			if (i == curEnd) {
				jumps++;
				curEnd = curFarthest;
			}
		}
		return jumps;
	}

	/**
	 * Given an array of non-negative integers, you are initially positioned at the
	 * first index of the array. Each element in the array represents your maximum
	 * jump length at that position. Your goal is to reach the last index in the
	 * minimum number of jumps. Link:
	 * https://practice.geeksforgeeks.org/problems/minimum-number-of-jumps/0,
	 * https://leetcode.com/problems/jump-game-ii/
	 */
	public int jumpBottomUp(int[] nums) {
		int length = nums.length - 1;
		int[] dpCache = new int[length + 1];
		dpCache[length] = 0;
		for (int i = length - 1; i >= 0; i--) {
			int current = i + nums[i];
			if (current >= length) {
				dpCache[i] = 1;
			} else {
				int min = Integer.MAX_VALUE;
				for (int j = i + 1; j <= current && j <= length; j++) {
					if (dpCache[j] != 0)
						min = Math.min(1 + dpCache[j], min);
				}
				if (min != Integer.MAX_VALUE)
					dpCache[i] = min;
			}
		}
		return dpCache[0];
	}

	/**
	 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in
	 * the diagram below). The robot can only move either down or right at any point
	 * in time. The robot is trying to reach the bottom-right corner of the grid
	 * (marked 'Finish' in the diagram below). Now consider if some obstacles are
	 * added to the grids. How many unique paths would there be? Link:
	 * https://leetcode.com/problems/unique-paths-ii/
	 * 
	 */
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int width = obstacleGrid[0].length;
		int[] dp = new int[width];
		dp[0] = 1;
		for (int[] row : obstacleGrid) {
			for (int j = 0; j < width; j++) {
				if (row[j] == 1)
					dp[j] = 0;
				else if (j > 0)
					dp[j] += dp[j - 1];
			}
		}
		return dp[width - 1];
	}

}
