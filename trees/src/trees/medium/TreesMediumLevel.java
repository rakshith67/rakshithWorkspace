package trees.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import linkedlists.NodeLL;
import trees.basic.Node;
import trees.basic.NodeRight;
import trees.basic.TreeIntOperations;
import trees.basic.TreeTraversals;
import trees.easy.TreesEasyLevel2;

public class TreesMediumLevel {

	/**
	 * clones the given binary tree and returns the new root.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/clone-a-binary-tree/1
	 * 
	 * @param root - Root of the tree
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
	 * @param root - Root of the tree
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
	 * @param root - Root of the tree
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
	 * @param root - Root of the tree
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
	 * @param root - Root of the tree
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
	 * @param root - Root of the tree
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
	 * @param root - Root of the tree
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
	 * @param root - Root of the tree
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
	 * modifies BST tree with greater values and current node sum.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/extreme-nodes-in-alternate-order/1
	 * 
	 * @param root - Root of the tree
	 */
	public void printExtremeNodesAlternate(Node root) {
		Map<Integer, Integer> map = new HashMap<>();
		printExtremeNodes(root, map, 0);
		int i = 0;
		while (map.get(i) != null) {
			System.out.print(map.get(i) + " ");
			i++;
		}
	}

	private void printExtremeNodes(Node root, Map<Integer, Integer> map, int level) {
		if (root == null) {
			return;
		}
		if (level % 2 != 0) {
			map.putIfAbsent(level, map.put(level, root.getValue()));
		} else {
			map.put(level, root.getValue());
		}
		printExtremeNodes(root.getLeft(), map, level + 1);
		printExtremeNodes(root.getRight(), map, level + 1);
	}

	/**
	 * prints the corner nodes of the each level of the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/leftmost-and-rightmost-nodes-of-binary-tree/1
	 * 
	 * @param root - Root of the tree
	 */
	public void printCornerNodes(Node root) {
		Map<Integer, Integer> map = new HashMap<>();
		fillMapWithCornerNodes(root, map, 0);
		int i = 0;
		while (map.get(-i) != null) {
			if (i == 0) {
				System.out.print(map.get(0) + " ");
			} else {
				System.out.print(map.get(-i) + " ");
				if (map.get(i) != null) {
					System.out.print(map.get(i) + " ");
				}
			}
			i++;
		}
	}

	private void fillMapWithCornerNodes(Node root, Map<Integer, Integer> map, int level) {
		if (root == null) {
			return;
		}
		if (map.get(level) == null) {
			map.put(-level, root.getValue());
			map.put(level, root.getValue());
		} else {
			map.put(level, root.getValue());
		}
		fillMapWithCornerNodes(root.getLeft(), map, level + 1);
		fillMapWithCornerNodes(root.getRight(), map, level + 1);
	}

	/**
	 * returns the count of pairs violating BST property.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/pairs-violating-bst-property/1
	 * 
	 * @param root - Root of the tree
	 */
	public int countPairsViolatingBST(Node root) {
		List<Integer> list = new ArrayList<>();
		fillListInorder(root, list);
		int count = 0;
		int size = list.size();
		int i = 0;
		while (i < size) {
			int j = i + 1;
			while (j < size) {
				if (list.get(i) > list.get(j)) {
					count++;
				}
				j++;
			}
			i++;
		}
		return count;
	}

	private void fillListInorder(Node root, List<Integer> list) {
		if (root == null) {
			return;
		}
		fillListInorder(root.getLeft(), list);
		list.add(root.getValue());
		fillListInorder(root.getRight(), list);

	}

	/**
	 * prints the inOrder traversal of two merged BSTs.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/construct-tree-from-preorder-traversal/1
	 * 
	 * @param root - Root of the tree
	 */
	public void mergeTwoBSTs(Node root1, Node root2) {
		List<Integer> list = new ArrayList<>();
		fillListInorder(root1, list);
		fillListInorder(root2, list);
		list.sort(null);
		for (Integer integer : list) {
			System.out.print(integer + " ");
		}
	}

	/**
	 * returns the count of pairs violating BST property.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/pairs-violating-bst-property/1
	 * 
	 * @param root - Root of the tree
	 */
	public void connectNodesWithRight(NodeRight root) {
		if (root != null) {
			Map<Integer, NodeRight> map = new HashMap<>();
			fillListWithFirstLevelNode(root, map, 0);
			setConnectionsToRight(root, map, 0);
			printLevelOrder(map);
			map.clear();
			System.out.println(" - LevelOrder of connected tree");
		}
	}

	private void fillListWithFirstLevelNode(NodeRight root, Map<Integer, NodeRight> map, int level) {
		if (root == null) {
			return;
		}
		map.putIfAbsent(-level, root);
		map.put(level, root);
		fillListWithFirstLevelNode(root.getLeft(), map, level + 1);
		fillListWithFirstLevelNode(root.getRight(), map, level + 1);
	}

