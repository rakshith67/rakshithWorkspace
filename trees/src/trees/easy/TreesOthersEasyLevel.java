package trees.easy;

import java.util.ArrayList;
import java.util.List;

import trees.basic.Node;
import trees.basic.TreeIntOperations;
import trees.basic.TreeTraversals;

public class TreesOthersEasyLevel {

	/**
	 * prints the postOrder traversal from inOrder and preOrder traversals.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/postorder-traversal-from-given-inorder-and-preorder-traversals/1
	 * 
	 * @param inOrder  - inOrder traversal
	 * @param preOrder - preOrder traversal
	 * @param n        - size of the array
	 */
	public void printPostOrder(int[] inOrder, int[] preOrder, int n) {
		TreesAmazonEasyLevel2 treeAmazonEasyLevel2 = new TreesAmazonEasyLevel2();
		int[] currentIndex = new int[1];
		Node root = treeAmazonEasyLevel2.buildTree(inOrder, preOrder, 0, n - 1, currentIndex);
		TreeTraversals treeTraversals = new TreeTraversals();
		treeTraversals.postOrderTraversal(root);
	}

	/**
	 * checks if the binary tree is perfect or not.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/perfect-binary-tree/1
	 * 
	 * @param root - Root of the tree
	 */
	public boolean isPerfectTree(Node root) {
		int[] leafLevel = new int[1];
		int level = 0;
		return checkPerfectTree(root, leafLevel, level);
	}

	private boolean checkPerfectTree(Node root, int[] leafLevel, int currentlevel) {
		if (root == null) {
			return Boolean.TRUE;
		} else if (root.getLeft() == null && root.getRight() != null) {
			return Boolean.FALSE;
		} else if (root.getLeft() != null && root.getRight() == null) {
			return Boolean.FALSE;
		} else if (root.getLeft() == null && root.getRight() == null) {
			if (leafLevel[0] == 0) {
				leafLevel[0] = currentlevel;
			} else if (leafLevel[0] != currentlevel) {
				return Boolean.FALSE;
			}
		}
		return checkPerfectTree(root.getLeft(), leafLevel, currentlevel + 1)
				&& checkPerfectTree(root.getRight(), leafLevel, currentlevel + 1);
	}

	/**
	 * prints the odd level nodes of the tree.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/nodes-at-odd-levels/1
	 * 
	 * @param root - Root of the tree
	 */
	public void printNodesAtOddLevel(Node root) {
		TreeIntOperations treeIntOperations = new TreeIntOperations();
		int height = treeIntOperations.height(root);
		for (int i = 1; i <= height; i++) {
			if (i % 2 == 1) {
				printNodesOdd(root, i, 1);
			}
		}
	}

	private void printNodesOdd(Node root, int level, int currentDepth) {
		if (root == null) {
			return;
		} else if (level == currentDepth) {
			System.out.print(root.getValue() + " ");
		}
		printNodesOdd(root.getLeft(), level, currentDepth + 1);
		printNodesOdd(root.getRight(), level, currentDepth + 1);
	}

	/**
	 * returns the number of traversals to leaves within the budget.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/leaf-under-budget/1
	 * 
	 * @param root   - Root of the tree
	 * @param budget = total budget
	 */
	public int getCountForBudget(Node root, int budget) {
		List<Integer> leafPathList = new ArrayList<>();
		fillArrayWithLeafPathLevels(root, leafPathList, 0);
		leafPathList.sort(null);
		int sum = 0;
		int count = 0;
		for (Integer integer : leafPathList) {
			sum = sum + integer;
			if (sum > budget) {
				break;
			}
			count++;
		}
		return count;
	}

	private void fillArrayWithLeafPathLevels(Node root, List<Integer> leafPathArray, int pathLength) {
		if (root == null) {
			return;
		}
		pathLength++;
		if (root.getLeft() == null && root.getRight() == null) {
			leafPathArray.add(pathLength);
		}
		fillArrayWithLeafPathLevels(root.getLeft(), leafPathArray, pathLength);
		fillArrayWithLeafPathLevels(root.getRight(), leafPathArray, pathLength);
	}

