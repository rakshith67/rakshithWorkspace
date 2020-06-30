package linkedlists;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinkedListMedium {

	/**
	 * swap nodes in pairs.
	 * 
	 * Link: https://leetcode.com/problems/swap-nodes-in-pairs/
	 */
	public NodeLL swapPairs(NodeLL head) {
		if (head == null || head.getNext() == null) {
			return head;
		}
		NodeLL temp1 = head, temp2 = head.getNext();
		while (temp1 != null && temp2 != null) {
			int temp = temp2.getValue();
			temp2.setValue(temp1.getValue());
			temp1.setValue(temp);
			temp1 = temp2.getNext();
			if (temp1 != null) {
				temp2 = temp1.getNext();
			}
		}
		return head;
	}

	/**
	 * odd even linkedList
	 * 
	 * Link: https://leetcode.com/problems/odd-even-linked-list/submissions/
	 */
	public NodeLL oddEvenList(NodeLL head) {
		if (head == null || head.getNext() == null) {
			return head;
		}
		NodeLL odd = head, even = head.getNext(), evenhead = even;
		while (even != null && even.getNext() != null) {
			odd.setNext(even.getNext());
			odd = odd.getNext();
			even.setNext(odd.getNext());
			even = even.getNext();
		}
		odd.setNext(evenhead);
		return head;
	}

	/**
	 * reverse in between linkedList
	 * 
	 * Link: https://leetcode.com/problems/reverse-linked-list-ii/
	 */
	public NodeLL reverseBetween(NodeLL head, int m, int n) {
		if (head == null) {
			return null;
		}
		NodeLL temp = head;
		NodeLL prev = null, next = null;
		while (m > 1) {
			prev = temp;
			temp = temp.getNext();
			m--;
			n--;
		}
		NodeLL currenthead = prev, tail = temp;
		while (n > 0) {
			next = temp.getNext();
			temp.setNext(prev);
			prev = temp;
			temp = next;
			n--;
		}
		if (currenthead != null) {
			currenthead.setNext(prev);
		} else {
			head = prev;
		}
		tail.setNext(temp);
		return head;
	}

	/**
	 * remove duplicates and their original.
	 * 
	 * Link: https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
	 * 
	 */
	public NodeLL deleteDuplicates(NodeLL head) {
		if (head == null) {
			return null;
		}
		NodeLL temp = head, prev = null;
		while (temp != null) {
			NodeLL current = temp;
			while (current.getNext() != null && current.getValue() == current.getNext().getValue()) {
				current = current.getNext();
			}
			if (current != temp) {
				if (temp == head) {
					head = current.getNext();
				} else {
					prev.setNext(current.getNext());
				}
			} else {
				prev = temp;
			}
			temp = current.getNext();
		}
		return head;
	}

	/**
	 * rotateRight linked list k times.
	 * 
	 * Link: https://leetcode.com/problems/rotate-list/
	 */
	public NodeLL rotateRight(NodeLL head, int k) {
		if (head == null) {
			return head;
		}
		int count = 0, current = 0;
		NodeLL temp = head, prev = null;
		while (temp != null) {
			temp = temp.getNext();
			count++;
		}
		temp = head;
		int number = k % count;
		while (count - current != number) {
			prev = temp;
			temp = temp.getNext();
			current++;
		}
		if (temp != null) {
			NodeLL currentHead = temp;
			while (temp.getNext() != null) {
				temp = temp.getNext();
			}
			temp.setNext(head);
			prev.setNext(null);
			head = currentHead;
		}
		return head;
	}

	/**
	 * split list into k parts. Link:
	 * https://leetcode.com/problems/split-linked-list-in-parts/
	 * 
	 */
	public NodeLL[] splitListToParts(NodeLL root, int k) {
		NodeLL[] array = new NodeLL[k];
		NodeLL temp = root;
		int count = 0;
		while (temp != null) {
			count++;
			temp = temp.getNext();
		}
		int extraNodes = count % k, divide = count / k;
		temp = root;
		for (int i = 0; i < k; ++i) {
			NodeLL head = temp;
			for (int j = 0; j < divide + (i < extraNodes ? 1 : 0) - 1; ++j) {
				if (temp != null)
					temp = temp.getNext();
			}
			if (temp != null) {
				NodeLL prev = temp;
				temp = temp.getNext();
				prev.setNext(null);
			}
			array[i] = head;
		}
		return array;
	}

	/**
	 * add two numbers and return list List:
	 * https://leetcode.com/problems/add-two-numbers-ii/submissions/
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	public NodeLL addTwoNumbers(NodeLL l1, NodeLL l2) {
		if (l1 == null && l2 == null) {
			return null;
		}
		NodeLL dummyHead = new NodeLL(0);
		NodeLL current = dummyHead;
		int carry = 0;
		while (l1 != null || l2 != null) {
			int sum = (l1 != null ? l1.getValue() : 0) + (l2 != null ? l2.getValue() : 0);
			sum = carry + sum;
			carry = sum / 10;
			current.setNext(new NodeLL(sum % 10));
			if (l1 != null)
				l1 = l1.getNext();
			if (l2 != null)
				l2 = l2.getNext();
			current = current.getNext();
		}
		if (carry != 0) {
			current.setNext(new NodeLL(carry));
		}
		return dummyHead.getNext();
	}

	/**
	 * gives array of next larger elements. Link:
	 * https://leetcode.com/problems/next-greater-node-in-linked-list/
	 * 
	 */
	public int[] nextLargerNodes(NodeLL head) {
		List<Integer> list = new ArrayList<>();
		while (head != null) {
			list.add(head.getValue());
			head = head.getNext();
		}
		if (list.size() == 0) {
			return new int[0];
		}

		Integer[] array = list.toArray(new Integer[list.size()]);
		Deque<Integer> deque = new ArrayDeque<>();
		int[] res = new int[array.length];
		deque.offerFirst(array[array.length - 1]);
		for (int i = array.length - 2; i >= 0; i--) {
			while (deque.size() != 0 && deque.peekFirst() <= array[i]) {
				deque.pollFirst();
			}
			if (deque.size() == 0) {
				res[i] = 0;
			} else {
				res[i] = deque.peekFirst();
			}
			deque.offerFirst(array[i]);
		}

		return res;
	}

	/**
	 * Partitioning the list greater than x. Link:
	 * https://leetcode.com/problems/partition-list/
	 * 
	 */
	public NodeLL partition(NodeLL head, int x) {
		NodeLL before_head = new NodeLL(0);
		NodeLL before = before_head;
		NodeLL after_head = new NodeLL(0);
		NodeLL after = after_head;
		while (head != null) {
			if (head.getValue() < x) {
				before.setNext(head);
				before = before.getNext();
			} else {
				after.setNext(head);
				after = after.getNext();
			}
			head = head.getNext();
		}
		after.setNext(null);
		before.setNext(after_head.getNext());
		return before_head.getNext();
	}

	/**
	 * remove consecutive nodes whose sum is 0; Link:
	 * https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/
	 * 
	 */
	public NodeLL removeZeroSumSublists(NodeLL head) {
		NodeLL temp = head, dummy = new NodeLL(0);
		dummy.setNext(head);
		Map<Integer, NodeLL> map = new HashMap<>();
		int sum = 0;
		map.put(0, dummy);
		while (temp != null) {
			sum += temp.getValue();
			if (map.containsKey(sum)) {
				NodeLL current = map.get(sum).getNext();
				int currentSum = sum;
				while (current != temp) {
					currentSum += current.getValue();
					map.remove(currentSum);
					current = current.getNext();
				}
				map.get(sum).setNext(temp.getNext());
			} else {
				map.put(sum, temp);
			}
			temp = temp.getNext();
		}
		return dummy.getNext();
	}

	/**
	 * reverse linked list in k group. Link:
	 * https://leetcode.com/problems/reverse-nodes-in-k-group/
	 * 
	 */
	public NodeLL reverseKGroup(NodeLL head, int k) {
		NodeLL temp = head;
		NodeLL prev = null, next = null;
		int length = 0;
		while (temp != null) {
			temp = temp.getNext();
			length++;
		}
		temp = head;
		while (temp != null) {
			if (length >= k) {
				length -= k;
				NodeLL currenthead = prev, tail = temp;
				int i = k;
				while (temp != null && i > 0) {
					next = temp.getNext();
					temp.setNext(prev);
					prev = temp;
					temp = next;
					i--;
				}
				if (currenthead != null) {
					currenthead.setNext(prev);
				} else {
					head = prev;
				}
				tail.setNext(temp);
				prev = tail;
			} else {
				break;
			}
		}
		return head;
	}
}
