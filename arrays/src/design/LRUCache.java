package design;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

	private Map<Integer, Node> map = new HashMap<>();
	private int capacity;
	private Node head, tail;

	public LRUCache(int capacity) {
		map = new HashMap<>();
		this.capacity = capacity;

		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.prev = head;
	}

	public int get(int key) {
		int result = -1;
		Node node = map.get(key);
		if (node != null) {
			result = node.value;
			remove(node);
			add(node);
		}
		return result;
	}

	public void put(int key, int value) {
		Node node = map.get(key);
		if (node != null) {
			node.value = value;
			remove(node);
			add(node);
		} else {
			Node curr = new Node();
			curr.key = key;
			curr.value = value;
			// System.out.println(map.size());
			if (map.size() == capacity) {
				map.remove(tail.prev.key);
				remove(tail.prev);
			}
			add(curr);
			map.put(key, curr);
		}
	}

	private void add(Node node) {
		Node head_next = head.next;
		node.next = head_next;
		node.prev = head;

		head.next = node;
		head_next.prev = node;
	}

	private void remove(Node node) {
		Node node_prev = node.prev;
		Node node_next = node.next;

		node_prev.next = node_next;
		node_next.prev = node_prev;
	}

	class Node {
		int key;
		int value;
		Node prev;
		Node next;
	}
}
