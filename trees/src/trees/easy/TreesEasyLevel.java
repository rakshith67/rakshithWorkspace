package trees.easy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import trees.basic.Node;
import trees.basic.NodeString;
import trees.basic.TreeIntOperations;
import trees.basic.TreeTraversals;
import trees.basic.TreeViews;

/**
 * 
 * @author Rakshith
 *
 */
public class TreesEasyLevel {

	/**
	 * determines whether the both trees are mirror trees or not.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/mirror-tree/1
	 * 
	 * @param root - Root of the first tree
	 */
	public void mirrorTree(Node root) {
		makeMirrorTree(root);
		TreeTraversals treeTraversals = new TreeTraversals();
		treeTraversals.inOrderTraversal(root);
	}

	private void makeMirrorTree(Node root) {
		if (root == null) {
			return;
		}
		Node temp = root.getLeft();
		root.setLeft(root.getRight());
		root.setRight(temp);
		makeMirrorTree(root.getLeft());
		makeMirrorTree(root.getRight());
	}

	/**
	 * evaluates the expression tree and gives the output.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/expression-tree/1
	 * 
	 * @param root - Root of the tree
	 */
	public int expressionTree(NodeString root) {
		if (root == null) {
			return 0;
		} else if (root.getLeft() == null && root.getRight() == null) {
			return Integer.valueOf(root.getValue());
		}
		int leftValue = expressionTree(root.getLeft());
		int rightValue = expressionTree(root.getRight());
		if (root.getValue().equals("*")) {
			return leftValue * rightValue;
		} else if (root.getValue().equals("+")) {
			return leftValue + rightValue;
		} else if (root.getValue().equals("-")) {
			return leftValue - rightValue;
		} else {
			return leftValue / rightValue;
		}
	}

	/**
	 * evaluates the max level sum of the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/max-level-sum-in-binary-tree/1
	 * 
	 * @param root - Root of the tree
	 */
	public int maxLevelSum(Node root) {
		TreeIntOperations treeOperations = new TreeIntOperations();
		int height = treeOperations.height(root);
		int maximum = 0;
		int[] a = new int[height];
		calculateLevelSum(root, 0, a);
		for (int i = 0; i < height; i++) {
			if (a[i] > maximum) {
				maximum = a[i];
			}
		}
		return maximum;
	}

	private void calculateLevelSum(Node root, int i, int[] a) {
		if (root != null) {
			a[i] += root.getValue();
			calculateLevelSum(root.getLeft(), i + 1, a);
			calculateLevelSum(root.getRight(), i + 1, a);
		}
	}

	/**
	 * determines whether the tree is full binary tree or not.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/full-binary-tree/1
	 * 
	 * @param root - Root of the tree
	 */
	public boolean fullBinaryTree(Node root) {
		if (root == null) {
			return Boolean.TRUE;
		} else if ((root.getLeft() != null && root.getRight() == null)
				|| root.getLeft() == null && root.getRight() != null) {
			return Boolean.FALSE;
		}
		return fullBinaryTree(root.getLeft()) && fullBinaryTree(root.getRight());
	}

	/**
	 * determines whether the tree is full binary tree or not.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/children-sum-parent/1
	 * 
	 * @param root - Root of the tree
	 */
	public boolean childSumParentTree(Node root) {
		if (root == null || root.getLeft() == null && root.getRight() == null) {
			return true;
		}
		if ((root.getLeft() != null && root.getRight() != null
				&& root.getLeft().getValue() + root.getRight().getValue() != root.getValue())
				|| (root.getRight() == null && root.getValue() != root.getLeft().getValue())
				|| (root.getLeft() == null && root.getValue() != root.getRight().getValue())) {
			return Boolean.FALSE;
		}
		return childSumParentTree(root.getLeft()) && childSumParentTree(root.getRight());
	}

	/**
	 * removes the half nodes in the tree.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/remove-half-nodes/1
	 * 
	 * @param root - Root of the tree
	 */
	public Node removeHalfNodes(Node root) {
		if (root == null) {
			return null;
		}
		root.setLeft(removeHalfNodes(root.getLeft()));
		root.setRight(removeHalfNodes(root.getRight()));
		if (root.getLeft() == null && root.getRight() == null) {
			return root;
		} else if (root.getLeft() == null) {
			return root.getRight();
		} else if (root.getRight() == null) {
			return root.getLeft();
		}
		return root;
	}

