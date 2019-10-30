package trees.basic;

public class NodeString {
	private String value;

	private NodeString left;

	private NodeString right;

	public NodeString(String value, NodeString left, NodeString right) {
		super();
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public NodeString(String value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public NodeString getLeft() {
		return left;
	}

	public void setLeft(NodeString left) {
		this.left = left;
	}

	public NodeString getRight() {
		return right;
	}

	public void setRight(NodeString right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return String.valueOf(this.value);
	}

	public static NodeString createTree() {
		NodeString root = new NodeString("1");
		NodeString root2 = new NodeString("2");
		NodeString root3 = new NodeString("3");
		root.setLeft(root2);
		root.setRight(root3);
		NodeString root4 = new NodeString("4");
		NodeString root5 = new NodeString("5");
		root2.setLeft(root4);
		root2.setRight(root5);
		NodeString root6 = new NodeString("6");
		NodeString root7 = new NodeString("7");
		root3.setLeft(root6);
		root3.setRight(root7);
		root4.setLeft(new NodeString("8"));
		root4.setRight(new NodeString("9"));
		root5.setLeft(new NodeString("10"));
		root5.setRight(new NodeString("11"));
		root6.setLeft(new NodeString("12"));
		root6.setRight(new NodeString("13"));
		root7.setLeft(new NodeString("14"));
		root7.setRight(new NodeString("15"));
		return root;
	}
}
