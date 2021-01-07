package trees.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import trees.basic.Node;
import trees.basic.TreeIntOperations;
import trees.basic.TreeTraversals;

public class TreesEasyLevel2 {

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

	public void calculateLevelSum(Node root, int i, int[] a) {
		if (root != null) {
			a[i] = a[i] + root.getValue();
			calculateLevelSum(root.getLeft(), i + 1, a);
			calculateLevelSum(root.getRight(), i + 1, a);
		}
	}

	/**
	 * prints the nodes that are k distance from the root of the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/determine-if-two-trees-are-identical/1
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
	 * checks if the tree is sum tree.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/sum-tree/1
	 * 
	 * @param root - Root of the tree
	 */
	public boolean isSumTree(Node root) {
		Map<Node, Integer> map = new HashMap<>();
		fillMap(root, map);
		return checkSumTree(root, map);
	}

	private boolean checkSumTree(Node root, Map<Node, Integer> map) {
		if (root == null || root.getLeft() == null && root.getRight() == null) {
			return Boolean.TRUE;
		} else if (root.getLeft() == null) {
			if (root.getValue() != map.get(root.getRight())) {
				return Boolean.FALSE;
			}
		} else if (root.getRight() == null) {
			if (root.getValue() != map.get(root.getLeft())) {
				return Boolean.FALSE;
			}
		} else {
			if (root.getValue() != map.get(root.getLeft()) + map.get(root.getRight())) {
				return Boolean.FALSE;
			}
		}
		return checkSumTree(root.getLeft(), map) && checkSumTree(root.getRight(), map);
	}

