package trees.easy;

import trees.basic.Node;
import trees.basic.TreeTraversals;

public class TreesEasyMain3 {

	public static void main(String[] args) {
		int[] inOrder = new int[] { 8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 13, 3, 14, 7, 15 };
		int[] preOrder = new int[] { 1, 2, 4, 8, 9, 5, 10, 11, 3, 6, 12, 15, 7, 14, 15 };
		String ternaryExpression = "1?2?3:4:5";
		String treeString = "(0(51(64()())(46()(97()())))(76(17()())(36()())))";
		Node root = Node.createTree();

		TreesEasyLevel3 treeOperations = new TreesEasyLevel3();
		treeOperations.printPostOrder(inOrder, preOrder, 15);
		System.out.println(" - PostOrder traversal of created tree.");
		boolean isPerfect = treeOperations.isPerfectTree(root);
		if (isPerfect) {
			System.out.println("Perfect tree.");
		} else {
			System.out.println("Not perfect tree.");
		}
		treeOperations.printNodesAtOddLevel(root);
		System.out.println(" - Nodes at odd levels.");
		int count = treeOperations.getCountForBudget(root, 13);
		System.out.println("count for 13 budget is " + count);
		treeOperations.printSumOfLeafNodesAtMinLevel(root);
		treeOperations.printPredecessorAndSuccessor(root, 12);
		boolean ismaxHeap = treeOperations.isMaxHeap(root);
		if (ismaxHeap) {
			System.out.println("Max heap tree.");
		} else {
			System.out.println("Not Max heap tree.");
		}
		int shortestDistance = treeOperations.shortestDistance(7, 14);
		System.out.println("Shortest distance between 7 and 14 is " + shortestDistance);
		int kthLevelSum = treeOperations.kthLevelSum(treeString, 2);
		System.out.println("Kth level sum of the tree is " + kthLevelSum);
		Node deletedTreeRoot = treeOperations.deleteNodesGreaterThanKey(root, 9);
		TreeTraversals treeTraversals = new TreeTraversals();
		treeTraversals.inOrderTraversal(deletedTreeRoot);
		System.out.println(" - deleted tree with nodes greater than 9");
		Node ternaryRoot = treeOperations.buildTreeFromTernaryExp(ternaryExpression, 0);
		treeTraversals.inOrderTraversal(ternaryRoot);
		System.out.println(" - inOrder traversal of created ternary tree.");
	}
}
