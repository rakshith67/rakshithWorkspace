package trees.leetcode;

import java.util.ArrayList;
import java.util.List;

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
	 * finds bottom left value of the tree Link:
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

	/**
	 * Given a binary tree and a sum, find all root-to-leaf paths where each path's
	 * sum equals the given sum. Link: https://leetcode.com/problems/path-sum-ii/
	 * 
	 */
	public List<List<Integer>> pathSum(Node root, int sum) {
		List<List<Integer>> list = new ArrayList<>();
		printRootToLeafPaths(root, list, new ArrayList<Integer>(), sum);
		return list;
	}

	private void printRootToLeafPaths(Node root, List<List<Integer>> list, List<Integer> path, int sum) {
		if (root == null) {
			return;
		}
		path.add(root.getValue());
		if (root.getLeft() == null && root.getRight() == null && sum == root.getValue()) {
			list.add(new ArrayList<>(path));
		} else {
			printRootToLeafPaths(root.getLeft(), list, path, sum - root.getValue());
			printRootToLeafPaths(root.getRight(), list, path, sum - root.getValue());
		}
		path.remove(path.size() - 1);
	}
}
