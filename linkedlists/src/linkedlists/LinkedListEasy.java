package linkedlists;

import java.util.ArrayDeque;
import java.util.Deque;

public class LinkedListEasy {

	/**
	 * Used to detect loop in a list, Floyd's cycle find algorithm.
	 * 
	 * GFG Link:
	 * https://practice.geeksforgeeks.org/problems/detect-loop-in-linked-list/1
	 * 
	 */
	public boolean detectLoop(NodeLL head) {
		NodeLL slow = head, fast = head;
		while (slow != null && fast != null && fast.getNext() != null) {
			slow = slow.getNext();
			fast = fast.getNext().getNext();
			if (slow == fast) {
				return true;
			}
		}
		return false;
	}

	/**
	 * reverse a list
	 * 
	 * GFG Link:
	 * https://practice.geeksforgeeks.org/problems/detect-loop-in-linked-list/1
	 * 
	 */
	public NodeLL reverseList(NodeLL head) {
		NodeLL current = head, prev = null, next = null;
		while (current != null) {
			next = current.getNext();
			current.setNext(prev);
			prev = current;
			current = next;
		}
		return prev;
	}

	/**
	 * reverse DLL
	 * 
	 * GFG Link:
	 * https://practice.geeksforgeeks.org/problems/reverse-a-doubly-linked-list/1
	 * 
	 */
	public NodeDLL reverseDLL(NodeDLL head) {
		NodeDLL current = head, prev = null, next = null;
		while (current != null) {
			next = current.getNext();
			current.setPrev(current.getNext());
			current.setNext(prev);
			prev = current;
			current = next;
		}
		return prev;
	}

	/**
	 * remove duplicates from a linked list.
	 * 
	 * GFG Link:
	 * https://practice.geeksforgeeks.org/problems/remove-duplicate-element-from-sorted-linked-list/1
	 * 
	 */
	public NodeLL removeDuplicates(NodeLL head) {
		if (head == null) {
			return null;
		}
		NodeLL temp = head;
		while (temp != null) {
			NodeLL current = temp;
			while (current != null && current.getValue() == temp.getValue()) {
				current = current.getNext();
			}
			temp.setNext(current);
			temp = temp.getNext();
		}
		return head;
	}

	/**
	 * remove nth node from end.
	 * 
	 * Link: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
	 * 
	 */
	public NodeLL removeNthFromEnd(NodeLL head, int n) {
		NodeLL temp = head, temp2 = head;
		int count = 0;
		while (temp != null) {
			temp = temp.getNext();
			count++;
		}
		if (count - n == 0) {
			head = head.getNext();
		} else {
			int i = 0;
			while (temp2 != null && i + 1 + n != count) {
				temp2 = temp2.getNext();
				i++;
			}
			if (temp2 != null) {
				temp = temp2.getNext();
				temp2.setNext(temp.getNext());
			}
		}
		return head;
	}

	public NodeLL addOne(NodeLL head) {
		Deque<NodeLL> deque = new ArrayDeque<>();
		NodeLL temp = head;
		while (temp != null) {
			deque.push(temp);
			temp = temp.getNext();
		}
		int add = 1;
		while (!deque.isEmpty() && add != 0) {
			NodeLL currentNode = deque.pop();
			int sum = currentNode.getValue() + add;
			add = sum / 10;
			currentNode.setValue(sum % 10);
		}
		if (add != 0) {
			NodeLL node = new NodeLL(add);
			node.setNext(head);
			head = node;
		}
		return head;
	}
}
