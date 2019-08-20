package trees;

public class TreeIntOperations {

	public void checkIfBalanced(Node root) {
		if (balancedTree(root)) {
			System.out.println("Balanced Tree");
		} else {
			System.out.println("Unbalanced Tree");
		}
	}

	private boolean balancedTree(Node root) {
		if (root == null) {
			return true;
		} else if (root.getLeft() == null && root.getRight() == null) {
			return true;
		}
		int leftHeight = height(root.getLeft());
		int rightHeight = height(root.getRight());
		int diff = leftHeight - rightHeight;
		if (Math.abs(diff) > 1) {
			return false;
		} else {
			return true;
		}
	}

	public int sizeOfTree(Node root) {
		if (root == null) {
			return 0;
		} else {
			int leftSize = 1 + sizeOfTree(root.getLeft());
			int rightSize = 1 + sizeOfTree(root.getRight());
			return leftSize + rightSize - 1;
		}
	}

	public int height(Node root) {
		if (root == null) {
			return 0;
		} else if (root.getLeft() == null && root.getRight() == null) {
			return 1;
		}
		int leftHeight = 1 + height(root.getLeft());
		int rightHeight = 1 + height(root.getRight());
		int height = leftHeight > rightHeight ? leftHeight : rightHeight;
		return height;
	}
}
