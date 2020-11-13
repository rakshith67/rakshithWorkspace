package trees.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List<Integer> distanceK(Node root, Node target, int K) {
		Map<Node, Integer> map = new HashMap<>();
		markDistance(root, target, map);
		List<Integer> list = new ArrayList<>();
		dfs(root, target, map, list, K, map.get(root));
		return list;
	}

	private int markDistance(Node root, Node target, Map<Node, Integer> map) {
		if (root == null) {
			return -1;
		}
		if (target == root) {
			map.put(root, 0);
			return 0;
		}
		int left = markDistance(root.getLeft(), target, map);
		if (left >= 0) {
			map.put(root, left + 1);
			return left + 1;
		}
		int right = markDistance(root.getRight(), target, map);
		if (right >= 0) {
			map.put(root, right + 1);
			return right + 1;
		}
		return -1;
	}

	private void dfs(Node root, Node target, Map<Node, Integer> map, List<Integer> list, int K, int dist) {
		if (root == null) {
			return;
		}
		if (map.containsKey(root)) {
			if (K == map.get(root)) {
				list.add(root.getValue());
			}
			dfs(root.getLeft(), target, map, list, K, map.get(root) + 1);
			dfs(root.getRight(), target, map, list, K, map.get(root) + 1);
		} else {
			if (dist == K) {
				list.add(root.getValue());
			}
			dfs(root.getLeft(), target, map, list, K, dist + 1);
			dfs(root.getRight(), target, map, list, K, dist + 1);
		}
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
		Node lca = treeOperations.leastCommonAncestor(root, first, second);
		int[] count = new int[1];
		if (lca == null) {
			return -1;
		}
		if (lca.getValue() != first && lca.getValue() != second) {
			countTurn(lca.getRight(), second, false, count);
			countTurn(lca.getLeft(), second, true, count);
			countTurn(lca.getRight(), first, false, count);
			countTurn(lca.getLeft(), first, true, count);
			return count[0] + 1;
		}
		if (lca.getValue() == first) {
			countTurn(lca.getRight(), second, false, count);
			countTurn(lca.getLeft(), second, true, count);
			return count[0];
		} else {
			countTurn(lca.getRight(), first, false, count);
			countTurn(lca.getLeft(), first, true, count);
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

	/**
	 * The thief has found himself a new place for his thievery again. There is only
	 * one entrance to this area, called the "root." Besides the root, each house
	 * has one and only one parent house. After a tour, the smart thief realized
	 * that "all houses in this place forms a binary tree". It will automatically
	 * contact the police if two directly-linked houses were broken into on the same
	 * night. Determine the maximum amount of money the thief can rob tonight
	 * without alerting the police.
	 * 
	 * Link: https://leetcode.com/problems/house-robber-iii/
	 * 
	 */
	public int rob(Node root) {
		Map<Node, Integer> map = new HashMap<>();
		return maxSumRobber(root, map);
	}

	private int maxSumRobber(Node root, Map<Node, Integer> map) {
		if (root == null) {
			return 0;
		}
		int left = maxSumRobber(root.getLeft(), map);
		int right = maxSumRobber(root.getRight(), map);
		int withoutMax = left + right;
		int withMax = 0;
		if (root.getLeft() != null) {
			if (root.getLeft().getRight() != null) {
				withMax += map.get(root.getLeft().getRight());
			}
			if (root.getLeft().getLeft() != null) {
				withMax += map.get(root.getLeft().getLeft());
			}
		}
		if (root.getRight() != null) {
			if (root.getRight().getLeft() != null) {
				withMax += map.get(root.getRight().getLeft());
			}
			if (root.getRight().getRight() != null) {
				withMax += map.get(root.getRight().getRight());
			}
		}
		int result = Math.max(root.getValue() + withMax, withoutMax);
		map.put(root, result);
		return result;
	}
}