	private void setConnectionsToRight(NodeRight root, Map<Integer, NodeRight> map, int level) {
		Deque<NodeRight> deque = new ArrayDeque<>();
		deque.add(root);
		NodeRight previousNode = null;
		while (!deque.isEmpty()) {
			NodeRight currentNode = deque.removeFirst();
			if (previousNode == null && map.get(level) == currentNode) {
				level++;
			} else if (previousNode != null && map.get(level) == currentNode) {
				previousNode.setNextRight(currentNode);
				previousNode = null;
				level++;
			} else if (previousNode == null) {
				previousNode = currentNode;
			} else if (map.get(level) != currentNode) {
				previousNode.setNextRight(currentNode);
				previousNode = currentNode;
			}
			if (currentNode.getLeft() != null) {
				deque.addLast(currentNode.getLeft());
			}
			if (currentNode.getRight() != null) {
				deque.addLast(currentNode.getRight());
			}
		}
	}

	private void printLevelOrder(Map<Integer, NodeRight> map) {
		int i = 0;
		while (map.get(-i) != null) {
			NodeRight root = map.get(-i);
			while (root != null) {
				System.out.print(root.getData() + " ");
				root = root.getNextRight();
			}
			i++;
		}
	}

	/**
	 * returns the minimum diff between nodes and k.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/find-the-closest-element-in-bst/1
	 * 
	 * @param root - Root of the tree
	 */
	public int minimumDifference(Node root, int k) {
		int[] a = new int[] { 529875 };
		minDifference(root, a, k);
		return a[0];
	}

	private void minDifference(Node root, int[] a, int k) {
		if (root == null) {
			return;
		}
		if (Math.abs(root.getValue() - k) < a[0]) {
			a[0] = Math.abs(root.getValue() - k);
		}
		if (root.getValue() > k) {
			minDifference(root.getLeft(), a, k);
		}
		if (root.getValue() < k) {
			minDifference(root.getRight(), a, k);
		}
	}

	/**
	 * checks whether it is a sub tree or not.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/check-if-subtree/1
	 * 
	 * @param root - Root of the tree
	 */
	public boolean isSubTree(Node root, Node subTreeRoot) {
		if (root == null && subTreeRoot == null) {
			return Boolean.TRUE;
		}
		if ((root == null) || (subTreeRoot == null)) {
			return Boolean.FALSE;
		}
		TreesEasyLevel2 treeEasyLevel2 = new TreesEasyLevel2();
		boolean isIdentical = treeEasyLevel2.isIdenticalTrees(root, subTreeRoot);
		return isIdentical || isSubTree(root.getLeft(), subTreeRoot) || isSubTree(root.getRight(), subTreeRoot);
	}

	/**
	 * builds the tree from postOrder and inOrder.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/tree-from-postorder-and-inorder/1
	 * 
	 * @param post - postOrder of the tree
	 * @param in   - inOrder of the tree.
	 */
	public Node buildTree(int[] in, int[] post, int n) {
		int start = 0;
		int end = n - 1;
		int[] currentIndex = new int[] { n - 1 };
		return buildTreeFromInorderAndPostOrder(in, post, start, end, currentIndex);
	}

	private Node buildTreeFromInorderAndPostOrder(int[] in, int[] post, int start, int end, int[] currentIndex) {
		if (start > end) {
			return null;
		}
		Node node = new Node(post[currentIndex[0]]);
		currentIndex[0]--;
		if (start == end) {
			return node;
		}
		TreesEasyLevel2 treeEasyLevel2 = new TreesEasyLevel2();
		int index = treeEasyLevel2.search(in, node.getValue(), start, end);
		node.setRight(buildTreeFromInorderAndPostOrder(in, post, index + 1, end, currentIndex));
		node.setLeft(buildTreeFromInorderAndPostOrder(in, post, start, index - 1, currentIndex));
		return node;
	}

	/**
	 * Serializes the tree into some generic String expression.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/serialize-and-deserialize-a-binary-tree/1
	 * 
	 * @param preOrder - root of the tree
	 */
	public String serialize(Node root) {
		StringBuilder builder = new StringBuilder();
		fillListWithSerializeValues(root, builder);
		return builder.toString();
	}

	private void fillListWithSerializeValues(Node root, StringBuilder builder) {
		if (root == null) {
			return;
		}
		if (root.getLeft() == null && root.getRight() == null) {
			return;
		}
		if (root.getLeft() != null) {
			builder.append(root.getValue() + " " + root.getLeft().getValue() + " L ");
		}
		if (root.getRight() != null) {
			builder.append(root.getValue() + " " + root.getRight().getValue() + " R ");
		}
		fillListWithSerializeValues(root.getLeft(), builder);
		fillListWithSerializeValues(root.getRight(), builder);
	}

