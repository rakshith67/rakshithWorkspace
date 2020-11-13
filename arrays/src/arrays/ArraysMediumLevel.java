package arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return
	 * the median of the two sorted arrays. Follow up: The overall run time
	 * complexity should be O(log (m+n)).
	 * 
	 * Link: https://leetcode.com/problems/median-of-two-sorted-arrays/
	 * 
	 */
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		if (nums1.length > nums2.length) {
			return findMedianSortedArrays(nums2, nums1);
		}
		int x = nums1.length;
		int y = nums2.length;
		int middle = (x + y + 1) / 2;

		int low = 0;
		int high = x;
		while (low <= high) {
			int partitionX = (low + high) / 2;
			int partitionY = middle - partitionX;

			int maxLeftX = partitionX == 0 ? Integer.MIN_VALUE : nums1[partitionX - 1];
			int minRightX = partitionX == x ? Integer.MAX_VALUE : nums1[partitionX];

			int maxLeftY = partitionY == 0 ? Integer.MIN_VALUE : nums2[partitionY - 1];
			int minRightY = partitionY == y ? Integer.MAX_VALUE : nums2[partitionY];

			if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
				if ((x + y) % 2 == 0) {
					return (Math.max(maxLeftX, maxLeftY) + Math.min(minRightY, minRightX)) / (double) 2;
				} else {
					return Math.max(maxLeftX, maxLeftY);
				}
			} else if (maxLeftX > minRightY) {
				high = partitionX - 1;
			} else {
				low = partitionX + 1;
			}
		}
		return -1;
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
	 * Given a collection of distinct integers, return all possible permutations.
	 * 
	 * Link: https://leetcode.com/problems/permutations/
	 * 
	 */
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		backTrackList(nums, list, new ArrayList<>(), new boolean[nums.length]);
		return list;
	}

	private void backTrackList(int[] nums, List<List<Integer>> list, List<Integer> current, boolean[] used) {
		if (current.size() == nums.length) {
			list.add(new ArrayList<>(current));
		}
		for (int i = 0; i < nums.length; i++) {
			if (used[i]) {
				continue;
			}
			used[i] = true;
			current.add(nums[i]);
			backTrackList(nums, list, current, used);
			used[i] = false;
			current.remove(current.size() - 1);
		}
	}

	/**
	 * Given a set of distinct integers, nums, return all possible subsets (the
	 * power set). Note: The solution set must not contain duplicate subsets.
	 * 
	 * Link: https://leetcode.com/problems/subsets/
	 * 
	 */
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		backTrackList(nums, list, new ArrayList<>(), 0);
		return list;
	}

	private void backTrackList(int[] nums, List<List<Integer>> list, List<Integer> current, int start) {
		list.add(new ArrayList<>(current));
		for (int i = start; i < nums.length; i++) {
			current.add(nums[i]);
			backTrackList(nums, list, current, i + 1);
			current.remove(current.size() - 1);
		}
	}

}
