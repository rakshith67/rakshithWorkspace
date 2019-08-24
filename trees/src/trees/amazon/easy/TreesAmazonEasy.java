package trees.amazon.easy;

import trees.Node;
import trees.TreeIntOperations;

public class TreesAmazonEasy {

	public static final String NOT_IMPLEMENTED = "Not implemented yet.";

	/**
	 * determines whether the both trees are mirror trees or not.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/mirror-tree/1
	 * 
	 * @param root1 - Root of the first tree
	 * @param root2 - Root of the second tree
	 */
	public void mirrorTree(Node root1, Node root2) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED);
	}

	/**
	 * evaluates the expression tree and gives the output.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/expression-tree/1
	 * 
	 * @param root - Root of the tree
	 */
	public int expressionTree(Node root1, Node root2) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED);
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
	public void fullBinaryTree(Node root) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED);
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
