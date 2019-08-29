package linkedlists;

public class Node {

	private int value;

	private Node next;

	Node(int value) {
		this.value = value;
		this.next = null;
	}

	Node(int value, Node next) {
		this.value = value;
		this.next = next;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

}
