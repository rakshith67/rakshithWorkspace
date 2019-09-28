package trees.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import trees.basic.Node;
import trees.easy.TreesEasyLevel2;

public class TreesMediumLevel2 {

	/**
	 * prints postOrder traversal of tree from preOrder traversal.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/preorder-to-postorder/0
	 * 
	 * @param preOrder - preOrder of the tree
	 */
	public void preOrderToPostOrder(int[] preOrder, int size) {
		int[] currentIndex = new int[1];
		int minimum = Integer.MIN_VALUE;
		int maximum = Integer.MAX_VALUE;
		printPreOrderToPostOrder(preOrder, size, minimum, maximum, currentIndex);
		System.out.println(" - postOrder from preOrder");
	}

	private void printPreOrderToPostOrder(int[] preOrder, int size, int minimum, int maximum, int[] currentIndex) {
		if (currentIndex[0] == size) {
			return;
		}
		if (preOrder[currentIndex[0]] < minimum || preOrder[currentIndex[0]] > maximum) {
			return;
		}
		int value = preOrder[currentIndex[0]];
		currentIndex[0]++;
		printPreOrderToPostOrder(preOrder, size, minimum, value, currentIndex);
		printPreOrderToPostOrder(preOrder, size, value, maximum, currentIndex);
		System.out.print(value + " ");
	}

	/**
	 * determines the minimum distance between the given two nodes.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/min-distance-between-two-given-nodes-of-a-binary-tree/1
	 * 
	 * @param root   - root of the tree
	 * @param value1 - value of the first node of the tree
	 * @param value2 - value of the second node of the tree
	 */
	public int findDistance(Node root, int value1, int value2) {
		TreesEasyLevel2 treeOperations = new TreesEasyLevel2();
		Node lCA = treeOperations.leastCommonAncestor(root, value1, value2);
		int[] depth = new int[1];
		findDepth(lCA, value1, 0, depth);
		int depth1 = depth[0];
		depth[0] = 0;
		findDepth(lCA, value2, 0, depth);
		int depth2 = depth[0];
		return depth1 + depth2;
	}

	private void findDepth(Node root, int value1, int currentDepth, int[] depth) {
		if (root == null) {
			return;
		}
		if (root.getValue() == value1) {
			depth[0] = currentDepth;
			return;
		}
		findDepth(root.getLeft(), value1, currentDepth + 1, depth);
		findDepth(root.getRight(), value1, currentDepth + 1, depth);
	}

	/**
	 * determines the maximum difference between the node and its ancestor.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/maximum-difference-between-node-and-its-ancestor/1
	 * 
	 * @param root - Root of the first tree
	 */
	public int maxDiffNodeAndAncestor(Node root) {
		int[] maxDifference = new int[1];
		getMin(root, maxDifference);
		return maxDifference[0];
	}

	private int getMin(Node root, int[] maxDifference) {
		if (root == null) {
			return Integer.MAX_VALUE;
		}
		int leftMin = getMin(root.getLeft(), maxDifference);
		int rightMin = getMin(root.getRight(), maxDifference);
		int min = Math.min(leftMin, rightMin);
		if (min != Integer.MAX_VALUE) {
			maxDifference[0] = Math.max(maxDifference[0], root.getValue() - min);
		}
		return Math.min(min, root.getValue());
	}

	/**
	 * create the tree from the given parent array
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/construct-binary-tree-from-parent-array/1
	 * 
	 * @param parentArray - parent array
	 */
	public Node createTreeFromParentArray(int[] parentArray, int size) {
		int i = 0;
		List<Node> list = new ArrayList<>();
		Node[] rootNode = new Node[1];
		for (int j = 0; j < size; j++) {
			list.add(new Node(j));
		}
		while (i < size) {
			insert(list, parentArray[i], i, rootNode);
			i = i + 1;
		}
		return rootNode[0];
	}

	private void insert(List<Node> list, int parentValue, int value, Node[] rootNode) {
		if (parentValue == -1) {
			rootNode[0] = list.get(value);
			return;
		}
		for (Node node : list) {
			if (node.getValue() == parentValue) {
				if (node.getLeft() == null) {
					node.setLeft(list.get(value));
				} else {
					node.setRight(list.get(value));
				}
				break;
			}
		}
	}

	/**
	 * returns if the given preOrder array can be represented as BST or not.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/preorder-traversal-and-bst/0
	 * 
	 * @param preOrder - preOrder of the tree
	 */
	public boolean isPreOrderOfBST(int[] preOrder, int size) {
		Deque<Integer> deque = new ArrayDeque<>();
		int root = Integer.MIN_VALUE;
		for (int i = 0; i < size; i++) {
			System.out.print(root + " ");
			if (preOrder[i] < root) {
				return Boolean.FALSE;
			}
			while (!deque.isEmpty() && deque.getLast() < preOrder[i]) {
				root = deque.removeLast();
			}
			deque.addLast(preOrder[i]);
		}
		return Boolean.TRUE;
	}

	/**
	 * number of nodes having k leaves.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/maximum-path-sum-from-any-node/1
	 * 
	 * @param root - Root of the first tree
	 */
	public void kLeafNodes(Node root, int k) {
		List<Integer> list = new ArrayList<>();
		fillNodesKLeaves(root, k, list);
		if (list.isEmpty()) {
			System.out.print("-1");
		} else {
			for (Integer integer : list) {
				System.out.print(integer + " ");
			}
		}
	}

