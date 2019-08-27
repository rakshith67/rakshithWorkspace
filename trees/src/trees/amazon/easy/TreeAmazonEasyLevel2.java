package trees.amazon.easy;

import trees.Node;
import trees.TreeIntOperations;

public class TreeAmazonEasyLevel2 {

	/**
	 * prints the nodes that are k distance from the root of the tree.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/k-distance-from-root/1
	 * 
	 * @param root - Root of the tree
	 */
	public void printNodesAtKLevel(Node root, int k) {
		printNodesAtKDistance(root, k, 0);
	}

	private void printNodesAtKDistance(Node root, int k, int i) {
		if (root == null) {
			return;
		} else if (i == k) {
			System.out.print(root.getValue() + " ");
		}
		printNodesAtKDistance(root.getLeft(), k, i + 1);
		printNodesAtKDistance(root.getRight(), k, i + 1);
	}

	/**
	 * evaluates the difference of add and even level sum of the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/max-level-sum-in-binary-tree/1
	 * 
	 * @param root - Root of the tree
	 */
	public int diffOddEvenLevelSum(Node root) {
		TreeIntOperations treeOperations = new TreeIntOperations();
		int height = treeOperations.height(root);
		int oddSum = 0;
		int evenSum = 0;
		int[] a = new int[height];
		calculateLevelSum(root, 0, a);
		for (int i = 0; i < height; i++) {
			if (i % 2 == 0) {
				oddSum = oddSum + a[i];
			} else {
				evenSum = evenSum + a[i];
			}
		}
		return oddSum - evenSum;
	}

	private void calculateLevelSum(Node root, int i, int[] a) {
		if (root != null) {
			a[i] = a[i] + root.getValue();
			calculateLevelSum(root.getLeft(), i + 1, a);
			calculateLevelSum(root.getRight(), i + 1, a);
		}
	}

	/**
	 * prints the nodes that are k distance from the root of the tree.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/k-distance-from-root/1
	 * 
	 * @param root1, root2 - Root of the trees
	 */
	public boolean isIdenticalTrees(Node root1, Node root2) {
		if ((root1 == null && root2 != null) || (root2 == null && root1 != null)) {
			return Boolean.FALSE;
		}
		if (root1 != null) {
			if (root1.getValue() != root2.getValue()) {
				return Boolean.FALSE;
			} else {
				return isIdenticalTrees(root1.getLeft(), root2.getLeft())
						&& isIdenticalTrees(root1.getRight(), root2.getRight());
			}
		}

		return Boolean.TRUE;
	}

	/**
	 * transforms the tree into sum tree.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/transform-to-sum-tree/1
	 * 
	 * @param root - Root of the tree
	 */
	public void toSumTree(Node root) {
		throw new UnsupportedOperationException(TreesAmazonEasyLevel.NOT_IMPLEMENTED);
	}
}
