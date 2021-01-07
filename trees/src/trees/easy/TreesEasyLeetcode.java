package trees.easy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import trees.basic.Node;

public class TreesEasyLeetcode {

	int sum;

	/**
	 * You are given the root of a binary tree where each node has a value 0 or 1.
	 * Each root-to-leaf path represents a binary number starting with the most
	 * significant bit. For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this
	 * could represent 01101 in binary, which is 13. For all leaves in the tree,
	 * consider the numbers represented by the path from the root to that leaf.
	 * Return the sum of these numbers. The answer is guaranteed to fit in a 32-bits
	 * integer.
	 * 
	 * Link: https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/
	 * 
	 */
	public int sumRootToLeaf(Node root) {
		sum = 0;
		fillSum(root, 0);
		return sum;
	}

	private void fillSum(Node root, int current) {
		if (root == null) {
			return;
		}
		current = (current * 2) + root.getValue();
		if (root.getLeft() == null && root.getRight() == null) {
			sum += current;
		} else {
			fillSum(root.getLeft(), current);
			fillSum(root.getRight(), current);
		}
	}

	Node next = null;

	/**
	 * Given the root of a binary search tree, rearrange the tree in in-order so
	 * that the leftmost node in the tree is now the root of the tree, and every
	 * node has no left child and only one right child.
	 * 
	 * Link: https://leetcode.com/problems/increasing-order-search-tree/
	 * 
	 */
	public Node increasingBST(Node root) {
		fillNext(root);
		return next;
	}

	private void fillNext(Node root) {
		if (root == null) {
			return;
		}
		fillNext(root.getRight());
		root.setRight(next);
		next = root;
		fillNext(root.getLeft());
		root.setLeft(null);
	}

	/**
	 * Consider all the leaves of a binary tree, from left to right order, the
	 * values of those leaves form a leaf value sequence. For example, in the given
	 * tree above, the leaf value sequence is (6, 7, 4, 9, 8). Two binary trees are
	 * considered leaf-similar if their leaf value sequence is the same. Return true
	 * if and only if the two given trees with head nodes root1 and root2 are
	 * leaf-similar.
	 * 
	 * Link: https://leetcode.com/problems/leaf-similar-trees/
	 * 
	 */
	public boolean leafSimilar(Node root1, Node root2) {
		StringBuilder builder1 = new StringBuilder();
		fillLeaves(root1, builder1);
		StringBuilder builder2 = new StringBuilder();
		fillLeaves(root2, builder2);
		return builder1.toString().equals(builder2.toString());
	}

	private void fillLeaves(Node root, StringBuilder builder) {
		if (root == null) {
			return;
		}
		if (root.getLeft() == null && root.getRight() == null) {
			builder.append(root.getValue());
			builder.append('*');
		} else {
			fillLeaves(root.getLeft(), builder);
			fillLeaves(root.getRight(), builder);
		}
	}

	/**
	 * Given a non-empty binary tree, return the average value of the nodes on each
	 * level in the form of an array.
	 * 
	 * Link: https://leetcode.com/problems/average-of-levels-in-binary-tree/
	 * 
	 */
	public List<Double> averageOfLevels(Node root) {
		List<Integer> count = new ArrayList<>();
		List<Double> sum = new ArrayList<>();
		fillListWithSum(root, 0, count, sum);
		for (int i = 0; i < sum.size(); i++) {
			sum.set(i, sum.get(i) / count.get(i));
		}
		return sum;
	}

	private void fillListWithSum(Node root, int level, List<Integer> count, List<Double> sum) {
		if (root == null) {
			return;
		}
		if (sum.size() == level) {
			sum.add(root.getValue() * 1.0);
			count.add(1);
		} else {
			sum.set(level, sum.get(level) + (root.getValue() * 1.0));
			count.set(level, count.get(level) + 1);
		}
		fillListWithSum(root.getLeft(), level + 1, count, sum);
		fillListWithSum(root.getRight(), level + 1, count, sum);
	}

