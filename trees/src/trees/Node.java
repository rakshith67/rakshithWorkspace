package trees;

public class Node {

	private int value;

	private Node left;

	private Node right;

	public Node(int value, Node left, Node right) {
		super();
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public Node(int value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}

	public Node() {
		this.value = 0;
		this.left = null;
		this.right = null;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return String.valueOf(this.value);
	}

	public static Node createTree() {
		Node root = new Node(1);
		Node root2 = new Node(2);
		root.setLeft(root2);
		Node root3 = new Node(3);
		root.setRight(root3);
		Node root4 = new Node(4);
		Node root5 = new Node(5);
		root2.setLeft(root4);
		root2.setRight(root5);
		Node root6 = new Node(6);
		Node root7 = new Node(7);
		root3.setLeft(root6);
		root3.setRight(root7);
		root4.setLeft(new Node(8));
		root4.setRight(new Node(9));
		root5.setLeft(new Node(10));
		root5.setRight(new Node(11));
		root6.setLeft(new Node(12));
		root6.setRight(new Node(13));
		root7.setLeft(new Node(14));
		root7.setRight(new Node(15));
		return root;
	}
}
