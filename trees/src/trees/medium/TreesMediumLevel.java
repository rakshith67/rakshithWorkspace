package trees.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import linkedlists.NodeLL;
import trees.basic.Node;
import trees.basic.TreeIntOperations;
import trees.basic.TreeTraversals;

public class TreesMediumLevel {

	/**
	 * clones the given binary tree and returns the new root.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/clone-a-binary-tree/1
	 * 
	 * @param root - Root of the first tree
	 */
	public Node cloneTree(Node root) {
		if (root == null) {
			return null;
		}
		Node newRoot = new Node(root.getValue());
		if (root.getLeft() != null) {
			newRoot.setLeft(cloneTree(root.getLeft()));
		}
		if (root.getRight() != null) {
			newRoot.setRight(cloneTree(root.getRight()));
		}
		return newRoot;
	}

	/**
	 * checks whether the given tree is foldable or not.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/foldable-binary-tree/1
	 * 
	 * @param root - Root of the first tree
	 */
	public boolean foldableTree(Node root) {
		if (root == null) {
			return Boolean.TRUE;
		}
		return isStructuralMirrorTrees(root.getLeft(), root.getRight());
	}

	private boolean isStructuralMirrorTrees(Node root1, Node root2) {
		if (root1 == null && root2 == null) {
			return Boolean.TRUE;
		}
		if (root1 == null) {
			return Boolean.FALSE;
		}
		if (root2 == null) {
			return Boolean.FALSE;
		}
		return isStructuralMirrorTrees(root1.getLeft(), root2.getRight())
				&& isStructuralMirrorTrees(root1.getRight(), root2.getLeft());
	}

	/**
	 * counts the number of nodes of the tree in the given range.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/count-bst-nodes-that-lie-in-a-given-range/1
	 * 
	 * @param root - Root of the first tree
	 */
	public int countNumberOfNodesInRange(Node root, int lowest, int highest) {
		if (root == null) {
			return 0;
		}
		if (root.getValue() < lowest) {
			return countNumberOfNodesInRange(root.getRight(), lowest, highest);
		}
		if (root.getValue() > highest) {
			return countNumberOfNodesInRange(root.getLeft(), lowest, highest);
		}
		if (root.getValue() > lowest && root.getValue() < highest) {
			return 1 + countNumberOfNodesInRange(root.getLeft(), lowest, highest)
					+ countNumberOfNodesInRange(root.getRight(), lowest, highest);
		}
		return 0;
	}

	/**
	 * makes DLL with the leaves and prints reverse and correct order.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/leaves-to-dll/1
	 * 
	 * @param root - Root of the first tree
	 */
	public void leavesToDLL(Node root) {
		List<Node> list = new ArrayList<>();
		createDLLFromLeaves(root, list);
		printDLL(list.get(0));
	}

	private void createDLLFromLeaves(Node root, List<Node> list) {
		if (root == null) {
			return;
		}
		createDLLFromLeaves(root.getLeft(), list);
		if (root.getLeft() == null && root.getRight() == null) {
			if (list.isEmpty()) {
				Node rootNode = new Node(root.getValue());
				list.add(rootNode);
			} else {
				Node currentNode = new Node(root.getValue());
				list.get(list.size() - 1).setRight(currentNode);
				currentNode.setLeft(list.get(list.size() - 1));
				list.add(currentNode);
			}
		}
		createDLLFromLeaves(root.getRight(), list);
	}

	public void printDLL(Node root) {
		while (root.getRight() != null) {
			System.out.print(root.getValue() + " ");
			root = root.getRight();
		}
		System.out.println(root.getValue() + " - traversal of DLL");
		while (root != null) {
			System.out.print(root.getValue() + " ");
			root = root.getLeft();
		}
		System.out.println(" - Reverse traversal of DLL");
	}

	/**
	 * makes CDLL with the tree and prints reverse and correct order.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/binary-tree-to-cdll/1
	 * 
	 * @param root - Root of the first tree
	 */
	public void treeToCDLL(Node root) {
		List<Node> list = new ArrayList<>();
		createDLLFromTree(root, list);
		Node lastNode = list.get(list.size() - 1);
		Node firstNode = list.get(0);
		lastNode.setRight(firstNode);
		firstNode.setLeft(lastNode);
		printCDLL(firstNode);
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

	private void printCDLL(Node head) {
		Node itr = head;
		do {
			System.out.print(itr.getValue() + " ");
			itr = itr.getRight();
		} while (head != itr);
		System.out.println(" - traversal of Tree CDLL");
		itr = itr.getLeft();
		do {
			System.out.print(itr.getValue() + " ");
			itr = itr.getLeft();
		} while (head != itr);
		System.out.println(itr.getValue() + " - Reverse traversal of tree CDLL");
	}

	/**
	 * makes tree from linked list and returns the root.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/make-binary-tree/1
	 * 
	 * @param root - Root of the first tree
	 */
	public void linkedListToTree(NodeLL head) {
		Deque<Node> deque = new ArrayDeque<>();
		Node root = null;
		while (head != null) {
			if (deque.isEmpty()) {
				root = new Node(head.getValue());
				deque.addFirst(root);
			} else {
				Node currentNode = deque.getFirst();
				Node childNode = new Node(head.getValue());
				if (currentNode.getLeft() == null) {
					currentNode.setLeft(childNode);
					deque.addLast(childNode);
				} else if (currentNode.getRight() == null) {
					currentNode.setRight(childNode);
					deque.addLast(childNode);
					deque.removeFirst();
				}
			}
			head = head.getNext();
		}
		deque.clear();
		TreeTraversals treeTraversals = new TreeTraversals();
		treeTraversals.inOrderTraversal(root);
		System.out.println(" - Tree from LinkedList Inorder traversal");
	}

	/**
	 * counts number of sub trees whose sum is x.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/count-number-of-subtrees-having-given-sum/1
	 * 
	 * @param root - Root of the first tree
	 * @param x    - sum of the sub tree
	 */
	public int countSubtreesWithSumX(Node root, int x) {
		if (root == null) {
			return 0;
		}
		TreeIntOperations treeIntOperations = new TreeIntOperations();
		if (treeIntOperations.sumOfTree(root) == x) {
			return 1 + countSubtreesWithSumX(root.getLeft(), x) + countSubtreesWithSumX(root.getRight(), x);
		} else {
			return countSubtreesWithSumX(root.getLeft(), x) + countSubtreesWithSumX(root.getRight(), x);
		}
	}

	/**
	 * modifies BST tree with greater values and current node sum.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/add-all-greater-values-to-every-node-in-a-bst/1
	 * 
	 * @param root - Root of the first tree
	 */
	public void modifyBSTWithGreaterNodesSum(Node root) {
		int[] a = new int[1];
		modifyBSTValues(root, a);
		TreeTraversals treeTraversals = new TreeTraversals();
		treeTraversals.inOrderTraversal(root);
		System.out.println(" - Inorder traversal of modified BST");
	}

	private void modifyBSTValues(Node root, int[] a) {
		if (root == null) {
			return;
		}
		modifyBSTValues(root.getRight(), a);
		a[0] = a[0] + root.getValue();
		root.setValue(a[0]);
		modifyBSTValues(root.getLeft(), a);
	}

	/**
	 * makes tree from preOrder traversal and returns the root.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/construct-tree-from-preorder-traversal/1
	 * 
	 * @param root - Root of the first tree
	 */
	public Node constructTree(int n, int[] pre, char[] preLN) {
		throw new UnsupportedOperationException();
	}

}
