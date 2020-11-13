package strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringsMediumLevel {

	/**
	 * Given two equal-size strings s and t. In one step you can choose any
	 * character of t and replace it with another character. Return the minimum
	 * number of steps to make t an anagram of s. An Anagram of a string is a string
	 * that contains the same characters with a different (or the same) ordering.
	 * 
	 * Link:
	 * https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram/
	 * 
	 */
	public int minSteps(String s, String t) {
		int[] frequency = new int[26];
		for (int i = 0; i < s.length(); i++) {
			frequency[s.charAt(i) - 'a']++;
			frequency[t.charAt(i) - 'a']--;
		}
		int sum = 0;
		for (int i = 0; i < 26; i++) {
			sum += Math.abs(frequency[i]);
		}
		return sum / 2;
	}

	/*
	 * You have a list of words and a pattern, and you want to know which words in
	 * words matches the pattern. A word matches the pattern if there exists a
	 * permutation of letters p so that after replacing every letter x in the
	 * pattern with p(x), we get the desired word. (Recall that a permutation of
	 * letters is a bijection from letters to letters: every letter maps to another
	 * letter, and no two letters map to the same letter.) Return a list of the
	 * words in words that match the given pattern. You may return the answer in any
	 * order.
	 * 
	 * Link: https://leetcode.com/problems/find-and-replace-pattern/
	 * 
	 */
	public List<String> findAndReplacePattern(String[] words, String pattern) {
		int[] pat = function(pattern);
		List<String> result = new ArrayList<>();
		for (int i = 0; i < words.length; i++) {
			if (Arrays.equals(function(words[i]), pat)) {
				result.add(words[i]);
			}
		}
		return result;
	}

	private int[] function(String word) {
		Map<Character, Integer> map = new HashMap<>();
		int[] result = new int[word.length()];
		for (int i = 0; i < word.length(); i++) {
			map.putIfAbsent(word.charAt(i), map.size());
			result[i] = map.get(word.charAt(i));
		}
		return result;
	}

	/**
	 * Given an array of strings strs, group the anagrams together. You can return
	 * the answer in any order. An Anagram is a word or phrase formed by rearranging
	 * the letters of a different word or phrase, typically using all the original
	 * letters exactly once.
	 * 
	 * Link: https://leetcode.com/problems/group-anagrams/
	 * 
	 */
	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> result = new ArrayList<>();
		Map<String, List<String>> map = new HashMap<>();
		for (int i = 0; i < strs.length; i++) {
			char[] charArr = strs[i].toCharArray();
			Arrays.sort(charArr);
			String x = String.valueOf(charArr);
			if (map.get(x) == null) {
				List<String> list = new ArrayList<>();
				map.put(x, list);
				list.add(strs[i]);
			} else {
				map.get(x).add(strs[i]);
			}
		}
		for (String key : map.keySet()) {
			result.add(map.get(key));
		}
		return result;
	}

	private int count;

	/**
	 * Given a string, your task is to count how many palindromic substrings in this
	 * string. The substrings with different start indexes or end indexes are
	 * counted as different substrings even they consist of same characters.
	 * 
	 * Link: https://leetcode.com/problems/palindromic-substrings/
	 * 
	 */
	public int countSubstrings(String s) {
		if (s.length() < 2) {
			return s.length();
		}
		count = 0;
		for (int i = 0; i < s.length(); i++) {
			palindromeCount(s, i, i);
			palindromeCount(s, i, i + 1);
		}
		return count;
	}

	private void palindromeCount(String s, int start, int end) {
		while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
			count++;
			start--;
			end++;
		}
	}

	private int lo;
	private int maxLength;

	/**
	 * Given a string s, return the longest palindromic substring in s.
	 * 
	 * Link: https://leetcode.com/problems/longest-palindromic-substring/
	 * 
	 */
	public String longestPalindrome(String s) {
		if (s.length() < 2) {
			return s;
		}
		lo = 0;
		maxLength = 0;
		for (int i = 0; i < s.length(); i++) {
			populateLength(s, i, i);
			populateLength(s, i, i + 1);
		}
		return s.substring(lo, lo + maxLength);
	}

	private void populateLength(String s, int start, int end) {
		while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
			start--;
			end++;
		}
		if (end - start - 1 > maxLength) {
			lo = start + 1;
			maxLength = end - start - 1;
		}
	}

	/**
	 * A string S of lowercase English letters is given. We want to partition this
	 * string into as many parts as possible so that each letter appears in at most
	 * one part, and return a list of integers representing the size of these parts.
	 *
	 * Link: https://leetcode.com/problems/partition-labels/
	 *
	 */
	public List<Integer> partitionLabels(String string) {
		if (string == null || string.length() == 0) {
			return null;
		}
		List<Integer> list = new ArrayList<>();
		int[] array = new int[26];
		for (int i = 0; i < string.length(); i++) {
			array[string.charAt(i) - 'a'] = i;
		}
		int last = 0;
		int start = 0;
		for (int i = 0; i < string.length(); i++) {
			last = Math.max(last, array[string.charAt(i) - 'a']);
			if (last == i) {
				list.add(last - start + 1);
				start = last + 1;
			}
		}
		return list;
	}

	/**
	 * Given a string s, find the length of the longest substring without repeating
	 * characters.
	 * 
	 * Link:
	 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
	 *
	 */
	public int lengthOfLongestSubstring(String s) {
		if (s.length() < 2) {
			return s.length();
		}
		int low = 0;
		int high = 0;
		int[] freq = new int[128];
		int max = Integer.MIN_VALUE;
		while (high < s.length()) {
			while (high < s.length() && freq[s.charAt(high)] == 0) {
				freq[s.charAt(high)]++;
				high++;
			}
			if (high - low > max) {
				max = high - low;
			}
			freq[s.charAt(low)]--;
			low++;
		}
		return max;
	}

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

}