	/**
	 * Deserializes the String into tree and returns the root.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/serialize-and-deserialize-a-binary-tree/1
	 * 
	 * @param expression - generic expression of the tree
	 */
	public Node deserialize(String expression) {
		int length = expression.length();
		int i = 0;
		Node root = null;
		while (i < length) {
			if (i == 0) {
				int value = findValue(expression, i);
				root = new Node(value);
			}
			int parentValue = findValue(expression, i);
			while (expression.charAt(i) != ' ') {
				i++;
			}
			i++;
			int rootValue = findValue(expression, i);
			while (expression.charAt(i) != ' ') {
				i++;
			}
			i++;
			char lr = expression.charAt(i);
			insert(root, parentValue, rootValue, lr);
			i = i + 2;

		}
		return root;
	}

	private void insert(Node root, int a, int a1, char lr) {
		if (root == null) {
			return;
		}
		if (root.getValue() == a) {
			if (lr == 'L') {
				root.setLeft(new Node(a1));
			} else if (lr == 'R') {
				root.setRight(new Node(a1));
			}
			return;
		}
		insert(root.getLeft(), a, a1, lr);
		insert(root.getRight(), a, a1, lr);
	}

	private int findValue(String expression, int currentIndex) {
		int value = 0;
		while (currentIndex < expression.length() && expression.charAt(currentIndex) != ' ') {
			value = value * 10 + Integer.valueOf(expression.charAt(currentIndex)) - '0';
			currentIndex++;
		}
		return value;
	}

	/**
	 * makes tree from preOrder traversal and leaf node array and returns the root.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/construct-tree-from-preorder-traversal/1
	 * 
	 * @param pre - preOrder traversal of the tree
	 */
	public Node constructTree(int n, int[] pre, char[] preLN) {
		int[] currentIndex = new int[1];
		return constructTreeFromPreOrder(pre, preLN, currentIndex, n);
	}

	private Node constructTreeFromPreOrder(int[] pre, char[] preLN, int[] currentIndex, int n) {
		if (currentIndex[0] >= n) {
			return null;
		}
		Node root = new Node(pre[currentIndex[0]]);
		currentIndex[0]++;
		if (preLN[currentIndex[0] - 1] == 'N') {
			root.setLeft(constructTreeFromPreOrder(pre, preLN, currentIndex, n));
			root.setRight(constructTreeFromPreOrder(pre, preLN, currentIndex, n));
		}
		return root;
	}

	/**
	 * returns the sum of product of node and its image node.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/image-multiplication/0
	 * 
	 * @param root - Root of the tree
	 */
	public int imageMultiplicationSum(Node root) {
		if (root == null) {
			return 0;
		}
		int[] product = new int[1];
		Map<Node, Node> map = new HashMap<>();
		fillSumOfProduct(root, root, product, map);
		map.clear();
		return product[0];
	}

	private void fillSumOfProduct(Node root, Node image, int[] product, Map<Node, Node> map) {
		product[0] += image.getValue() * root.getValue();
		map.put(root, image);
		if (image.getLeft() != null && root.getRight() != null && map.get(image.getLeft()) == null) {
			fillSumOfProduct(root.getRight(), image.getLeft(), product, map);
		}
		if (image.getRight() != null && root.getLeft() != null && map.get(image.getRight()) == null) {
			fillSumOfProduct(root.getLeft(), image.getRight(), product, map);
		}
	}

	/**
	 * prints the root nodes of duplicate subtrees of the tree.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/duplicate-subtrees/1
	 * 
	 * @param root - Root of the tree
	 */
	public void printDupSubTrees(Node root) {
		System.out.print("Duplicate subtree roots: ");
		Map<String, Integer> map = new HashMap<>();
		List<Integer> list = new ArrayList<>();
		isDuplicateSubTree(root, map, list);
		map.clear();
		if (list.isEmpty()) {
			System.out.println("-1");
		} else {
			list.sort(null);
			for (Integer integer : list) {
				System.out.print(integer + " ");
			}
			System.out.println();
		}
	}

	private String isDuplicateSubTree(Node root, Map<String, Integer> map, List<Integer> list) {
		if (root == null) {
			return "";
		}
		String value = isDuplicateSubTree(root.getLeft(), map, list);
		value += isDuplicateSubTree(root.getRight(), map, list);
		value += "$" + root.getValue();
		if (map.get(value) != null && map.get(value) == 1) {
			list.add(root.getValue());
			map.put(value, 2);
		} else {
			map.put(value, 1);
		}
		return value;
	}

}
