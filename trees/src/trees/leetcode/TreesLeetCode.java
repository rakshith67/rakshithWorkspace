package trees.leetcode;

import trees.basic.Node;
import trees.basic.TreeIntOperations;

public class TreesLeetCode {

	/**
	 * returns sum of left leaves. Link:
	 * https://leetcode.com/problems/sum-of-left-leaves/submissions/
	 * 
	 */
	public int sumOfLeftLeaves(Node root) {
		return sumOfLeavesLeft(root, false, 0);
	}

	private int sumOfLeavesLeft(Node root, boolean isLeft, int sum) {
		if (root == null) {
			return 0;
		}
		int leftSum = sumOfLeavesLeft(root.getLeft(), true, sum);
		int rightSum = sumOfLeavesLeft(root.getRight(), false, sum);
		if (isLeft && root.getLeft() == null && root.getRight() == null) {
			return root.getValue();
		}
		return leftSum + rightSum;
	}

	/**
	 * finds bottom left value of the tre Link:
	 * https://leetcode.com/problems/find-bottom-left-tree-value/
	 * 
	 */
	public int findBottomLeftValue(Node root) {
		TreeIntOperations treeIntOperations = new TreeIntOperations();
		int height = treeIntOperations.height(root);
		int[] array = new int[1];
		levelOrderTraversal(array, root, height - 1, 0);
		return array[0];
	}

	private void levelOrderTraversal(int[] array, Node root, int depth, int currentDepth) {
		if (root == null) {
			return;
		}
		if (depth == currentDepth && array[0] == -876) {
			array[0] = root.getValue();
			return;
		}
		levelOrderTraversal(array, root.getLeft(), depth, currentDepth + 1);
		levelOrderTraversal(array, root.getRight(), depth, currentDepth + 1);
	}
}
