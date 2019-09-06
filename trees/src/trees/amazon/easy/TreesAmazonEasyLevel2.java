package trees.amazon.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import trees.Node;
import trees.TreeIntOperations;
import trees.TreeTraversals;

public class TreesAmazonEasyLevel2 {

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
	 * checks whether the leaves of the tree are at same level.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/leaf-at-same-level/1
	 * 
	 * @param root - Root of the tree
	 */
	public boolean leavesAtSameLevel(Node root) {
		int[] a = new int[2];
		checkLevelOfLeaves(root, a, 0);
		if (a[0] == a[1]) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	private void checkLevelOfLeaves(Node root, int[] a, int level) {
		if (root == null) {
			return;
		}
		if (root.getLeft() == null && root.getRight() == null) {
			if (a[0] == 0) {
				a[0] = level;
				a[1] = level;
			} else {
				a[1] = level;
			}
		}
		if (a[0] == a[1]) {
			checkLevelOfLeaves(root.getLeft(), a, level + 1);
			checkLevelOfLeaves(root.getRight(), a, level + 1);
		}
	}

	/**
	 * returns the kth largest element in BST.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/kth-largest-element-in-bst/1
	 * 
	 * @param root - Root of the tree
	 */
	public void kthLargestElement(Node root, int k) {
		int[] a = new int[1];
		kthLargest(root, k, a);
	}

	private void kthLargest(Node root, int k, int[] a) {
		if (root == null) {
			return;
		}
		kthLargest(root.getRight(), k, a);
		a[0]++;
		if (a[0] == k) {
			System.out.print(root.getValue() + " ");
		}
		kthLargest(root.getLeft(), k, a);
	}

	/**
	 * prints elements in a given range of BST.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/print-bst-elements-in-given-range/1
	 * 
	 * @param root - Root of the tree
	 */
	public void printElementsInRange(Node root, int k1, int k2) {
		if (root == null) {
			return;
		}
		printElementsInRange(root.getLeft(), k1, k2);
		if (root.getValue() >= k1 && root.getValue() <= k2) {
			System.out.print(root.getValue() + " ");
		}
		if (root.getValue() < k2) {
			printElementsInRange(root.getRight(), k1, k2);
		}
	}

	/**
	 * returns the maximum sum of path of tree.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/maximum-path-sum/1
	 * 
	 * @param root - Root of the tree
	 */
	public int maximumPathSum(Node root) {
		if (root == null) {
			return 0;
		}
		int leftChildSum = determineMaxPathSum(root.getLeft());
		int rightChildSum = determineMaxPathSum(root.getRight());
		int leftSum = maximumPathSum(root.getLeft());
		int rightSum = maximumPathSum(root.getRight());
		return Math.max(leftChildSum + rightChildSum + root.getValue(), Math.max(leftSum, rightSum));
	}

	public int determineMaxPathSum(Node root) {
		if (root == null) {
			return 0;
		}
		int[] path = new int[10];
		List<Integer> list = new ArrayList<>();
		sumOfLeafPaths(root, path, 0, list);
		return list.get(0);
	}

	private void sumOfLeafPaths(Node root, int[] path, int pathLength, List<Integer> list) {
		if (root == null) {
			return;
		}
		path[pathLength] = root.getValue();
		pathLength++;
		if (root.getLeft() == null && root.getRight() == null) {
			int sum = 0;
			for (int i = 0; i < pathLength; i++) {
				sum = sum + path[i];
			}
			if (list.isEmpty()) {
				list.add(sum);
			} else if (sum > list.get(0)) {
				list.clear();
				list.add(sum);
			}
		}
		sumOfLeafPaths(root.getLeft(), path, pathLength, list);
		sumOfLeafPaths(root.getRight(), path, pathLength, list);
	}

	/**
	 * checks whether the trees are isomorphic.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/check-if-tree-is-isomorphic/1
	 * 
	 * @param root - Root of the tree
	 */
	public boolean isIsomorphicTrees(Node root1, Node root2) {
		if (root1 == null && root2 == null) {
			return true;
		}
		if (root1 == null || root2 == null) {
			return true;
		}
		if (root1.getValue() != root2.getValue()) {
			return false;
		}
		return isIsomorphicTrees(root1.getLeft(), root2.getLeft())
				&& isIsomorphicTrees(root1.getRight(), root2.getRight())
				|| isIsomorphicTrees(root1.getLeft(), root2.getRight())
				|| isIsomorphicTrees(root2.getLeft(), root1.getRight());
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
		if (root == null) {
			return null;
		}
		if (root.getValue() == value1 || root.getValue() == value2) {
			return root;
		}
		Node left = leastCommonAncestor(root.getLeft(), value1, value2);
		Node right = leastCommonAncestor(root.getRight(), value1, value2);
		if (left != null && right != null) {
			return root;
		}
		if (left == null && right == null) {
			return null;
		}
		return left != null ? left : right;
	}

	/**
	 * prints the ancestors of the given node in a tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/ancestors-in-binary-tree/1
	 * 
	 * @param root - Root of the tree
	 */
	public void printAncestors(Node root, int k) {
		int[] path = new int[100];
		printRootToLeafPath(root, path, 0, k);
	}

	private void printRootToLeafPath(Node root, int[] path, int pathLength, int k) {
		if (root == null) {
			return;
		}
		if (root.getValue() == k) {
			for (int i = pathLength - 1; i >= 0; i--) {
				System.out.print(path[i] + " ");
			}
		} else {
			path[pathLength] = root.getValue();
			pathLength++;
			printRootToLeafPath(root.getLeft(), path, pathLength, k);
			printRootToLeafPath(root.getRight(), path, pathLength, k);
		}
	}

	/**
	 * converts binary tree to BST.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/binary-tree-to-bst/1
	 * 
	 * @param root - Root of the tree
	 */
	public Node binaryTreeToBST(Node root) {
		List<Integer> list = new ArrayList<>();
		inOrderTraversal(root, list);
		list.sort(null);
		makeBSTFromBinaryTree(root, list);
		return root;
	}

	private void inOrderTraversal(Node root, List<Integer> list) {
		if (root == null) {
			return;
		}
		inOrderTraversal(root.getLeft(), list);
		list.add(root.getValue());
		inOrderTraversal(root.getRight(), list);
	}

	private void makeBSTFromBinaryTree(Node root, List<Integer> list) {
		if (root == null) {
			return;
		}
		makeBSTFromBinaryTree(root.getLeft(), list);
		root.setValue(list.get(0));
		list.remove(0);
		makeBSTFromBinaryTree(root.getRight(), list);
	}
}
