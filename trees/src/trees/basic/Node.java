package trees.basic;

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
		Node root3 = new Node(3);
		root.setLeft(root2);
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

	public static Node createLeftTree() {
		Node root = new Node(1);
		Node root2 = new Node(2);
		Node root3 = new Node(3);
		Node root4 = new Node(4);
		Node root5 = new Node(5);
		Node root6 = new Node(6);
		Node root7 = new Node(7);
		root.setLeft(root2);
		root2.setLeft(root3);
		root3.setLeft(root4);
		root4.setLeft(root5);
		root5.setLeft(root6);
		root6.setLeft(root7);
		return root;
	}

	public static Node createRightTree() {
		Node root = new Node(1);
		Node root2 = new Node(2);
		Node root3 = new Node(3);
		Node root4 = new Node(4);
		Node root5 = new Node(5);
		Node root6 = new Node(6);
		Node root7 = new Node(7);
		root.setRight(root2);
		root2.setRight(root3);
		root3.setRight(root4);
		root4.setRight(root5);
		root5.setRight(root6);
		root6.setRight(root7);
		return root;
	}

	public static Node createBST() {
		Node root8 = new Node(8);
		Node root12 = new Node(12);
		Node root4 = new Node(4);
		Node root2 = new Node(2);
		Node root6 = new Node(6);
		Node root10 = new Node(10);
		Node root14 = new Node(14);
		root8.setLeft(root4);
		root8.setRight(root12);
		root4.setLeft(root2);
		root4.setRight(root6);
		root12.setLeft(root10);
		root12.setRight(root14);
		root2.setLeft(new Node(1));
		root2.setRight(new Node(3));
		root6.setLeft(new Node(5));
		root6.setRight(new Node(7));
		root10.setLeft(new Node(9));
		root10.setRight(new Node(11));
		root14.setLeft(new Node(13));
		root14.setRight(new Node(15));
		return root8;
	}

	public static Node createCustomTree() {
		Node root = new Node(1);
		Node root1 = new Node(3);
		Node root2 = new Node(2);
		Node root3 = new Node(-1);
		Node root4 = new Node(-1);
		Node root5 = new Node(1);
		root.setLeft(root1);
		root.setRight(root3);
		root1.setLeft(root2);
		root1.setRight(root4);
		root3.setRight(root5);
		return root;
	}

}
