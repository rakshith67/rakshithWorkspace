package trees;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class TreeViews {

	/**
	 * Prints the left view of the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/left-view-of-binary-tree/1
	 * 
	 * @param root - root of the tree
	 */
	public void leftView(Node root) {
		TreeIntOperations treeOperations = new TreeIntOperations();
		int height = treeOperations.height(root);
		int[] a = new int[height];
		for (int i = 0; i < height; i++) {
			printFirstNodeOfLevelFromLeft(root, i, 0, a);
		}
		for (int i = 0; i < height; i++) {
			System.out.print(a[i] + " ");
		}
	}

	private void printFirstNodeOfLevelFromLeft(Node root, int i, int j, int[] a) {
		if (root == null) {
			return;
		}
		if (i == j && a[i] == 0) {
			a[i] = root.getValue();
			return;
		}
		if (a[i] == 0) {
			if (root.getLeft() != null) {
				printFirstNodeOfLevelFromLeft(root.getLeft(), i, j + 1, a);
			}
			if (root.getRight() != null) {
				printFirstNodeOfLevelFromLeft(root.getRight(), i, j + 1, a);
			}
		}
	}

	/**
	 * Prints the right view of the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/right-view-of-binary-tree/1
	 * 
	 * @param root - root of the tree
	 */
	public void rightView(Node root) {
		TreeIntOperations treeOperations = new TreeIntOperations();
		int height = treeOperations.height(root);
		int[] a = new int[height];
		for (int i = 0; i < height; i++) {
			printFirstNodeOfLevelFromRight(root, i, 0, a);
		}
		for (int i = 0; i < height; i++) {
			System.out.print(a[i] + " ");
		}
	}

	private void printFirstNodeOfLevelFromRight(Node root, int i, int j, int[] a) {
		if (root == null) {
			return;
		}
		if (i == j && a[i] == 0) {
			a[i] = root.getValue();
			return;
		}
		if (a[i] == 0) {
			if (root.getRight() != null) {
				printFirstNodeOfLevelFromLeft(root.getRight(), i, j + 1, a);
			}
			if (root.getLeft() != null) {
				printFirstNodeOfLevelFromLeft(root.getLeft(), i, j + 1, a);
			}
		}
	}

	/**
	 * Prints the top view of the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/top-view-of-binary-tree/1
	 * 
	 * @param root - root of the tree
	 */
	public void topView(Node root) {
		int[] edge = new int[2];
		verticalEdgesFill(root, edge, 0);
		edge[0] = Math.abs(edge[0]);
		edge[1] = Math.abs(edge[1]);
		int[] a = new int[edge[0] + edge[1] + 1];
		Arrays.fill(a, -765324732);
		fillArrayInTopView(root, edge[1], a);
		for (int i = 0; i < edge[1] + edge[0] + 1; i++) {
			if (a[i] != -765324732)
				System.out.print(a[i] + " ");
		}
	}

	private void verticalEdgesFill(Node root, int[] edge, int currentEdge) {
		if (edge[0] < currentEdge) {
			edge[0] = currentEdge;
		} else if (edge[1] > currentEdge) {
			edge[1] = currentEdge;
		}
		if (root.getLeft() != null) {
			verticalEdgesFill(root.getLeft(), edge, currentEdge - 1);
		}
		if (root.getRight() != null) {
			verticalEdgesFill(root.getRight(), edge, currentEdge + 1);
		}
	}

	private void fillArrayInTopView(Node root, int rootIndex, int[] a) {
		Deque<Node> deque = new ArrayDeque<>();
		deque.push(root);
		Map<Node, Integer> map = new HashMap<>();
		map.put(root, rootIndex);
		while (!deque.isEmpty()) {
			Node currentNode = deque.removeFirst();
			int currentLevel = map.get(currentNode);
			if (a[currentLevel] == -765324732) {
				a[currentLevel] = currentNode.getValue();
			}
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
	 * Prints the bottom view of the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1
	 * 
	 * @param root - root of the tree
	 */
	public void bottomView(Node root) {
		int[] edge = new int[2];
		verticalEdgesFill(root, edge, 0);
		edge[0] = Math.abs(edge[0]);
		edge[1] = Math.abs(edge[1]);
		int[] a = new int[edge[1] + edge[0] + 1];
		Arrays.fill(a, -765324732);
		fillArrayInBottomView(root, edge[1], a);
		for (int i = 0; i < edge[1] + edge[0] + 1; i++) {
			if (a[i] != -765324732)
				System.out.print(a[i] + " ");
		}
	}

	private void fillArrayInBottomView(Node root, int rootIndex, int[] a) {
		Deque<Node> deque = new ArrayDeque<>();
		deque.push(root);
		Map<Node, Integer> map = new HashMap<>();
		map.put(root, rootIndex);
		while (!deque.isEmpty()) {
			Node currentNode = deque.removeFirst();
			int currentLevel = map.get(currentNode);
			a[currentLevel] = currentNode.getValue();
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
}
