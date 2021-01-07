package trees.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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

	int currentMax;

	/**
	 * Given the root of a binary tree, the depth of each node is the shortest
	 * distance to the root. Return the smallest subtree such that it contains all
	 * the deepest nodes in the original tree. A node is called the deepest if it
	 * has the largest depth possible among any node in the entire tree. The subtree
	 * of a node is tree consisting of that node, plus the set of all descendants of
	 * that node. Note: This question is the same as 1123:
	 * https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
	 * 
	 * Link:
	 * https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/
	 * 
	 */
	public Node subtreeWithAllDeepest(Node root) {
		List<Node> list = new ArrayList<>();
		currentMax = -1;
		markDeepestNodes(root, 0, list);
		while (list.size() != 1) {
			Node first = list.remove(list.size() - 1);
			Node second = list.remove(list.size() - 1);
			list.add(lca(root, first, second));
		}
		return list.get(0);
	}

	private void markDeepestNodes(Node root, int level, List<Node> list) {
		if (root == null) {
			return;
		}
		if (level > currentMax) {
			list.clear();
			list.add(root);
			currentMax = level;
		}
		if (level == currentMax) {
			list.add(root);
		}
		markDeepestNodes(root.getLeft(), level + 1, list);
		markDeepestNodes(root.getRight(), level + 1, list);
	}

	private Node lca(Node root, Node node1, Node node2) {
		if (root == null) {
			return null;
		}
		if (root == node1 || root == node2) {
			return root;
		}
		Node l1 = lca(root.getLeft(), node1, node2);
		Node l2 = lca(root.getRight(), node1, node2);
		if (l1 != null && l2 != null) {
			return root;
		}
		if (l1 == null && l2 == null) {
			return null;
		}
		return l1 != null ? l1 : l2;
	}

	/**
	 * For a binary tree T, we can define a flip operation as follows: choose any
	 * node, and swap the left and right child subtrees. A binary tree X is flip
	 * equivalent to a binary tree Y if and only if we can make X equal to Y after
	 * some number of flip operations. Given the roots of two binary trees root1 and
	 * root2, return true if the two trees are flip equivelent or false otherwise.
	 * 
	 * Link: https://leetcode.com/problems/flip-equivalent-binary-trees/
	 * 
	 */
	public boolean flipEquiv(Node root1, Node root2) {
		if (root1 == null && root2 == null) {
			return true;
		} else if (root1 == null || root2 == null || root1.getValue() != root2.getValue()) {
			return false;
		}
		return (flipEquiv(root1.getLeft(), root2.getLeft()) || flipEquiv(root1.getLeft(), root2.getRight()))
				&& (flipEquiv(root1.getRight(), root2.getRight()) || flipEquiv(root1.getRight(), root2.getLeft()));
	}

	/**
	 * Given a complete binary tree, count the number of nodes. Note: Definition of
	 * a complete binary tree from Wikipedia: In a complete binary tree every level,
	 * except possibly the last, is completely filled, and all nodes in the last
	 * level are as far left as possible. It can have between 1 and 2h nodes
	 * inclusive at the last level h.
	 * 
	 * Link: https://leetcode.com/problems/count-complete-tree-nodes/
	 * 
	 */
	public int countNodes(Node root) {
		int leftDepth = leftDepth(root);
		int rightDepth = rightDepth(root);
		if (leftDepth == rightDepth) {
			return (1 << leftDepth) - 1;
		} else {
			return 1 + countNodes(root.getLeft()) + countNodes(root.getRight());
		}
	}

	private int rightDepth(Node root) {
		int depth = 0;
		while (root != null) {
			root = root.getRight();
			depth++;
		}
		return depth;
	}

	private int leftDepth(Node root) {
		int depth = 0;
		while (root != null) {
			root = root.getLeft();
			depth++;
		}
		return depth;
	}

	int longestPath;

	/**
	 * Given the root of a binary tree, return the length of the longest path, where
	 * each node in the path has the same value. This path may or may not pass
	 * through the root. The length of the path between two nodes is represented by
	 * the number of edges between them.
	 * 
	 * Link: https://leetcode.com/problems/longest-univalue-path/
	 * 
	 */
	public int longestUnivaluePath(Node root) {
		longestPath = 0;
		univalue(root);
		return longestPath;
	}

	private int univalue(Node root) {
		if (root == null) {
			return 0;
		}
		int left = univalue(root.getLeft());
		int right = univalue(root.getRight());
		int leftAns = 0;
		int rightAns = 0;
		if (root.getRight() != null && root.getRight().getValue() == root.getValue()) {
			rightAns = right + 1;
		}
		if (root.getLeft() != null && root.getLeft().getValue() == root.getValue()) {
			leftAns = left + 1;
		}
		longestPath = Math.max(longestPath, leftAns + rightAns);
		return Math.max(leftAns, rightAns);
	}

	/**
	 * Given a root node reference of a BST and a key, delete the node with the
	 * given key in the BST. Return the root node reference (possibly updated) of
	 * the BST. Basically, the deletion can be divided into two stages: Search for a
	 * node to remove. If the node is found, delete the node. Follow up: Can you
	 * solve it with time complexity O(height of tree)?
	 * 
	 * Link: https://leetcode.com/problems/delete-node-in-a-bst/
	 * 
	 */
	public Node deleteNode(Node root, int key) {
		if (root == null) {
			return null;
		}
		if (root.getValue() > key) {
			root.setLeft(deleteNode(root.getLeft(), key));
		} else if (root.getValue() < key) {
			root.setRight(deleteNode(root.getRight(), key));
		} else {
			if (root.getLeft() == null && root.getRight() == null) {
				root = null;
			} else if (root.getLeft() == null) {
				root = root.getRight();
			} else if (root.getRight() == null) {
				root = root.getLeft();
			} else {
				Node node = root.getRight();
				while (node.getLeft() != null) {
					node = node.getLeft();
				}
				root.setValue(node.getValue());
				// node = node.right;
				root.setRight(deleteNode(root.getRight(), node.getValue()));
			}
		}
		return root;
	}

	/**
	 * Given the root of a binary tree, each node in the tree has a distinct value.
	 * After deleting all nodes with a value in to_delete, we are left with a forest
	 * (a disjoint union of trees). Return the roots of the trees in the remaining
	 * forest. You may return the result in any order.
	 * 
	 * Link: https://leetcode.com/problems/delete-nodes-and-return-forest/
	 * 
	 */
	public List<Node> delNodes(Node root, int[] to_delete, boolean space) {
		List<Node> list = new ArrayList<>();
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < to_delete.length; i++) {
			set.add(to_delete[i]);
		}
		if (space) {
			Node dummy = new Node(-895758);
			dummy.setLeft(root);
			fillList(root, dummy, set, list, true);
			if (dummy.getLeft() != null) {
				list.add(root);
			}
		} else {
			fillList(root, set, list);
			if (!set.contains(root.getValue()))
				list.add(root);
		}
		return list;
	}

	private Node fillList(Node root, Set<Integer> set, List<Node> list) {
		if (root == null) {
			return null;
		}
		if (set.contains(root.getValue())) {
			if (root.getLeft() != null && !set.contains(root.getLeft().getValue()))
				list.add(root.getLeft());
			if (root.getRight() != null && !set.contains(root.getRight().getValue()))
				list.add(root.getRight());
			fillList(root.getLeft(), set, list);
			fillList(root.getRight(), set, list);
			return null;
		}
		root.setLeft(fillList(root.getLeft(), set, list));
		root.setRight(fillList(root.getRight(), set, list));
		return root;
	}

	private void fillList(Node root, Node parent, Set<Integer> set, List<Node> list, boolean isLeft) {
		if (root == null) {
			return;
		}
		if (set.contains(root.getValue())) {
			if (isLeft) {
				parent.setLeft(null);
			} else {
				parent.setRight(null);
			}
			if (root.getLeft() != null && !set.contains(root.getLeft().getValue())) {
				list.add(root.getLeft());
			}
			if (root.getRight() != null && !set.contains(root.getRight().getValue())) {
				list.add(root.getRight());
			}
		}
		fillList(root.getLeft(), root, set, list, true);
		fillList(root.getRight(), root, set, list, false);
	}

	/**
	 * Return the root node of a binary search tree that matches the given preorder
	 * traversal. (Recall that a binary search tree is a binary tree where for every
	 * node, any descendant of node.left has a value < node.val, and any descendant
	 * of node.right has a value > node.val. Also recall that a preorder traversal
	 * displays the value of the node first, then traverses node.left, then
	 * traverses node.right.) It's guaranteed that for the given test cases there is
	 * always possible to find a binary search tree with the given requirements.
	 * 
	 * Link:
	 * https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
	 * 
	 */
	public Node bstFromPreorder(int[] preorder) {
		return constructTree(preorder, 0, preorder.length - 1);
	}

	private Node constructTree(int[] preorder, int start, int end) {
		if (start > end) {
			return null;
		}
		Node root = new Node(preorder[start]);
		if (start == end) {
			return root;
		}
		int rightIndex = getRightIndex(preorder, start, end);
		if (rightIndex == -1) {
			root.setLeft(constructTree(preorder, start + 1, end));
		} else if (rightIndex == start + 1) {
			root.setRight(constructTree(preorder, rightIndex, end));
		} else {
			root.setLeft(constructTree(preorder, start + 1, rightIndex - 1));
			root.setRight(constructTree(preorder, rightIndex, end));
		}
		return root;
	}

	private int getRightIndex(int[] preorder, int index, int end) {
		if (index >= preorder.length) {
			return -1;
		}
		for (int i = index + 1; i <= end; i++) {
			if (preorder[i] > preorder[index]) {
				return i;
			}
		}
		return -1;
	}

	int moves;

	/**
	 * You are given the root of a binary tree with n nodes where each node in the
	 * tree has node.val coins and there are n coins total. In one move, we may
	 * choose two adjacent nodes and move one coin from one node to another. (A move
	 * may be from parent to child, or from child to parent.) Return the number of
	 * moves required to make every node have exactly one coin.
	 * 
	 * Link: https://leetcode.com/problems/distribute-coins-in-binary-tree/
	 * 
	 */
	public int distributeCoins(Node root) {
		moves = 0;
		distrbuteCoins(root);
		return moves;
	}

	private int distrbuteCoins(Node root) {
		if (root == null) {
			return 0;
		}
		int left = distrbuteCoins(root.getLeft());
		int right = distrbuteCoins(root.getRight());
		moves += Math.abs(left) + Math.abs(right);
		return root.getValue() + left + right - 1;
	}

	Map<Integer, List<Node>> dpCache = new HashMap<>();

	/**
	 * A full binary tree is a binary tree where each node has exactly 0 or 2
	 * children. Return a list of all possible full binary trees with N nodes. Each
	 * element of the answer is the root node of one possible tree. Each node of
	 * each tree in the answer must have node.val = 0. You may return the final list
	 * of trees in any order.
	 * 
	 * Link: https://leetcode.com/problems/all-possible-full-binary-trees/
	 * 
	 */
	public List<Node> allPossibleFBT(int N) {
		if (!dpCache.containsKey(N)) {
			List<Node> list = new LinkedList<>();
			if (N == 1) {
				list.add(new Node(0));
			} else if (N % 2 == 1) {
				for (int left = 1; left < N; left = left + 2) {
					int right = N - 1 - left;
					for (Node leftTree : allPossibleFBT(left)) {
						for (Node rightTree : allPossibleFBT(right)) {
							Node root = new Node(0);
							root.setLeft(leftTree);
							root.setRight(rightTree);
							list.add(root);
						}
					}
				}
			}
			dpCache.put(N, list);
		}
		return dpCache.get(N);
	}

	int pathMaximum;

	/**
	 * Given a non-empty binary tree, find the maximum path sum. For this problem, a
	 * path is defined as any node sequence from some starting node to any node in
	 * the tree along the parent-child connections. The path must contain at least
	 * one node and does not need to go through the root.
	 * 
	 * Link: https://leetcode.com/problems/binary-tree-maximum-path-sum/
	 * 
	 */
	public int maxPathSum(Node root) {
		pathMaximum = Integer.MIN_VALUE;
		getMax(root);
		return pathMaximum;
	}

	private int getMax(Node root) {
		if (root == null) {
			return 0;
		}
		int left = Math.max(0, getMax(root.getLeft()));
		int right = Math.max(0, getMax(root.getRight()));
		pathMaximum = Math.max(left + right + root.getValue(), pathMaximum);
		return Math.max(left, right) + root.getValue();
	}

	Node first = null;
	Node second = null;
	Node prev = new Node(Integer.MIN_VALUE);

	/**
	 * You are given the root of a binary search tree (BST), where exactly two nodes
	 * of the tree were swapped by mistake. Recover the tree without changing its
	 * structure. Follow up: A solution using O(n) space is pretty straight forward.
	 * Could you devise a constant space solution?
	 * 
	 * Link: https://leetcode.com/problems/recover-binary-search-tree/
	 * 
	 */
	public void recoverTree(Node root) {
		inOrder(root);
		int temp = first.getValue();
		first.setValue(second.getValue());
		second.setValue(temp);
	}

	private void inOrder(Node root) {
		if (root == null) {
			return;
		}
		inOrder(root.getLeft());
		if (first == null && prev.getValue() > root.getValue()) {
			first = prev;
		}
		if (first != null && prev.getValue() > root.getValue()) {
			second = root;
		}
		prev = root;
		inOrder(root.getRight());
	}

}
