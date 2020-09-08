package trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {

	private Map<Character, TrieNode> children = new HashMap<>();

	private boolean isEndOfWord;

	public TrieNode(Map<Character, TrieNode> children) {
		this.children = children;
	}

	public TrieNode(boolean isEndOfWord) {
		this.isEndOfWord = isEndOfWord;
	}

	public TrieNode(Map<Character, TrieNode> children, boolean isEndOfWord) {
		this.children = children;
		this.isEndOfWord = isEndOfWord;
	}

	public Map<Character, TrieNode> getChildren() {
		return children;
	}

	public void setChildren(Map<Character, TrieNode> children) {
		this.children = children;
	}

	public boolean isEndOfWord() {
		return isEndOfWord;
	}

	public void setEndOfWord(boolean isEndOfWord) {
		this.isEndOfWord = isEndOfWord;
	}

}
