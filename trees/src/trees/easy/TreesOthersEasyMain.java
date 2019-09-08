package trees.easy;

import trees.basic.Node;
import trees.basic.TreeTraversals;

public class TreesOthersEasyMain {

	public static void main(String[] args) {
		int[] inOrder = new int[] { 8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 13, 3, 14, 7, 15 };
		int[] preOrder = new int[] { 1, 2, 4, 8, 9, 5, 10, 11, 3, 6, 12, 15, 7, 14, 15 };
		Node root = Node.createTree();

		TreesOthersEasyLevel treeOperations = new TreesOthersEasyLevel();
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
		Node deletedTreeRoot = treeOperations.deleteNodesGreaterThanKey(root, 9);
		TreeTraversals treeTraversals = new TreeTraversals();
		treeTraversals.inOrderTraversal(deletedTreeRoot);
	}
}
