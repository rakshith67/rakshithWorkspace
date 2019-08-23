package trees;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 
 * @author Rakshith
 */
public class TreeIntOperations {

	/**
	 * determines whether the tree is balanced or not. A Tree is balanced if
	 * difference in heights of let and right is less than or equal to 1
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/check-for-balanced-tree/1
	 * 
	 * @param root - the root of the tree
	 */
	public void checkIfBalanced(Node root) {
		if (balancedTree(root)) {
			System.out.println("Balanced Tree");
		} else {
			System.out.println("Unbalanced Tree");
		}
	}

	private boolean balancedTree(Node root) {
		if (root == null) {
			return true;
		} else if (root.getLeft() == null && root.getRight() == null) {
			return true;
		}
		int leftHeight = 1 + height(root.getLeft());
		int rightHeight = 1 + height(root.getRight());
		int diff = leftHeight - rightHeight;
		if (Math.abs(diff) > 1) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * determines the depth or height of the tree.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/height-of-binary-tree/1
	 * 
	 * @param root - the root of the tree
	 */
	public int height(Node root) {
		if (root == null) {
			return 0;
		}
		int leftHeight = 1 + height(root.getLeft());
		int rightHeight = 1 + height(root.getRight());
		int height = leftHeight > rightHeight ? leftHeight : rightHeight;
		return height;
	}

	/**
	 * determines the size or number of nodes of the tree.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/size-of-binary-tree/1
	 * 
	 * @param root - the root of the tree
	 */
	public int sizeOfTree(Node root) {
		if (root == null) {
			return 0;
		} else {
			return sizeOfTree(root.getRight()) + sizeOfTree(root.getLeft()) + 1;
		}
	}

	/**
	 * determines the number of leaves of the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/count-leaves-in-binary-tree/1
	 * 
	 * @param root - the root of the tree
	 */
	public int numberOfLeaves(Node root) {
		if (root == null) {
			return 0;
		} else if (root.getLeft() == null && root.getRight() == null) {
			return 1;
		} else {
			return numberOfLeaves(root.getLeft()) + numberOfLeaves(root.getRight());
		}
	}

	/**
	 * determines the number of non leaves of the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/count-non-leaf-nodes-in-tree/1
	 * 
	 * @param root - the root of the tree
	 */
	public int numberOfNonLeaves(Node root) {
		int a[] = new int[1];
		countNumberOfNonLeaves(root, a);
		return a[0];
	}

	private void countNumberOfNonLeaves(Node root, int a[]) {
		if (root == null) {
			return;
		} else if (root.getLeft() != null || root.getRight() != null) {
			a[0]++;
		}
		countNumberOfNonLeaves(root.getLeft(), a);
		countNumberOfNonLeaves(root.getRight(), a);
	}

	/**
	 * determines the sum of values of all nodes of the tree.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/sum-of-binary-tree/1
	 * 
	 * @param root - the root of the tree
	 */
	public int sumOfTree(Node root) {
		if (root == null) {
			return 0;
		} else if (root.getLeft() == null && root.getRight() == null) {
			return root.getValue();
		} else {
			return root.getValue() + sumOfTree(root.getLeft()) + sumOfTree(root.getRight());
		}
	}

	/**
	 * determines the sum of values of all leaf nodes of the tree.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/sum-of-leaf-nodes/1
	 * 
	 * @param root - the root of the tree
	 */
	public int sumOfLeaves(Node root) {
		if (root == null) {
			return 0;
		} else if (root.getLeft() == null && root.getRight() == null) {
			return root.getValue();
		} else {
			return sumOfLeaves(root.getLeft()) + sumOfLeaves(root.getRight());
		}
	}

	/**
	 * determines the sum of values of all right leaf nodes of the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/sum-of-right-leaf-nodes/1
	 * 
	 * @param root - the root of the tree
	 */
	public int sumOfRightLeaves(Node root, int number) {
		if (root == null) {
			return 0;
		} else if (root.getLeft() == null && root.getRight() == null && number != 1) {
			return root.getValue();
		} else {
			return sumOfRightLeaves(root.getLeft(), 1) + sumOfRightLeaves(root.getRight(), -1);
		}
	}

	/**
	 * determines the sum of values of all left leaf nodes of the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/sum-of-left-leaf-nodes/1
	 * 
	 * @param root - the root of the tree
	 */
	public int sumOfLeftLeaves(Node root, int number) {
		if (root == null) {
			return 0;
		} else if (root.getLeft() == null && root.getRight() == null && number != -1) {
			return root.getValue();
		} else {
			return sumOfLeftLeaves(root.getLeft(), 1) + sumOfLeftLeaves(root.getRight(), -1);
		}
	}

	/**
	 * determines the maximum element in the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/max-and-min-element-in-binary-tree/1
	 * 
	 * @param root - the root of the tree
	 */
	public int maxElement(Node root) {
		if (root == null) {
			return 0;
		}
		Deque<Node> deque = new ArrayDeque<>();
		deque.add(root);
		int maximum = root.getValue();
		while (!deque.isEmpty()) {
			Node currentNode = deque.removeFirst();
			if (currentNode.getValue() > maximum) {
				maximum = currentNode.getValue();
			}
			if (currentNode.getRight() != null) {
				deque.add(currentNode.getRight());
			}
			if (currentNode.getLeft() != null) {
				deque.add(currentNode.getLeft());
			}
		}
		return maximum;
	}

	/**
	 * determines the minimum element in the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/minimum-element-in-bst/1
	 * 
	 * @param root - the root of the tree
	 */
	public int minElement(Node root) {
		if (root == null) {
			return 0;
		}
		Deque<Node> deque = new ArrayDeque<>();
		deque.add(root);
		int minimum = root.getValue();
		while (!deque.isEmpty()) {
			Node currentNode = deque.removeFirst();
			if (currentNode.getValue() < minimum) {
				minimum = currentNode.getValue();
			}
			if (currentNode.getRight() != null) {
				deque.add(currentNode.getRight());
			}
			if (currentNode.getLeft() != null) {
				deque.add(currentNode.getLeft());
			}
		}
		return minimum;
	}

	/**
	 * determines the maximum width or diameter of the tree.
	 * 
	 * GFG Link: https://practice.geeksforgeeks.org/problems/maximum-width-of-tree/1
	 * 
	 * @param root - the root of the tree
	 */
	public int maxWidth(Node root) {
		int depth = 0;
		int height = height(root);
		int[] width = new int[height];

		widthArrayFill(root, depth, width);
		int maximumWidth = 0;
		for (int i = 0; i < height; i++) {
			if (width[i] > maximumWidth) {
				maximumWidth = width[i];
			}
		}
		return maximumWidth;
	}

	private void widthArrayFill(Node root, int depth, int a[]) {
		if (root == null) {
			return;
		}
		a[depth]++;
		depth++;
		widthArrayFill(root.getLeft(), depth, a);
		widthArrayFill(root.getRight(), depth, a);
	}

	/**
	 * determines the maximum width of the tree.
	 * 
	 * GFG Link:
	 * https://practice.geeksforgeeks.org/problems/level-of-a-node-in-binary-tree/1
	 * 
	 * @param root - the root of the tree
	 */
	public int findLevelOfKey(Node root, int key) {
		int a[] = new int[1];
		a[0] = 0;
		storLevelInArray(root, key, a, 1);
		return a[0];
	}

	private void storLevelInArray(Node root, int key, int a[], int depth) {
		if (root == null) {
			return;
		} else if (root.getValue() == key) {
			a[0] = depth;
		}
		depth++;
		storLevelInArray(root.getRight(), key, a, depth);
		storLevelInArray(root.getLeft(), key, a, depth);
	}

	/**
	 * determines the minimum depth or height of the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/minimum-depth-of-a-binary-tree/1
	 * 
	 * @param root - the root of the tree
	 */
	public int minimumHeight(Node root) {
		int a[] = new int[1];
		minHeight(root, false, 1, a);
		return a[0];
	}

	private void minHeight(Node root, boolean end, int depth, int a[]) {
		if (root == null) {
			return;
		} else if (root.getLeft() == null && root.getRight() == null) {
			end = Boolean.TRUE;
			a[0] = depth;
			return;
		}
		if (!end) {
			depth++;
			minHeight(root.getLeft(), false, depth, a);
			minHeight(root.getRight(), false, depth, a);
		}
	}
}