	private void fillMap(Node root, Map<Node, Integer> map) {
		if (root == null) {
			return;
		}
		fillMap(root.getLeft(), map);
		fillMap(root.getRight(), map);
		if (root.getLeft() == null && root.getRight() == null) {
			map.put(root, root.getValue());
		} else if (root.getLeft() == null) {
			map.put(root, root.getValue() + map.get(root.getRight()));
		} else if (root.getRight() == null) {
			map.put(root, root.getValue() + map.get(root.getLeft()));
		} else {
			map.put(root, root.getValue() + map.get(root.getLeft()) + map.get(root.getRight()));
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

	public void inOrderTraversal(Node root, List<Integer> list) {
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

	/**
	 * builds binary tree from preOrder and inOrder traversals.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/construct-tree-1/1
	 * 
	 * @param inorder  - inOrder of the tree
	 * @param preorder - preOrder of the tree
	 */
	public Node buildTree(int[] inOrder, int[] preOrder, int start, int end, int[] currentIndex) {
		if (start > end) {
			return null;
		}
		Node node = new Node(preOrder[currentIndex[0]]);
		if (start == end) {
			return node;
		}
		currentIndex[0]++;
		int index = search(inOrder, node.getValue(), start, end);
		node.setLeft(buildTree(inOrder, preOrder, start, index - 1, currentIndex));
		node.setRight(buildTree(inOrder, preOrder, index + 1, end, currentIndex));
		return node;
	}

	public int search(int[] array, int element, int start, int end) {
		for (int i = start; i <= end; i++) {
			if (array[i] == element) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * prints common nodes in the two BST's.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/print-common-nodes-in-bst/1
	 * 
	 * @param root - Roots of the trees
	 */
	public void printCommonNodesBST(Node root1, Node root2) {
		List<Integer> list1 = new ArrayList<>();
		inOrderTraversal(root1, list1);
		List<Integer> list2 = new ArrayList<>();
		addCommonNodes(root2, list1, list2);
		list2.sort(null);
		for (Integer integer : list2) {
			System.out.print(integer + " ");
		}
	}

	private void addCommonNodes(Node root, List<Integer> list1, List<Integer> list2) {
		if (root == null) {
			return;
		}
		addCommonNodes(root.getLeft(), list1, list2);
		if (list1.contains(root.getValue())) {
			list2.add(root.getValue());
		}
		addCommonNodes(root.getRight(), list1, list2);
	}

	/**
	 * determines height of the spiral tree.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/height-of-spiral-tree/1
	 * 
	 * @param root - Roots of the trees
	 */
	public int heightOfSpiralTree(Node root) {
		if (root == null) {
			return 0;
		} else if (root.getRight() != null && root.getRight().getLeft() == root) {
			return 1;
		}
		int leftHeight = heightOfSpiralTree(root.getLeft());
		int rightHeight = heightOfSpiralTree(root.getRight());
		return leftHeight > rightHeight ? leftHeight : rightHeight;
	}

	/**
	 * prints the postOrder traversal from inOrder and preOrder traversals.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/postorder-traversal-from-given-inorder-and-preorder-traversals/1
	 * 
	 * @param inOrder  - inOrder traversal
	 * @param preOrder - preOrder traversal
	 * @param n        - size of the array
	 */
	public void printPostOrder(int[] inOrder, int[] preOrder, int n) {
		TreesEasyLevel2 treeAmazonEasyLevel2 = new TreesEasyLevel2();
		int[] currentIndex = new int[1];
		Node root = treeAmazonEasyLevel2.buildTree(inOrder, preOrder, 0, n - 1, currentIndex);
		TreeTraversals treeTraversals = new TreeTraversals();
		treeTraversals.postOrderTraversal(root);
	}

	/**
	 * checks if the binary tree is perfect or not.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/perfect-binary-tree/1
	 * 
	 * @param root - Root of the tree
	 */
	public boolean isPerfectTree(Node root) {
		int[] leafLevel = new int[1];
		int level = 0;
		return checkPerfectTree(root, leafLevel, level);
	}

	private boolean checkPerfectTree(Node root, int[] leafLevel, int currentlevel) {
		if (root == null) {
			return Boolean.TRUE;
		} else if (root.getLeft() == null && root.getRight() != null) {
			return Boolean.FALSE;
		} else if (root.getLeft() != null && root.getRight() == null) {
			return Boolean.FALSE;
		} else if (root.getLeft() == null && root.getRight() == null) {
			if (leafLevel[0] == 0) {
				leafLevel[0] = currentlevel;
			} else if (leafLevel[0] != currentlevel) {
				return Boolean.FALSE;
			}
		}
		return checkPerfectTree(root.getLeft(), leafLevel, currentlevel + 1)
				&& checkPerfectTree(root.getRight(), leafLevel, currentlevel + 1);
	}

	/**
	 * prints the odd level nodes of the tree.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/nodes-at-odd-levels/1
	 * 
	 * @param root - Root of the tree
	 */
	public void printNodesAtOddLevel(Node root) {
		TreeIntOperations treeIntOperations = new TreeIntOperations();
		int height = treeIntOperations.height(root);
		for (int i = 1; i <= height; i++) {
			if (i % 2 == 1) {
				printNodesOdd(root, i, 1);
			}
		}
	}

	private void printNodesOdd(Node root, int level, int currentDepth) {
		if (root == null) {
			return;
		} else if (level == currentDepth) {
			System.out.print(root.getValue() + " ");
		}
		printNodesOdd(root.getLeft(), level, currentDepth + 1);
		printNodesOdd(root.getRight(), level, currentDepth + 1);
	}

	/**
	 * returns the number of traversals to leaves within the budget.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/leaf-under-budget/1
	 * 
	 * @param root   - Root of the tree
	 * @param budget = total budget
	 */
	public int getCountForBudget(Node root, int budget) {
		List<Integer> leafPathList = new ArrayList<>();
		fillArrayWithLeafPathLevels(root, leafPathList, 0);
		leafPathList.sort(null);
		int sum = 0;
		int count = 0;
		for (Integer integer : leafPathList) {
			sum = sum + integer;
			if (sum > budget) {
				break;
			}
			count++;
		}
		return count;
	}

	private void fillArrayWithLeafPathLevels(Node root, List<Integer> leafPathArray, int pathLength) {
		if (root == null) {
			return;
		}
		pathLength++;
		if (root.getLeft() == null && root.getRight() == null) {
			leafPathArray.add(pathLength);
		}
		fillArrayWithLeafPathLevels(root.getLeft(), leafPathArray, pathLength);
		fillArrayWithLeafPathLevels(root.getRight(), leafPathArray, pathLength);
	}

	/**
	 * prints the sum of minimum level leaf nodes of the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/sum-of-leaf-nodes-at-min-level/1
	 * 
	 * @param root - Root of the tree
	 */
	public void printSumOfLeafNodesAtMinLevel(Node root) {
		TreeIntOperations treeIntOperations = new TreeIntOperations();
		int height = treeIntOperations.height(root);
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < height; i++) {
			addLeafNodesToList(root, i, 0, list);
			if (!list.isEmpty()) {
				break;
			}
		}
		int sum = 0;
		for (Integer integer : list) {
			sum = sum + integer;
		}
		System.out.println("Sum of leaf nodes at min level " + sum);
	}

	private void addLeafNodesToList(Node root, int level, int currentLevel, List<Integer> list) {
		if (root == null) {
			return;
		} else if (level == currentLevel && root.getLeft() == null && root.getRight() == null) {
			list.add(root.getValue());
		}
		addLeafNodesToList(root.getLeft(), level, currentLevel + 1, list);
		addLeafNodesToList(root.getRight(), level, currentLevel + 1, list);
	}

	/**
	 * prints the predecessor and successor of inOrder traversal of the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/predecessor-and-successor/1
	 * 
	 * @param root - Root of the tree
	 * @param key  - key whose predecessor and successor need to find
	 */
	public void printPredecessorAndSuccessor(Node root, int key) {
		List<Integer> list = new ArrayList<>();
		TreesEasyLevel2 treeOperations = new TreesEasyLevel2();
		treeOperations.inOrderTraversal(root, list);
		int predecessor = -1;
		int successor = -1;
		for (Integer integer : list) {
			if (integer > key) {
				successor = integer;
				break;
			}
			if (integer != key) {
				predecessor = integer;
			}
		}
		System.out.println("Predecessor and Successors is " + predecessor + " " + successor);
	}

	/**
	 * deletes the nodes that are greater than the key
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/delete-nodes-greater-than-k/1
	 * 
	 * @param root - Root of the tree
	 * @param key  - key
	 */
	public Node deleteNodesGreaterThanKey(Node root, int key) {
		if (root == null) {
			return null;
		}
		root.setLeft(deleteNodesGreaterThanKey(root.getLeft(), key));
		root.setRight(deleteNodesGreaterThanKey(root.getRight(), key));
		if (root.getValue() > key) {
			return root.getLeft();
		}
		return root;
	}

	/**
	 * checks if the binary tree has max heap property or not.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/perfect-binary-tree/1
	 * 
	 * @param root - Root of the tree
	 */
	public boolean isMaxHeap(Node root) {
		if (root == null) {
			return Boolean.TRUE;
		}
		if ((root.getLeft() != null && root.getValue() < root.getLeft().getValue())
				|| (root.getRight() != null && root.getValue() < root.getRight().getValue())) {
			return Boolean.FALSE;
		}
		return isMaxHeap(root.getLeft()) && isMaxHeap(root.getRight());
	}

	/**
	 * builds the tree from ternary expression.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/convert-ternary-expression-to-binary-tree/1
	 * 
	 * @param root - Root of the tree
	 */
	public Node buildTreeFromTernaryExp(String expression, int i) {
		if (i > expression.length())
			return null;
		Node root = new Node(Integer.valueOf(expression.charAt(i)) - '0');
		i++;
		if (i < expression.length() && expression.charAt(i) == '?')
			root.setLeft(buildTreeFromTernaryExp(expression, i + 1));
		if (i < expression.length() && expression.charAt(i) == ':')
			root.setRight(buildTreeFromTernaryExp(expression, i + 1));
		return root;
	}

	/**
	 * determines the shortest distance between the two nodes in a infinity tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/find-the-distance-between-two-nodes/0
	 * 
	 * @param root - Root of the tree
	 */
	public int shortestDistance(int x, int y) {
		if (x < y) {
			int temp = x;
			x = y;
			y = temp;
		}
		int c = 0;
		while (x != y) {
			++c;
			if (x > y) {
				x = x / 2;
			}
			if (x < y) {
				y = y / 2;
				++c;
			}
		}
		return c;
	}

	/**
	 * determines the sum of the kth level of the tree.Input is taken as string
	 * which is in the form of expression (0(5(6()())(4()(9()())))(7(1()())(3()())))
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/binary-tree-k-level-sum/0
	 * 
	 * @param root - Root of the tree
	 */
	public int kthLevelSum(String expression, int k) {
		int level = -1;
		int sum = 0;
		int i = 0;
		while (i < expression.length()) {
			if (expression.charAt(i) == '(') {
				level++;
				i++;
			} else if (expression.charAt(i) == ')') {
				level--;
				i++;
			} else if (level == k) {
				int power = 0;
				int j = i;
				while (expression.charAt(j) != ')' && expression.charAt(j) != '(') {
					power++;
					j++;
				}
				int value = 0;
				int currentIndex = 0;
				while (currentIndex < power) {
					value = (int) (value + (expression.charAt(i) - '0') * Math.pow(10, power - currentIndex - 1d));
					i++;
					currentIndex++;
				}
				System.out.print(value + " ");
				sum = sum + value;
			} else {
				i++;
			}
		}
		return sum;
	}
}
