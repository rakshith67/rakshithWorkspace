package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BacktrackingLevel {

	/**
	 * Given a 2D board and a word, find if the word exists in the grid. The word
	 * can be constructed from letters of sequentially adjacent cells, where
	 * "adjacent" cells are horizontally or vertically neighboring. The same letter
	 * cell may not be used more than once.
	 * 
	 * Link: https://leetcode.com/problems/word-search/submissions/
	 * 
	 */
	public boolean exist(char[][] board, String word) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (hasWord(board, word, i, j, 0)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean hasWord(char[][] board, String word, int i, int j, int k) {
		if (k == word.length()) {
			return true;
		}
		if (i < 0 || j < 0 || i == board.length || j == board[0].length) {
			return false;
		}
		if (board[i][j] != word.charAt(k)) {
			return false;
		}
		board[i][j] ^= 256;
		boolean exist = hasWord(board, word, i + 1, j, k + 1) || hasWord(board, word, i, j + 1, k + 1)
				|| hasWord(board, word, i - 1, j, k + 1) || hasWord(board, word, i, j - 1, k + 1);
		board[i][j] ^= 256;
		return exist;
	}

	/**
	 * Given n pairs of parentheses, write a function to generate all combinations
	 * of well-formed parentheses.
	 * 
	 * Link: https://leetcode.com/problems/generate-parentheses/
	 * 
	 */
	public List<String> generateParenthesis(int n) {
		char[] array = new char[2 * n];
		List<String> result = new ArrayList<>();
		backTrackChars(result, array, 0, 0, 0);
		return result;
	}

	private void backTrackChars(List<String> list, char[] array, int front, int back, int size) {
		if (size == array.length) {
			list.add(String.valueOf(array));
		} else {
			if (front == back) {
				if (front < array.length / 2) {
					array[size] = '(';
					backTrackChars(list, array, front + 1, back, size + 1);
				}
			} else {
				if (front < array.length / 2) {
					array[size] = '(';
					backTrackChars(list, array, front + 1, back, size + 1);
				}
				if (back < array.length / 2) {
					array[size] = ')';
					backTrackChars(list, array, front, back + 1, size + 1);
				}
			}
		}
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
			return;
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
	 * Given a collection of numbers, nums, that might contain duplicates, return
	 * all possible unique permutations in any order.
	 * 
	 * Link: https://leetcode.com/problems/permutations-ii/
	 * 
	 */
	public List<List<Integer>> permuteUnique(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> result = new ArrayList<>();
		backTrack(result, new ArrayList<Integer>(), new boolean[nums.length], nums);
		return result;
	}

	private void backTrack(List<List<Integer>> result, ArrayList<Integer> list, boolean[] used, int[] nums) {
		if (list.size() == nums.length) {
			result.add(new ArrayList<>(list));
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
				continue;
			}
			used[i] = true;
			list.add(nums[i]);
			backTrack(result, list, used, nums);
			used[i] = false;
			list.remove(list.size() - 1);
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

	/**
	 * Given a collection of integers that might contain duplicates, nums, return
	 * all possible subsets (the power set). Note: The solution set must not contain
	 * duplicate subsets.
	 * 
	 * Link: https://leetcode.com/problems/subsets-ii/
	 * 
	 */
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		backTrack(result, new ArrayList<Integer>(), nums, 0);
		return result;
	}

	private void backTrack(List<List<Integer>> result, ArrayList<Integer> list, int[] nums, int start) {
		result.add(new ArrayList<>(list));
		for (int i = start; i < nums.length; i++) {
			if (i > start && nums[i] == nums[i - 1]) {
				continue;
			}
			list.add(nums[i]);
			backTrack(result, list, nums, i + 1);
			list.remove(list.size() - 1);
		}
	}

	/**
	 * Given an array of distinct integers candidates and a target integer target,
	 * return a list of all unique combinations of candidates where the chosen
	 * numbers sum to target. You may return the combinations in any order. The same
	 * number may be chosen from candidates an unlimited number of times. Two
	 * combinations are unique if the frequency of at least one of the chosen
	 * numbers is different. It is guaranteed that the number of unique combinations
	 * that sum up to target is less than 150 combinations for the given input.
	 * 
	 * Link: https://leetcode.com/problems/combination-sum/
	 * 
	 */
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<>();
		backTrack(result, new ArrayList<Integer>(), candidates, target, 0);
		return result;
	}

	private void backTrack(List<List<Integer>> result, ArrayList<Integer> list, int[] nums, int target, int start) {
		if (target < 0) {
			return;
		}
		if (0 == target) {
			result.add(new ArrayList<>(list));
			return;
		}
		for (int i = start; i < nums.length; i++) {
			list.add(nums[i]);
			backTrack(result, list, nums, target - nums[i], i);
			list.remove(list.size() - 1);
		}
	}

	/**
	 * Given a collection of candidate numbers (candidates) and a target number
	 * (target), find all unique combinations in candidates where the candidate
	 * numbers sum to target. Each number in candidates may only be used once in the
	 * combination. Note: The solution set must not contain duplicate combinations.
	 * 
	 * Link: https://leetcode.com/problems/combination-sum-ii/
	 * 
	 */
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(candidates);
		backTrackSum(result, new ArrayList<Integer>(), candidates, target, 0);
		return result;
	}

	private void backTrackSum(List<List<Integer>> result, ArrayList<Integer> list, int[] nums, int target, int start) {
		if (target < 0) {
			return;
		}
		if (0 == target) {
			result.add(new ArrayList<>(list));
			return;
		}
		for (int i = start; i < nums.length; i++) {
			if (i > start && nums[i] == nums[i - 1]) {
				continue;
			}
			list.add(nums[i]);
			backTrack(result, list, nums, target - nums[i], i + 1);
			list.remove(list.size() - 1);
		}
	}

	/**
	 * Given a string s, partition s such that every substring of the partition is a
	 * palindrome. Return all possible palindrome partitioning of s.
	 * 
	 * Link: https://leetcode.com/problems/palindrome-partitioning/
	 * 
	 */
	public List<List<String>> partition(String s) {
		List<List<String>> result = new ArrayList<>();
		backTrackString(result, new ArrayList<String>(), s, 0);
		return result;
	}

	private void backTrackString(List<List<String>> result, ArrayList<String> list, String s, int start) {
		if (start == s.length()) {
			result.add(new ArrayList<>(list));
			return;
		}
		for (int i = start; i < s.length(); i++) {
			if (isPalindrome(s, start, i)) {
				list.add(s.substring(start, i + 1));
				backTrackString(result, list, s, i + 1);
				list.remove(list.size() - 1);
			}
		}
	}

	public boolean isPalindrome(String s, int low, int high) {
		while (low < high) {
			if (s.charAt(low++) != s.charAt(high--)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Given a string containing digits from 2-9 inclusive, return all possible
	 * letter combinations that the number could represent. Return the answer in any
	 * order. A mapping of digit to letters (just like on the telephone buttons) is
	 * given below. Note that 1 does not map to any letters.
	 * 
	 * Link: https://leetcode.com/problems/letter-combinations-of-a-phone-number/
	 * 
	 */
	public List<String> letterCombinations(String digits) {
		List<String> result = new ArrayList<>();
		if (digits.length() == 0) {
			return result;
		}
		List<String> current = new ArrayList<>();
		for (int i = 0; i < digits.length(); i++) {
			current.add(getChars(digits.charAt(i)));
		}
		backTrackList(result, current, new StringBuilder(), 0);
		return result;
	}

	private void backTrackList(List<String> result, List<String> list, StringBuilder s, int index) {
		if (index == list.size()) {
			result.add(s.toString());
			return;
		} else {
			String actual = list.get(index);
			for (int j = 0; j < actual.length(); j++) {
				s.append(actual.charAt(j));
				backTrackList(result, list, s, index + 1);
				s.deleteCharAt(index);
			}
		}
	}

	private String getChars(char number) {
		if (number == '2') {
			return "abc";
		}
		if (number == '3') {
			return "def";
		}
		if (number == '4') {
			return "ghi";
		}
		if (number == '5') {
			return "jkl";
		}
		if (number == '6') {
			return "mno";
		}
		if (number == '7') {
			return "pqrs";
		}
		if (number == '8') {
			return "tuv";
		}
		if (number == '9') {
			return "wxyz";
		}
		return "";
	}
}