	/**
	 * calculates the vertical sum of the tree.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/vertical-sum/1
	 * 
	 * @param root - Root of the tree
	 */
	public void verticalSum(Node root) {
		TreeViews treeViews = new TreeViews();
		int[] edge = new int[2];
		treeViews.verticalEdgesFill(root, edge, 0);
		edge[0] = Math.abs(edge[0]);
		edge[1] = Math.abs(edge[1]);
		int[] a = new int[edge[0] + edge[1] + 1];
		fillArrayVerticalSum(root, edge[1], a);
		for (int i = 0; i < edge[1] + edge[0] + 1; i++) {
			System.out.print(a[i] + " ");
		}
	}

	private void fillArrayVerticalSum(Node root, int rootIndex, int[] a) {
		Deque<Node> deque = new ArrayDeque<>();
		deque.push(root);
		Map<Node, Integer> map = new HashMap<>();
		map.put(root, rootIndex);
		while (!deque.isEmpty()) {
			Node currentNode = deque.removeFirst();
			int currentLevel = map.get(currentNode);
			a[currentLevel] += currentNode.getValue();
			if (currentNode.getLeft() != null) {
				map.put(currentNode.getLeft(), currentLevel - 1);
				deque.add(currentNode.getLeft());
			}
			if (currentNode.getRight() != null) {
				map.put(currentNode.getRight(), currentLevel + 1);
				deque.add(currentNode.getRight());
			}
		}
	}

	/**
	 * calculates the diagonal sum of the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/diagonal-sum-in-binary-tree/1
	 * 
	 * @param root - Root of the tree
	 */
	public void diagonalSum(Node root) {
		Map<Integer, Integer> map = new HashMap<>();
		fillMapWithDiagonalSum(root, map, 0);
		int i = 0;
		while (map.get(i) != null) {
			System.out.print(map.get(i) + " ");
			i++;
		}
	}

	private void fillMapWithDiagonalSum(Node root, Map<Integer, Integer> map, int currentDiagonal) {
		if (root == null) {
			return;
		}
		if (map.get(currentDiagonal) == null) {
			map.put(currentDiagonal, root.getValue());
		} else {
			map.put(currentDiagonal, map.get(currentDiagonal) + root.getValue());
		}
		fillMapWithDiagonalSum(root.getLeft(), map, currentDiagonal + 1);
		fillMapWithDiagonalSum(root.getRight(), map, currentDiagonal);
	}

	/**
	 * determines the max node level of the tree.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/maximum-node-level/1
	 * 
	 * @param root - Root of the tree
	 */
	public int maxNodeLevel(Node root) {
		if (root == null) {
			return -1;
		}
		TreeIntOperations treeIntOperations = new TreeIntOperations();
		int height = treeIntOperations.height(root);
		int[] level = new int[height];
		fillLevelArrayWithNumberOfNodes(root, level, 0);
		int maximumLevel = 0;
		int maximumValue = 0;
		for (int i = 0; i < height; i++) {
			if (level[i] > maximumValue) {
				maximumLevel = i;
				maximumValue = level[i];
			}
		}
		return maximumLevel;
	}

	private void fillLevelArrayWithNumberOfNodes(Node root, int[] level, int currentLevel) {
		if (root != null) {
			level[currentLevel]++;
			fillLevelArrayWithNumberOfNodes(root.getLeft(), level, currentLevel + 1);
			fillLevelArrayWithNumberOfNodes(root.getRight(), level, currentLevel + 1);
		}
	}

	/**
	 * determines the root to leaf paths sum of the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/root-to-leaf-paths-sum/1
	 * 
	 * @param root - Root of the tree
	 */
	public int rootToLeafPathsSum(Node root) {
		int[] path = new int[100];
		List<Integer> list = new ArrayList<>();
		printSumOfLeafPaths(root, path, 0, list);
		return totalSum(list);
	}

