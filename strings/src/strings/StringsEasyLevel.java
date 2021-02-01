package strings;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.StringTokenizer;
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

	/**
	 * You are given the array paths, where paths[i] = [cityAi, cityBi] means there
	 * exists a direct path going from cityAi to cityBi. Return the destination
	 * city, that is, the city without any path outgoing to another city. It is
	 * guaranteed that the graph of paths forms a line without any loop, therefore,
	 * there will be exactly one destination city.
	 * 
	 * Link: https://leetcode.com/problems/destination-city/
	 * 
	 */
	public String destCity(List<List<String>> paths) {
		Map<String, String> map = new HashMap<>();
		for (List<String> list : paths) {
			map.put(list.get(0), list.get(1));
		}
		String key = paths.get(0).get(0);
		String dest = null;
		while (true) {
			dest = map.get(key);
			if (map.containsKey(dest)) {
				key = dest;
			} else {
				break;
			}
		}
		return dest;
	}

	/**
	 * Given a string s formed by digits ('0' - '9') and '#' . We want to map s to
	 * English lowercase characters as follows: Characters ('a' to 'i') are
	 * represented by ('1' to '9') respectively. Characters ('j' to 'z') are
	 * represented by ('10#' to '26#') respectively. Return the string formed after
	 * mapping. It's guaranteed that a unique mapping will always exist.
	 * 
	 * Link:
	 * https://leetcode.com/problems/decrypt-string-from-alphabet-to-integer-mapping/
	 * 
	 */
	public String freqAlphabets(String s) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '1' || s.charAt(i) == '2') {
				if (i + 1 < s.length() && s.charAt(i) >= '1' && s.charAt(i) <= '6' && i + 2 < s.length()
						&& s.charAt(i + 2) == '#') {
					int val = (s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0');
					builder.append((char) (96 + val));
					i += 2;
					continue;
				}
			}
			int val = s.charAt(i) - '0';
			builder.append((char) (96 + val));
		}

		return builder.toString();
	}

	/**
	 * There is a robot starting at position (0, 0), the origin, on a 2D plane.
	 * Given a sequence of its moves, judge if this robot ends up at (0, 0) after it
	 * completes its moves. The move sequence is represented by a string, and the
	 * character moves[i] represents its ith move. Valid moves are R (right), L
	 * (left), U (up), and D (down). If the robot returns to the origin after it
	 * finishes all of its moves, return true. Otherwise, return false. Note: The
	 * way that the robot is "facing" is irrelevant. "R" will always make the robot
	 * move to the right once, "L" will always make it move left, etc. Also, assume
	 * that the magnitude of the robot's movement is the same for each move.
	 * 
	 * Link: https://leetcode.com/problems/robot-return-to-origin/
	 * 
	 */
	public boolean judgeCircle(String moves) {
		int x = 0;
		int y = 0;
		for (char c : moves.toCharArray()) {
			if (c == 'L') {
				x--;
			} else if (c == 'R') {
				x++;
			} else if (c == 'U') {
				y++;
			} else if (c == 'D') {
				y--;
			}
		}
		return x == 0 && y == 0;
	}

	/**
	 * Given a string, you need to reverse the order of characters in each word
	 * within a sentence while still preserving whitespace and initial word order.
	 * Example 1: Input: "Let's take LeetCode contest" Output: "s'teL ekat edoCteeL
	 * tsetnoc" Note: In the string, each word is separated by single space and
	 * there will not be any extra space in the string.
	 * 
	 * Link: https://leetcode.com/problems/reverse-words-in-a-string-iii/
	 * 
	 */
	public String reverseWords(String s) {
		int start = 0;
		char[] array = s.toCharArray();
		for (int i = 0; i < array.length; i++) {
			if (s.charAt(i) == ' ') {
				reverse(array, start, i - 1);
				start = i + 1;
			}
		}
		if (start < array.length) {
			reverse(array, start, array.length - 1);
		}
		return String.valueOf(array);
	}

	private void reverse(char[] array, int start, int end) {
		while (start < end) {
			char temp = array[start];
			array[start] = array[end];
			array[end] = temp;
			start++;
			end--;
		}
	}

	/**
	 * Every email consists of a local name and a domain name, separated by the @
	 * sign. For example, in alice@leetcode.com, alice is the local name, and
	 * leetcode.com is the domain name. Besides lowercase letters, these emails may
	 * contain '.'s or '+'s. If you add periods ('.') between some characters in the
	 * local name part of an email address, mail sent there will be forwarded to the
	 * same address without dots in the local name. For example,
	 * "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email
	 * address. (Note that this rule does not apply for domain names.) If you add a
	 * plus ('+') in the local name, everything after the first plus sign will be
	 * ignored. This allows certain emails to be filtered, for example
	 * m.y+name@email.com will be forwarded to my@email.com. (Again, this rule does
	 * not apply for domain names.) It is possible to use both of these rules at the
	 * same time. Given a list of emails, we send one email to each address in the
	 * list. How many different addresses actually receive mails?
	 * 
	 * Link: https://leetcode.com/problems/unique-email-addresses/
	 * 
	 */
	public int numUniqueEmails(String[] emails) {
		Set<String> set = new HashSet<>();
		for (int i = 0; i < emails.length; i++) {
			String current = emails[i];
			int j = 0;
			StringBuilder builder = new StringBuilder();
			while (j < current.length()) {
				if (current.charAt(j) == '+') {
					while (current.charAt(j) != '@') {
						j++;
					}
					continue;
				} else if (current.charAt(j) == '@') {
					break;
				} else if (current.charAt(j) != '.') {
					builder.append(current.charAt(j));
				}
				j++;
			}
			while (j < current.length()) {
				builder.append(current.charAt(j));
				j++;
			}
			set.add(builder.toString());
		}
		return set.size();
	}

	/**
	 * You are given a phone number as a string number. number consists of digits,
	 * spaces ' ', and/or dashes '-'. You would like to reformat the phone number in
	 * a certain manner. Firstly, remove all spaces and dashes. Then, group the
	 * digits from left to right into blocks of length 3 until there are 4 or fewer
	 * digits. The final digits are then grouped as follows: 2 digits: A single
	 * block of length 2. 3 digits: A single block of length 3. 4 digits: Two blocks
	 * of length 2 each. The blocks are then joined by dashes. Notice that the
	 * reformatting process should never produce any blocks of length 1 and produce
	 * at most two blocks of length 2. Return the phone number after formatting.
	 * 
	 * Link: https://leetcode.com/problems/reformat-phone-number/
	 * 
	 */
	public String reformatNumber(String number) {
		int count = 0;
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < number.length(); i++) {
			if (number.charAt(i) >= '0' && number.charAt(i) <= '9') {
				builder.append(number.charAt(i));
				count++;
			}
		}
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < builder.length(); i++) {
			if (count > 4) {
				result.append(builder.charAt(i));
				result.append(builder.charAt(i + 1));
				result.append(builder.charAt(i + 2));
				result.append('-');
				count -= 3;
				i += 2;
			} else if (count == 4) {
				result.append(builder.charAt(i));
				result.append(builder.charAt(i + 1));
				result.append('-');
				result.append(builder.charAt(i + 2));
				result.append(builder.charAt(i + 3));
				break;
			} else if (count == 3) {
				result.append(builder.charAt(i));
				result.append(builder.charAt(i + 1));
				result.append(builder.charAt(i + 2));
				break;
			} else {
				result.append(builder.charAt(i));
				result.append(builder.charAt(i + 1));
				break;
			}
		}
		return result.toString();
	}

	/**
	 * Given a sentence that consists of some words separated by a single space, and
	 * a searchWord. You have to check if searchWord is a prefix of any word in
	 * sentence. Return the index of the word in sentence where searchWord is a
	 * prefix of this word (1-indexed). If searchWord is a prefix of more than one
	 * word, return the index of the first word (minimum index). If there is no such
	 * word return -1. A prefix of a string S is any leading contiguous substring of
	 * S.
	 * 
	 * Link:
	 * https://leetcode.com/problems/check-if-a-word-occurs-as-a-prefix-of-any-word-in-a-sentence/
	 * 
	 */
	public int isPrefixOfWord(String sentence, String searchWord) {
		int j = 0;
		int word = -1;
		for (int i = 0; i < sentence.length(); i++) {
			j = 0;
			word++;
			while (j < searchWord.length() && i < sentence.length()) {
				if (searchWord.charAt(j) != sentence.charAt(i)) {
					break;
				}
				i++;
				j++;
			}
			if (j == searchWord.length()) {
				return word + 1;
			} else {
				while (i < sentence.length() && sentence.charAt(i) != ' ') {
					i++;
				}
			}
		}
		return -1;
	}

	/**
	 * Given a string s consisting only of letters 'a' and 'b'. In a single step you
	 * can remove one palindromic subsequence from s. Return the minimum number of
	 * steps to make the given string empty. A string is a subsequence of a given
	 * string, if it is generated by deleting some characters of a given string
	 * without changing its order. A string is called palindrome if is one that
	 * reads the same backward as well as forward.
	 * 
	 * Link: https://leetcode.com/problems/remove-palindromic-subsequences/
	 * 
	 */
	public int removePalindromeSub(String s) {
		return s.isEmpty() ? 0 : (s.equals(new StringBuilder(s).reverse().toString()) ? 1 : 2);
	}

	/**
	 * Given a string text, you want to use the characters of text to form as many
	 * instances of the word "balloon" as possible. You can use each character in
	 * text at most once. Return the maximum number of instances that can be formed.
	 * 
	 * Link: https://leetcode.com/problems/maximum-number-of-balloons/
	 * 
	 */
	public int maxNumberOfBalloons(String text) {
		int[] array = new int[26];
		char[] cs = text.toCharArray();
		for (int i = 0; i < cs.length; i++) {
			array[cs[i] - 'a']++;
		}
		int min = Integer.MAX_VALUE;
		min = Math.min(min, array[0]);
		min = Math.min(min, array[1]);
		min = Math.min(min, array[13]);
		min = Math.min(min, array[11] / 2);
		min = Math.min(min, array[14] / 2);
		return min;
	}

	/**
	 * Given a string s, the power of the string is the maximum length of a
	 * non-empty substring that contains only one unique character. Return the power
	 * of the string.
	 * 
	 * Link: https://leetcode.com/problems/consecutive-characters/
	 * 
	 */
	public int maxPower(String s) {
		if (s.isEmpty()) {
			return 0;
		}
		char[] string = s.toCharArray();
		int power = 1;
		for (int i = 0; i < string.length; i++) {
			int j = i + 1;
			while (j < string.length && string[i] == string[j]) {
				j++;
			}
			power = Math.max(j - i, power);
			i = j - 1;
		}
		return power;
	}

	/**
	 * Given a string s, return the length of the longest substring between two
	 * equal characters, excluding the two characters. If there is no such substring
	 * return -1. A substring is a contiguous sequence of characters within a
	 * string.
	 * 
	 * Link:
	 * https://leetcode.com/problems/largest-substring-between-two-equal-characters/
	 * 
	 */
	public int maxLengthBetweenEqualCharacters(String s) {
		char[] string = s.toCharArray();
		int[] array = new int[26];
		Arrays.fill(array, -1);
		for (int i = 0; i < string.length; i++) {
			if (array[string[i] - 'a'] == -1) {
				array[string[i] - 'a'] = i;
			}
		}
		int max = -1;
		for (int i = string.length - 1; i >= 0; i--) {
			max = Math.max(max, i - array[string[i] - 'a'] - 1);
		}
		return max;
	}

	/**
	 * Given a string S, return the "reversed" string where all characters that are
	 * not a letter stay in the same place, and all letters reverse their positions.
	 * 
	 * Link: https://leetcode.com/problems/reverse-only-letters/
	 * 
	 */
	public String reverseOnlyLetters(String S) {
		char[] string = S.toCharArray();
		int start = 0;
		int end = string.length - 1;
		while (start < end) {
			while (start < end && !Character.isLetter(string[start])) {
				start++;
			}
			while (start < end && !Character.isLetter(string[end])) {
				end--;
			}
			char temp = string[start];
			string[start] = string[end];
			string[end] = temp;
			start++;
			end--;
		}
		return String.valueOf(string);
	}

	/**
	 * Given an integer n, add a dot (".") as the thousands separator and return it
	 * in string format.
	 * 
	 * Link: https://leetcode.com/problems/thousand-separator/
	 * 
	 */
	public String thousandSeparator(int n) {
		if (n == 0) {
			return "0";
		}
		StringBuilder builder = new StringBuilder();
		int j = 0;
		while (n > 0) {
			if (j == 3) {
				if (n > 0) {
					builder.insert(0, '.');
				}
				j = 0;
			}
			builder.insert(0, (char) (48 + (n % 10)));
			n /= 10;
			j++;
		}
		return builder.toString();
	}

	/**
	 * Give a string s, count the number of non-empty (contiguous) substrings that
	 * have the same number of 0's and 1's, and all the 0's and all the 1's in these
	 * substrings are grouped consecutively. Substrings that occur multiple times
	 * are counted the number of times they occur.
	 * 
	 * Link: https://leetcode.com/problems/count-binary-substrings/
	 * 
	 */
	public int countBinarySubstrings(String s) {
		int prev = 0;
		int result = 0;
		char[] string = s.toCharArray();
		for (int i = 0; i < string.length; i++) {
			int count = 1;
			while (i < string.length - 1 && string[i] == string[i + 1]) {
				count++;
				i++;
			}
			result += Math.min(prev, count);
			prev = count;
		}
		return result;
	}

	/**
	 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D
	 * and M. Symbol Value I 1 V 5 X 10 L 50 C 100 D 500 M 1000 For example, 2 is
	 * written as II in Roman numeral, just two one's added together. 12 is written
	 * as XII, which is simply X + II. The number 27 is written as XXVII, which is
	 * XX + V + II. Roman numerals are usually written largest to smallest from left
	 * to right. However, the numeral for four is not IIII. Instead, the number four
	 * is written as IV. Because the one is before the five we subtract it making
	 * four. The same principle applies to the number nine, which is written as IX.
	 * There are six instances where subtraction is used: I can be placed before V
	 * (5) and X (10) to make 4 and 9. X can be placed before L (50) and C (100) to
	 * make 40 and 90. C can be placed before D (500) and M (1000) to make 400 and
	 * 900. Given a roman numeral, convert it to an integer.
	 * 
	 * Link: https://leetcode.com/problems/roman-to-integer/
	 * 
	 */
	public int romanToInt(String s) {
		int value = 0;
		int length = s.length();
		for (int i = 0; i < length; i++) {
			if (s.charAt(i) == 'I') {
				if (i + 1 < length && s.charAt(i + 1) == 'X') {
					value += 9;
					i++;
				} else if (i + 1 < length && s.charAt(i + 1) == 'V') {
					value += 4;
					i++;
				} else {
					value += 1;
				}
			} else if (s.charAt(i) == 'V') {
				value += 5;
			} else if (s.charAt(i) == 'X') {
				if (i + 1 < length && s.charAt(i + 1) == 'L') {
					value += 40;
					i++;
				} else if (i + 1 < length && s.charAt(i + 1) == 'C') {
					value += 90;
					i++;
				} else {
					value += 10;
				}
			} else if (s.charAt(i) == 'L') {
				value += 50;
			} else if (s.charAt(i) == 'C') {
				if (i + 1 < length && s.charAt(i + 1) == 'D') {
					value += 400;
					i++;
				} else if (i + 1 < length && s.charAt(i + 1) == 'M') {
					value += 900;
					i++;
				} else {
					value += 100;
				}
			} else if (s.charAt(i) == 'D') {
				value += 500;
			} else {
				value += 1000;
			}
		}
		return value;
	}

	/**
	 * Given a string s of zeros and ones, return the maximum score after splitting
	 * the string into two non-empty substrings (i.e. left substring and right
	 * substring). The score after splitting a string is the number of zeros in the
	 * left substring plus the number of ones in the right substring.
	 * 
	 * Link: https://leetcode.com/problems/maximum-score-after-splitting-a-string/
	 * 
	 */
	public int maxScore(String s) {
		char[] string = s.toCharArray();
		int ones = 0;
		for (int i = 0; i < string.length; i++) {
			if (string[i] == '1') {
				ones++;
			}
		}
		int max = 0;
		int leftZeros = 0;
		int leftOnes = 0;
		for (int i = 0; i < string.length - 1; i++) {
			if (string[i] == '0') {
				leftZeros++;
			} else {
				leftOnes++;
			}
			max = Math.max(max, leftZeros + ones - leftOnes);
		}
		return max;
	}

	/**
	 * Given alphanumeric string s. (Alphanumeric string is a string consisting of
	 * lowercase English letters and digits). You have to find a permutation of the
	 * string where no letter is followed by another letter and no digit is followed
	 * by another digit. That is, no two adjacent characters have the same type.
	 * Return the reformatted string or return an empty string if it is impossible
	 * to reformat the string.
	 * 
	 * Link: https://leetcode.com/problems/reformat-the-string/
	 * 
	 */
	public String reformat(String s) {
		int charCount = 0, digitCount = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
				digitCount++;
			} else {
				charCount++;
			}
		}
		if (Math.abs(charCount - digitCount) > 1) {
			return "";
		}
		if (digitCount > charCount) {
			return helperFunction(0, 1, s);
		}
		return helperFunction(1, 0, s);
	}

	public String helperFunction(int digit, int letter, String s) {
		char[] result = new char[s.length()];
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
				result[letter] = s.charAt(i);
				letter += 2;
			} else {
				result[digit] = s.charAt(i);
				digit += 2;
			}
		}
		return String.valueOf(result);
	}

	/**
	 * Given a string path, where path[i] = 'N', 'S', 'E' or 'W', each representing
	 * moving one unit north, south, east, or west, respectively. You start at the
	 * origin (0, 0) on a 2D plane and walk on the path specified by path. Return
	 * True if the path crosses itself at any point, that is, if at any time you are
	 * on a location you've previously visited. Return False otherwise.
	 * 
	 * Link: https://leetcode.com/problems/path-crossing/
	 * 
	 */
	public boolean isPathCrossing(String path) {
		Set<String> set = new HashSet<>();
		int x = 0;
		int y = 0;
		set.add(new StringBuilder().append(x).append('#').append(y).toString());
		for (int i = 0; i < path.length(); i++) {
			if (path.charAt(i) == 'N') {
				y++;
			} else if (path.charAt(i) == 'S') {
				y--;
			} else if (path.charAt(i) == 'E') {
				x++;
			} else {
				x--;
			}
			StringBuilder builder = new StringBuilder();
			builder.append(x).append('#').append(y);
			if (set.contains(builder.toString())) {
				return true;
			}
			set.add(builder.toString());
		}
		return false;
	}

	/**
	 * Given a word, you need to judge whether the usage of capitals in it is right
	 * or not. We define the usage of capitals in a word to be right when one of the
	 * following cases holds: All letters in this word are capitals, like "USA". All
	 * letters in this word are not capitals, like "leetcode". Only the first letter
	 * in this word is capital, like "Google". Otherwise, we define that this word
	 * doesn't use capitals in a right way.
	 * 
	 * Link: https://leetcode.com/problems/detect-capital/
	 * 
	 */
	public boolean detectCapitalUse(String word) {
		if (word.isEmpty()) {
			return true;
		}
		int capitals = 0;
		int start = 0;
		if (word.charAt(0) >= 'A' && word.charAt(0) <= 'Z') {
			start = 1;
			capitals++;
		}
		for (int i = 1; i < word.length(); i++) {
			if (word.charAt(i) >= 'A' && word.charAt(i) <= 'Z') {
				capitals++;
			}
		}
		if (capitals == 0 || capitals == word.length() || (capitals == 1 && start == 1)) {
			return true;
		}
		return false;
	}

	/**
	 * Given a string s of lower and upper case English letters. A good string is a
	 * string which doesn't have two adjacent characters s[i] and s[i + 1] where: 0
	 * <= i <= s.length - 2 s[i] is a lower-case letter and s[i + 1] is the same
	 * letter but in upper-case or vice-versa. To make the string good, you can
	 * choose two adjacent characters that make the string bad and remove them. You
	 * can keep doing this until the string becomes good. Return the string after
	 * making it good. The answer is guaranteed to be unique under the given
	 * constraints. Notice that an empty string is also good.
	 * 
	 * Link: https://leetcode.com/problems/make-the-string-great/
	 * 
	 */
	public String makeGood(String s) {
		List<Character> list = new ArrayList<>();
		for (int i = 0; i < s.length(); i++) {
			while (!list.isEmpty() && i < s.length() && Math.abs(s.charAt(i) - list.get(list.size() - 1)) == 32) {
				list.remove(list.size() - 1);
				i++;
			}
			if (i < s.length())
				list.add(s.charAt(i));
		}
		StringBuilder builder = new StringBuilder();
		for (Character ch : list) {
			builder.append(ch);
		}
		return builder.toString();
	}

	/**
	 * Given an arbitrary ransom note string and another string containing letters
	 * from all the magazines, write a function that will return true if the ransom
	 * note can be constructed from the magazines ; otherwise, it will return false.
	 * Each letter in the magazine string can only be used once in your ransom note.
	 * 
	 * Link: https://leetcode.com/problems/ransom-note/
	 * 
	 */
	public boolean canConstruct(String ransomNote, String magazine) {
		int[] array = new int[26];
		char[] mag = magazine.toCharArray();
		for (int i = 0; i < mag.length; i++) {
			array[mag[i] - 'a']++;
		}
		char[] ran = ransomNote.toCharArray();
		for (int i = 0; i < ran.length; i++) {
			if (array[ran[i] - 'a'] == 0) {
				return false;
			}
			array[ran[i] - 'a']--;
		}
		return true;
	}

	/**
	 * Given an array of string words. Return all strings in words which is
	 * substring of another word in any order. String words[i] is substring of
	 * words[j], if can be obtained removing some characters to left and/or right
	 * side of words[j].
	 * 
	 * Link: https://leetcode.com/problems/string-matching-in-an-array/
	 * 
	 */
	public List<String> stringMatching(String[] words) {
		Arrays.sort(words, (s1, s2) -> {
			return s1.length() - s2.length();
		});
		List<String> result = new ArrayList<>();
		for (int i = 0; i < words.length; i++) {
			for (int j = i + 1; j < words.length; j++) {
				int index = words[j].indexOf(words[i]);
				if (index != -1) {
					result.add(words[i]);
					break;
				}
			}
		}
		return result;
	}

	/**
	 * Given a string and an integer k, you need to reverse the first k characters
	 * for every 2k characters counting from the start of the string. If there are
	 * less than k characters left, reverse all of them. If there are less than 2k
	 * but greater than or equal to k characters, then reverse the first k
	 * characters and left the other as original.
	 * 
	 * Link: https://leetcode.com/problems/reverse-string-ii/
	 * 
	 */
	public String reverseStr(String s, int k) {
		int start = 0;
		int end = k - 1;
		char[] string = s.toCharArray();
		while (end < s.length()) {
			reverse(string, start, end);
			start += 2 * k;
			end += 2 * k;
		}
		if (start < s.length()) {
			reverse(string, start, string.length - 1);
		}
		return String.valueOf(string);
	}

	/**
	 * Given two non-negative integers num1 and num2 represented as string, return
	 * the sum of num1 and num2. Note: The length of both num1 and num2 is < 5100.
	 * Both num1 and num2 contains only digits 0-9. Both num1 and num2 does not
	 * contain any leading zero. You must not use any built-in BigInteger library or
	 * convert the inputs to integer directly.
	 * 
	 * Link: https://leetcode.com/problems/add-strings/
	 * 
	 */
	public String addStrings(String num1, String num2) {
		StringBuilder builder = new StringBuilder();
		int carry = 0;
		int index1 = num1.length() - 1;
		int index2 = num2.length() - 1;
		while (index1 >= 0 || index2 >= 0) {
			int first = index1 >= 0 ? num1.charAt(index1) - '0' : 0;
			int second = index2 >= 0 ? num2.charAt(index2) - '0' : 0;
			int sum = first + second + carry;
			builder.append(sum % 10);
			carry = sum / 10;
			index1--;
			index2--;
		}
		if (carry > 0) {
			builder.append(carry);
		}
		return builder.reverse().toString();
	}

	/**
	 * Given two binary strings a and b, return their sum as a binary string.
	 * 
	 * Link: https://leetcode.com/problems/add-binary/
	 * 
	 */
	public String addBinary(String num1, String num2) {
		StringBuilder builder = new StringBuilder();
		int carry = 0;
		int index1 = num1.length() - 1;
		int index2 = num2.length() - 1;
		while (index1 >= 0 || index2 >= 0) {
			int first = index1 >= 0 ? num1.charAt(index1) - '0' : 0;
			int second = index2 >= 0 ? num2.charAt(index2) - '0' : 0;
			int sum = first + second + carry;
			builder.append(sum % 2);
			carry = sum / 2;
			index1--;
			index2--;
		}
		if (carry > 0) {
			builder.append(carry);
		}
		return builder.reverse().toString();
	}

	/**
	 * You are given a string representing an attendance record for a student. The
	 * record only contains the following three characters: 'A' : Absent. 'L' :
	 * Late. 'P' : Present. A student could be rewarded if his attendance record
	 * doesn't contain more than one 'A' (absent) or more than two continuous 'L'
	 * (late). You need to return whether the student could be rewarded according to
	 * his attendance record.
	 * 
	 * Link: https://leetcode.com/problems/student-attendance-record-i/
	 * 
	 */
	public boolean checkRecord(String s) {
		int absent = 0;
		char[] string = s.toCharArray();
		for (int i = 0; i < string.length; i++) {
			if (string[i] == 'A') {
				absent++;
				if (absent > 1) {
					return false;
				}
			} else if (string[i] == 'L') {
				int count = 0;
				while (i < string.length && string[i] == 'L') {
					count++;
					i++;
				}
				if (count > 2) {
					return false;
				}
				i--;
			}
		}
		return true;
	}

	/**
	 * Write a function that takes a string as input and reverse only the vowels of
	 * a string.
	 * 
	 * Link: https://leetcode.com/problems/reverse-vowels-of-a-string/
	 * 
	 */
	public String reverseVowels(String s) {
		char[] string = s.toCharArray();
		int start = 0;
		int end = string.length - 1;
		while (start < end) {
			while (start < end && !isVowel(string[start])) {
				start++;
			}
			while (start < end && !isVowel(string[end])) {
				end--;
			}
			char temp = string[start];
			string[start] = string[end];
			string[end] = temp;
			start++;
			end--;
		}
		return String.valueOf(string);
	}

	private boolean isVowel(char ch) {
		if (ch == 'a' || ch == 'i' || ch == 'e' || ch == 'o' || ch == 'u' || ch == 'A' || ch == 'I' || ch == 'E'
				|| ch == 'O' || ch == 'U') {
			return true;
		}
		return false;
	}

	/**
	 * Given a string s containing only lower case English letters and the '?'
	 * character, convert all the '?' characters into lower case letters such that
	 * the final string does not contain any consecutive repeating characters. You
	 * cannot modify the non '?' characters. It is guaranteed that there are no
	 * consecutive repeating characters in the given string except for '?'. Return
	 * the final string after all the conversions (possibly zero) have been made. If
	 * there is more than one solution, return any of them. It can be shown that an
	 * answer is always possible with the given constraints.
	 * 
	 * Link:
	 * https://leetcode.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters/
	 * 
	 */
	public String modifyString(String s) {
		char[] arr = s.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == '?') {
				if (arr[i] == '?') {
					for (int j = 0; j < 3; j++) {
						if (i > 0 && arr[i - 1] - 'a' == j) {
							continue;
						}
						if (i + 1 < arr.length && arr[i + 1] - 'a' == j) {
							continue;
						}
						arr[i] = (char) ('a' + j);
						break;
					}
				}
			}
		}
		return String.valueOf(arr);
	}

	/**
	 * For two strings s and t, we say "t divides s" if and only if s = t + ... + t
	 * (t concatenated with itself 1 or more times) Given two strings str1 and str2,
	 * return the largest string x such that x divides both str1 and str2.
	 * 
	 * Link: https://leetcode.com/problems/greatest-common-divisor-of-strings/
	 * 
	 */
	public String gcdOfStrings(String str1, String str2) {
		if (str1.length() < str2.length()) {
			return gcdOfStrings(str2, str1);
		} else if (!str1.startsWith(str2)) {
			return "";
		} else if (str2.isEmpty()) {
			return str1;
		} else {
			return gcdOfStrings(str1.substring(str2.length()), str2);
		}
	}

	/**
	 * You are given an array A of strings. A move onto S consists of swapping any
	 * two even indexed characters of S, or any two odd indexed characters of S. Two
	 * strings S and T are special-equivalent if after any number of moves onto S, S
	 * == T. For example, S = "zzxy" and T = "xyzz" are special-equivalent because
	 * we may make the moves "zzxy" -> "xzzy" -> "xyzz" that swap S[0] and S[2],
	 * then S[1] and S[3]. Now, a group of special-equivalent strings from A is a
	 * non-empty subset of A such that: Every pair of strings in the group are
	 * special equivalent, and; The group is the largest size possible (ie., there
	 * isn't a string S not in the group such that S is special equivalent to every
	 * string in the group) Return the number of groups of special-equivalent
	 * strings from A.
	 * 
	 * Link: https://leetcode.com/problems/groups-of-special-equivalent-strings/
	 * 
	 */
	public int numSpecialEquivGroups(String[] A) {
		Set<String> seen = new HashSet<>();
		for (String S : A) {
			int[] count = new int[52];
			for (int i = 0; i < S.length(); ++i)
				count[S.charAt(i) - 'a' + 26 * (i % 2)]++;
			seen.add(Arrays.toString(count));
		}
		return seen.size();
	}

	/**
	 * X is a good number if after rotating each digit individually by 180 degrees,
	 * we get a valid number that is different from X. Each digit must be rotated -
	 * we cannot choose to leave it alone. A number is valid if each digit remains a
	 * digit after rotation. 0, 1, and 8 rotate to themselves; 2 and 5 rotate to
	 * each other (on this case they are rotated in a different direction, in other
	 * words 2 or 5 gets mirrored); 6 and 9 rotate to each other, and the rest of
	 * the numbers do not rotate to any other number and become invalid. Now given a
	 * positive number N, how many numbers X from 1 to N are good?
	 * 
	 * Link: https://leetcode.com/problems/rotated-digits/
	 * 
	 */
	public int rotatedDigits(int N) {
		int[] dp = new int[N + 1];
		int count = 0;
		for (int i = 0; i <= N; i++) {
			if (i < 10) {
				if (i == 0 || i == 1 || i == 8) {
					dp[i] = 1;
				} else if (i == 2 || i == 5 || i == 6 || i == 9) {
					dp[i] = 2;
					count++;
				}
			} else {
				int a = dp[i / 10], b = dp[i % 10];
				if (a == 1 && b == 1) {
					dp[i] = 1;
				} else if (a >= 1 && b >= 1) {
					dp[i] = 2;
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * Given an integer n, return a string with n characters such that each
	 * character in such string occurs an odd number of times. The returned string
	 * must contain only lowercase English letters. If there are multiples valid
	 * strings, return any of them.
	 * 
	 * Link:
	 * https://leetcode.com/problems/generate-a-string-with-characters-that-have-odd-counts/
	 * 
	 */
	public String generateTheString(int n) {
		boolean isEven = false;
		if (n % 2 == 0) {
			isEven = true;
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < n - 1; i++) {
			builder.append('a');
		}
		if (isEven) {
			builder.append('b');
		} else {
			builder.append('a');
		}
		return builder.toString();
	}

	/**
	 * Given two strings a and b, find the length of the longest uncommon
	 * subsequence between them. A subsequence of a string s is a string that can be
	 * obtained after deleting any number of characters from s. For example, "abc"
	 * is a subsequence of "aebdc" because you can delete the underlined characters
	 * in "aebdc" to get "abc". Other subsequences of "aebdc" include "aebdc",
	 * "aeb", and "" (empty string). An uncommon subsequence between two strings is
	 * a string that is a subsequence of one but not the other. Return the length of
	 * the longest uncommon subsequence between a and b. If the longest uncommon
	 * subsequence doesn't exist, return -1.
	 * 
	 * Link: https://leetcode.com/problems/longest-uncommon-subsequence-i/
	 * 
	 */
	public int findLUSlength(String a, String b) {
		if (a.equals(b)) {
			return -1;
		}
		return Math.max(a.length(), b.length());
	}

	/**
	 * A sentence S is given, composed of words separated by spaces. Each word
	 * consists of lowercase and uppercase letters only. We would like to convert
	 * the sentence to "Goat Latin" (a made-up language similar to Pig Latin.) The
	 * rules of Goat Latin are as follows: If a word begins with a vowel (a, e, i,
	 * o, or u), append "ma" to the end of the word. For example, the word 'apple'
	 * becomes 'applema'. If a word begins with a consonant (i.e. not a vowel),
	 * remove the first letter and append it to the end, then add "ma". For example,
	 * the word "goat" becomes "oatgma". Add one letter 'a' to the end of each word
	 * per its word index in the sentence, starting with 1. For example, the first
	 * word gets "a" added to the end, the second word gets "aa" added to the end
	 * and so on. Return the final sentence representing the conversion from S to
	 * Goat Latin.
	 * 
	 * Link: https://leetcode.com/problems/goat-latin/
	 * 
	 */
	public String toGoatLatin(String S) {
		StringBuilder builder = new StringBuilder();
		int count = 1;
		for (int i = 0; i < S.length(); i++) {
			boolean isVowel = false;
			if (isVowel(S.charAt(i))) {
				isVowel = true;
			}
			char ch = S.charAt(i);
			if (isVowel) {
				builder.append(ch);
			}
			i++;
			while (i < S.length() && S.charAt(i) != ' ') {
				builder.append(S.charAt(i));
				i++;
			}
			if (!isVowel) {
				builder.append(ch);
			}
			builder.append("ma");
			int j = 0;
			while (j < count) {
				builder.append('a');
				j++;
			}
			count++;
			builder.append(' ');
		}
		return builder.toString().trim();
	}

	private static final List<String> list = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
			"Sep", "Oct", "Nov", "Dec");

	/**
	 * Given a date string in the form Day Month Year, where: Day is in the set
	 * {"1st", "2nd", "3rd", "4th", ..., "30th", "31st"}. Month is in the set
	 * {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov",
	 * "Dec"}. Year is in the range [1900, 2100]. Convert the date string to the
	 * format YYYY-MM-DD, where: YYYY denotes the 4 digit year. MM denotes the 2
	 * digit month. DD denotes the 2 digit day.
	 * 
	 * Link: https://leetcode.com/problems/reformat-date/
	 * 
	 */
	public String reformatDate(String date) {
		int day = 0;
		int i = 0;
		while (Character.isDigit(date.charAt(i))) {
			day = day * 10 + date.charAt(i) - '0';
			i++;
		}
		i += 3;
		int month = list.indexOf(date.substring(i, i + 3)) + 1;
		i += 4;
		int year = 0;
		while (i < date.length()) {
			year = year * 10 + date.charAt(i) - '0';
			i++;
		}
		StringBuilder builder = new StringBuilder();
		builder.append(year);
		builder.append('-');
		if (month < 10) {
			builder.append('0');
		}
		builder.append(month);
		builder.append('-');
		if (day < 10) {
			builder.append('0');
		}
		builder.append(day);
		return builder.toString();
	}

	/**
	 * You are given a string text of words that are placed among some number of
	 * spaces. Each word consists of one or more lowercase English letters and are
	 * separated by at least one space. It's guaranteed that text contains at least
	 * one word. Rearrange the spaces so that there is an equal number of spaces
	 * between every pair of adjacent words and that number is maximized. If you
	 * cannot redistribute all the spaces equally, place the extra spaces at the
	 * end, meaning the returned string should be the same length as text. Return
	 * the string after rearranging the spaces.
	 * 
	 * Link: https://leetcode.com/problems/rearrange-spaces-between-words/
	 * 
	 */
	public String reorderSpaces(String text) {
		StringTokenizer st = new StringTokenizer(text, " ");
		int wordCount = st.countTokens();
		int charCount = 0;
		List<String> words = new ArrayList<>();

		while (st.hasMoreTokens()) {
			String w = st.nextToken();
			charCount += w.length();
			words.add(w);
		}

		int delimiterLength, tailLength;
		if (wordCount > 1) {
			delimiterLength = (text.length() - charCount) / (wordCount - 1);
			tailLength = (text.length() - charCount) % (wordCount - 1);
		} else {
			delimiterLength = 0;
			tailLength = text.length() - charCount;
		}

		StringBuilder temp = new StringBuilder();
		for (int j = 0; j < delimiterLength; j++)
			temp.append(' ');
		String delimiter = temp.toString();

		temp = new StringBuilder();
		for (int j = 0; j < tailLength; j++)
			temp.append(' ');
		String tail = temp.toString();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < words.size() - 1; i++) {
			sb.append(words.get(i));
			sb.append(delimiter);
		}
		sb.append(words.get(words.size() - 1));
		sb.append(tail);

		return sb.toString();
	}

	/**
	 * Given a string s. You should re-order the string using the following
	 * algorithm: Pick the smallest character from s and append it to the result.
	 * Pick the smallest character from s which is greater than the last appended
	 * character to the result and append it. Repeat step 2 until you cannot pick
	 * more characters. Pick the largest character from s and append it to the
	 * result. Pick the largest character from s which is smaller than the last
	 * appended character to the result and append it. Repeat step 5 until you
	 * cannot pick more characters. Repeat the steps from 1 to 6 until you pick all
	 * characters from s. In each step, If the smallest or the largest character
	 * appears more than once you can choose any occurrence and append it to the
	 * result. Return the result string after sorting s with this algorithm.
	 * 
	 * Link: https://leetcode.com/problems/increasing-decreasing-string/
	 * 
	 */
	public String sortString(String s) {
		int[] array = new int[26];
		for (int i = 0; i < s.length(); i++) {
			array[s.charAt(i) - 'a']++;
		}
		StringBuilder builder = new StringBuilder();
		int count = s.length();
		while (count > 0) {
			for (int i = 0; i < 26; i++) {
				if (array[i] > 0) {
					builder.append((char) (i + 97));
					array[i]--;
					count--;
				}
			}
			for (int i = 25; i >= 0; i--) {
				if (array[i] > 0) {
					builder.append((char) (i + 97));
					array[i]--;
					count--;
				}
			}
		}
		return builder.toString();
	}

	static final int[] PRIMES = new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
			73, 79, 83, 89, 97 };

	/**
	 * Given a non-empty string check if it can be constructed by taking a substring
	 * of it and appending multiple copies of the substring together. You may assume
	 * the given string consists of lowercase English letters only and its length
	 * will not exceed 10000.
	 * 
	 * Link: https://leetcode.com/problems/repeated-substring-pattern/
	 * 
	 */
	public static boolean repeatedSubstringPattern(String s) {
		int n = s.length();
		for (int p : PRIMES) {
			if (n < p || n % p != 0) {
				continue;
			}
			int m = n / p;
			boolean ok = true;
			for (int i = 1; i < p; i++) {
				if (!s.substring(0, m).equals(s.substring(i * m, (i + 1) * m))) {
					ok = false;
					break;
				}
			}
			if (ok) {
				return true;
			}
		}
		return false;
	}

	/**
	 * You are given a string time in the form of hh:mm, where some of the digits in
	 * the string are hidden (represented by ?). The valid times are those
	 * inclusively between 00:00 and 23:59. Return the latest valid time you can get
	 * from time by replacing the hidden digits.
	 * 
	 * Link: https://leetcode.com/problems/latest-time-by-replacing-hidden-digits/
	 * 
	 */
	public String maximumTime(String time) {
		char[] string = time.toCharArray();
		if (string[0] == '?') {
			if (string[1] >= '4' && string[1] <= '9') {
				string[0] = '1';
			} else {
				string[0] = '2';
			}
		}
		if (string[1] == '?') {
			if (string[0] == '2') {
				string[1] = '3';
			} else {
				string[1] = '9';
			}
		}
		if (string[3] == '?') {
			string[3] = '5';
		}
		if (string[4] == '?') {
			string[4] = '9';
		}
		return String.valueOf(string);
	}

	/**
	 * Given a string, determine if it is a palindrome, considering only
	 * alphanumeric characters and ignoring cases.
	 * 
	 * Link: https://leetcode.com/problems/valid-palindrome/
	 * 
	 */
	public boolean isPalindrome(String s) {
		int i = 0;
		int j = s.length() - 1;
		char[] string = s.toCharArray();
		while (i < j) {
			if (!Character.isLetter(string[i]) && !Character.isDigit(string[i])) {
				i++;
				continue;
			}
			if (!Character.isLetter(string[j]) && !Character.isDigit(string[j])) {
				j--;
				continue;
			}
			char left = string[i];
			char right = string[j];
			if (left != right) {
				if (Character.isLetter(left) && Character.isLetter(right)) {
					if (Math.abs(left - right) != 32) {
						return false;
					}
				} else {
					return false;
				}
			}
			i++;
			j--;
		}
		return true;
	}

	/**
	 * Your friend is typing his name into a keyboard. Sometimes, when typing a
	 * character c, the key might get long pressed, and the character will be typed
	 * 1 or more times. You examine the typed characters of the keyboard. Return
	 * True if it is possible that it was your friends name, with some characters
	 * (possibly none) being long pressed.
	 * 
	 * Link: https://leetcode.com/problems/long-pressed-name/
	 * 
	 */
	public boolean isLongPressedName(String name, String typed) {
		char[] string1 = name.toCharArray();
		char[] type = typed.toCharArray();
		int k = 0;
		for (int i = 0; i < string1.length; i++) {
			int j = i + 1;
			while (j < string1.length && string1[j] == string1[i]) {
				j++;
			}
			char ch = string1[i];
			int count = 0;
			while (k < type.length && type[k] == ch) {
				count++;
				k++;
			}
			if (count < j - i) {
				return false;
			}
			i = j - 1;
		}
		if (k < type.length) {
			return false;
		}
		return true;
	}

	/**
	 * You are given a string s, return the number of segments in the string. A
	 * segment is defined to be a contiguous sequence of non-space characters.
	 * 
	 * Link: https://leetcode.com/problems/number-of-segments-in-a-string/
	 * 
	 */
	public int countSegments(String s) {
		if (s.isEmpty()) {
			return 0;
		}
		int count = 0;
		int i = 0;
		while (i < s.length() && s.charAt(i) == ' ') {
			i++;
		}
		while (i < s.length()) {
			count++;
			while (i < s.length() && s.charAt(i) != ' ') {
				i++;
			}
			while (i < s.length() && s.charAt(i) == ' ') {
				i++;
			}
		}
		return count;
	}

	/**
	 * Given a string s consists of some words separated by spaces, return the
	 * length of the last word in the string. If the last word does not exist,
	 * return 0. A word is a maximal substring consisting of non-space characters
	 * only.
	 * 
	 * Link: https://leetcode.com/problems/length-of-last-word/
	 * 
	 */
	public int lengthOfLastWord(String s) {
		int i = s.length() - 1;
		while (i >= 0 && s.charAt(i) == ' ') {
			i--;
		}
		int j = i;
		while (i >= 0 && s.charAt(i) != ' ') {
			i--;
		}
		return j - i;
	}

	/**
	 * Given two strings A and B of lowercase letters, return true if you can swap
	 * two letters in A so the result is equal to B, otherwise, return false.
	 * Swapping letters is defined as taking two indices i and j (0-indexed) such
	 * that i != j and swapping the characters at A[i] and A[j]. For example,
	 * swapping at indices 0 and 2 in "abcd" results in "cbad".
	 * 
	 * Link: https://leetcode.com/problems/buddy-strings/
	 * 
	 */
	public boolean buddyStrings(String A, String B) {
		if (A.length() != B.length()) {
			return false;
		}
		if (A.equals(B)) {
			int[] count = new int[26];
			for (int i = 0; i < A.length(); i++) {
				count[A.charAt(i) - 'a']++;
			}
			for (int i = 0; i < 26; i++) {
				if (count[i] > 1) {
					return true;
				}
			}
			return false;
		}
		int index1 = -1;
		int index2 = -1;
		for (int i = 0; i < A.length(); i++) {
			if (A.charAt(i) != B.charAt(i)) {
				if (index1 == -1) {
					index1 = i;
				} else if (index2 == -1) {
					index2 = i;
				} else {
					return false;
				}
			}
		}
		if (index1 == -1 || index2 == -1) {
			return false;
		}
		return A.charAt(index1) == B.charAt(index2) && A.charAt(index2) == B.charAt(index1);
	}

}
