package strings;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class StringsEasyLevel {

	/**
	 * Given two strings S and T, return if they are equal when both are typed into
	 * empty text editors. # means a backspace character. Note that after
	 * backspacing an empty text, the text will continue empty.
	 * 
	 * Link: https://leetcode.com/problems/backspace-string-compare/
	 * 
	 */
	public boolean backspaceCompare(String S, String T) {
		int i = S.length() - 1, j = T.length() - 1, back;
		while (true) {
			back = 0;
			while (i >= 0 && (back > 0 || S.charAt(i) == '#')) {
				back += S.charAt(i) == '#' ? 1 : -1;
				i--;
			}
			back = 0;
			while (j >= 0 && (back > 0 || T.charAt(j) == '#')) {
				back += T.charAt(j) == '#' ? 1 : -1;
				j--;
			}
			if (i >= 0 && j >= 0 && S.charAt(i) == T.charAt(j)) {
				i--;
				j--;
			} else {
				break;
			}
		}
		return i == -1 && j == -1;
	}

	/**
	 * Given a paragraph and a list of banned words, return the most frequent word
	 * that is not in the list of banned words. It is guaranteed there is at least
	 * one word that isn't banned, and that the answer is unique. Words in the list
	 * of banned words are given in lowercase, and free of punctuation. Words in the
	 * paragraph are not case sensitive. The answer is in lowercase.
	 * 
	 * Link: https://leetcode.com/problems/most-common-word/
	 * 
	 */
	public String mostCommonWord(String paragraph, String[] banned) {
		List<String> words = new ArrayList<>();
		int start = 0;
		for (int i = 0; i < paragraph.length() && start < paragraph.length(); i++) {
			if (start < i && isEnd(paragraph.charAt(i))) {
				words.add(paragraph.substring(start, i).toLowerCase());
				i++;
				while (i < paragraph.length() && isEnd(paragraph.charAt(i))) {
					i++;
				}
				start = i;
			}
		}
		if (start < paragraph.length()) {
			words.add(paragraph.substring(start, paragraph.length()).toLowerCase());
		}
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < words.size(); i++) {
			if (words.get(i) != "") {
				map.put(words.get(i), map.getOrDefault(words.get(i), 0) + 1);
			}
		}
		int max = Integer.MIN_VALUE;
		String result = null;
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			// System.out.println(entry.getKey() + " " + entry.getValue());
			if (!find(banned, entry.getKey())) {
				if (entry.getValue() > max) {
					max = entry.getValue();
					result = entry.getKey();
				}
			}
		}
		return result;
	}

	private boolean isEnd(char word) {
		if (word == '!' || word == '?' || word == '\'' || word == ',' || word == ';' || word == '.' || word == ' ') {
			return true;
		}
		return false;
	}

	private boolean find(String[] banned, String word) {
		for (int i = 0; i < banned.length; i++) {
			if (banned[i].equalsIgnoreCase(word)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * As part of Day 1 challenge, your manager has created a word game for you and
	 * your teammates to play. The word game begins with your manager writing a
	 * string, and a number K on the board. You and your teammates must find a
	 * substring of size K such that there is exactly one character that is repeated
	 * once. In other words, there should be K - 1 distinct characters in the
	 * substring. Write an algorithm to help your teammates find the correct answer.
	 * If no such substring can be found, return an empty list; If multiple such
	 * substrings exit, return all of them, without repetitions. The order in which
	 * the substrings are returned does not matter.
	 *
	 * Link: https://leetcode.com/discuss/interview-question/877624/
	 *
	 */
	public String getCountDistinctKChars(String word, int k) {
		int start = 0;
		int end = 0;
		String result = null;
		Map<Character, Integer> map = new HashMap<>();
		while (end < word.length()) {
			if (end - start < k) {
				map.put(word.charAt(end), map.getOrDefault(word.charAt(end), 0) + 1);
				k++;
			}
			if (map.size() == k - 1) {
				result = word.substring(start, end);
				break;
			} else {
				if (map.get(word.charAt(start)) == 1) {
					map.remove(word.charAt(start));
				} else {
					map.put(word.charAt(start), map.get(word.charAt(start)) - 1);
				}
				start++;
			}
		}
		return result;
	}

	public List<Integer> numberOfItems(String s, List<Integer> start, List<Integer> end) {
		NavigableMap<Integer, Integer> treeMap = new TreeMap<>();

		int countSoFar = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '|') {
				treeMap.put(i, countSoFar);
			} else {
				countSoFar++;
			}
		}
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < start.size(); i++) {
			list.add(number(treeMap, start.get(i) - 1, end.get(i) - 1));
		}
		return list;
	}

	private int number(NavigableMap<Integer, Integer> treemap, int start, int end) {
		if (treemap.floorEntry(end) == null || treemap.ceilingEntry(start) == null)
			return 0;
		int i = treemap.floorEntry(end).getValue() - treemap.ceilingEntry(start).getValue();
		return Math.max(i, 0);
	}

	public int winPrize(String[][] codeList, String[] shoppingCart) {
		if (codeList == null || codeList.length == 0) {
			return 1;
		}
		if (shoppingCart == null || shoppingCart.length == 0) {
			return 0;
		}
		int i = 0;
		int j = 0;
		while (i < codeList.length && j + codeList[i].length < shoppingCart.length) {
			boolean match = true;
			int k = 0;
			while (k < codeList[i].length && j + k < shoppingCart.length) {
				if (!codeList[i][k].equals("anything") && codeList[i][k].equals(shoppingCart[j + k])) {
					match = false;
					break;
				}
				k++;
			}
			if (match) {
				j += codeList[i].length;
				i++;
			} else {
				j++;
			}
		}
		return codeList.length == i ? 1 : 0;
	}

	/**
	 * Given an array of strings, return all groups of strings that are anagrams.
	 * Represent a group by a list of integers representing the index in the
	 * original list. Look at the sample case for clarification. Anagram : a word,
	 * phrase, or name formed by rearranging the letters of another, such as 'spar',
	 * formed from 'rasp' Note: All inputs will be in lower-case.
	 * 
	 * Link: https://www.interviewbit.com/problems/anagrams/
	 * 
	 */
	public ArrayList<ArrayList<Integer>> anagrams(final List<String> list) {
		HashMap<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
		for (int i = 0; i < list.size(); i++) {
			char[] t = list.get(i).toCharArray();
			Arrays.sort(t);
			String token = new String(t);
			ArrayList<Integer> val = null;
			if (map.get(token) == null) {
				val = new ArrayList<Integer>();
			} else {
				val = map.get(token);
			}
			val.add(i + 1);
			map.put(token, val);
		}
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		for (String l : map.keySet()) {
			ArrayList<Integer> val = map.get(l);
			result.add(val);
		}
		return result;
	}

	/**
	 * Link: https://www.hackerrank.com/challenges/balanced-brackets/problem
	 * 
	 */
	String isBalanced(String s) {
		Deque<Character> deque = new ArrayDeque<>();
		if (s.isEmpty()) {
			return "YES";
		}
		for (int i = 0; i < s.length(); i++) {
			char current = s.charAt(i);
			if (current == '[' || current == '{' || current == '(') {
				deque.addFirst(current);
			} else {
				if (deque.isEmpty()) {
					return "NO";
				}
				char removed = deque.removeFirst();
				if (removed == '{' && current != '}') {
					return "NO";
				}
				if (removed == '(' && current != ')') {
					return "NO";
				}
				if (removed == '[' && current != ']') {
					return "NO";
				}
			}
		}
		if (deque.isEmpty()) {
			return "YES";
		} else {
			return "NO";
		}
	}

	/**
	 * Given an array A of strings made only from lowercase letters, return a list
	 * of all characters that show up in all strings within the list (including
	 * duplicates). For example, if a character occurs 3 times in all strings but
	 * not 4 times, you need to include that character three times in the final
	 * answer. You may return the answer in any order.
	 * 
	 * Link: https://leetcode.com/problems/find-common-characters/
	 * 
	 */
	public List<String> commonChars(String[] A) {
		int[] array = new int[26];
		Arrays.fill(array, Integer.MAX_VALUE);
		for (String str : A) {
			int[] count = new int[26];
			str.chars().forEach(c -> count[c - 'a']++);
			for (int i = 0; i < 26; i++) {
				array[i] = Math.min(count[i], array[i]);
			}
		}
		List<String> result = new ArrayList<>();
		for (char c = 'a'; c <= 'z'; c++) {
			while (array[c - 'a']-- > 0) {
				result.add("" + c);
			}
		}
		return result;
	}

	/**
	 * You are given an array of strings words and a string chars. A string is good
	 * if it can be formed by characters from chars (each character can only be used
	 * once). Return the sum of lengths of all good strings in words.
	 * 
	 * Link:
	 * https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/
	 * 
	 */
	public int countCharacters(String[] words, String chars) {
		int length = 0;
		int[] array = new int[26];
		for (int i = 0; i < chars.length(); i++) {
			array[chars.charAt(i) - 'a']++;
		}
		for (String string : words) {
			int[] count = new int[26];
			boolean isValid = true;
			for (int i = 0; i < string.length(); i++) {
				int j = string.charAt(i) - 'a';
				count[j]++;
				if (count[j] > array[j]) {
					isValid = false;
					break;
				}
			}
			if (isValid) {
				length += string.length();
			}
		}
		return length;
	}

	/**
	 * Hi there. This is Rakshit 8 Hi, there., This, is, Rakshit
	 * 
	 * @param input
	 * @param len
	 * @return
	 */
	String[] split(String input, int len) {
		String[] splitString = input.split(" ");
		List<String> result = new ArrayList<>();
		for (int i = 0; i < splitString.length; i++) {
			int currentWordLen = splitString.length;
			if (currentWordLen > len) {
				result.add(splitString[i].substring(0, len));
			} else if (currentWordLen == len) {
				result.add(splitString[i]);
			} else {
				if (i + 1 < splitString.length) {
					if (splitString[i + 1].length() + currentWordLen < len) {
						result.add(splitString[i] + " " + splitString[i + 1]);
						i++;
					} else {
						result.add(splitString[i]);
					}
				}
			}
		}
		return (String[]) result.toArray();
	}

	/**
	 * Given a string, find the first non-repeating character in it and return its
	 * index. If it doesn't exist, return -1.
	 * 
	 * Link: https://leetcode.com/problems/first-unique-character-in-a-string/
	 * 
	 */
	public int firstUniqChar(String s) {
		int ans = Integer.MAX_VALUE;
		for (char c = 'a'; c <= 'z'; c++) {
			int left = s.indexOf(c);
			if (left != -1 && left == s.lastIndexOf(c)) {
				ans = Math.min(left, ans);
			}

		}
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	/**
	 * Given a non-empty string s, you may delete at most one character. Judge
	 * whether you can make it a palindrome.
	 * 
	 * Link: https://leetcode.com/problems/valid-palindrome-ii/
	 * 
	 */
	public boolean validPalindrome(String s) {
		int l = 0;
		int r = s.length() - 1;
		while (l <= r) {
			if (s.charAt(l) == s.charAt(r)) {
				l++;
				r--;
			} else {
				return isPalindrome(s, l, r - 1) || isPalindrome(s, l + 1, r);
			}
		}
		return true;
	}

	private boolean isPalindrome(String str, int s, int t) {
		while (s <= t) {
			if (str.charAt(s) != str.charAt(t)) {
				return false;
			}
			s++;
			t--;
		}
		return true;
	}

	/**
	 * Given a column title as appear in an Excel sheet, return its corresponding
	 * column number.
	 * 
	 * Link: https://leetcode.com/problems/excel-sheet-column-number/
	 * 
	 */
	public int titleToNumber(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			int val = s.charAt(i) - 'A' + 1;
			count = count * 26 + val;
		}
		return count;
	}
}