	private int totalSum(List<Integer> list) {
		int sum = 0;
		for (Integer integer : list) {
			sum = sum + integer;
		}
		return sum;
	}

	private void printSumOfLeafPaths(Node root, int[] path, int pathLength, List<Integer> list) {
		if (root == null) {
			return;
		}
		path[pathLength] = root.getValue();
		pathLength++;
		if (root.getLeft() == null && root.getRight() == null) {
			int currentSum = 0;
			for (int i = 0; i < pathLength; i++) {
				int index = pathLength - i - 1;
				currentSum = (int) (currentSum + (path[i] * Math.pow(10, index)));
			}
			list.add(currentSum);
		}
		printSumOfLeafPaths(root.getLeft(), path, pathLength, list);
		printSumOfLeafPaths(root.getRight(), path, pathLength, list);
	}

	/**
	 * determines the root to leaf paths sum has the sum given.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/root-to-leaf-paths-sum/1
	 * 
	 * @param root - Root of the tree
	 */
	public boolean hasPathSum(Node root, int sum) {
		int[] path = new int[100];
		return checkIfPathSumExists(root, sum, path, 0);
	}

	private boolean checkIfPathSumExists(Node root, int sum, int[] path, int pathLength) {
		if (root == null) {
			return Boolean.FALSE;
		}
		path[pathLength] = root.getValue();
		pathLength++;
		if (root.getLeft() == null && root.getRight() == null) {
			int currentSum = 0;
			for (int i = 0; i < pathLength; i++) {
				currentSum = currentSum + path[i];
			}
			if (sum == currentSum) {
				return Boolean.TRUE;
			}
		}
		return checkIfPathSumExists(root.getLeft(), sum, path, pathLength)
				|| checkIfPathSumExists(root.getRight(), sum, path, pathLength);
	}

	/**
	 * prints the root to leaf paths of the tree.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/root-to-leaf-paths/1
	 * 
	 * @param root - Root of the tree
	 */
	public void rootToLeafPaths(Node root) {
		int[] path = new int[100];
		printRootToLeafPaths(root, path, 0);
	}

	private void printRootToLeafPaths(Node root, int[] path, int pathLength) {
		if (root == null) {
			return;
		}
		path[pathLength] = root.getValue();
		pathLength++;
		if (root.getLeft() == null && root.getRight() == null) {
			printCurrentPath(path, pathLength);
		}
		printRootToLeafPaths(root.getLeft(), path, pathLength);
		printRootToLeafPaths(root.getRight(), path, pathLength);
	}

	private void printCurrentPath(int[] path, int pathLength) {
		for (int i = 0; i < pathLength; i++) {
			System.out.print(path[i] + " ");
		}
		System.out.print("#");
	}

	/**
	 * prints the root to leaf paths of the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/diameter-of-binary-tree/1
	 * 
	 * @param root - Root of the tree
	 */
	public int diameter(Node root) {
		if (root == null) {
			return 0;
		}
		TreeIntOperations treeOperations = new TreeIntOperations();
		int leftHeight = treeOperations.height(root.getLeft());
		int rightHeight = treeOperations.height(root.getRight());
		int leftDiameter = diameter(root.getLeft());
		int rightDiameter = diameter(root.getRight());
		return Math.max(leftHeight + rightHeight + 1, Math.max(leftDiameter, rightDiameter));
	}

