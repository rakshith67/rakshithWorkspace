package trie;

import java.util.Map;
import java.util.Scanner;

public class TrieOperations {

	public void insert(TrieNode trieNode, String word) {
		TrieNode current = trieNode;
		for (int i = 0; i < word.length(); i++) {
			Map<Character, TrieNode> map = current.getChildren();
			Character character = word.charAt(i);
			TrieNode node = map.get(character);
			if (node == null) {
				node = new TrieNode(false);
				map.put(character, node);
			}
			current = node;
		}
		current.setEndOfWord(true);
	}

	public boolean search(TrieNode trieNode, String word) {
		TrieNode current = trieNode;
		for (int i = 0; i < word.length(); i++) {
			Map<Character, TrieNode> map = current.getChildren();
			Character character = word.charAt(i);
			TrieNode node = map.get(character);
			if (node == null) {
				return false;
			}
			current = node;
		}
		return current.isEndOfWord();
	}

	public void delete(TrieNode trieNode, String word) {
		if (search(trieNode, word)) {
			delete(trieNode, word, 0);
		}
	}

	/**
	 * CASE#1 -- Some other word's prefix is same as Prefix of this word (BCDE,
	 * BCKG) CASE#2 -- We are at last character of this word and This word is a
	 * Prefix of some other word (BCDE, BCDEFG) CASE#3 -- Some other word is a
	 * Prefix of this word (BCDE, BC) CASE#4 -- No one is dependent on this Word
	 * (BCDE, BCDE)
	 */
	private void delete(TrieNode trieNode, String word, int index) {

	}

	/**
	 * Insert and search words in a trie Link:
	 * https://practice.geeksforgeeks.org/problems/trie-insert-and-search/0
	 * 
	 */
	public void insertandSearch() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter number of strings to insert");
		int numberOfStrings = scanner.nextInt();
		TrieNode trieNode = new TrieNode(false);
		for (int i = 0; i < numberOfStrings; i++) {
			System.out.println("Enter " + i + "th string");
			String current = scanner.next();
			insert(trieNode, current);
		}
		System.out.println("Enter the string to search");
		String toSearch = scanner.next();
		if (search(trieNode, toSearch)) {
			System.out.println(toSearch + "is present in the trie");
		} else {
			System.out.println(toSearch + "is  not present in the trie");
		}
		scanner.close();
	}

}