	private int fillNodesKLeaves(Node root, int k, List<Integer> list) {
		if (root == null) {
			return 0;
		}
		if (root.getRight() == null && root.getLeft() == null) {
			return 1;
		}
		int left = fillNodesKLeaves(root.getLeft(), k, list);
		int right = fillNodesKLeaves(root.getRight(), k, list);
		if (left + right == k) {
			list.add(root.getValue());
		}
		return left + right;
	}

	/**
	 * determines the maximum path sum of the tree from any node.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/maximum-path-sum-from-any-node/1
	 * 
	 * @param root - Root of the first tree
	 */
	public int maximumPathSumOfTree(Node root) {
		int[] path = new int[1];
		findMaxPathSum(root, path);
		return path[0];
	}

	private int findMaxPathSum(Node root, int[] path) {
		if (root == null) {
			return 0;
		}
		int left = findMaxPathSum(root.getLeft(), path);
		int right = findMaxPathSum(root.getRight(), path);
		int maximum = Math.max(Math.max(left, right) + root.getValue(), root.getValue());
		int maximumTotal = Math.max(maximum, left + right + root.getValue());
		if (maximumTotal > path[0]) {
			path[0] = maximumTotal;
		}
		return maximum;
	}

	/**
	 * returns the count of number of nodes at k distance from leaves.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/node-at-distance/1
	 * 
	 * @param root - Root of the first tree
	 * @param k    - the kth distance from root
	 */
	public int nodesKDistanceFromLeaf(Node root, int k) {
		Map<Node, Integer> map = new HashMap<>();
		Node[] path = new Node[100];
		fillMapWithNodesKDistanceLeaf(root, map, path, 0, k);
		return map.size();
	}

	private void fillMapWithNodesKDistanceLeaf(Node root, Map<Node, Integer> map, Node[] path, int pathLength, int k) {
		if (root == null) {
			return;
		}
		path[pathLength] = root;
		pathLength++;
		if (root.getLeft() == null && root.getRight() == null) {
			addNodes(path, pathLength, map, k);
		}
		fillMapWithNodesKDistanceLeaf(root.getLeft(), map, path, pathLength, k);
		fillMapWithNodesKDistanceLeaf(root.getRight(), map, path, pathLength, k);
	}

	private void addNodes(Node[] path, int pathLength, Map<Node, Integer> map, int k) {
		if (pathLength - 1 - k >= 0) {
			map.put(path[pathLength - 1 - k], path[pathLength - 1 - k].getValue());
		}
	}

	/**
	 * checks if the given tree is BST or Not.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/check-for-bst/1
	 * 
	 * @param root - Root of the first tree
	 */
	public boolean isBinarySearchTree(Node root) {
		int maximum = Integer.MAX_VALUE;
		int minimum = Integer.MIN_VALUE;
		return isBST(root, minimum, maximum);
	}

	private boolean isBST(Node root, int minimum, int maximum) {
		if (root == null) {
			return true;
		}
		if (root.getValue() < minimum || root.getValue() > maximum) {
			return false;
		}
		return (isBST(root.getLeft(), minimum, root.getValue()) && isBST(root.getRight(), root.getValue(), maximum));
	}

	/**
	 * returns the count of number of any paths whose sum is k.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/k-sum-paths/1
	 * 
	 * @param root - Root of the first tree
	 * @param k    - the k sum.
	 */
	public int countNodesSumAnyPathK(Node root, int k) {
		int[] path = new int[1];
		List<Integer> list = new ArrayList<>();
		findPathSumK(root, list, path, k);
		return path[0];
	}

	private void findPathSumK(Node root, List<Integer> list, int[] path, int k) {
		if (root == null) {
			return;
		}
		list.add(root.getValue());
		findPathSumK(root.getLeft(), list, path, k);
		findPathSumK(root.getRight(), list, path, k);
		addCount(path, list, k);
		list.remove(list.size() - 1);
	}

	private void addCount(int[] path, List<Integer> list, int k) {
		int sum = 0;
		int size = list.size();
		for (int i = size - 1; i >= 0; i--) {
			sum = sum + list.get(i);
			if (sum == k) {
				path[0]++;
			}
		}
	}

	/**
	 * returns the size of largest BST in the tree.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/largest-bst/1
	 * 
	 * @param root - Root of the first tree
	 */
	public int sizeOfLargestBSTinTree(Node root) {
		int[] size = new int[3];
		boolean[] isbst = new boolean[1];
		fillArrayWithLargestSize(root, size, isbst);
		return size[0];
	}

	private int fillArrayWithLargestSize(Node root, int[] size, boolean[] isBst) {
		if (root == null) {
			isBst[0] = Boolean.TRUE;
			return 0;
		}
		boolean leftFlag = false;
		boolean rightFlag = false;
		size[2] = Integer.MIN_VALUE;
		int left = fillArrayWithLargestSize(root.getLeft(), size, isBst);
		if (isBst[0] && root.getValue() > size[2]) {
			leftFlag = true;
		}
		int min = size[1];
		size[1] = Integer.MAX_VALUE;
		int right = fillArrayWithLargestSize(root.getRight(), size, isBst);
		if (isBst[0] && root.getValue() < size[1]) {
			rightFlag = true;
		}
		size[1] = Math.min(root.getValue(), Math.min(size[1], min));
		size[2] = Math.max(root.getValue(), size[2]);
		if (leftFlag && rightFlag) {
			int count = left + right + 1;
			if (count > size[0]) {
				size[0] = count;
			}
			return count;
		} else {
			isBst[0] = Boolean.FALSE;
			return 0;
		}
	}
}
