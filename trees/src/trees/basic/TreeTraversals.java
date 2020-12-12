package trees.basic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
	 * Link: https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/submissions/
	 * 
	 * @param root - root of the tree
	 */
	public List<List<Integer>> zigzagLevelOrder(Node root) {
        List<List<Integer>> ret = new ArrayList<>();
        fillZigZag(root, ret, 0);
        return ret;
    }
    
    private void fillZigZag(Node root, List<List<Integer>> list, int level){
        if(root == null){
            return;
        }
        if(level >= list.size()){
            list.add(new LinkedList<>());
        }
        List<Integer> current = list.get(level);
        if(level % 2 == 0){
            current.add(root.getValue());
        }else{
            current.add(0, root.getValue());
        }
        fillZigZag(root.getLeft(), list, level + 1);
        fillZigZag(root.getRight(), list, level + 1);
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

	private void fillArrayInLevel(Node root, int[] array) {
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
			array[i] = removedNode.getValue();
			i++;
		}
	}

	/**
	 * Prints the diagonal traversal of the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/diagonal-traversal-of-binary-tree/1
	 * 
	 * @param root - root of the tree
	 */
	public void diagonalTraversal(Node root) {
		TreeIntOperations treeIntOperations = new TreeIntOperations();
		int height = treeIntOperations.height(root);
		for (int i = 0; i < height; i++) {
			printCurrentDiagonalLevel(root, i, 0);
		}
	}

	private void printCurrentDiagonalLevel(Node root, int depth, int currentDepth) {
		if (root == null) {
			return;
		}
		if (depth == currentDepth) {
			System.out.print(root.getValue() + " ");
		}
		printCurrentDiagonalLevel(root.getLeft(), depth, currentDepth + 1);
		printCurrentDiagonalLevel(root.getRight(), depth, currentDepth);
	}

	/**
	 * Prints the vertical order traversal of the tree.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/print-a-binary-tree-in-vertical-order/1
	 * 
	 * @param root - root of the tree
	 */
	public void verticalOrderTraversal(Node root) {
		if (root == null) {
			return;
		}
		Map<Integer, String> map = new HashMap<>();
		Map<Node, Integer> levelMap = new HashMap<>();
		Deque<Node> queue = new ArrayDeque<>();
		queue.add(root);
		map.put(0, String.valueOf(root.getValue()));
		levelMap.put(root, 0);
		int lowestLevel = 0;
		int highestLevel = 0;
		while (!queue.isEmpty()) {
			Node removedNode = queue.removeFirst();
			if (removedNode.getLeft() != null) {
				int level = levelMap.get(removedNode) - 1;
				putInMap(map, level, removedNode.getLeft().getValue());
				if (level < lowestLevel) {
					lowestLevel = level;
				}
				queue.add(removedNode.getLeft());
				levelMap.put(removedNode.getLeft(), level);
			}
			if (removedNode.getRight() != null) {
				int level = levelMap.get(removedNode) + 1;
				putInMap(map, level, removedNode.getRight().getValue());
				if (level > highestLevel) {
					highestLevel = level;
				}
				queue.add(removedNode.getRight());
				levelMap.put(removedNode.getRight(), level);
			}
		}
		for (int i = lowestLevel; i <= highestLevel; i++) {
			System.out.print(map.get(i) + " ");
		}
		map.clear();
		levelMap.clear();
	}

	private void putInMap(Map<Integer, String> map, Integer key, Integer value) {
		String currentValue = String.valueOf(value);
		if (map.get(key) != null) {
			String x = map.get(key) + " " + currentValue;
			map.put(key, x);
		} else {
			map.put(key, currentValue);
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
		throw new UnsupportedOperationException();
	}

}
