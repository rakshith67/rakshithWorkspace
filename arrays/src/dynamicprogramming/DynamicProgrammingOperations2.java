package dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicProgrammingOperations2 {

	/**
	 * Samu is in super market and in a mood to do a lot of shopping. She needs to
	 * buy shirts, pants and shoes for herself and her family. There are N different
	 * shops. Each shop contains all these three items but at different prices. Now
	 * Samu has a strategy that she won't buy the same item from the current shop if
	 * she had already bought that item from the shop adjacent to the current shop.
	 * Now Samu is confused, because although she want to follow her strategy
	 * strictly but at the same time she want to minimize the total money she spends
	 * on shopping. Being a good programmer, she asks for your help. You are
	 * provided description about all N shops i.e costs of all three items in each
	 * shop. You need to help Samu find minimum money that she needs to spend such
	 * that she buys exactly one item from every shop. Link:
	 * https://www.hackerearth.com/problem/algorithm/samu-and-shopping-165-5691d19b/discussion/java-simple-concise--2fb5b73d/ @
	 *
	 */
	public void minAmountShopping(int[][] array) {
		System.out.println("Min amount " + minAmount(array, array.length - 1));
	}

	private int minAmount(int[][] array, int row) {
		for (int i = 1; i <= row; i++) {
			array[i][0] += Math.min(array[i - 1][1], array[i - 1][2]);
			array[i][1] += Math.min(array[i - 1][0], array[i - 1][2]);
			array[i][2] += Math.min(array[i - 1][1], array[i - 1][0]);
		}
		return Math.min(array[row][0], Math.min(array[row][1], array[row][2]));
	}

	/**
	 * iven a list of non negative integers, arrange them such that they form the
	 * largest number. For example: Given [3, 30, 34, 5, 9], the largest formed
	 * number is 9534330. Link:
	 * https://www.interviewbit.com/problems/largest-number/ Note: The result may be
	 * very large, so you need to return a string instead of an integer.
	 * 
	 */
	public String largestNumber(final List<Integer> list) {
		String[] arr = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			arr[i] = String.valueOf(list.get(i));
		}

		Arrays.sort(arr, (c, b) -> {
			return (b + c).compareTo(c + b);
		});

		StringBuilder sb = new StringBuilder();
		for (String s : arr) {
			sb.append(s);
		}
		if (sb.charAt(0) == '0') {
			return String.valueOf(0);
		}
		return sb.toString();
	}

	/**
	 * Given a positive integer n and a string s consisting only of letters D or I,
	 * you have to find any permutation of first n positive integer that satisfy the
	 * given input string. D means the next number is smaller, while I means the
	 * next number is greater. Link:
	 * https://www.interviewbit.com/problems/find-permutation/
	 * 
	 */
	public ArrayList<Integer> findPerm(final String A, int B) {
		ArrayList<Integer> list = new ArrayList<>();
		if (B != 0) {
			int minimum = 1;
			int maximum = B;
			for (int i = 0; i < B - 1; i++) {
				if (A.charAt(i) == 'I') {
					list.add(minimum);
					minimum++;
				} else {
					list.add(maximum);
					maximum--;
				}
			}
			list.add(minimum);
		}
		return list;
	}

	/**
	 * Given the position of a Bishop (A, B) on an 8 * 8 chessboard. Your task is to
	 * count the total number of squares that can be visited by the Bishop in one
	 * move. The position of the Bishop is denoted using row and column number of
	 * the chessboard. Link:
	 * https://www.interviewbit.com/problems/total-moves-for-bishop/
	 * 
	 */
	public int maxPointsBishop(int A, int B) {
		int result = 0;
		result += Math.min(Math.abs(1 - A), Math.abs(1 - B));
		result += Math.min(Math.abs(8 - A), Math.abs(8 - B));
		result += Math.min(Math.abs(1 - A), Math.abs(8 - B));
		result += Math.min(Math.abs(8 - A), Math.abs(1 - B));
		return result;
	}

	/**
	 * There is a list of sorted integers from 1 to n. Starting from left to right,
	 * remove the first number and every other number afterward until you reach the
	 * end of the list. Repeat the previous step again, but this time from right to
	 * left, remove the right most number and every other number from the remaining
	 * numbers. We keep repeating the steps again, alternating left to right and
	 * right to left, until a single number remains. Find the last number that
	 * remains starting with a list of length n. Link:
	 * https://leetcode.com/problems/elimination-game/
	 */
	public int eliminationGame(int n) {
		boolean leftDirection = true;
		int head = 1;
		int step = 1;
		int remaining = n;
		while (remaining > 1) {
			if (leftDirection || remaining % 2 == 1) {
				head += step;
			}
			remaining /= 2;
			step *= 2;
			leftDirection = !leftDirection;
		}
		return head;
	}

	public int josepheus(int n, int k) {
		if (n == 1) {
			return 1;
		}
		return (josepheus(n - 1, k) + k - 1) % (n + 1);
	}

	/**
	 * Given n non-negative integers representing an elevation map where the width
	 * of each bar is 1, compute how much water it is able to trap after raining.
	 * Link: https://leetcode.com/problems/trapping-rain-water/
	 * 
	 */
	public int trapRainWater(int[] height, boolean isExtraSpace) {
		if (isExtraSpace) {
			return trapRainWate(height);
		} else {
			return trapRain(height);
		}
	}

	private int trapRainWate(int[] height) {
		int length = height.length;
		if (length == 0) {
			return 0;
		}
		int[] leftMax = new int[length];
		int[] rightMax = new int[length];
		int max = Integer.MIN_VALUE;
		leftMax[0] = 0;
		rightMax[length - 1] = 0;
		for (int i = 1; i < length; i++) {
			if (height[i - 1] > max) {
				max = height[i - 1];
			}
			leftMax[i] = max;
		}
		max = Integer.MIN_VALUE;
		for (int i = length - 2; i >= 0; i--) {
			if (height[i + 1] > max) {
				max = height[i + 1];
			}
			rightMax[i] = max;
		}
		int count = 0;
		for (int i = 0; i < length; i++) {
			int min = Math.min(leftMax[i], rightMax[i]);
			if (min > height[i]) {
				count += min - height[i];
			}
		}
		return count;
	}

	private int trapRain(int[] height) {
		int length = height.length;
		if (length == 0) {
			return 0;
		}
		int leftMax = 0;
		int rightMax = 0;
		int count = 0;
		int left = 0;
		int right = length - 1;
		while (left <= right) {
			if (height[left] <= height[right]) {
				if (height[left] > leftMax) {
					leftMax = height[left];
				} else {
					count += leftMax - height[left];
				}
				left++;
			} else {
				if (height[right] > rightMax) {
					rightMax = height[right];
				} else {
					count += rightMax - height[right];
				}
				right--;
			}
		}
		return count;
	}

	/**
	 * Given n non-negative integers a1, a2, ..., an , where each represents a point
	 * at coordinate (i, ai). n vertical lines are drawn such that the two endpoints
	 * of line i is at (i, ai) and (i, 0). Find two lines, which together with
	 * x-axis forms a container, such that the container contains the most water.
	 * Note: You may not slant the container and n is at least 2. Link:
	 * https://leetcode.com/problems/container-with-most-water/
	 * 
	 */
	public int maxArea(int[] height) {
		int length = height.length;
		if (length == 0 || length == 1) {
			return 0;
		}
		int left = 0;
		int right = length - 1;
		int maxArea = 0;
		while (left < right) {
			int area = (right - left) * Math.min(height[left], height[right]);
			if (area > maxArea) {
				maxArea = area;
			}
			if (height[left] <= height[right]) {
				left++;
			} else {
				right--;
			}
		}
		return maxArea;
	}

	/**
	 * Say you have an array for which the i-th element is the price of a given
	 * stock on day i. Design an algorithm to find the maximum profit. You may
	 * complete at most k transactions. Note: You may not engage in multiple
	 * transactions at the same time (ie, you must sell the stock before you buy
	 * again).
	 * 
	 * Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
	 * 
	 * Hint: dp[i][j] = maximum profit from at most i transactions using
	 * prices[0..j] A transaction is defined as one buy + sell. Now on day j, we
	 * have two options Do nothing (or buy) which doesn't change the acquired profit
	 * : dp[i][j] = dp[i][j-1] Sell the stock: In order to sell the stock, you
	 * must've bought it on a day t=[0..j-1]. Maximum profit that can be attained is
	 * t:0->j-1 max(prices[j]-prices[t]+dp[i-1][t-1]) where prices[j]-prices[t] is
	 * the profit from buying on day t and selling on day j. dp[i-1][t-1] is the
	 * maximum profit that can be made with at most i-1 transactions with prices
	 * prices[0..t-1]. Time complexity of this approach is O(n2k).
	 * 
	 */
	public int maxProfit(int transactions, int[] prices) {
		int length = prices.length;
		if (length == 0 || length == 1) {
			return 0;
		}
		if (transactions >= length / 2) {
			return quickSolve(prices);
		}
		int[][] dpCache = new int[transactions + 1][length];
		for (int transaction = 1; transaction <= transactions; transaction++) {
			for (int j = 1; j < length; j++) {
				int max = 0;
				for (int m = 0; m < j; m++) {
					max = Math.max(max, prices[j] - prices[m] + dpCache[transaction - 1][m]);
				}
				dpCache[transaction][j] = Math.max(max, dpCache[transaction][j - 1]);
			}
		}
		return dpCache[transactions][length - 1];
	}

	public int maxProfit2(int k, int[] prices) {
		int len = prices.length;
		if (k >= len / 2) {
			return quickSolve(prices);
		}
		int[][] t = new int[k + 1][len];
		for (int i = 1; i <= k; i++) {
			int tmpMax = -prices[0];
			for (int j = 1; j < len; j++) {
				t[i][j] = Math.max(t[i][j - 1], prices[j] + tmpMax);
				tmpMax = Math.max(tmpMax, t[i - 1][j - 1] - prices[j]);
			}
		}
		return t[k][len - 1];
	}

	private int quickSolve(int[] prices) {
		int len = prices.length, profit = 0;
		for (int i = 1; i < len; i++) {
			if (prices[i] > prices[i - 1]) {
				profit += prices[i] - prices[i - 1];
			}
		}
		return profit;
	}

	/**
	 * Say you have an array for which the ith element is the price of a given stock
	 * on day i. Design an algorithm to find the maximum profit. You may complete at
	 * most two transactions. Note: You may not engage in multiple transactions at
	 * the same time (i.e., you must sell the stock before you buy again).
	 * 
	 * Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
	 * 
	 */
	public int maxProfitUptoTwo(int[] array) {
		if (array.length < 2) {
			return 0;
		}
		int[] first = new int[array.length];
		int[] second = new int[array.length + 1];
		int min = array[0];
		int current = Integer.MIN_VALUE;
		for (int i = 1; i < array.length; i++) {
			int diff = array[i] - min;
			if (diff > current) {
				current = diff;
			}
			first[i] = current;
			if (array[i] < min) {
				min = array[i];
			}
		}
		int max = array[array.length - 1];
		current = Integer.MIN_VALUE;
		for (int i = array.length - 1; i >= 0; i--) {
			int diff = max - array[i];
			if (diff > current) {
				current = diff;
			}
			second[i] = current;
			if (array[i] > max) {
				max = array[i];
			}
		}
		int firstMax = Integer.MIN_VALUE;
		for (int i = 0; i < array.length; i++) {
			if (first[i] > firstMax) {
				firstMax = first[i];
			}
		}
		int totalMax = Integer.MIN_VALUE;
		for (int i = 0; i < array.length; i++) {
			if (first[i] + second[i + 1] > totalMax) {
				totalMax = Math.max(first[i], first[i] + second[i + 1]);
			}
		}
		return totalMax;
	}

	/**
	 * Given an array, strs, with strings consisting of only 0s and 1s. Also two
	 * integers m and n. Now your task is to find the maximum number of strings that
	 * you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.
	 * 
	 * Link: https://leetcode.com/problems/ones-and-zeroes/
	 * 
	 * Hint: he problem can be interpreted as: What's the max number of str can we
	 * pick from strs with limitation of m "0"s and n "1"s. Thus we can define
	 * dp[i][j] stands for max number of str can we pick from strs with limitation
	 * of i "0"s and j "1"s. For each str, assume it has a "0"s and b "1"s, we
	 * update the dp array iteratively and set dp[i][j] = Math.max(dp[i][j], dp[i -
	 * a][j - b] + 1). So at the end, dp[m][n] is the answer.
	 * 
	 */
	public int findMaxForm(String[] strs, int m, int n) {
		int[][] dpCache = new int[m + 1][n + 1];
		for (String str : strs) {
			int count = getOnes(str);
			for (int i = m; i >= str.length() - count; i--) {
				for (int j = n; j >= count; j--) {
					dpCache[i][j] = Math.max(1 + dpCache[i - (str.length() - count)][j - count], dpCache[i][j]);
				}
			}
		}
		return dpCache[m][n];
	}

	private int getOnes(String string) {
		int count = 0;
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) == '1') {
				count++;
			}
		}
		return count;
	}

	/**
	 * There are a row of N houses, each house can be painted with one of the three
	 * colors: red, blue or green. The cost of painting each house with a certain
	 * color is different. You have to paint all the houses such that no two
	 * adjacent houses have the same color. The cost of painting each house with a
	 * certain color is represented by a N x 3 cost matrix A. For example, A[0][0]
	 * is the cost of painting house 0 with color red; A[1][2] is the cost of
	 * painting house 1 with color green, and so on. Find the minimum total cost to
	 * paint all houses.
	 * 
	 * Link: https://www.interviewbit.com/problems/paint-house/
	 * 
	 */
	public int paintHouse(ArrayList<ArrayList<Integer>> A) {
		int[] dpCache = new int[3];
		int temp0 = A.get(0).get(0);
		int temp1 = A.get(0).get(1);
		int temp2 = A.get(0).get(2);
		for (int i = 0; i < A.size(); i++) {
			temp0 = dpCache[0];
			temp1 = dpCache[1];
			temp2 = dpCache[2];
			dpCache[0] = A.get(i).get(0) + Math.min(temp1, temp2);
			dpCache[1] = A.get(i).get(1) + Math.min(temp0, temp2);
			dpCache[2] = A.get(i).get(2) + Math.min(temp0, temp1);
		}
		return Math.min(dpCache[0], Math.min(dpCache[1], dpCache[2]));
	}

	/**
	 * The chess knight has a unique movement, it may move two squares vertically
	 * and one square horizontally, or two squares horizontally and one square
	 * vertically (with both forming the shape of an L). The possible movements of
	 * chess knight are shown in this diagaram: A chess knight can move as indicated
	 * in the chess diagram below: We have a chess knight and a phone pad as shown
	 * below, the knight can only stand on a numeric cell (i.e. blue cell). Given an
	 * integer n, return how many distinct phone numbers of length n we can dial.
	 * You are allowed to place the knight on any numeric cell initially and then
	 * you should perform n - 1 jumps to dial a number of length n. All jumps should
	 * be valid knight jumps. As the answer may be very large, return the answer
	 * modulo 109 + 7.
	 * 
	 * Link: https://leetcode.com/problems/knight-dialer/
	 * 
	 */
	public int knightDialer(int n) {
		if (n == 1) {
			return 10;
		}
		long mod = 1000000007;
		long[] pre = new long[10];
		long[] cur = new long[10];
		Arrays.fill(pre, 1);
		while (--n > 0) {
			cur[0] = (pre[4] + pre[6]) % mod;
			cur[1] = (pre[6] + pre[8]) % mod;
			cur[2] = (pre[7] + pre[9]) % mod;
			cur[3] = (pre[4] + pre[8]) % mod;
			cur[4] = (pre[3] + pre[9] + pre[0]) % mod;
			// cur[5]=0;
			cur[6] = (pre[1] + pre[7] + pre[0]) % mod;
			cur[7] = (pre[2] + pre[6]) % mod;
			cur[8] = (pre[1] + pre[3]) % mod;
			cur[9] = (pre[2] + pre[4]) % mod;
			for (int i = 0; i < 10; i++) {
				pre[i] = cur[i];
			}
		}
		long sum = 0;
		for (int i = 0; i < 10; i++) {
			sum = (sum + cur[i]) % mod;
		}
		return (int) sum;

	}

	/**
	 * Given a positive integer n, find the least number of perfect square numbers
	 * (for example, 1, 4, 9, 16, ...) which sum to n.
	 * 
	 * Link: https://leetcode.com/problems/perfect-squares/
	 * 
	 */
	public int numSquares(int n) {
		int size = (int) Math.sqrt(n);
		int[] squares = new int[size + 1];
		for (int i = 1; i < squares.length; i++) {
			squares[i] = i * i;
		}
		int[] cache = new int[n + 1];
		Arrays.fill(cache, Integer.MAX_VALUE);
		cache[0] = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= size; j++) {
				if (i - squares[j] >= 0) {
					int res = cache[i - squares[j]];
					if (res != Integer.MAX_VALUE && 1 + res < cache[i]) {
						cache[i] = 1 + res;
					}
				}
			}
		}
		return cache[n];
	}

	/**
	 * Given n, how many structurally unique BST's (binary search trees) that store
	 * values 1 ... n?
	 * 
	 * Link: https://leetcode.com/problems/unique-binary-search-trees/
	 * 
	 */
	public int numTrees(int n) {
		int[] nums = new int[n + 1];
		nums[0] = 1;
		nums[1] = 1;
		for (int i = 2; i <= n; i++) {
			for (int j = 1; j <= i; j++) {
				nums[i] += nums[j - 1] * nums[i - j];
			}
		}
		return nums[n];
	}

	/**
	 * You are given a list of non-negative integers, a1, a2, ..., an, and a target,
	 * S. Now you have 2 symbols + and -. For each integer, you should choose one
	 * from + and - as its new symbol. Find out how many ways to assign symbols to
	 * make sum of integers equal to target S.
	 * 
	 * Link: https://leetcode.com/problems/target-sum/
	 * 
	 */
	public int findTargetSumWays(int[] nums, int S) {
		Map<Map.Entry<Integer, Integer>, Integer> map = new HashMap<>();
		return sum(nums, map, 0, 0, S);
	}

	private int sum(int[] nums, Map<Map.Entry<Integer, Integer>, Integer> map, int index, int sum, int target) {
		Map.Entry<Integer, Integer> entry = null; // Map.entry(index, sum);
		if (map.containsKey(entry)) {
			return map.get(entry);
		}
		if (index == nums.length) {
			if (sum == target) {
				return 1;
			}
			return 0;
		}
		int withPlus = sum(nums, map, index + 1, sum + nums[index], target);
		int withMinus = sum(nums, map, index + 1, sum - nums[index], target);
		// entry = Map.entry(index, sum);
		map.put(entry, withPlus + withMinus);
		return withPlus + withMinus;
	}

	/**
	 * Given a 2D binary matrix filled with 0's and 1's, find the largest square
	 * containing only 1's and return its area.
	 * 
	 * Link: https://leetcode.com/problems/maximal-square/
	 * 
	 */
	public int maximalSquare(char[][] matrix) {
		if (matrix.length == 0) {
			return 0;
		}
		int rows = matrix.length;
		int columns = matrix[0].length;
		int[][] cache = new int[rows + 1][columns + 1];
		int result = Integer.MIN_VALUE;
		for (int i = 1; i <= rows; i++) {
			for (int j = 1; j <= columns; j++) {
				if (matrix[i - 1][j - 1] == '1') {
					cache[i][j] = Math.min(cache[i - 1][j], Math.min(cache[i][j - 1], cache[i - 1][j - 1])) + 1;
					result = Math.max(result, cache[i][j]);
				}
			}
		}
		return result * result;
	}

	/**
	 * Given a non-empty string s and a dictionary wordDict containing a list of
	 * non-empty words, determine if s can be segmented into a space-separated
	 * sequence of one or more dictionary words. Note: The same word in the
	 * dictionary may be reused multiple times in the segmentation. You may assume
	 * the dictionary does not contain duplicate words.
	 * 
	 * Link: https://leetcode.com/problems/word-break/
	 * 
	 */
	public boolean wordBreak(String s, List<String> wordDict) {
		Boolean[] seen = new Boolean[s.length()];
		return wordBreakUtil(s, wordDict, 0, seen);
	}

	private boolean wordBreakUtil(String s, List<String> dict, int start, Boolean[] seen) {
		if (start == s.length()) {
			return true;
		}
		if (seen[start] == null) {
			for (String word : dict) {
				if (s.startsWith(word, start) && wordBreakUtil(s, dict, start + word.length(), seen)) {
					seen[start] = true;
					return true;
				}
			}
		}
		seen[start] = false;
		return seen[start];
	}

	/**
	 * Given a non-empty array nums containing only positive integers, find if the
	 * array can be partitioned into two subsets such that the sum of elements in
	 * both subsets is equal.
	 * 
	 * Link: https://leetcode.com/problems/partition-equal-subset-sum/
	 * 
	 */
	public boolean canPartition(int[] nums) {
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		if ((sum & 1) == 1) {
			return false;
		}
		Boolean[] cache = new Boolean[(sum / 2) + 1];
		return canPart(nums, cache, sum / 2, 0);
	}

	private boolean canPart(int[] nums, Boolean[] dpCache, int target, int start) {
		if (target < 0) {
			return false;
		}
		if (target == 0) {
			return true;
		}

		if (start == nums.length) {
			return false;
		}
		if (dpCache[target] == null) {
			dpCache[target] = canPart(nums, dpCache, target - nums[start], start + 1)
					|| canPart(nums, dpCache, target, start + 1);
		}
		return dpCache[target];
	}

	/**
	 * We write the integers of A and B (in the order they are given) on two
	 * separate horizontal lines. Now, we may draw connecting lines: a straight line
	 * connecting two numbers A[i] and B[j] such that: A[i] == B[j]; The line we
	 * draw does not intersect any other connecting (non-horizontal) line. Note that
	 * a connecting lines cannot intersect even at the endpoints: each number can
	 * only belong to one connecting line. Return the maximum number of connecting
	 * lines we can draw in this way.
	 * 
	 * Link: https://leetcode.com/problems/uncrossed-lines/
	 * 
	 */
	public int maxUncrossedLines(int[] A, int[] B) {
		int m = A.length;
		int n = B.length;
		int[][] dpCache = new int[m + 1][n + 1];
		for (int i = 1; i <= m; ++i) {
			for (int j = 1; j <= n; ++j) {
				if (A[i - 1] == B[j - 1]) {
					dpCache[i][j] = 1 + dpCache[i - 1][j - 1];
				} else {
					dpCache[i][j] = Math.max(dpCache[i][j - 1], dpCache[i - 1][j]);
				}
			}
		}
		return dpCache[m][n];
	}

}