	/**
	 * prints the sum of minimum level leaf nodes of the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/sum-of-leaf-nodes-at-min-level/1
	 * 
	 * @param root - Root of the tree
	 */
	public void printSumOfLeafNodesAtMinLevel(Node root) {
		TreeIntOperations treeIntOperations = new TreeIntOperations();
		int height = treeIntOperations.height(root);
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < height; i++) {
			addLeafNodesToList(root, i, 0, list);
			if (!list.isEmpty()) {
				break;
			}
		}
		int sum = 0;
		for (Integer integer : list) {
			sum = sum + integer;
		}
		System.out.println("Sum of leaf nodes at min level " + sum);
	}

	private void addLeafNodesToList(Node root, int level, int currentLevel, List<Integer> list) {
		if (root == null) {
			return;
		} else if (level == currentLevel && root.getLeft() == null && root.getRight() == null) {
			list.add(root.getValue());
		}
		addLeafNodesToList(root.getLeft(), level, currentLevel + 1, list);
		addLeafNodesToList(root.getRight(), level, currentLevel + 1, list);
	}

	/**
	 * prints the predecessor and successor of inOrder traversal of the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/predecessor-and-successor/1
	 * 
	 * @param root - Root of the tree
	 * @param key  - key whose predecessor and successor need to find
	 */
	public void printPredecessorAndSuccessor(Node root, int key) {
		List<Integer> list = new ArrayList<>();
		TreesAmazonEasyLevel2 treeOperations = new TreesAmazonEasyLevel2();
		treeOperations.inOrderTraversal(root, list);
		int predecessor = -1;
		int successor = -1;
		for (Integer integer : list) {
			if (integer > key) {
				successor = integer;
				break;
			}
			if (integer != key) {
				predecessor = integer;
			}
		}
		System.out.println("Predecessor and Successors is " + predecessor + " " + successor);
	}

	/**
	 * deletes the nodes that are greater than the key
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/delete-nodes-greater-than-k/1
	 * 
	 * @param root - Root of the tree
	 * @param key  - key
	 */
	public Node deleteNodesGreaterThanKey(Node root, int key) {
		if (root == null) {
			return null;
		}
		root.setLeft(deleteNodesGreaterThanKey(root.getLeft(), key));
		root.setRight(deleteNodesGreaterThanKey(root.getRight(), key));
		if (root.getValue() > key) {
			return root.getLeft();
		}
		return root;
	}

	/**
	 * checks if the binary tree has max heap property or not.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/perfect-binary-tree/1
	 * 
	 * @param root - Root of the tree
	 */
	public boolean isMaxHeap(Node root) {
		if (root == null) {
			return Boolean.TRUE;
		}
		if ((root.getLeft() != null && root.getValue() < root.getLeft().getValue())
				|| (root.getRight() != null && root.getValue() < root.getRight().getValue())) {
			return Boolean.FALSE;
		}
		return isMaxHeap(root.getLeft()) && isMaxHeap(root.getRight());
	}

	/**
	 * builds the tree from ternary expression.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/convert-ternary-expression-to-binary-tree/1
	 * 
	 * @param root - Root of the tree
	 */
	public Node buildTreeFromTernaryExp(String expression, int i) {
		if (i > expression.length())
			return null;
		Node root = new Node(Integer.valueOf(expression.charAt(i)));
		i++;
		if (i < expression.length() && expression.charAt(i) == '?')
			root.setLeft(buildTreeFromTernaryExp(expression, i + 1));
		if (i < expression.length() && expression.charAt(i) == ':')
			root.setRight(buildTreeFromTernaryExp(expression, i + 1));
		return root;
	}

	/**
	 * determines the shortest distance between the two nodes in a infinity tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/find-the-distance-between-two-nodes/0
	 * 
	 * @param root - Root of the tree
	 */
	public int shortestDistance(int x, int y) {
		if (x < y) {
			int temp = x;
			x = y;
			y = temp;
		}
		int c = 0;
		while (x != y) {
			++c;
			if (x > y) {
				x = x / 2;
			}
			if (x < y) {
				y = y / 2;
				++c;
			}
		}
		return c;
	}

	/**
	 * determines the sum of the kth level of the tree.Input is taken as string
	 * which is in the form of expression (0(5(6()())(4()(9()())))(7(1()())(3()())))
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/binary-tree-k-level-sum/0
	 * 
	 * @param root - Root of the tree
	 */
	public int kthLevelSum(String expression, int k) {
		int level = -1;
		int sum = 0;
		int i = 0;
		while (i < expression.length()) {
			if (expression.charAt(i) == '(') {
				level++;
				i++;
			} else if (expression.charAt(i) == ')') {
				level--;
				i++;
			} else if (level == k) {
				int power = 0;
				int j = i;
				while (expression.charAt(j) != ')' && expression.charAt(j) != '(') {
					power++;
					j++;
				}
				int value = 0;
				int currentIndex = 0;
				while (currentIndex < power) {
					value = (int) (value + (expression.charAt(i) - '0') * Math.pow(10, power - currentIndex - 1d));
					i++;
					currentIndex++;
				}
				System.out.print(value + " ");
				sum = sum + value;
			} else {
				i++;
			}
		}
		return sum;
	}
}
