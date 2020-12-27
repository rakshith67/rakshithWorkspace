package trees.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import trees.basic.Node;
import trees.basic.NodeRight;
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

	/**
	 * Given a binary tree, flatten it to a linked list in-place.
	 * 
	 * Link: https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
	 * 
	 */
	public void flatten(Node root) {
		flatList(root);
	}

	private Node flatList(Node root) {
		if (root == null) {
			return null;
		}
		Node leftFlat = flatList(root.getLeft());
		Node rightFlat = flatList(root.getRight());
		if (leftFlat != null) {
			root.setRight(leftFlat);
			while (leftFlat.getRight() != null) {
				leftFlat = leftFlat.getRight();
			}
			leftFlat.setRight(rightFlat);
		} else {
			root.setRight(rightFlat);
		}
		root.setLeft(null);
		return root;
	}

	int sum;

	/**
	 * Given the root of a Binary Search Tree (BST), convert it to a Greater Tree
	 * such that every key of the original BST is changed to the original key plus
	 * sum of all keys greater than the original key in BST. As a reminder, a binary
	 * search tree is a tree that satisfies these constraints: The left subtree of a
	 * node contains only nodes with keys less than the node's key. The right
	 * subtree of a node contains only nodes with keys greater than the node's key.
	 * Both the left and right subtrees must also be binary search trees.
	 *
	 * Link: https://leetcode.com/problems/convert-bst-to-greater-tree/
	 *
	 */
	public Node convertBST(Node root) {
		sum = 0;
		gst(root);
		return root;
	}

	private void gst(Node root) {
		if (root == null) {
			return;
		}
		gst(root.getRight());
		sum += root.getValue();
		root.setValue(sum);
		gst(root.getLeft());
	}

	/**
	 * Given an integer array with no duplicates. A maximum tree building on this
	 * array is defined as follow: The root is the maximum number in the array. The
	 * left subtree is the maximum tree constructed from left part subarray divided
	 * by the maximum number. The right subtree is the maximum tree constructed from
	 * right part subarray divided by the maximum number. Construct the maximum tree
	 * by the given array and output the root node of this tree.
	 * 
	 * Link: https://leetcode.com/problems/maximum-binary-tree/
	 * 
	 */
	public Node constructMaximumBinaryTree(int[] nums) {
		return constructBinaryTree(nums, 0, nums.length - 1);
	}

	private Node constructBinaryTree(int[] nums, int start, int end) {
		if (start > end) {
			return null;
		}
		if (start == end) {
			return new Node(nums[start]);
		}
		int index = findMax(nums, start, end);
		Node root = new Node(nums[index]);
		root.setLeft(constructBinaryTree(nums, start, index - 1));
		root.setRight(constructBinaryTree(nums, index + 1, end));
		return root;
	}

	private int findMax(int[] nums, int start, int end) {
		int index = -1;
		int max = Integer.MIN_VALUE;
		for (int i = start; i <= end; i++) {
			if (nums[i] > max) {
				max = nums[i];
				index = i;
			}
		}
		return index;
	}

	/**
	 * You are given a perfect binary tree where all leaves are on the same level,
	 * and every parent has two children. The binary tree has the following
	 * definition: Populate each next pointer to point to its next right node. If
	 * there is no next right node, the next pointer should be set to NULL.
	 * Initially, all next pointers are set to NULL.
	 * 
	 * Link:
	 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
	 * 
	 */
	public NodeRight connect(NodeRight root) {
		if (root == null || root.getLeft() == null)
			return root;

		root.getLeft().setNextRight(root.getRight());
		if (root.getNextRight() != null) {
			root.getRight().setNextRight(root.getNextRight().getLeft());
		}
		connect(root.getLeft());
		connect(root.getRight());

		return root;
	}

	/**
	 * Given a binary tree, write a function to get the maximum width of the given
	 * tree. The maximum width of a tree is the maximum width among all levels. The
	 * width of one level is defined as the length between the end-nodes (the
	 * leftmost and right most non-null nodes in the level, where the null nodes
	 * between the end-nodes are also counted into the length calculation. It is
	 * guaranteed that the answer will in the range of 32-bit signed integer.
	 * 
	 * Link: https://leetcode.com/problems/maximum-width-of-binary-tree/
	 * 
	 */
	public int widthOfBinaryTree(Node root) {
		return dfs(root, 0, 1, new ArrayList<Integer>(), new ArrayList<Integer>());
	}

	public int dfs(Node root, int level, int order, List<Integer> start, List<Integer> end) {
		if (root == null) {
			return 0;
		}
		if (start.size() == level) {
			start.add(order);
			end.add(order);
		} else {
			end.set(level, order);
		}
		int cur = end.get(level) - start.get(level) + 1;
		int left = dfs(root.getLeft(), level + 1, 2 * order, start, end);
		int right = dfs(root.getRight(), level + 1, 2 * order + 1, start, end);
		return Math.max(cur, Math.max(left, right));
	}

	/**
	 * We are given the head node root of a binary tree, where additionally every
	 * node's value is either a 0 or a 1. Return the same tree where every subtree
	 * (of the given tree) not containing a 1 has been removed. (Recall that the
	 * subtree of a node X is X, plus every node that is a descendant of X.)
	 * 
	 * Link: https://leetcode.com/problems/binary-tree-pruning/
	 * 
	 */
	public Node pruneTree(Node root) {
		removeSubTree(root);
		if (root.getLeft() == null && root.getRight() == null) {
			if (root.getValue() == 0) {
				return null;
			}
		}
		return root;
	}

	private boolean removeSubTree(Node root) {
		if (root == null) {
			return true;
		}
		boolean left = removeSubTree(root.getLeft());
		boolean right = removeSubTree(root.getRight());
		if (left) {
			root.setLeft(null);
		}
		if (right) {
			root.setRight(null);
		}
		return left && right && root.getValue() == 0;
	}

	int result;

	/**
	 * Given the root of a binary tree, find the maximum value V for which there
	 * exist different nodes A and B where V = |A.val - B.val| and A is an ancestor
	 * of B. A node A is an ancestor of B if either: any child of A is equal to B,
	 * or any child of A is an ancestor of B.
	 * 
	 * Link:
	 * https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/
	 * 
	 */
	public int maxAncestorDiff(Node root) {
		result = 0;
		fillMax(root, root.getValue(), root.getValue());
		return result;
	}

	private void fillMax(Node root, int min, int max) {
		if (root == null) {
			return;
		}
		if (Math.abs(root.getValue() - min) > result) {
			result = Math.abs(root.getValue() - min);
		}
		if (Math.abs(root.getValue() - max) > result) {
			result = Math.abs(root.getValue() - max);
		}
		if (root.getValue() < min) {
			min = root.getValue();
		} else if (root.getValue() > max) {
			max = root.getValue();
		}
		fillMax(root.getLeft(), min, max);
		fillMax(root.getRight(), min, max);
	}

	/**
	 * Given the root of a binary tree, determine if it is a complete binary tree.
	 * In a complete binary tree, every level, except possibly the last, is
	 * completely filled, and all nodes in the last level are as far left as
	 * possible. It can have between 1 and 2h nodes inclusive at the last level h.
	 * 
	 * Link: https://leetcode.com/problems/check-completeness-of-a-binary-tree/
	 * 
	 */
	public boolean isCompleteTree(Node root) {
		if (root == null) {
			return true;
		}
		Queue<Node> deque = new LinkedList<>();
		deque.add(root);
		while (null != deque.peek()) {
			Node current = deque.poll();
			deque.add(current.getLeft());
			deque.add(current.getRight());
		}
		while (!deque.isEmpty() && deque.peek() == null) {
			deque.poll();
		}
		return deque.isEmpty();
	}

	private int maxLevel = 0;
	private int sum2 = 0;

	/**
	 * Given a binary tree, return the sum of values of its deepest leaves.
	 * 
	 * Link: https://leetcode.com/problems/deepest-leaves-sum/
	 * 
	 */
	public int deepestLeavesSum(Node root) {
		if (root == null)
			return 0;
		calculateSumAtLevel(root, 0);
		return sum2;

	}

	private void calculateSumAtLevel(Node root, int level) {

		if (root == null)
			return;
		if (level > maxLevel) {
			sum2 = 0;
			maxLevel = level;
		}
		if (level == maxLevel) {
			sum2 = sum2 + root.getValue();
		}
		calculateSumAtLevel(root.getLeft(), level + 1);
		calculateSumAtLevel(root.getRight(), level + 1);
	}
}
