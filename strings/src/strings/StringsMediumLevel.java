package strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

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
	 * Given an encoded string, return its decoded string. The encoding rule is:
	 * k[encoded_string], where the encoded_string inside the square brackets is
	 * being repeated exactly k times. Note that k is guaranteed to be a positive
	 * integer. You may assume that the input string is always valid; No extra white
	 * spaces, square brackets are well-formed, etc. Furthermore, you may assume
	 * that the original data does not contain any digits and that digits are only
	 * for those repeat numbers, k. For example, there won't be input like 3a or
	 * 2[4].
	 * 
	 * Link: https://leetcode.com/problems/decode-string/
	 * 
	 */
	public String decodeString(String s) {
		String result = "";
		Stack<Integer> count = new Stack<>();
		Stack<String> stack = new Stack<>();
		int index = 0;
		while (index < s.length()) {
			if (Character.isDigit(s.charAt(index))) {
				int num = 0;
				while (Character.isDigit(s.charAt(index))) {
					num = num * 10 + s.charAt(index) - '0';
					index++;
				}
				count.push(num);
			} else if (s.charAt(index) == '[') {
				index++;
				stack.push(result);
				result = "";
			} else if (s.charAt(index) == ']') {
				int current = count.pop();
				StringBuilder builder = new StringBuilder();
				while (current > 0) {
					builder.append(result);
					current--;
				}
				String curr = stack.pop();
				result = curr + builder.toString();
				index++;
			} else {
				result += s.charAt(index);
				index++;
			}
		}
		return result;
	}

	/**
	 * Given a string s and a non-empty string p, find all the start indices of p's
	 * anagrams in s. Strings consists of lowercase English letters only and the
	 * length of both strings s and p will not be larger than 20,100. The order of
	 * output does not matter.
	 * 
	 * Link: https://leetcode.com/problems/find-all-anagrams-in-a-string/
	 * 
	 */
	public List<Integer> findAnagrams(String s, String p) {
		int[] array = new int[26];
		int[] subArray = new int[26];
		int low = 0;
		int high = 0;
		int length = p.length();
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < length; i++) {
			subArray[p.charAt(i) - 'a']++;
		}
		while (high < s.length()) {
			if (high - low < length - 1) {
				if (subArray[s.charAt(high) - 'a'] == 0) {
					low = high + 1;
					Arrays.fill(array, 0);
				} else {
					array[s.charAt(high) - 'a']++;
				}
				high++;
				continue;
			} else if (high - low == length - 1) {
				array[s.charAt(high) - 'a']++;
				high++;
				if (Arrays.equals(array, subArray)) {
					list.add(low);
				}
			} else {
				array[s.charAt(low) - 'a']--;
				low++;
			}
		}
		return list;
	}

	/**
	 * Given a string, sort it in decreasing order based on the frequency of
	 * characters.
	 * 
	 * Link: https://leetcode.com/problems/sort-characters-by-frequency/
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String frequencySort(String s) {
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
		}
		List<Character>[] bucket = new List[s.length() + 1];
		for (char ch : map.keySet()) {
			int freq = map.get(ch);
			if (bucket[freq] == null) {
				bucket[freq] = new ArrayList<>();
			}
			bucket[freq].add(ch);
		}
		StringBuilder builder = new StringBuilder();
		for (int i = s.length(); i > 0; i--) {
			if (bucket[i] != null) {
				for (char ch : bucket[i]) {
					int j = i;
					while (j > 0) {
						builder.append(ch);
						j--;
					}
				}
			}
		}
		return builder.toString();
	}

	/**
	 * Given a string S, check if the letters can be rearranged so that two
	 * characters that are adjacent to each other are not the same. If possible,
	 * output any possible result. If not possible, return the empty string.
	 * 
	 * Link: https://leetcode.com/problems/reorganize-string/
	 * 
	 */
	public String reorganizeString(String S) {
		int[] count = new int[26];
		for (int i = 0; i < S.length(); i++) {
			count[S.charAt(i) - 'a']++;
		}
		StringBuilder builder = new StringBuilder();
		PriorityQueue<Frequency> queue = new PriorityQueue<>();
		for (int i = 0; i < 26; i++) {
			if (count[i] != 0) {
				queue.add(new Frequency((char) (97 + i), count[i]));
			}
		}
		while (queue.size() >= 2) {
			Frequency f1 = queue.poll();
			Frequency f2 = queue.poll();
			builder.append(f1.ch);
			builder.append(f2.ch);
			if (f1.count > 1) {
				f1.count--;
				queue.add(f1);
			}
			if (f2.count > 1) {
				f2.count--;
				queue.add(f2);
			}
		}
		if (queue.size() > 0) {
			Frequency f1 = queue.poll();
			if (f1.count > 1) {
				return "";
			}
			builder.append(f1.ch);
		}
		return builder.toString();
	}

	/**
	 * Given a string s that consists of only uppercase English letters, you can
	 * perform at most k operations on that string. In one operation, you can choose
	 * any character of the string and change it to any other uppercase English
	 * character. Find the length of the longest sub-string containing all repeating
	 * letters you can get after performing the above operations. Note: Both the
	 * string's length and k will not exceed 104.
	 * 
	 * Link: https://leetcode.com/problems/longest-repeating-character-replacement/
	 * 
	 * Hint: The initial step is to extend the window to its limit, that is, the
	 * longest we can get to with maximum number of modifications. Until then the
	 * variable start will remain at 0. Then as end increase, the whole substring
	 * from 0 to end will violate the rule, so we need to update start accordingly
	 * (slide the window). We move start to the right until the whole string satisfy
	 * the constraint again. Then each time we reach such situation, we update our
	 * max length.
	 * 
	 */
	public int characterReplacement(String s, int k) {
		int[] frequency = new int[26];
		int max = 0;
		int start = 0;
		int maxCount = 0;
		for (int end = 0; end < s.length(); end++) {
			frequency[s.charAt(end) - 'A']++;
			if (maxCount < frequency[s.charAt(end) - 'A']) {
				maxCount = frequency[s.charAt(end) - 'A'];
			}
			if (end - start + 1 - maxCount > k) {
				frequency[s.charAt(start) - 'A']--;
				start++;
			}
			if (end - start + 1 > max) {
				max = end - start + 1;
			}
		}
		return max;
	}

	/**
	 * Given an array of strings products and a string searchWord. We want to design
	 * a system that suggests at most three product names from products after each
	 * character of searchWord is typed. Suggested products should have common
	 * prefix with the searchWord. If there are more than three products with a
	 * common prefix return the three lexicographically minimums products. Return
	 * list of lists of the suggested products after each character of searchWord is
	 * typed.
	 * 
	 * Link: https://leetcode.com/problems/search-suggestions-system/
	 * 
	 */
	public List<List<String>> suggestedProducts(String[] products, String searchWord) {
		TrieNode node = new TrieNode();
		for (String word : products) {
			insert(node, word);
		}
		List<List<String>> list = new ArrayList<>();
		for (int i = 0; i < searchWord.length(); i++) {
			StringBuilder builder = new StringBuilder();
			List<String> current = new ArrayList<>();
			search(current, node, searchWord.substring(0, i + 1), 0, builder);
			list.add(current);
		}
		return list;
	}

	private void insert(TrieNode node, String word) {
		TrieNode current = node;
		for (int i = 0; i < word.length(); i++) {
			TrieNode children = current.child[word.charAt(i) - 'a'];
			if (children == null) {
				children = new TrieNode();
				current.child[word.charAt(i) - 'a'] = children;
			}
			current = children;
		}
		current.isWord = true;
	}

	private void search(List<String> list, TrieNode node, String prefix, int i, StringBuilder builder) {
		if (list.size() == 3) {
			return;
		}
		if (i >= prefix.length() && node.isWord) {
			list.add(builder.toString());
		}
		if (i < prefix.length()) {
			TrieNode child = node.child[prefix.charAt(i) - 'a'];
			if (child == null) {
				return;
			}
			builder.append(prefix.charAt(i));
			search(list, child, prefix, i + 1, builder);
		} else {
			for (int j = 0; j < 26; j++) {
				if (node.child[j] != null) {
					builder.append((char) (97 + j));
					search(list, node.child[j], prefix, i + 1, builder);
					builder.deleteCharAt(builder.length() - 1);
				}
			}
		}
	}

	/**
	 * Given a non-empty list of words, return the k most frequent elements. Your
	 * answer should be sorted by frequency from highest to lowest. If two words
	 * have the same frequency, then the word with the lower alphabetical order
	 * comes first.
	 * 
	 * Link: https://leetcode.com/problems/top-k-frequent-words/
	 * 
	 */
	public List<String> topKFrequent(String[] words, int k) {
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < words.length; i++) {
			map.put(words[i], map.getOrDefault(words[i], 0) + 1);
		}
		PriorityQueue<String> queue = new PriorityQueue<>((s1, s2) -> {
			if (map.getOrDefault(s2, 0) - map.getOrDefault(s1, 0) == 0) {
				return s1.compareTo(s2);
			}
			return map.getOrDefault(s2, 0) - map.getOrDefault(s1, 0);
		});
		for (String key : map.keySet()) {
			queue.add(key);
		}
		List<String> list = new ArrayList<>();
		while (!queue.isEmpty() && k > 0) {
			list.add(queue.poll());
			k--;
		}
		return list;
	}

	/**
	 * All DNA is composed of a series of nucleotides abbreviated as 'A', 'C', 'G',
	 * and 'T', for example: "ACGAATTCCG". When studying DNA, it is sometimes useful
	 * to identify repeated sequences within the DNA. Write a function to find all
	 * the 10-letter-long sequences (substrings) that occur more than once in a DNA
	 * molecule.
	 * 
	 * Link: https://leetcode.com/problems/repeated-dna-sequences/
	 * 
	 * Hint: The key point is that it is not doing hash, it is doing the exact
	 * coding of a 10-letter sequence into a 4-bytes number, which is simply not
	 * possible for any generic string, but is possible for strings in this problem
	 * because they can have only 4 differfent characters. In more detail: If two
	 * objects have same hash it means that they may or may not be equal (though two
	 * equal objects are required to have same hash). So hashing is not enough here
	 * (like calling just "AACCCCCGGG".hashCode() and storing it in the map),
	 * because there can be another (different) string with same hash and the
	 * program will output wrong result. We also cannot store the 10-letter
	 * substrings themselves because they consume too much memory and the program
	 * will exceed memory limit. So, instead of hashing or storing strings
	 * themselves the solution converts 10 letter string into 4-bytes integer (which
	 * is much smaller than string in terms of consumed memory). This would not be
	 * possible if the string could contain all 26 letters of English alphabet for
	 * example. But it is possible for our case, because there can be only 'A', 'C',
	 * 'G' and 'T' letters. So we have only 4 possible letters, and we can use as
	 * little bits as possible to store each character of our 10-letter string. We
	 * really need only 2 bits (bits, not bytes) for this. Specifically the solution
	 * uses the following coding: 0 = 00 (bits in binary number system) = 'A' 1 = 01
	 * (bits in binary number system) = 'C' 2 = 10 (bits in binary number system) =
	 * 'G' 3 = 11 (bits in binary number system) = 'T' Note that since there 10
	 * letters and each letter requires only 2 bits, we will need only 10 * 2= 20
	 * bits to code the string (which is less then size of integer in java (as well
	 * as in all othere popular languages), which is 4 bytes = 32 bits). For
	 * example, this is how "AACCTCCGGT" string will be coded: A A C C T C C G G T
	 * 00 00 01 01 11 01 01 10 10 11 = 00000101110101101011 (binary) = 23915
	 * (decimal)
	 * 
	 */
	public List<String> findRepeatedDnaSequences(String s) {
		Set<Integer> words = new HashSet<>();
		Set<Integer> doubleWords = new HashSet<>();
		List<String> rv = new ArrayList<>();
		char[] map = new char[26];
		// map['A' - 'A'] = 0;
		map['C' - 'A'] = 1;
		map['G' - 'A'] = 2;
		map['T' - 'A'] = 3;

		for (int i = 0; i < s.length() - 9; i++) {
			int v = 0;
			for (int j = i; j < i + 10; j++) {
				v <<= 2;
				v |= map[s.charAt(j) - 'A'];
			}
			if (!words.add(v) && doubleWords.add(v)) {
				rv.add(s.substring(i, i + 10));
			}
		}
		return rv;
	}

	/**
	 * Given a balanced parentheses string S, compute the score of the string based
	 * on the following rule: () has score 1 AB has score A + B, where A and B are
	 * balanced parentheses strings. (A) has score 2 * A, where A is a balanced
	 * parentheses string.
	 * 
	 * Link: https://leetcode.com/problems/score-of-parentheses/
	 * 
	 */
	public int scoreOfParentheses(String S) {
		int current = 0;
		int result = 0;
		for (int i = 0; i < S.length(); i++) {
			if (S.charAt(i) == '(') {
				current++;
			} else {
				current--;
				if (S.charAt(i - 1) == '(') {
					result += 1 << current;
				}
			}
		}
		return result;
	}

	/**
	 * Given an input string s, reverse the order of the words. A word is defined as
	 * a sequence of non-space characters. The words in s will be separated by at
	 * least one space. Return a string of the words in reverse order concatenated
	 * by a single space. Note that s may contain leading or trailing spaces or
	 * multiple spaces between two words. The returned string should only have a
	 * single space separating the words. Do not include any extra spaces.
	 * 
	 * Link: https://leetcode.com/problems/reverse-words-in-a-string/
	 * 
	 */
	public String reverseWords(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		int n = s.length(), i = n - 1;
		StringBuilder sb = new StringBuilder();
		while (i >= 0) {
			while (i >= 0 && s.charAt(i) == ' ') {
				i--;
			}
			if (i < 0) {
				break;
			}
			int nextSpace = s.lastIndexOf(' ', i);
			if (sb.length() > 0) {
				sb.append(' ');
			}
			sb.append(s.substring(nextSpace + 1, i + 1));
			i = nextSpace - 1;
		}
		return sb.toString();
	}

}

class TrieNode {
	public TrieNode[] child = new TrieNode[26];
	public boolean isWord = false;

}
