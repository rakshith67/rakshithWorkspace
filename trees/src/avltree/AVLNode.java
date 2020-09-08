package avltree;

public class AVLNode {

	private int value;

	private AVLNode left;

	private AVLNode right;

	private int height;

	public AVLNode(int value, AVLNode left, AVLNode right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public AVLNode(int value, int height) {
		this.value = value;
		this.left = null;
		this.right = null;
		this.height = height;
	}

	public AVLNode(int value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public AVLNode getLeft() {
		return left;
	}

	public void setLeft(AVLNode left) {
		this.left = left;
	}

	public AVLNode getRight() {
		return right;
	}

	public void setRight(AVLNode right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return String.valueOf(this.value);
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
