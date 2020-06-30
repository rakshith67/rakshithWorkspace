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

	public static NodeLL createLL() {
		NodeLL head = new NodeLL(1);
		NodeLL node2 = new NodeLL(2);
		NodeLL node3 = new NodeLL(3);
		NodeLL node4 = new NodeLL(4);
		NodeLL node5 = new NodeLL(5);
		NodeLL node6 = new NodeLL(6);
		NodeLL node7 = new NodeLL(7);
		NodeLL node8 = new NodeLL(8);
		NodeLL node9 = new NodeLL(9);
		NodeLL node10 = new NodeLL(10);
		NodeLL node11 = new NodeLL(11);
		NodeLL node12 = new NodeLL(12);
		NodeLL node13 = new NodeLL(13);
		NodeLL node14 = new NodeLL(14);
		NodeLL node15 = new NodeLL(15);
		head.setNext(node2);
		node2.setNext(node3);
		node3.setNext(node4);
		node4.setNext(node5);
		node5.setNext(node6);
		node6.setNext(node7);
		node7.setNext(node8);
		node8.setNext(node9);
		node9.setNext(node10);
		node10.setNext(node11);
		node11.setNext(node12);
		node12.setNext(node13);
		node13.setNext(node14);
		node14.setNext(node15);
		return head;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
