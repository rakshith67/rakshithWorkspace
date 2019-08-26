package trees.amazon.easy;

import trees.Node;
import trees.TreeIntOperations;
import trees.TreeTraversals;

/**
 * 
 * @author Rakshith
 *
 */
public class TreesAmazonEasyLevel {

	public static final String NOT_IMPLEMENTED = "Not implemented yet.";

	/**
	 * determines whether the both trees are mirror trees or not.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/mirror-tree/1
	 * 
	 * @param root - Root of the first tree
	 */
	public void mirrorTree(Node root) {
		makeMirrorTree(root);
		TreeTraversals treeTraversals = new TreeTraversals();
		treeTraversals.inOrderTraversal(root);
	}

	private void makeMirrorTree(Node root) {
		if (root == null) {
			return;
		}
		if (root.getLeft() != null && root.getRight() != null) {
			Node temp = root.getLeft();
			root.setLeft(root.getRight());
			root.setRight(temp);
			makeMirrorTree(root.getLeft());
			makeMirrorTree(root.getRight());
		} else if (root.getRight() != null) {
			Node temp = root.getRight();
			root.setLeft(root.getRight());
			root.setRight(null);
			makeMirrorTree(temp);
		} else {
			Node temp = root.getLeft();
			root.setRight(root.getLeft());
			root.setLeft(null);
			makeMirrorTree(temp);
		}
	}

	/**
	 * evaluates the expression tree and gives the output.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/expression-tree/1
	 * 
	 * @param root - Root of the tree
	 */
	public int expressionTree(NodeString root) {
		if (root == null) {
			return 0;
		} else if (root.getLeft() == null && root.getRight() == null) {
			return Integer.valueOf(root.getValue());
		}
		int leftValue = expressionTree(root.getLeft());
		int rightValue = expressionTree(root.getRight());
		if (root.getValue().equals("*")) {
			return leftValue * rightValue;
		} else if (root.getValue().equals("+")) {
			return leftValue + rightValue;
		} else if (root.getValue().equals("-")) {
			return leftValue - rightValue;
		} else {
			return leftValue / rightValue;
		}
	}

	/**
	 * evaluates the max level sum of the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/max-level-sum-in-binary-tree/1
	 * 
	 * @param root - Root of the tree
	 */
	public int maxLevelSum(Node root) {
		TreeIntOperations treeOperations = new TreeIntOperations();
		int height = treeOperations.height(root);
		int maximum = 0;
		int[] a = new int[height];
		calculateLevelSum(root, 0, a);
		for (int i = 0; i < height; i++) {
			if (a[i] > maximum) {
				maximum = a[i];
			}
		}
		return maximum;
	}

	private void calculateLevelSum(Node root, int i, int[] a) {
		if (root != null) {
			a[i] = a[i] + root.getValue();
			calculateLevelSum(root.getLeft(), i + 1, a);
			calculateLevelSum(root.getRight(), i + 1, a);
		}
	}

	/**
	 * determines whether the tree is full binary tree or not.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/full-binary-tree/1
	 * 
	 * @param root - Root of the tree
	 */
	public boolean fullBinaryTree(Node root) {
		if (root == null) {
			return Boolean.TRUE;
		} else if ((root.getLeft() != null && root.getRight() == null)
				|| root.getLeft() == null && root.getRight() != null) {
			return Boolean.FALSE;
		}
		return fullBinaryTree(root.getLeft()) && fullBinaryTree(root.getRight());
	}

	/**
	 * determines whether the tree is full binary tree or not.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/children-sum-parent/1
	 * 
	 * @param root - Root of the tree
	 */
	public boolean childSumParentTree(Node root) {
		if (root == null || root.getLeft() == null && root.getRight() == null) {
			return true;
		} else if ((root.getLeft() != null && root.getRight() != null
				&& root.getLeft().getValue() + root.getRight().getValue() != root.getValue())
				|| (root.getRight() == null && root.getValue() != root.getLeft().getValue())
				|| (root.getLeft() == null && root.getValue() != root.getRight().getValue())) {
			return Boolean.FALSE;
		}
		return childSumParentTree(root.getLeft()) && childSumParentTree(root.getRight());
	}

}