	/**
	 * Given the root of a binary search tree and the lowest and highest boundaries
	 * as low and high, trim the tree so that all its elements lies in [low, high].
	 * Trimming the tree should not change the relative structure of the elements
	 * that will remain in the tree (i.e., any node's descendant should remain a
	 * descendant). It can be proven that there is a unique answer. Return the root
	 * of the trimmed binary search tree. Note that the root may change depending on
	 * the given bounds.
	 * 
	 * Link: https://leetcode.com/problems/trim-a-binary-search-tree/
	 * 
	 */
	public Node trimBST(Node root, int low, int high) {
		Node dummy = new Node();
		dummy.setLeft(root);
		trim(root, dummy, low, high, true);
		return dummy.getLeft();
	}

	private void trim(Node root, Node parent, int low, int high, boolean left) {
		if (root == null) {
			return;
		}
		if (root.getValue() < low) {
			if (left) {
				parent.setLeft(root.getRight());
				trim(root.getRight(), parent, low, high, true);
			} else {
				parent.setRight(root.getRight());
				trim(root.getRight(), parent, low, high, false);
			}
			return;
		}
		if (root.getValue() > high) {
			if (left) {
				parent.setLeft(root.getLeft());
				trim(root.getLeft(), parent, low, high, true);
			} else {
				parent.setRight(root.getLeft());
				trim(root.getLeft(), parent, low, high, false);
			}
			return;
		}
		trim(root.getLeft(), root, low, high, true);
		trim(root.getRight(), root, low, high, false);
	}

	/**
	 * Given the root of a Binary Search Tree and a target number k, return true if
	 * there exist two elements in the BST such that their sum is equal to the given
	 * target.
	 * 
	 * Link: https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
	 * 
	 */
	public boolean findTarget(Node root, int k) {
		Set<Integer> set = new HashSet<>();
		return hasSum(root, set, k);
	}

	private boolean hasSum(Node root, Set<Integer> set, int k) {
		if (root == null) {
			return false;
		}
		if (set.contains(k - root.getValue())) {
			return true;
		}
		set.add(root.getValue());
		return hasSum(root.getLeft(), set, k) || hasSum(root.getRight(), set, k);
	}

	int result;
	Integer prev;

	/**
	 * Given a Binary Search Tree (BST) with the root node root, return the minimum
	 * difference between the values of any two different nodes in the tree.
	 * 
	 * Link: https://leetcode.com/problems/minimum-distance-between-bst-nodes/
	 * 
	 */
	public int minDiffInBST(Node root) {
		prev = null;
		result = Integer.MAX_VALUE;
		minimumDiff(root);
		return result;
	}

	private void minimumDiff(Node root) {
		if (root == null) {
			return;
		}
		minimumDiff(root.getLeft());
		if (prev != null && root.getValue() - prev < result) {
			result = root.getValue() - prev;
		}
		prev = root.getValue();
		minimumDiff(root.getRight());
	}

	/**
	 * Given two non-empty binary trees s and t, check whether tree t has exactly
	 * the same structure and node values with a subtree of s. A subtree of s is a
	 * tree consists of a node in s and all of this node's descendants. The tree s
	 * could also be considered as a subtree of itself.
	 * 
	 * Link: https://leetcode.com/problems/subtree-of-another-tree/
	 * 
	 */
	public boolean isSubtree(Node s, Node t) {
		if (s == null && t == null) {
			return true;
		} else if (s == null || t == null) {
			return false;
		}
		if (s.getValue() == t.getValue()) {
			return isEqual(s, t) || isSubtree(s.getLeft(), t) || isSubtree(s.getRight(), t);
		}
		return isSubtree(s.getLeft(), t) || isSubtree(s.getRight(), t);
	}

	private boolean isEqual(Node s, Node t) {
		if (s == null && t == null) {
			return true;
		} else if (s == null || t == null) {
			return false;
		}
		if (s.getValue() != t.getValue()) {
			return false;
		}
		return isEqual(s.getLeft(), t.getLeft()) && isEqual(s.getRight(), t.getRight());
	}

	Integer min1 = null;
	Integer min2 = null;

