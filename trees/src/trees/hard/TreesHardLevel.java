package trees.hard;

import java.util.ArrayList;
import java.util.List;

import trees.basic.Node;
import trees.easy.TreesEasyLevel2;
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
		list.clear();
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

	/**
	 * This function returns distance of root from target node, it returns -1 if
	 * target node is not present in tree rooted with root.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/nodes-at-given-distance-in-binary-tree/1
	 * 
	 * @param root     - Root of the first tree
	 * @param target   - target node in tree
	 * @param distance - prints this distance nodes
	 */
	public int printKdistanceNodes(Node root, Node target, int distance) {
		if (root == null) {
			return -1;
		}
		if (root == target) {
			printNodesDown(root, distance);
			return 0;
		}
		int left = printKdistanceNodes(root.getLeft(), target, distance);
		if (left != -1) {
			if (left + 1 == distance) {
				System.out.print(root.getValue() + " ");
			} else {
				printNodesDown(root.getRight(), distance - left - 2);
			}
			return 1 + left;
		}
		int right = printKdistanceNodes(root.getRight(), target, distance);
		if (right != -1) {
			if (right + 1 == distance) {
				System.out.print(root.getValue() + " ");
			} else {
				printNodesDown(root.getLeft(), distance - right - 2);
			}
			return 1 + right;
		}
		return -1;
	}

	private void printNodesDown(Node root, int distance) {
		if (root == null || distance < 0) {
			return;
		}
		if (distance == 0) {
			System.out.print(root.getValue() + " ");
			return;
		}
		printNodesDown(root.getLeft(), distance - 1);
		printNodesDown(root.getRight(), distance - 1);
	}

	/**
	 * This function the count of the number of turns needs to reach from one node
	 * to another node of the Binary tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/number-of-turns-in-binary-tree/1
	 * 
	 * @param root   - Root of the first tree
	 * @param first  - first node in tree
	 * @param second - second node in tree
	 */
	public int numberOfTurns(Node root, int first, int second) {
		TreesEasyLevel2 treeOperations = new TreesEasyLevel2();
		Node LCA = treeOperations.leastCommonAncestor(root, first, second);
		int[] count = new int[1];
		if (LCA == null) {
			return -1;
		}
		if (LCA.getValue() != first && LCA.getValue() != second) {
			countTurn(LCA.getRight(), second, false, count);
			countTurn(LCA.getLeft(), second, true, count);
			countTurn(LCA.getRight(), first, false, count);
			countTurn(LCA.getLeft(), first, true, count);
			return count[0] + 1;
		}
		if (LCA.getValue() == first) {
			countTurn(LCA.getRight(), second, false, count);
			countTurn(LCA.getLeft(), second, true, count);
			return count[0];
		} else {
			countTurn(LCA.getRight(), first, false, count);
			countTurn(LCA.getLeft(), first, true, count);
			return count[0];
		}
	}

	private boolean countTurn(Node root, int key, boolean turn, int[] count) {
		if (root == null) {
			return false;
		}
		if (root.getValue() == key) {
			return true;
		}
		if (turn) {
			if (countTurn(root.getLeft(), key, turn, count)) {
				return true;
			}
			if (countTurn(root.getRight(), key, !turn, count)) {
				count[0]++;
				return true;
			}
		} else {
			if (countTurn(root.getRight(), key, turn, count)) {
				return true;
			}
			if (countTurn(root.getLeft(), key, !turn, count)) {
				count[0]++;
				return true;
			}
		}
		return false;
	}
}
