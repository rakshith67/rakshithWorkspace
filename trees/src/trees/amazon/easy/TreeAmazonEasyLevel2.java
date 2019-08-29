package trees.amazon.easy;

import java.util.HashMap;
import java.util.Map;

import trees.Node;
import trees.TreeIntOperations;
import trees.TreeTraversals;

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
		Map<Node, Integer> map = new HashMap<>();
		printSumTree(root, map);
		TreeTraversals treeTraversals = new TreeTraversals();
		treeTraversals.inOrderTraversal(root);
	}

	private void printSumTree(Node root, Map<Node, Integer> map) {
		if (root == null) {
			return;
		}
		printSumTree(root.getLeft(), map);
		printSumTree(root.getRight(), map);
		if (root.getLeft() == null && root.getRight() == null) {
			map.put(root, root.getValue());
			root.setValue(0);
		} else if (root.getLeft() == null && root.getRight() != null) {
			map.put(root, map.get(root.getRight()) + root.getValue());
			root.setValue(map.get(root.getRight()));
		} else if (root.getLeft() != null && root.getRight() == null) {
			map.put(root, map.get(root.getLeft()) + root.getValue());
			root.setValue(map.get(root.getLeft()));
		} else {
			map.put(root, map.get(root.getLeft()) + map.get(root.getRight()) + root.getValue());
			root.setValue(map.get(root.getLeft()) + map.get(root.getRight()));
		}
	}

	/**
	 * checks whether the tree is symmetric tree.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/symmetric-tree/1
	 * 
	 * @param root - Root of the tree
	 */
	public boolean isSymmetricTree(Node root) {
		if (root == null || (root.getLeft() == null && root.getRight() == null)) {
			return Boolean.TRUE;
		}
		return isSymmetric(root.getLeft(), root.getRight());
	}

	private boolean isSymmetric(Node left, Node right) {
		if ((left == null && right != null) || (left != null && right == null)) {
			return Boolean.FALSE;
		} else if (left == null) {
			return Boolean.TRUE;
		} else if (left.getValue() != right.getValue()) {
			return Boolean.FALSE;
		}
		return isSymmetric(left.getLeft(), right.getRight()) && isSymmetric(left.getRight(), right.getLeft());
	}

	/**
	 * checks whether the trees are mirror tree.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/symmetric-tree/1
	 * 
	 * @param root - Root of the tree
	 */
	public boolean areMirrorTrees(Node root1, Node root2) {
		if (root1 == null && root2 == null) {
			return Boolean.TRUE;
		} else if (root1 == null) {
			return Boolean.FALSE;
		} else if (root2 == null) {
			return Boolean.FALSE;
		} else if (root1.getValue() != root2.getValue()) {
			return Boolean.FALSE;
		}
		return areMirrorTrees(root1.getRight(), root2.getLeft()) && areMirrorTrees(root1.getLeft(), root2.getRight());
	}

	/**
	 * checks whether the trees are isomorphic.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/check-if-tree-is-isomorphic/1
	 * 
	 * @param root - Root of the tree
	 */
	public boolean areIsomorphicTrees(Node root1, Node root2) {
		return Boolean.FALSE;
	}

	/**
	 * determines the least common ancestor of the two values.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/lowest-common-ancestor-in-a-bst/1
	 * 
	 * @param root - Root of the tree
	 */
	public Node leastCommonAncestor(Node root, int value1, int value2) {
		return root;
	}
}