	/**
	 * Given a non-empty special binary tree consisting of nodes with the
	 * non-negative value, where each node in this tree has exactly two or zero
	 * sub-node. If the node has two sub-nodes, then this node's value is the
	 * smaller value among its two sub-nodes. More formally, the property root.val =
	 * min(root.left.val, root.right.val) always holds. Given such a binary tree,
	 * you need to output the second minimum value in the set made of all the nodes'
	 * value in the whole tree. If no such second minimum value exists, output -1
	 * instead.
	 * 
	 * Link: https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/
	 * 
	 */
	public int findSecondMinimumValue(Node root) {
		fillMins(root);
		return min2 == null ? -1 : min2;
	}

	private void fillMins(Node root) {
		if (root == null) {
			return;
		}
		if (min1 == null) {
			min1 = root.getValue();
		} else if (root.getValue() != min1 && min2 == null) {
			min2 = root.getValue();
		} else if (root.getValue() < min1) {
			min2 = min1;
			min1 = root.getValue();
		} else if (root.getValue() != min1 && root.getValue() < min2) {
			min2 = root.getValue();
		}
		fillMins(root.getLeft());
		fillMins(root.getRight());
	}

	/**
	 * You need to construct a string consists of parenthesis and integers from a
	 * binary tree with the preorder traversing way. The null node needs to be
	 * represented by empty parenthesis pair "()". And you need to omit all the
	 * empty parenthesis pairs that don't affect the one-to-one mapping relationship
	 * between the string and the original binary tree.
	 * 
	 * Link: https://leetcode.com/problems/construct-string-from-binary-tree/
	 * 
	 */
	public String tree2str(Node root) {
		if (root == null) {
			return "";
		}
		String left = tree2str(root.getLeft());
		String right = tree2str(root.getRight());
		String result = String.valueOf(root.getValue());
		if (left.isEmpty() && right.isEmpty()) {
			return result;
		}
		if (right.isEmpty()) {
			result = result + "(" + left + ")";
		} else {
			result = result + "(" + left + ")" + "(" + right + ")";
		}
		return result;
	}

	int result2;

	/**
	 * Given the root of a binary tree, return the sum of every tree node's tilt.
	 * 
	 * The tilt of a tree node is the absolute difference between the sum of all
	 * left subtree node values and all right subtree node values. If a node does
	 * not have a left child, then the sum of the left subtree node values is
	 * treated as 0. The rule is similar if there the node does not have a right
	 * child.
	 * 
	 * Link: https://leetcode.com/problems/binary-tree-tilt/
	 * 
	 */
	public int findTilt(Node root) {
		result = 0;
		tilt(root);
		return result2;
	}

	private int tilt(Node root) {
		if (root == null) {
			return 0;
		}
		int left = root.getValue() + tilt(root.getLeft());
		int right = root.getValue() + tilt(root.getRight());
		int temp = root.getValue();
		root.setValue(Math.abs(left - right));
		result2 += root.getValue();
		return left + right - temp;
	}

	int max;
	Integer previous = null;
	int count;

	/**
	 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the
	 * most frequently occurred element) in the given BST. Assume a BST is defined
	 * as follows: The left subtree of a node contains only nodes with keys less
	 * than or equal to the node's key. The right subtree of a node contains only
	 * nodes with keys greater than or equal to the node's key. Both the left and
	 * right subtrees must also be binary search trees.
	 * 
	 * Link: https://leetcode.com/problems/find-mode-in-binary-search-tree/
	 * 
	 */
	public int[] findMode(Node root) {
		if (root == null) {
			return new int[0];
		}
		List<Integer> list = new ArrayList<>();
		max = 0;
		count = 1;
		traverse(root, list);
		int[] res = new int[list.size()];
		for (int i = 0; i < list.size(); ++i) {
			res[i] = list.get(i);
		}
		return res;
	}

	private void traverse(Node root, List<Integer> list) {
		if (root == null) {
			return;
		}
		traverse(root.getLeft(), list);
		if (previous != null) {
			if (previous == root.getValue()) {
				count++;
			} else {
				count = 1;
			}
		}
		if (count > max) {
			list.clear();
			max = count;
			list.add(root.getValue());
		} else if (count == max) {
			list.add(root.getValue());
		}
		previous = root.getValue();
		traverse(root.getRight(), list);
	}

}
