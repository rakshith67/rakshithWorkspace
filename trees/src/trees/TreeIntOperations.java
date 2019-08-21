package trees;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 
 * @author Rakshith
 */
public class TreeIntOperations {

	/**
	 * determines whether the tree is balanced or not. A Tree is balanced if
	 * difference in heights of let and right is less than or equal to 1
	 * 
	 * @param root - the root of the tree
	 */
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

	/**
	 * determines the depth or height of the tree.
	 * 
	 * @param root - the root of the tree
	 */
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

	/**
	 * determines the size or number of nodes of the tree.
	 * 
	 * @param root - the root of the tree
	 */
	public int sizeOfTree(Node root) {
		if (root == null) {
			return 0;
		} else {
			return sizeOfTree(root.getRight()) + sizeOfTree(root.getLeft()) + 1;
		}
	}

	/**
	 * determines the number of leaves of the tree.
	 * 
	 * @param root - the root of the tree
	 */
	public int numberOfLeaves(Node root) {
		if (root == null) {
			return 0;
		} else if (root.getLeft() == null && root.getRight() == null) {
			return 1;
		} else {
			return numberOfLeaves(root.getLeft()) + numberOfLeaves(root.getRight());
		}
	}

	/**
	 * determines the sum of values of all nodes of the tree.
	 * 
	 * @param root - the root of the tree
	 */
	public int sumOfTree(Node root) {
		if (root == null) {
			return 0;
		} else if (root.getLeft() == null && root.getRight() == null) {
			return root.getValue();
		} else {
			return root.getValue() + sumOfTree(root.getLeft()) + sumOfTree(root.getRight());
		}
	}

	/**
	 * determines the sum of values of all leaf nodes of the tree.
	 * 
	 * @param root - the root of the tree
	 */
	public int sumOfLeaves(Node root) {
		if (root == null) {
			return 0;
		} else if (root.getLeft() == null && root.getRight() == null) {
			return root.getValue();
		} else {
			return sumOfLeaves(root.getLeft()) + sumOfLeaves(root.getRight());
		}
	}

	/**
	 * determines the maximum element in the tree.
	 * 
	 * @param root - the root of the tree
	 */
	public int maxElement(Node root) {
		if (root == null) {
			return 0;
		}
		Deque<Node> deque = new ArrayDeque<>();
		deque.add(root);
		int maximum = root.getValue();
		while (!deque.isEmpty()) {
			Node currentNode = deque.removeFirst();
			if (currentNode.getValue() > maximum) {
				maximum = currentNode.getValue();
			}
			if (currentNode.getRight() != null) {
				deque.add(currentNode.getRight());
			}
			if (currentNode.getLeft() != null) {
				deque.add(currentNode.getLeft());
			}
		}
		return maximum;
	}

	/**
	 * determines the maximum element in the tree.
	 * 
	 * @param root - the root of the tree
	 */
	public int minElement(Node root) {
		if (root == null) {
			return 0;
		}
		Deque<Node> deque = new ArrayDeque<>();
		deque.add(root);
		int minimum = root.getValue();
		while (!deque.isEmpty()) {
			Node currentNode = deque.removeFirst();
			if (currentNode.getValue() < minimum) {
				minimum = currentNode.getValue();
			}
			if (currentNode.getRight() != null) {
				deque.add(currentNode.getRight());
			}
			if (currentNode.getLeft() != null) {
				deque.add(currentNode.getLeft());
			}
		}
		return minimum;
	}

}
