package linkedlists;

public class NodeDLL {

	private int value;

	private NodeDLL next;

	private NodeDLL prev;

	public NodeDLL(int value) {
		this.value = value;
		this.next = null;
		this.prev = null;
	}

	NodeDLL(int value, NodeDLL next, NodeDLL prev) {
		this.value = value;
		this.next = next;
		this.prev = prev;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public NodeDLL getNext() {
		return next;
	}

	public void setNext(NodeDLL next) {
		this.next = next;
	}

	public NodeDLL getPrev() {
		return prev;
	}

	public void setPrev(NodeDLL prev) {
		this.prev = prev;
	}

}
