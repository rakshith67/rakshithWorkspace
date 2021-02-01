package strings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringsHardLevel {

	/**
	 * Given a non-empty string s and a dictionary wordDict containing a list of
	 * non-empty words, add spaces in s to construct a sentence where each word is a
	 * valid dictionary word. Return all such possible sentences.
	 * 
	 * Link: https://leetcode.com/problems/word-break-ii/
	 * 
	 */
	public List<String> wordBreakTwo(String s, List<String> wordDict) {
		Set<String> dict = new HashSet<>(wordDict);
		List<String> ans = new ArrayList<>();
		if (s.length() > 100)
			return ans;
		recursion(0, s, dict, new StringBuilder(), ans);
		return ans;
	}

	private void recursion(int idx, String s, Set<String> dict, StringBuilder sb, List<String> ans) {
		if (idx == s.length()) {
			ans.add(sb.toString().trim());
			return;
		}
		for (int i = idx + 1; i <= s.length(); ++i) {
			String sub = s.substring(idx, i);
			if (dict.contains(sub)) {
				sb.append(sub).append(" ");
				recursion(i, s, dict, sb, ans);
				sb.delete(sb.length() - sub.length() - 1, sb.length());
			}
		}
	}

	/**
	 * Given a list of words (without duplicates), please write a program that
	 * returns all concatenated words in the given list of words. A concatenated
	 * word is defined as a string that is comprised entirely of at least two
	 * shorter words in the given array.
	 *
	 * Link: https://leetcode.com/problems/concatenated-words/
	 *
	 */
	public List<String> findAllConcatenatedWordsInADict(String[] words) {
		Set<String> set = new HashSet<>();
		List<String> res = new ArrayList<>();
		int min = Integer.MAX_VALUE;
		for (String word : words) {
			if (word.length() == 0) {
				continue;
			}
			set.add(word);
			min = Math.min(min, word.length());
		}
		for (String word : words) {
			if (check(set, word, 0, min)) {
				res.add(word);
			}
		}
		return res;
	}

	private boolean check(Set<String> set, String word, int start, int min) {
		for (int i = start + min; i <= word.length() - min; i++) {
			if (set.contains(word.substring(start, i))
					&& (set.contains(word.substring(i)) || check(set, word, i, min))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Given an m x n board of characters and a list of strings words, return all
	 * words on the board. Each word must be constructed from letters of
	 * sequentially adjacent cells, where adjacent cells are horizontally or
	 * vertically neighboring. The same letter cell may not be used more than once
	 * in a word.
	 * 
	 * Link: https://leetcode.com/problems/word-search-ii/
	 * 
	 */
	public List<String> findWords(char[][] board, String[] words) {
		List<String> list = new ArrayList<>();
		for (String word : words) {
			boolean flag = false;
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[0].length; j++) {
					if (hasWord(board, word, i, j, 0)) {
						flag = true;
						list.add(word);
						break;
					}
				}
				if (flag) {
					break;
				}
			}
		}
		return list;
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
	 * Given two strings s and t, return the minimum window in s which will contain
	 * all the characters in t. If there is no such window in s that covers all
	 * characters in t, return the empty string "". Note that If there is such a
	 * window, it is guaranteed that there will always be only one unique minimum
	 * window in s.
	 * 
	 * Link: https://leetcode.com/problems/minimum-window-substring/
	 * 
	 */
	public String minWindow(String s, String t) {
		int[] tFreq = new int[58];
		for (int i = 0; i < t.length(); i++) {
			tFreq[t.charAt(i) - 'A']++;
		}
		int[] sFreq = new int[58];
		int low = 0;
		int high = 0;
		int min = Integer.MAX_VALUE;
		String result = null;
		while (high < s.length()) {
			sFreq[s.charAt(high) - 'A']++;
			while (high - low + 1 >= t.length() && isEqual(tFreq, sFreq)) {
				if (min > high - low + 1) {
					result = s.substring(low, high + 1);
					min = high - low + 1;
				}
				sFreq[s.charAt(low) - 'A']--;
				low++;
			}
			high++;
		}
		return result == null ? "" : result;
	}

	private boolean isEqual(int[] tFreq, int[] sFreq) {
		for (int i = 0; i < 58; i++) {
			if (tFreq[i] > sFreq[i]) {
				return false;
			}
		}
		return true;
	}
}
