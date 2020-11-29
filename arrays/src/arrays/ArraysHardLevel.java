package arrays;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class ArraysHardLevel {

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
	 * Given an unsorted array of integers nums, return the length of the longest
	 * consecutive elements sequence. Follow up: Could you implement the O(n)
	 * solution?
	 * 
	 * Link: https://leetcode.com/problems/longest-consecutive-sequence/
	 * 
	 */
	public int longestConsecutive(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		Set<Integer> set = new HashSet<>();
		for (int current : nums) {
			set.add(current);
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			if (!set.contains(nums[i] - 1)) {
				int y = nums[i] + 1;
				while (set.contains(y)) {
					y++;
				}
				max = Math.max(max, y - nums[i]);
			}
		}
		return max;
	}

	/**
	 * Given an unsorted integer array nums, find the smallest missing positive
	 * integer. Follow up: Could you implement an algorithm that runs in O(n) time
	 * and uses constant extra space.?
	 * 
	 * Link: https://leetcode.com/problems/first-missing-positive/
	 * 
	 */
	public int firstMissingPositive(int[] nums) {
		int next;
		for (int i = nums.length - 1; i >= 0; i--) {
			int curr = nums[i];
			while (curr > 0 && curr <= nums.length && curr != nums[curr - 1]) {
				next = nums[curr - 1];
				nums[curr - 1] = curr;
				curr = next;
			}
		}
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != i + 1) {
				return i + 1;
			}
		}
		return nums.length + 1;
	}

	/**
	 * Given a string containing just the characters '(' and ')', find the length of
	 * the longest valid (well-formed) parentheses substring.
	 * 
	 * Link: https://leetcode.com/problems/longest-valid-parentheses/
	 * 
	 */
	public int longestValidParentheses(String s) {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == '(') {
				stack.push(i);
			} else {
				if (!stack.isEmpty()) {
					if (s.charAt(stack.peek()) == '(') {
						stack.pop();
					} else {
						stack.push(i);
					}
				} else {
					stack.push(i);
				}
			}
		}
		if (stack.isEmpty()) {
			return s.length();
		}
		int low = 0;
		int high = s.length();
		int max = Integer.MIN_VALUE;
		while (!stack.isEmpty()) {
			low = stack.pop();
			if (high - low - 1 > max) {
				max = high - low - 1;
			}
			high = low;
		}
		max = Math.max(max, low);
		return max;
	}

}
