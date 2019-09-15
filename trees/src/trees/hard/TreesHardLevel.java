package trees.hard;

import java.util.ArrayList;
import java.util.List;

import trees.basic.Node;
import trees.medium.TreesMediumLevel;

public class TreesHardLevel {

	/**
	 * makes DLL with the tree and prints reverse and correct order.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/binary-tree-to-dll/1
	 * 
	 * @param root - Root of the first tree
	 */
	public void treeToDLL(Node root) {
		TreesMediumLevel treeOperations = new TreesMediumLevel();
		List<Node> list = new ArrayList<>();
		createDLLFromTree(root, list);
		treeOperations.printDLL(list.get(0));
	}

	private void createDLLFromTree(Node root, List<Node> list) {
		if (root == null) {
			return;
		}
		createDLLFromTree(root.getLeft(), list);
		if (list.isEmpty()) {
			Node rootNode = new Node(root.getValue());
			list.add(rootNode);
		} else {
			Node currentNode = new Node(root.getValue());
			list.get(list.size() - 1).setRight(currentNode);
			currentNode.setLeft(list.get(list.size() - 1));
			list.add(currentNode);
		}
		createDLLFromTree(root.getRight(), list);
	}
}