	/**
	 * checks if the two nodes are cousins or not.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/check-if-two-nodes-are-cousins/1
	 * 
	 * @param root - Root of the tree
	 */
	public boolean isCousins(Node root, int x, int y) {
		int[] array = new int[2];
		Map<Integer, Node> map = new HashMap<>();
		levelOrderTraversal(root, root, 0, array, x, y, map);
		if (array[0] == array[1] && array[0] != 0 && map.get(x) != map.get(y)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	private void levelOrderTraversal(Node root, Node parent, int depth, int[] array, int x, int y,
			Map<Integer, Node> map) {
		if (root == null) {
			return;
		}
		if (root.getValue() == x) {
			array[0] = depth;
			map.put(x, parent);
		}
		if (root.getValue() == y) {
			array[1] = depth;
			map.put(y, parent);
		}
		levelOrderTraversal(root.getLeft(), root, depth + 1, array, x, y, map);
		levelOrderTraversal(root.getRight(), root, depth + 1, array, x, y, map);
	}

	/**
	 * prints the nodes that don't have the siblings.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/print-all-nodes-that-dont-have-sibling/1
	 * 
	 * @param root - Root of the tree
	 */
	public void printNonSiblingNodes(Node root) {
		List<Integer> list = new ArrayList<>();
		fillListWithNonSiblingNodes(root.getLeft(), root, list);
		fillListWithNonSiblingNodes(root.getRight(), root, list);
		if (list.isEmpty()) {
			System.out.print("-1");
		} else {
			list.sort(null);
			for (Integer integer : list) {
				System.out.print(integer + " ");
			}
		}
	}

	private void fillListWithNonSiblingNodes(Node root, Node parent, List<Integer> list) {
		if (root == null) {
			return;
		} else if (parent.getLeft() != null && parent.getRight() == null) {
			list.add(root.getValue());
		} else if (parent.getLeft() == null && parent.getRight() != null) {
			list.add(root.getValue());
		}
		fillListWithNonSiblingNodes(root.getLeft(), root, list);
		fillListWithNonSiblingNodes(root.getRight(), root, list);
	}

	/**
	 * Builds the tree for array and prints inOrder traversal.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/array-to-bst/0
	 * 
	 * @param array - array of values of nodes
	 */
	public Node arrayToBST(int[] array, int start, int end) {
		if (start > end) {
			return null;
		}
		int rootIndex = (start + end) / 2;
		Node node = new Node(array[rootIndex]);
		if (start == end) {
			return node;
		}
		node.setLeft(arrayToBST(array, start, rootIndex - 1));
		node.setRight(arrayToBST(array, rootIndex + 1, end));
		return node;
	}

	/**
	 * reverses the alternate levels of the root.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/reverse-alternate-levels-of-a-perfect-binary-tree/1
	 * 
	 * @param root - Root of the tree
	 */
	public void reverseAlternateLevelsOfTree(Node root) {
		List<Integer> list = new ArrayList<>();
		fillListWithOddLevels(root, list, 0);
		inOrderTraversalReverseAlternateLevels(root, list, 0);
	}

	private void inOrderTraversalReverseAlternateLevels(Node root, List<Integer> list, int level) {
		if (root == null) {
			return;
		}
		inOrderTraversalReverseAlternateLevels(root.getLeft(), list, level + 1);
		if (level % 2 == 1) {
			root.setValue(list.get(list.size() - 1));
			list.remove(list.size() - 1);
		}
		inOrderTraversalReverseAlternateLevels(root.getRight(), list, level + 1);
	}

	private void fillListWithOddLevels(Node root, List<Integer> list, int currentLevel) {
		if (root == null) {
			return;
		}
		fillListWithOddLevels(root.getLeft(), list, currentLevel + 1);
		if (currentLevel % 2 == 1) {
			list.add(root.getValue());
		}
		fillListWithOddLevels(root.getRight(), list, currentLevel + 1);
	}

	/**
	 * prints the longest consecutive sequence of the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/longest-consecutive-sequence-in-binary-tree/1
	 * 
	 * @param root - root of the tree
	 */
	public int largestConsecutiveSequence(Node root) {
		int[] a = new int[1];
		largestSequence(root, 0, root.getValue(), a);
		if (a[0] == 1) {
			return -1;
		}
		return a[0];
	}

	private void largestSequence(Node root, int i, int parentValue, int[] a) {
		if (root == null) {
			return;
		} else if (i == 0) {
			i++;
			a[0] = 1;
		} else if (parentValue - root.getValue() == -1) {
			i++;
			if (a[0] < i) {
				a[0] = i;
			}
		} else if (parentValue - root.getValue() != -1) {
			i = 1;
		}
		largestSequence(root.getLeft(), i, root.getValue(), a);
		largestSequence(root.getRight(), i, root.getValue(), a);
	}
}
