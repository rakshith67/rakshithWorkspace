package trees;

import java.util.ArrayDeque;
import java.util.Deque;

public class TreeTraversals {

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

	public void lineOrderTraversal(Node root) {
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
