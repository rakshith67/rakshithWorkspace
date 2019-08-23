package trees;

import java.util.ArrayDeque;
import java.util.Deque;

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
			if (removedNode != null) {
				if (removedNode.getLeft() != null)
					queue.add(removedNode.getLeft());
				if (removedNode.getRight() != null)
					queue.add(removedNode.getRight());
				System.out.print(removedNode.getValue() + " ");
			}
		}
	}
}
