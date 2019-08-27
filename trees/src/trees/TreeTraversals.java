package trees;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import trees.amazon.easy.TreesAmazonEasyLevel;

/**
 * 
 * @author Rakshith
 */
public class TreeTraversals {

	/**
	 * Prints the preOrder traversal of the tree.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/preorder-traversal/1
	 * 
	 * @param root - root of the tree
	 */
	public void preOrderTraversal(Node root) {
		if (root == null) {
			return;
		}
		System.out.print(root.getValue() + " ");
		if (root.getLeft() != null) {
			preOrderTraversal(root.getLeft());
		}
		if (root.getRight() != null) {
			preOrderTraversal(root.getRight());
		}
	}

	/**
	 * Prints the inOrder traversal of the tree.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/inorder-traversal/1
	 * 
	 * @param root - root of the tree
	 */
	public void inOrderTraversal(Node root) {
		if (root == null) {
			return;
		}
		if (root.getLeft() != null) {
			inOrderTraversal(root.getLeft());
		}
		System.out.print(root.getValue() + " ");
		if (root.getRight() != null) {
			inOrderTraversal(root.getRight());
		}
	}

	/**
	 * Prints the postOrder traversal of the tree.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/postorder-traversal/1
	 * 
	 * @param root - root of the tree
	 */
	public void postOrderTraversal(Node root) {
		if (root == null) {
			return;
		}
		if (root.getLeft() != null) {
			postOrderTraversal(root.getLeft());
		}
		if (root.getRight() != null) {
			postOrderTraversal(root.getRight());
		}
		System.out.print(root.getValue() + " ");
	}

	/**
	 * Prints the levelOrder traversal of the tree.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/level-order-traversal/1
	 * 
	 * @param root - root of the tree
	 */
	public void levelOrderTraversal(Node root) {
		if (root == null) {
			return;
		}
		Deque<Node> queue = new ArrayDeque<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node removedNode = queue.removeFirst();
			if (removedNode.getLeft() != null)
				queue.add(removedNode.getLeft());
			if (removedNode.getRight() != null)
				queue.add(removedNode.getRight());
			System.out.print(removedNode.getValue() + " ");
		}
	}

	/**
	 * Prints the levelOrder traversal of the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/level-order-traversal-line-by-line/1
	 * 
	 * @param root - root of the tree
	 */
	public void levelOrderTraversalLine(Node root) {
		if (root == null) {
			return;
		}
		Deque<Node> queue = new ArrayDeque<>();
		queue.add(root);
		int depth = 0;
		int currentDepth = 0;
		int i = 0;
		int readNodes = 0;
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, 0);
		while (!queue.isEmpty()) {
			Node removedNode = queue.removeFirst();
			if (map.get(readNodes) > currentDepth) {
				currentDepth = depth;
				System.out.print("$ ");
			}
			System.out.print(removedNode.getValue() + " ");
			readNodes++;
			if (removedNode.getLeft() != null || removedNode.getRight() != null) {
				depth++;
				if (removedNode.getLeft() != null) {
					i++;
					queue.add(removedNode.getLeft());
					map.put(i, depth);
				}
				if (removedNode.getRight() != null) {
					i++;
					queue.add(removedNode.getRight());
					map.put(i, depth);
				}
			}
		}
		System.out.print("$ ");
	}

	/**
	 * Prints the spiral levelOrder traversal of the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/level-order-traversal-in-spiral-form/1
	 * 
	 * @param root - root of the tree
	 */
	public void spiralLevelOrderTraversal(Node root) {
		TreeIntOperations intOperations = new TreeIntOperations();
		int height = intOperations.height(root);
		for (int i = 1; i <= height; i++) {
			levelOrderTraversalSpiralRecursive(root, i, 1);
		}
	}

	private void levelOrderTraversalSpiralRecursive(Node root, int depth, int j) {
		if (root == null) {
			return;
		}
		if (depth == j) {
			System.out.print(root.getValue() + " ");
		}
		if (depth % 2 == 0) {
			levelOrderTraversalSpiralRecursive(root.getLeft(), depth, j + 1);
			levelOrderTraversalSpiralRecursive(root.getRight(), depth, j + 1);
		} else {
			levelOrderTraversalSpiralRecursive(root.getRight(), depth, j + 1);
			levelOrderTraversalSpiralRecursive(root.getLeft(), depth, j + 1);
		}
	}

	/**
	 * Prints the reverse levelOrder traversal of the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/reverse-level-order-traversal/1
	 * 
	 * @param root - root of the tree
	 */
	public void reverseLevelOrderTraversal(Node root) {
		TreeIntOperations intOperations = new TreeIntOperations();
		int size = intOperations.sizeOfTree(root);
		int[] a = new int[size];
		fillArrayInLevel(root, a);
		for (int i = size - 1; i >= 0; i--) {
			System.out.print(a[i] + " ");
		}
	}

	private void fillArrayInLevel(Node root, int[] a) {
		if (root == null) {
			return;
		}
		Deque<Node> queue = new ArrayDeque<>();
		queue.add(root);
		int i = 0;
		while (!queue.isEmpty()) {
			Node removedNode = queue.removeFirst();
			if (removedNode.getRight() != null)
				queue.add(removedNode.getRight());
			if (removedNode.getLeft() != null)
				queue.add(removedNode.getLeft());
			a[i] = removedNode.getValue();
			i++;
		}
	}

	/**
	 * Prints the boundary traversal of the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/boundary-traversal-of-binary-tree/1
	 * 
	 * @param root - root of the tree
	 */
	public void boundaryTraversal(Node root) {
		throw new UnsupportedOperationException(TreesAmazonEasyLevel.NOT_IMPLEMENTED);
	}

}
