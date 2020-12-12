package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class ArraysMediumLevel {

	public int maxProfit(int[] lengths, int metalPrice, int cutPrice, int length) {
		int maxLength = 0;
		for (int i = 0; i < length; i++) {
			if (maxLength < lengths[i]) {
				maxLength = lengths[i];
			}
		}
		int currentProfit = 0;
		int maxProfit = 0;
		int totalRods = 0;
		int totalCuts = 0;
		for (int cutLength = 1; cutLength <= maxLength; cutLength++) {
			totalRods = 0;
			totalCuts = 0;
			for (int i = 0; i < length; i++) {
				totalRods += lengths[i] / cutLength;
				totalCuts += (lengths[i] - 1) / cutLength;
			}
			currentProfit = totalRods * metalPrice * cutLength - cutPrice * totalCuts;
			if (maxProfit < currentProfit) {
				maxProfit = currentProfit;
			}
		}
		return maxProfit;
	}

	/**
	 * Given an integer array nums, find the contiguous subarray within an array
	 * (containing at least one number) which has the largest product.
	 * 
	 * Link: https://leetcode.com/problems/maximum-product-subarray/
	 * 
	 */
	public int maxProduct(int[] nums) {
		int product = 1;
		int result = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			product = product * nums[i];
			result = Math.max(product, result);
			if (product == 0) {
				product = 1;
			}
		}
		product = 1;
		for (int i = nums.length - 1; i >= 0; i--) {
			product = product * nums[i];
			result = Math.max(product, result);
			if (product == 0) {
				product = 1;
			}
		}
		return result;
	}

	/**
	 * Given an array of n positive integers and a positive integer s, find the
	 * minimal length of a contiguous subarray of which the sum ≥ s. If there isn't
	 * one, return 0 instead.
	 * 
	 * Link: https://leetcode.com/problems/minimum-size-subarray-sum/
	 * 
	 */
	public int minSubArrayLen(int s, int[] nums) {
		int start = 0;
		int end = 0;
		int currentMin = nums.length;
		int currentSum = 0;
		boolean greater = false;
		while (end < nums.length) {
			while (currentSum < s && end < nums.length) {
				currentSum += nums[end];
				end++;
			}
			while (currentSum >= s && start < nums.length) {
				greater = true;
				if (end - start < currentMin) {
					currentMin = end - start;
				}
				currentSum -= nums[start];
				start++;
			}
		}
		return greater ? currentMin : 0;
	}

	/**
	 * Given an array of integers nums and an integer k, return the number of unique
	 * k-diff pairs in the array. A k-diff pair is an integer pair (nums[i],
	 * nums[j]), where the following are true: 0 <= i, j < nums.length i != j
	 * |nums[i] - nums[j]| == k Notice that |val| denotes the absolute value of val.
	 * 
	 * Link: https://leetcode.com/problems/k-diff-pairs-in-an-array/
	 * 
	 */
	public int findPairs(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
		}
		int count = 0;
		for (int key : map.keySet()) {
			if (k == 0) {
				if (map.get(key) > 1) {
					count++;
				}
			} else {
				if (map.containsKey(key + k)) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * Tic-tac-toe is played by two players A and B on a 3 x 3 grid. Here are the
	 * rules of Tic-Tac-Toe: Players take turns placing characters into empty
	 * squares (" "). The first player A always places "X" characters, while the
	 * second player B always places "O" characters. "X" and "O" characters are
	 * always placed into empty squares, never on filled ones. The game ends when
	 * there are 3 of the same (non-empty) character filling any row, column, or
	 * diagonal. The game also ends if all squares are non-empty. No more moves can
	 * be played if the game is over. Given an array moves where each element is
	 * another array of size 2 corresponding to the row and column of the grid where
	 * they mark their respective character in the order in which A and B play.
	 * Return the winner of the game if it exists (A or B), in case the game ends in
	 * a draw return "Draw", if there are still movements to play return "Pending".
	 * You can assume that moves is valid (It follows the rules of Tic-Tac-Toe), the
	 * grid is initially empty and A will play first.
	 * 
	 * Link: https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game/
	 * 
	 */
	public String tictactoe(int[][] moves) {
		int[] aRow = new int[3];
		int[] aCol = new int[3];
		int[] bRow = new int[3];
		int[] bCol = new int[3];
		int aDiag1 = 0;
		int aDiag2 = 0;
		int bDiag1 = 0;
		int bDiag2 = 0;
		int row = -1;
		int col = -1;
		for (int i = 0; i < moves.length; i++) {
			row = moves[i][0];
			col = moves[i][1];
			if (i % 2 == 0) {
				aRow[row]++;
				aCol[col]++;
				if (row == col) {
					aDiag1++;
				}
				if (row + col == 2) {
					aDiag2++;
				}
				if (aRow[row] == 3 || aCol[col] == 3 || aDiag1 == 3 || aDiag2 == 3) {
					return "A";
				}
			} else {
				bRow[row]++;
				bCol[col]++;
				if (row == col) {
					bDiag1++;
				}
				if (row + col == 2) {
					bDiag2++;
				}
				if (bRow[row] == 3 || bCol[col] == 3 || bDiag1 == 3 || bDiag2 == 3) {
					return "B";
				}
			}
		}

		if (moves.length == 9) {
			return "Draw";
		}
		return "Pending";
	}

	/**
	 * Given an integer k, return the minimum number of Fibonacci numbers whose sum
	 * is equal to k. The same Fibonacci number can be used multiple times. The
	 * Fibonacci numbers are defined as: F1 = 1 F2 = 1 Fn = Fn-1 + Fn-2 for n > 2.
	 * It is guaranteed that for the given constraints we can always find such
	 * Fibonacci numbers that sum up to k.
	 * 
	 * Link:
	 * https://leetcode.com/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k/
	 * 
	 */
	public int findMinFibonacciNumbers(int k) {
		if (k < 2)
			return k;
		int a = 1, b = 1;
		while (b <= k) {
			b += a;
			a = b - a;
		}
		return 1 + findMinFibonacciNumbers(k - a);
	}

	/**
	 * Given an array nums of n integers where n > 1, return an array output such
	 * that output[i] is equal to the product of all the elements of nums except
	 * nums[i].
	 * 
	 * Link: https://leetcode.com/problems/product-of-array-except-self/
	 * 
	 */
	public int[] productExceptSelf(int[] nums) {
		int n = nums.length;
		int[] product = new int[n];
		product[0] = 1;
		for (int i = 1; i < n; i++) {
			product[i] = product[i - 1] * nums[i - 1];
		}
		int right = 1;
		for (int i = n - 1; i >= 0; i--) {
			product[i] *= right;
			right *= nums[i];
		}
		return product;
	}

	/**
	 * There are n soldiers standing in a line. Each soldier is assigned a unique
	 * rating value. You have to form a team of 3 soldiers amongst them under the
	 * following rules: Choose 3 soldiers with index (i, j, k) with rating
	 * (rating[i], rating[j], rating[k]). A team is valid if: (rating[i] < rating[j]
	 * < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k <
	 * n). Return the number of teams you can form given the conditions. (soldiers
	 * can be part of multiple teams).
	 * 
	 * Link: https://leetcode.com/problems/count-number-of-teams/
	 * 
	 */
	public int numTeams(int[] rating) {
		if (rating.length < 3) {
			return 0;
		}
		int[] less = new int[2];
		int[] great = new int[2];
		int count = 0;
		for (int i = 0; i < rating.length; i++) {
			less[0] = 0;
			less[1] = 0;
			great[0] = 0;
			great[1] = 0;
			for (int j = 0; j < rating.length; j++) {
				if (rating[j] < rating[i]) {
					less[j > i ? 1 : 0]++;
				} else if (rating[j] > rating[i]) {
					great[j > i ? 1 : 0]++;
				}
			}
			count += ((less[0] * great[1]) + (less[1] * great[0]));
		}
		return count;
	}

	/**
	 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements
	 * appear twice and others appear once. Find all the elements that appear twice
	 * in this array. Could you do it without extra space and in O(n) runtime?
	 * 
	 * Link: https://leetcode.com/problems/find-all-duplicates-in-an-array/
	 * 
	 */
	public List<Integer> findDuplicates(int[] nums) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			int index = Math.abs(nums[i]) - 1;
			if (nums[index] < 0) {
				list.add(Math.abs(nums[i]));
			}
			nums[index] = -nums[index];
		}
		return list;
	}

	/**
	 * You are given an integer array nums sorted in ascending order, and an integer
	 * target. Suppose that nums is rotated at some pivot unknown to you beforehand
	 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]). If target is found in
	 * the array return its index, otherwise, return -1.
	 * 
	 * Link: https://leetcode.com/problems/search-in-rotated-sorted-array/
	 * 
	 */
	public int search(int[] nums, int target) {
		int start = 0;
		int end = nums.length - 1;
		while (start < end) {
			if (nums[start] < nums[end]) {
				break;
			}
			int mid = (start + end) / 2;
			if (nums[start] <= nums[mid]) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		int minimum = start;
		start = 0;
		end = nums.length - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			int realmid = (mid + minimum) % nums.length;
			if (nums[realmid] == target) {
				return realmid;
			}
			if (nums[realmid] < target) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return -1;
	}

	/**
	 * Given an array of integers nums and an integer k, return the total number of
	 * continuous subarrays whose sum equals to k.
	 * 
	 * Link: https://leetcode.com/problems/subarray-sum-equals-k/submissions/
	 * 
	 */
	public int subarraySum(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		int sum = 0;
		int count = 0;
		map.put(0, 1);
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (map.containsKey(sum - k)) {
				count += map.get(sum - k);
			}
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}
		return count;
	}

	/**
	 * Given a non-empty array of integers, return the k most frequent elements.
	 * 
	 * Link: https://leetcode.com/problems/top-k-frequent-elements/
	 * 
	 */
	@SuppressWarnings("unchecked")
	public int[] topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
		}
		List<Integer>[] list = new List[nums.length + 1];
		for (Integer key : map.keySet()) {
			int freq = map.get(key);
			if (list[freq] == null) {
				list[freq] = new ArrayList<>();
			}
			list[freq].add(key);
		}
		List<Integer> resultList = new ArrayList<>();
		for (int i = nums.length; i > 0; i--) {
			if (list[i] != null)
				resultList.addAll(list[i]);
		}
		int[] result = new int[k];
		for (int i = 0; i < k; i++) {
			result[i] = resultList.get(i);
		}
		return result;
	}

	/**
	 * Given a non negative integer number num. For every numbers i in the range 0 ≤
	 * i ≤ num calculate the number of 1's in their binary representation and return
	 * them as an array.
	 * 
	 * Link: https://leetcode.com/problems/counting-bits/submissions/
	 * 
	 */
	public int[] countBits(int num) {
		int[] result = new int[num + 1];
		result[0] = 0;
		for (int i = 1; i <= num; i++) {
			result[i] = result[i / 2] + (i & 1);
		}
		return result;
	}

	/**
	 * Given a list of daily temperatures T, return a list such that, for each day
	 * in the input, tells you how many days you would have to wait until a warmer
	 * temperature. If there is no future day for which this is possible, put 0
	 * instead. For example, given the list of temperatures T = [73, 74, 75, 71, 69,
	 * 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0]. Note: The length
	 * of temperatures will be in the range [1, 30000]. Each temperature will be an
	 * integer in the range [30, 100].
	 * 
	 * Link: https://leetcode.com/problems/daily-temperatures/submissions/
	 * 
	 */
	public int[] dailyTemperatures(int[] temp) {
		if (temp.length == 1) {
			return new int[1];
		}
		int[] result = new int[temp.length];
		int[] stack = new int[temp.length];
		int top = -1;
		for (int i = 0; i < temp.length; i++) {
			while (top > -1 && temp[i] > temp[stack[top]]) {
				int index = stack[top--];
				result[index] = i - index;
			}
			stack[++top] = i;
		}
		return result;
	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	public int nextGreaterElement(int n) {
		char[] arr = String.valueOf(n).toCharArray();
		int i = arr.length - 2;
		while (i >= 0 && arr[i] >= arr[i + 1]) {
			i--;
		}
		if (i < 0) {
			return -1;
		}
		int high = arr.length - 1;
		while (high > 0 && arr[high] <= arr[i]) {
			high--;
		}
		if (high < 0) {
			return -1;
		}
		swap(arr, i, high);
		int low = i + 1;
		high = arr.length - 1;
		while (low < high) {
			swap(arr, low, high);
			low++;
			high--;
		}
		try {
			return Integer.valueOf(String.valueOf(arr));
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	private void swap(char[] arr, int i, int j) {
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	/**
	 * Given a circular array (the next element of the last element is the first
	 * element of the array), print the Next Greater Number for every element. The
	 * Next Greater Number of a number x is the first greater number to its
	 * traversing-order next in the array, which means you could search circularly
	 * to find its next greater number. If it doesn't exist, output -1 for this
	 * number.
	 *
	 * Link: https://leetcode.com/problems/next-greater-element-ii/
	 *
	 */
	public int[] nextGreaterElements(int[] nums) {
		if (nums.length == 1) {
			return new int[] { -1 };
		}
		int n = nums.length;
		int[] result = new int[n];
		Arrays.fill(result, -1);
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < 2 * n; i++) {
			while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
				result[stack.pop()] = nums[i % n];
			}
			stack.push(i % n);
		}
		return result;
	}

	/**
	 * Suppose you have a random list of people standing in a queue. Each person is
	 * described by a pair of integers (h, k), where h is the height of the person
	 * and k is the number of people in front of this person who have a height
	 * greater than or equal to h. Write an algorithm to reconstruct the queue.
	 * 
	 * Link: https://leetcode.com/problems/queue-reconstruction-by-height/
	 * 
	 */
	public int[][] reconstructQueue(int[][] people) {
		PriorityQueue<Person> queue = new PriorityQueue<>((p1, p2) -> {
			if (p1.height != p2.height) {
				return p2.height - p1.height;
			} else {
				return p1.nums - p2.nums;
			}
		});
		for (int i = 0; i < people.length; i++) {
			queue.add(new Person(people[i][0], people[i][1]));
		}
		List<Person> list = new LinkedList<>();
		while (!queue.isEmpty()) {
			Person p = queue.poll();
			if (p.nums < list.size()) {
				list.add(p.nums, p);
			} else {
				list.add(p);
			}
		}
		int k = 0;
		for (Person p : list) {
			people[k][0] = p.height;
			people[k][1] = p.nums;
			k++;
		}
		return people;
	}

	/**
	 * Given an n x n binary grid, in one step you can choose two adjacent rows of
	 * the grid and swap them. A grid is said to be valid if all the cells above the
	 * main diagonal are zeros. Return the minimum number of steps needed to make
	 * the grid valid, or -1 if the grid cannot be valid. The main diagonal of a
	 * grid is the diagonal that starts at cell (1, 1) and ends at cell (n, n).
	 * 
	 * Link: https://leetcode.com/problems/minimum-swaps-to-arrange-a-binary-grid/
	 * 
	 */
	public int minSwaps(int[][] grid) {
		int[] right = new int[grid.length];
		for (int i = 0; i < grid.length; i++) {
			for (int j = grid[0].length - 1; j >= 0; j--) {
				if (grid[i][j] == 1) {
					right[i] = j;
					break;
				}
			}
		}
		int count = 0;
		int length = right.length;
		for (int i = 0; i < length; i++) {
			if (right[i] <= i) {
				continue;
			}
			int j = i;
			while (j < length && right[j] > i) {
				j++;
			}
			if (j == length) {
				return -1;
			}
			while (j > i) {
				int temp = right[j];
				right[j] = right[j - 1];
				right[j - 1] = temp;
				j--;
				count++;
			}
		}
		return count;
	}

	/**
	 * You are given an n x n 2D matrix representing an image, rotate the image by
	 * 90 degrees (clockwise). You have to rotate the image in-place, which means
	 * you have to modify the input 2D matrix directly. DO NOT allocate another 2D
	 * matrix and do the rotation.
	 * 
	 * Link: https://leetcode.com/problems/rotate-image/
	 * 
	 */
	public void rotate(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = i + 1; j < matrix.length; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
		for (int i = 0; i < matrix.length; i++) {
			int j = 0;
			int k = matrix.length - 1;
			while (j < k) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[i][k];
				matrix[i][k] = temp;
				j++;
				k--;
			}
		}
	}

	/**
	 * Write an efficient algorithm that searches for a value in an m x n matrix.
	 * This matrix has the following properties: Integers in each row are sorted in
	 * ascending from left to right. Integers in each column are sorted in ascending
	 * from top to bottom.
	 * 
	 * Link: https://leetcode.com/problems/search-a-2d-matrix-ii/
	 * 
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
			return false;
		}
		int col = matrix[0].length - 1;
		int row = 0;
		while (col >= 0 && row <= matrix.length - 1) {
			if (target == matrix[row][col]) {
				return true;
			} else if (target < matrix[row][col]) {
				col--;
			} else if (target > matrix[row][col]) {
				row++;
			}
		}
		return false;
	}

	/**
	 * Given an array of intervals where intervals[i] = [starti, endi], merge all
	 * overlapping intervals, and return an array of the non-overlapping intervals
	 * that cover all the intervals in the input.
	 * 
	 * Link: https://leetcode.com/problems/merge-intervals/
	 * 
	 */
	public int[][] merge(int[][] intervals) {
		if (intervals.length <= 1) {
			return intervals;
		}
		Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));
		List<int[]> result = new ArrayList<>();
		result.add(new int[] { intervals[0][0], intervals[0][1] });
		for (int i = 1; i < intervals.length; i++) {
			int[] current = result.get(result.size() - 1);
			if (current[1] >= intervals[i][0]) {
				current[1] = Math.max(current[1], intervals[i][1]);
			} else {
				result.add(new int[] { intervals[i][0], intervals[i][1] });
			}
		}
		return result.toArray(new int[result.size()][]);
	}

	/**
	 * Given an array nums of n integers, are there elements a, b, c in nums such
	 * that a + b + c = 0? Find all unique triplets in the array which gives the sum
	 * of zero. Notice that the solution set must not contain duplicate triplets.
	 * 
	 * Link: https://leetcode.com/problems/3sum/
	 * 
	 */
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		int low = 0;
		int high = nums.length - 1;
		int sum = -1;
		for (int i = 0; i < nums.length - 2; i++) {
			if (i == 0 || (nums[i] != nums[i - 1])) {
				low = i + 1;
				high = nums.length - 1;
				while (low < high) {
					sum = nums[i] + nums[low] + nums[high];
					if (sum == 0) {
						List<Integer> curr = new ArrayList<>();
						list.add(curr);
						curr.add(nums[i]);
						curr.add(nums[low]);
						curr.add(nums[high]);
						while (low < nums.length - 1 && nums[low] == nums[low + 1]) {
							low++;
						}
						while (high > 0 && nums[high] == nums[high - 1]) {
							high--;
						}
						low++;
						high--;
					} else if (sum < 0) {
						low++;
					} else {
						high--;
					}
				}
			}
		}
		return list;
	}

	/**
	 * Given an integer array nums, you need to find one continuous subarray that if
	 * you only sort this subarray in ascending order, then the whole array will be
	 * sorted in ascending order. Return the shortest such subarray and output its
	 * length.
	 * 
	 * Link: https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
	 * 
	 * Hint: from left find the index that is less than max of left and from right
	 * find the index that is greater than min of right.
	 * 
	 */
	public int findUnsortedSubarray(int[] nums) {
		int n = nums.length;
		int max = nums[0];
		int min = nums[n - 1];
		int low = -1;
		int high = -2;
		;
		for (int i = 1; i < n; i++) {
			max = Math.max(max, nums[i]);
			min = Math.min(min, nums[n - 1 - i]);
			if (nums[i] < max) {
				high = i;
			}
			if (nums[n - 1 - i] > min) {
				low = n - 1 - i;
			}
		}
		return high - low + 1;
	}

	/**
	 * Given a characters array tasks, representing the tasks a CPU needs to do,
	 * where each letter represents a different task. Tasks could be done in any
	 * order. Each task is done in one unit of time. For each unit of time, the CPU
	 * could complete either one task or just be idle. However, there is a
	 * non-negative integer n that represents the cooldown period between two same
	 * tasks (the same letter in the array), that is that there must be at least n
	 * units of time between any two same tasks. Return the least number of units of
	 * times that the CPU will take to finish all the given tasks.
	 * 
	 * Link: https://leetcode.com/problems/task-scheduler/
	 * 
	 */
	public int leastInterval(char[] tasks, int n) {
		if (n == 0) {
			return tasks.length;
		}
		int[] array = new int[26];
		int max = 0;
		for (int i = 0; i < tasks.length; i++) {
			array[tasks[i] - 'A']++;
			if (array[tasks[i] - 'A'] > max) {
				max = array[tasks[i] - 'A'];
			}
		}
		int maxCount = 0;
		for (int i = 0; i < 26; i++) {
			if (array[i] == max) {
				maxCount++;
			}
		}
		// System.out.println(max + " " + maxCount);
		int result = max + ((max - 1) * n) + (maxCount - 1);
		return result > tasks.length ? result : tasks.length;
	}

	/**
	 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be
	 * validated according to the following rules: Each row must contain the digits
	 * 1-9 without repetition. Each column must contain the digits 1-9 without
	 * repetition. Each of the nine 3 x 3 sub-boxes of the grid must contain the
	 * digits 1-9 without repetition. Note: A Sudoku board (partially filled) could
	 * be valid but is not necessarily solvable. Only the filled cells need to be
	 * validated according to the mentioned rules.
	 * 
	 * Link: https://leetcode.com/problems/valid-sudoku
	 * 
	 */
	public boolean isValidSudoku(char[][] board) {
		Map<Integer, Set<Character>> rows = new HashMap<>();
		Map<Integer, Set<Character>> columns = new HashMap<>();
		Map<Integer, Set<Character>> cubes = new HashMap<>();
		for (int i = 0; i < 9; i++) {
			rows.put(i, new HashSet<>());
			columns.put(i, new HashSet<>());
			cubes.put(i, new HashSet<>());
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] != '.') {
					Set<Character> row = rows.get(i);
					Set<Character> column = columns.get(j);
					int index = 3 * (i / 3) + (j / 3);
					Set<Character> cube = cubes.get(index);
					if (row.contains(board[i][j])) {
						return false;
					}
					if (column.contains(board[i][j])) {
						return false;
					}
					if (cube.contains(board[i][j])) {
						return false;
					}
					cube.add(board[i][j]);
					row.add(board[i][j]);
					column.add(board[i][j]);
				}
			}
		}
		return true;
	}

	/**
	 * Given a n x n matrix where each of the rows and columns are sorted in
	 * ascending order, find the kth smallest element in the matrix. Note that it is
	 * the kth smallest element in the sorted order, not the kth distinct element.
	 * 
	 * Link: https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
	 * 
	 */
	public int kthSmallest(int[][] matrix, int k) {
		PriorityQueue<PointVal> queue = new PriorityQueue<>();
		for (int j = 0; j < matrix.length; j++) {
			queue.add(new PointVal(0, j, matrix[0][j]));
		}
		for (int i = 0; i < k - 1; i++) {
			PointVal tuple = queue.poll();
			if (tuple.x == matrix.length - 1) {
				continue;
			}
			queue.add(new PointVal(tuple.x + 1, tuple.y, matrix[tuple.x + 1][tuple.y]));
		}
		return queue.poll().val;
	}

	/**
	 * Given an array of integers nums sorted in ascending order, find the starting
	 * and ending position of a given target value. If target is not found in the
	 * array, return [-1, -1].
	 * 
	 * Link:
	 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
	 * 
	 */
	public int[] searchRange(int[] nums, int target) {
		int[] range = new int[] { -1, -1 };
		if (nums.length == 0) {
			return range;
		}

		int start = 0;
		int end = nums.length - 1;
		while (start < end) {
			int mid = (start + end) / 2;
			if (nums[mid] < target) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}

		if (nums[start] != target) {
			return range;
		}

		range[0] = start;
		end = nums.length - 1;
		while (start < end) {
			int mid = ((start + end) / 2) + 1;
			if (nums[mid] > target) {
				end = mid - 1;
			} else {
				start = mid;
			}
		}
		range[1] = end;
		return range;
	}

	/**
	 * A sequence of numbers is called a wiggle sequence if the differences between
	 * successive numbers strictly alternate between positive and negative. The
	 * first difference (if one exists) may be either positive or negative. A
	 * sequence with fewer than two elements is trivially a wiggle sequence. For
	 * example, [1,7,4,9,2,5] is a wiggle sequence because the differences
	 * (6,-3,5,-7,3) are alternately positive and negative. In contrast, [1,4,7,2,5]
	 * and [1,7,4,5,5] are not wiggle sequences, the first because its first two
	 * differences are positive and the second because its last difference is zero.
	 * Given a sequence of integers, return the length of the longest subsequence
	 * that is a wiggle sequence. A subsequence is obtained by deleting some number
	 * of elements (eventually, also zero) from the original sequence, leaving the
	 * remaining elements in their original order.
	 * 
	 * Link: https://leetcode.com/problems/wiggle-subsequence/
	 * 
	 */
	public int wiggleMaxLength(int[] nums) {
		if (nums.length < 2) {
			return nums.length;
		}
		int up = 1;
		int down = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > nums[i - 1]) {
				up = down + 1;
				continue;
			}
			if (nums[i] < nums[i - 1]) {
				down = up + 1;
			}
		}
		return Math.max(up, down);
	}

	/**
	 * Given an m x n matrix. If an element is 0, set its entire row and column to
	 * 0. Do it in-place.
	 * 
	 * Link: https://leetcode.com/problems/set-matrix-zeroes/
	 * 
	 */
	void setZeroes(int[][] matrix) {
		int col0 = 1;
		int rows = matrix.length;
		int cols = matrix[0].length;

		for (int i = 0; i < rows; i++) {
			if (matrix[i][0] == 0) {
				col0 = 0;
			}
			for (int j = 1; j < cols; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = matrix[0][j] = 0;
				}
			}
		}

		for (int i = rows - 1; i >= 0; i--) {
			for (int j = cols - 1; j >= 1; j--) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
			if (col0 == 0) {
				matrix[i][0] = 0;
			}
		}
	}

	/**
	 * Implement next permutation, which rearranges numbers into the
	 * lexicographically next greater permutation of numbers. If such an arrangement
	 * is not possible, it must rearrange it as the lowest possible order (i.e.,
	 * sorted in ascending order). The replacement must be in place and use only
	 * constant extra memory.
	 * 
	 * Link: https://leetcode.com/problems/next-permutation/
	 * 
	 */
	public void nextPermutation(int[] arr) {
		int i = arr.length - 2;
		while (i >= 0 && arr[i] >= arr[i + 1]) {
			i--;
		}
		if (i < 0) {
			Arrays.sort(arr);
			return;
		}
		int high = arr.length - 1;
		while (high > 0 && arr[high] <= arr[i]) {
			high--;
		}
		if (high < 0) {
			Arrays.sort(arr);
			return;
		}
		swap(arr, i, high);
		int low = i + 1;
		high = arr.length - 1;
		while (low < high) {
			swap(arr, low, high);
			low++;
			high--;
		}
	}

	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	/**
	 * Given an m x n matrix, return all elements of the matrix in spiral order.
	 * 
	 * Link: https://leetcode.com/problems/spiral-matrix/
	 * 
	 */
	public List<Integer> spiralOrder(int[][] matrix) {
		int rowBegin = 0;
		int rowEnd = matrix.length - 1;
		int columnBegin = 0;
		int columnEnd = matrix[0].length - 1;
		List<Integer> list = new ArrayList<>();
		while (rowBegin <= rowEnd && columnBegin <= columnEnd) {
			for (int j = columnBegin; j <= columnEnd; j++) {
				list.add(matrix[rowBegin][j]);
			}
			rowBegin++;

			for (int i = rowBegin; i <= rowEnd; i++) {
				list.add(matrix[i][columnEnd]);
			}
			columnEnd--;

			if (rowBegin <= rowEnd) {
				for (int j = columnEnd; j >= columnBegin; j--) {
					list.add(matrix[rowEnd][j]);
				}
				rowEnd--;
			}
			if (columnBegin <= columnEnd) {
				for (int i = rowEnd; i >= rowBegin; i--) {
					list.add(matrix[i][columnBegin]);
				}
				columnBegin++;
			}
		}
		return list;
	}

	/**
	 * Given a positive integer n, generate an n x n matrix filled with elements
	 * from 1 to n2 in spiral order.
	 * 
	 * Link: https://leetcode.com/problems/spiral-matrix-ii/
	 * 
	 */
	public int[][] generateMatrix(int n) {
		int[][] matrix = new int[n][n];
		int rowBegin = 0;
		int rowEnd = n - 1;
		int columnBegin = 0;
		int columnEnd = n - 1;
		int count = 1;
		while (rowBegin <= rowEnd && columnBegin <= columnEnd) {
			for (int j = columnBegin; j <= columnEnd; j++) {
				matrix[rowBegin][j] = count;
				count++;
			}
			rowBegin++;

			for (int i = rowBegin; i <= rowEnd; i++) {
				matrix[i][columnEnd] = count;
				count++;
			}
			columnEnd--;

			if (rowBegin <= rowEnd) {
				for (int j = columnEnd; j >= columnBegin; j--) {
					matrix[rowEnd][j] = count;
					count++;
				}
				rowEnd--;
			}

			if (columnBegin <= columnEnd) {
				for (int i = rowEnd; i >= rowBegin; i--) {
					matrix[i][columnBegin] = count;
					count++;
				}
				columnBegin++;
			}
		}
		return matrix;
	}

	/**
	 * Given an array nums of n integers and an integer target, find three integers
	 * in nums such that the sum is closest to target. Return the sum of the three
	 * integers. You may assume that each input would have exactly one solution.
	 * 
	 * Link: https://leetcode.com/problems/3sum-closest/submissions/
	 * 
	 */
	public int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int low = 0;
		int high = nums.length - 1;
		int sum = 0;
		int closest = 100000;
		for (int i = 0; i < nums.length - 2; i++) {
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			low = i + 1;
			high = nums.length - 1;
			while (low < high) {
				sum = nums[i] + nums[low] + nums[high];
				int diff = Math.abs(sum - target);
				if (diff < Math.abs(closest - target)) {
					closest = sum;
				}
				if (sum > target) {
					high--;
				} else {
					low++;
				}
			}
		}
		return closest;
	}

	/**
	 * Given an array, rotate the array to the right by k steps, where k is
	 * non-negative. Follow up: Try to come up as many solutions as you can, there
	 * are at least 3 different ways to solve this problem. Could you do it in-place
	 * with O(1) extra space?
	 * 
	 * Link: https://leetcode.com/problems/rotate-array/
	 * 
	 */
	public void rotate(int[] nums, int k) {
		k %= nums.length;
		reverse(nums, 0, nums.length - 1);
		reverse(nums, 0, k - 1);
		reverse(nums, k, nums.length - 1);
	}

	private void reverse(int[] nums, int start, int end) {
		while (start < end) {
			int temp = nums[start];
			nums[start] = nums[end];
			nums[end] = temp;
			start++;
			end--;
		}
	}

	/**
	 * A peak element is an element that is strictly greater than its neighbors.
	 * Given an integer array nums, find a peak element, and return its index. If
	 * the array contains multiple peaks, return the index to any of the peaks. You
	 * may imagine that nums[-1] = nums[n] = -∞.
	 * 
	 * Link: https://leetcode.com/problems/find-peak-element/
	 * 
	 */
	public int findPeakElement(int[] nums) {
		int low = 0;
		int high = nums.length - 1;
		while (low < high) {
			int mid1 = (low + high) / 2;
			int mid2 = mid1 + 1;
			if (nums[mid1] < nums[mid2]) {
				low = mid2;
			} else {
				high = mid1;
			}
		}
		return low;
	}

	/**
	 * Given a sorted array nums, remove the duplicates in-place such that
	 * duplicates appeared at most twice and return the new length. Do not allocate
	 * extra space for another array; you must do this by modifying the input array
	 * in-place with O(1) extra memory.
	 *
	 * Link: https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
	 */
	public int removeDuplicates(int[] nums) {
		int i = 0;
		int count = 0;
		int k = 0;
		while (i < nums.length) {
			if (i + 1 < nums.length && nums[i] == nums[i + 1]) {
				nums[k++] = nums[i++];
				nums[k++] = nums[i++];
				count += 2;
				while (i < nums.length && nums[i] == nums[i - 1]) {
					i++;
				}
			} else {
				nums[k++] = nums[i++];
				count++;
			}
		}
		return count;
	}

}
