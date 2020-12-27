package design;

import java.util.HashMap;
import java.util.Map;

public class InsertSearchDataStructure {

	public static void main(String[] args) {
		WordDictionary wordDictionary = new WordDictionary();
		wordDictionary.addWord("bad");
		wordDictionary.addWord("dad");
		wordDictionary.addWord("mad");
		wordDictionary.search("pad"); // return False
		wordDictionary.search("bad"); // return True
		wordDictionary.search(".ad"); // return True
		wordDictionary.search("b.."); // return True
	}
}

class WordDictionary {

	public class TrieNode {
		public Map<Character, TrieNode> map;
		public boolean isWord;

		public TrieNode() {
			map = new HashMap<>();
		}
	}

	public TrieNode root;

	/** Initialize your data structure here. */
	public WordDictionary() {
		root = new TrieNode();
	}

	/** Adds a word into the data structure. */
	public void addWord(String word) {
		TrieNode current = root;
		for (int i = 0; i < word.length(); i++) {
			Map<Character, TrieNode> child = current.map;
			TrieNode node = child.get(word.charAt(i));
			if (node == null) {
				node = new TrieNode();
				child.put(word.charAt(i), node);
			}
			current = node;
		}
		current.isWord = true;
	}

	/**
	 * Returns if the word is in the data structure. A word could contain the dot
	 * character '.' to represent any one letter.
	 */
	public boolean search(String word) {
		return match(word, 0, root);
	}

	private boolean match(String word, int index, TrieNode root) {
		if (index == word.length()) {
			return root.isWord;
		}
		if (word.charAt(index) == '.') {
			for (Character ch : root.map.keySet()) {
				if (match(word, index + 1, root.map.get(ch))) {
					return true;
				}
			}
		} else {
			Map<Character, TrieNode> map = root.map;
			if (map.containsKey(word.charAt(index))) {
				return match(word, index + 1, root.map.get(word.charAt(index)));
			}
		}
		return false;
	}
}
