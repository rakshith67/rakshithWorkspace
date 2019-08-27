package trees.amazon.easy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import trees.Node;
import trees.TreeIntOperations;
import trees.TreeTraversals;
import trees.TreeViews;

/**
 * 
 * @author Rakshith
 *
 */
public class TreesAmazonEasyLevel {

	public static final String NOT_IMPLEMENTED = "Not implemented yet.";

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
		if (root.getLeft() != null && root.getRight() != null) {
			Node temp = root.getLeft();
			root.setLeft(root.getRight());
			root.setRight(temp);
			makeMirrorTree(root.getLeft());
			makeMirrorTree(root.getRight());
		} else if (root.getRight() != null) {
			Node temp = root.getRight();
			root.setLeft(root.getRight());
			root.setRight(null);
			makeMirrorTree(temp);
		} else {
			Node temp = root.getLeft();
			root.setRight(root.getLeft());
			root.setLeft(null);
			makeMirrorTree(temp);
		}
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
			a[i] = a[i] + root.getValue();
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
		} else if ((root.getLeft() != null && root.getRight() != null
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
		throw new UnsupportedOperationException(NOT_IMPLEMENTED);
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
	public void rootToLeafPathsSum(Node root) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED);
	}
}
