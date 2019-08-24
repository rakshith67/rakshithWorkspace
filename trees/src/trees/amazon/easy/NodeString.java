package trees.amazon.easy;

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
}
