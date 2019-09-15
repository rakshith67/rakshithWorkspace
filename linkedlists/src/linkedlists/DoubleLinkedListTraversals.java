package linkedlists;

public class DoubleLinkedListTraversals {

	public void printDoubleLinkedList(NodeDLL head) {
		while (head != null) {
			System.out.print(head.getValue() + " ");
			head = head.getNext();
		}
	}

	public void printDoubleLinkedListReverse(NodeDLL head) {
		if (head == null) {
			return;
		}
		while (head.getNext() != null) {
			head = head.getNext();
		}
		while (head != null) {
			System.out.print(head.getValue() + " ");
			head = head.getPrev();
		}
	}
}
