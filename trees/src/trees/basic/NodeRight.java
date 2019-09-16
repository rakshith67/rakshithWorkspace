package trees.basic;

public class NodeRight {

	int data;

	NodeRight left;

	NodeRight right;

	NodeRight nextRight;

	NodeRight(int data) {
		this.data = data;
		left = null;
		right = null;
		nextRight = null;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public NodeRight getLeft() {
		return left;
	}

	public void setLeft(NodeRight left) {
		this.left = left;
	}

	public NodeRight getRight() {
		return right;
	}

	public void setRight(NodeRight right) {
		this.right = right;
	}

	public NodeRight getNextRight() {
		return nextRight;
	}

	public void setNextRight(NodeRight nextRight) {
		this.nextRight = nextRight;
	}

	public static NodeRight createTree() {
		NodeRight root = new NodeRight(1);
		NodeRight root2 = new NodeRight(2);
		NodeRight root3 = new NodeRight(3);
		root.setLeft(root2);
		root.setRight(root3);
		NodeRight root4 = new NodeRight(4);
		NodeRight root5 = new NodeRight(5);
		root2.setLeft(root4);
		root2.setRight(root5);
		NodeRight root6 = new NodeRight(6);
		NodeRight root7 = new NodeRight(7);
		root3.setLeft(root6);
		root3.setRight(root7);
		root4.setLeft(new NodeRight(8));
		root4.setRight(new NodeRight(9));
		root5.setLeft(new NodeRight(10));
		root5.setRight(new NodeRight(11));
		root6.setLeft(new NodeRight(12));
		root6.setRight(new NodeRight(13));
		root7.setLeft(new NodeRight(14));
		root7.setRight(new NodeRight(15));
		return root;
	}

	@Override
	public String toString() {
		return String.valueOf(this.data);
	}
}
