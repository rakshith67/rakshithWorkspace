package linkedlists;

public class NodeLL {

	private int value;

	private NodeLL next;

	NodeLL(int value) {
		this.value = value;
		this.next = null;
	}

	NodeLL(int value, NodeLL next) {
		this.value = value;
		this.next = next;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public NodeLL getNext() {
		return next;
	}

	public void setNext(NodeLL next) {
		this.next = next;
	}

}
